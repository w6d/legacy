package visualalgol.casosdeuso.comandos;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.mozilla.javascript.Scriptable;

import visualalgol.casosdeuso.Ator;
import visualalgol.casosdeuso.Sistema;
import visualalgol.entidades.Comando;
import visualalgol.entidades.InstrucaoGenerica;

public class InterpretarWhen extends InterpretadorDeComandoAbstrato{
	private final static Logger logger = Logger.getLogger(InterpretarWhen.class);
	private String variavel;
	private String valor;
	
	@Override
	public void interpretar(Sistema sistema, Ator ator, String textoDigitado) throws InterruptedException, EntradaInesperadaException {
		String args[] = tratarEntrada(textoDigitado);
		while(args.length<3){
			sistema.informar("Please, type something like \"when variable=value?\".");
			textoDigitado = ator.digitarTexto();
			args = tratarEntrada(textoDigitado);
		}
		variavel = args[1];
		valor = args[3];
		sistema.informar("I will tell you when '" + variavel +"' changed it's value to '"+valor+"'.");
		sistema.informar("Please, run the program again. Press F9.");
	}
	
	@Override
	public void aoEncerrar() {
		
	}

	@Override
	public String exemplo() {
		return "when someVariable=value?";
	}
	
	@Override
	public boolean podeTratar(String comando) {
		return comando.startsWith("when ");
	}

	@Override
	public void informarComandoExecutado(InstrucaoGenerica instrucao, Scriptable scope, String s) {
		if(instrucao instanceof Comando){
			//procurar por alteracoes de variavel
			int iIgual = s.indexOf("=");
			if(iIgual!=-1){
				String varName = s.substring(0,iIgual).trim();
				Object value = scope.get(varName, scope);
				logger.debug(s + " --> " + varName + " = " + value);
				if(varName.equals(this.variavel)){
					boolean avisar = false;
					if(value.toString().equals(valor)){
						avisar = true;
					}else if(valor.matches("^[0-9][0-9]*$")){
						//verificar se virou double
						if((valor+".0").equals(value.toString())){
							avisar = true;
						}
					}
					if(avisar){
						sistema.informar("The variable is equals to the expected value.");
						sistema.apontarPara(instrucao);
						JOptionPane.showMessageDialog(sistema.getComponent(),"Found when "+this.variavel+" is "+valor+".");
					}
				}
			}
		}
	}

	@Override
	public void interpretadorFluxogramaIniciado() {
		
	}

}
