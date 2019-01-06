package visualalgol.casosdeuso.comandos;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.mozilla.javascript.Scriptable;

import visualalgol.casosdeuso.Ator;
import visualalgol.casosdeuso.Sistema;
import visualalgol.entidades.Comando;
import visualalgol.entidades.CondicaoIf;
import visualalgol.entidades.InstrucaoGenerica;
import visualalgol.entidades.Variavel;
import visualalgol.utils.LogSimples;

public class InterpretarWhy extends InterpretadorDeComandoAbstrato{
	private static Logger logger = Logger.getLogger(InterpretarWhy.class);
	private boolean encontrado = false;
	private String nomeVariavel;
	private String valor;
	private LogSimples logSimples;
	private List<InstrucaoGenerica> executados = new ArrayList<InstrucaoGenerica>();
	public InterpretarWhy(){
		logSimples = new LogSimples();
		File file = new File(getPastaDoPrograma(),"log-interpretacao.js");
		logSimples.setPath(file);
	}
	
	private String toString(List<Variavel> variaveis) {
		String retorno = " the values are ";
		for(Variavel var: variaveis){
			retorno +=var.getName()+"="+var.getValue()+ ";";
		}
		return retorno;
	}

	public void informarComandoExecutado(InstrucaoGenerica instrucao, Scriptable scope, String s) {
		int passo = executados.size();

		if(s!=null){
			if(instrucao instanceof Comando){
				//procurar por alteracoes de variavel
				int iIgual = s.indexOf("=");
				if(iIgual!=-1){
					String varName = s.substring(0,iIgual).trim();
					Object value = scope.get(varName, scope);
					instrucao.put(varName, value.toString(), passo);
					logger.info(varName + " = " + value.toString());
				}
			}else if(instrucao instanceof CondicaoIf){
				//Por solicitacao do clemilson, guardar as variaveis da condicional
				String sSimples = s.replace("==","=").replace(">=", ">").replace("<=","<");
				String tokens[] = sSimples.split("(=|>|<)");
				for(int i=0;i<tokens.length;i++){
					Object value = scope.get(tokens[i].trim(),scope);
					if(!Scriptable.NOT_FOUND.equals(value)){
						System.out.println("valor da variavel '"+tokens[i]+"' na condicao '"+s+"' é '" + value + "'");
						instrucao.put(tokens[i], value, passo);
					}
				}
			}
		}
		executados.add(instrucao);
	}

	/**
	 * Chamado do aspecto
	 * @param name
	 * @param oldValue
	 * @param newValue
	 */
	public void informarVariavelAlterada(String name, Object oldValue, Object newValue) {
		logSimples.append(name+" = "+newValue+"; //"+oldValue+"\n");
	}

	public void interpretadorFluxogramaIniciado() {
		logSimples.apagar();
		executados.clear();
	}

	@Override
	public boolean podeTratar(String comando) {
		return comando.startsWith("why ");
	}

	@Override
	public void aoEncerrar() {
		nomeVariavel = null;
		valor = null;
		sistema.apontarPara(null);
	}

	@Override
	public void interpretar(Sistema sistema, Ator ator, String textoDigitado) throws InterruptedException, EntradaInesperadaException {
		encontrado = false;
		String args[] = tratarEntrada(textoDigitado);
		while(args.length<3){
			sistema.informar("Please type something like \"why variable=value?\".");
			textoDigitado = ator.digitarTexto();
			args = tratarEntrada(textoDigitado);
		}
		nomeVariavel =  args[1];
		valor = args[3];
		
		sistema.informarNoRodape("Procurando o momento em que a variavel '" + nomeVariavel + "' fica com o valor '" + valor + "'");
		//a primeira vez devemos achar um comando
		int i = 0,direcao=1;
		while (i < executados.size()) {
			if(i<0 && direcao==-1){//se voltou ao inicio
				sistema.informar("because you pressed run (F9)");
				encontrado = true;
				break;
			}
			InstrucaoGenerica instrucao = executados.get(i);
			int pos = instrucao.contemVariavel(nomeVariavel,valor,i);
			if(pos!=-1 && instrucao instanceof Comando){// contem a variavel
				sistema.apontarPara(instrucao);// coloca a criacao de michelangelo
				sistema.informar("because '"+instrucao.getPseudoCodigo()+"'");
				String texto = ator.digitarTexto();// ver se o usuario quer continuar
				if(!texto.equals("why?")) throw new EntradaInesperadaException();
				for(i=i-1;i>=0;){// procurar o proximo if
					InstrucaoGenerica aux = executados.get(i);
					if(aux instanceof CondicaoIf){
						CondicaoIf condicao = (CondicaoIf) aux;
						sistema.apontarPara(condicao);
						//Por solicitacao do clemilson, devemos saber o valor da variavel da condicao
						List<Variavel> variaveis = condicao.getVariaveis(i);
						sistema.informar("because " + condicao.getPseudoCodigo() + " is " + condicao.isResultado()+"\n"+toString(variaveis));
						encontrado = true;
						texto = ator.digitarTexto();
						if(texto.equals("why?")){//continua indo para tras?
							encontrado = false;
							{//poderia entrar uma recursao?
								direcao=-1;
								if(variaveis.size()==1){// nao precisa perguntar qual variavel
									Variavel novoParametro = variaveis.get(0);
									nomeVariavel = novoParametro.getName();
									valor = novoParametro.getValue();
									sistema.informarNoRodape("Procurando o momento em que a variavel " + nomeVariavel + " fica com o valor " + valor);
								}else{// perguntar qual variavel
									perguntarQualVariavel(variaveis);
								}
							}
						}
						break;
					}
					i--;
				}
			}
			if(encontrado) break;
			i+=direcao;
		}
		if(!encontrado){
			sistema.informar("I don't know, sorry...");
		}
	}

	private void perguntarQualVariavel(List<Variavel> variaveis) throws InterruptedException {
		sistema.informar("Choose a variable:");
		int k=1;
		for(Variavel var: variaveis) sistema.informar("    " + (k++) + " - " + var.getName());
		String escolha = ator.digitarTexto();
		try{
			k=Integer.parseInt(escolha);
			Variavel var = variaveis.get(k-1);
			nomeVariavel = var.getName();
			valor = var.getValue();
		}catch(Exception e){
			sistema.informar("Unexpected value.");
			throw new EntradaInesperadaException();
		}
	}

	@Override
	public String exemplo() {
		return "why someVariable=someValue?";
	}
	
}
