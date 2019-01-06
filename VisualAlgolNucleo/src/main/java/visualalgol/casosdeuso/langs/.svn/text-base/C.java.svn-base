package visualalgol.casosdeuso.langs;

import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import visualalgol.casosdeuso.InterpretarFluxograma;
import visualalgol.entidades.Algoritmo;
import visualalgol.entidades.Variavel;

public class C implements Linguagem {
	private Algoritmo alg;
	protected OutputLang outputLang;
	
	/**
	 * Somente para encapsular a chamada ao metodo
	 * @param condicao string em portugol
	 * @return condicao tratada para funcionar em C like
	 */
	protected String tratarCondicao(String condicao){
		return InterpretarFluxograma.tratarStringDeCondicao(condicao);
	}
	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverWhile(java.lang.String)
	 */
	public void escreverWhile(String condicao){
		outputLang.print("while (" + tratarCondicao(condicao) + "){");
		outputLang.addTab();
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverEndWhile()
	 */
	public void escreverEndWhile() {
		outputLang.subTab();
		outputLang.print("}//fim while ");
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverIf(java.lang.String)
	 */
	public void escreverIf(String pseudoCodigo) {
		outputLang.print("if (" + tratarCondicao(pseudoCodigo) + "){");
		outputLang.addTab();
	}
	
	private int getTipo(String nome){
		for(Variavel var: alg.getVariaveis()){
			if(var.getName().equals(nome)){
				return var.getTipo();
			}
		}
		return 0;
	}
	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverComando(java.lang.String)
	 */
	public void escreverComando(String comando) {
		if(comando.startsWith("leia ")){
			String var = comando.substring(5);
			String vars[] = var.split(",");
			for(int i=0;i<vars.length;i++){
				var = vars[i];
				//achar o tipo da variavel
				int tipo = getTipo(var);
				if(tipo==2){//Real
					outputLang.print("printf(\"Informe um valor para "+var+"\\n\");\nscanf(\"%f\", &"+var+");");
				}else if(tipo==3){//inteiro
					outputLang.print("printf(\"Informe um valor para "+var+"\\n\");\nscanf(\"%d\", &"+var+");");
				}else if(tipo==4){//boolean
					outputLang.print("printf(\"Informe um valor para "+var+" (1=true 0=false)\\n\");\nscanf(\"%d\", &"+var+");");
				}else{//string?
					outputLang.print("printf(\"Informe um valor para "+var+"\\n\");\nscanf(\"%s\", "+var+");//note que nao existe &");
				}
			}
		}else if(comando.startsWith("imprima ")){
			String var = comando.substring(8);//complexo transformar em C
			//achar o tipo da variavel
			int tipo = getTipo(var);
			if(tipo==2){//Real
				outputLang.print("printf(\"%4.2f \\n\"," + var + ");");
			}else if(tipo==3){//inteiro
				outputLang.print("printf(\"%d \\n\"," + var + ");");
			}else{
				outputLang.print("printf(\"%s \\n\",("+var+"));");
			}
		}else{
			/*
			 * se houver uma atribuicao e o ultimo comando
			 * terminar com uma divisao com um inteiro, transformar o inteiro para flutuante
			 */
			if(comando.matches("^[a-zA-Z][a-zA-Z0-9]*=.*/[0-9][0-9]*$")){
				comando+=".0";
			}
			comando=comando.replaceAll("=true$", "=1");
			comando=comando.replaceAll("=false$", "=0");
			outputLang.print(comando + ";");
		}
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverComandoVazio()
	 */
	public void escreverComandoVazio() {
		outputLang.print("//comando qualquer");
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverElse()
	 */
	public void escreverElse() {
		outputLang.subTab();
		outputLang.print("}else{");
		outputLang.addTab();
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverFimCondicao()
	 */
	public void escreverFimCondicao() {
		outputLang.subTab();
		outputLang.print("}//fim de condicao");
	}

	@Override
	public String getNome() {
		return "C";
	}

	@Override
	public void getInicio() {
		outputLang.print("int main(){//Programa principal");
		outputLang.addTab();
	}

	@Override
	public void getFim() {
		outputLang.subTab();
		outputLang.print("\treturn 0;\n}");
	}

	private String getTipo(int x){
		switch (x) {
		case 1:
			return "char ";
		case 2:
			return "float ";
		case 3:
			return "int ";
		case 4:
			return "unsigned char ";
		default:
			return "??";
		}
	}
	@Override
	public void getCabecalho(Algoritmo alg) {
		this.alg = alg;
		String retorno = "#include <stdio.h>\n";
		//Organizar por tipo
		for(int i=0;i<Variavel.getTipos().size();i++){
			boolean tem=false;
			for(Variavel var:alg.getVariaveis()){
				if(var.getTipo()==i){
					if(tem){
						retorno+=", " + var.getName();
					}else{
						tem=true;
						retorno +=getTipo(i) + var.getName();
					}
					if(i==1){
						retorno+="[255]";
					}
				}
			}
			if(tem) retorno+=";\n";
		}
		outputLang.print(retorno);
	}

	public String getLinguagemStyle() {
		return SyntaxConstants.SYNTAX_STYLE_C;
	}

	@Override
	public void escreverDo() {
		outputLang.print("do {");
		outputLang.addTab();
	}

	@Override
	public void escreverDoWhile(String condicao) {
		outputLang.subTab();
		outputLang.print("} while("+tratarCondicao(condicao)+");");
	}

	@Override
	public void setOutputLang(OutputLang outputLang) {
		this.outputLang = outputLang;
	}
}
