package visualalgol.casosdeuso.langs;

import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import visualalgol.entidades.Algoritmo;
import visualalgol.entidades.Variavel;

public class Pascal implements Linguagem {
	private OutputLang outputLang;
	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverWhile(java.lang.String)
	 */
	public void escreverWhile(String condicao){
		outputLang.print("while " + condicao + " do begin");
		outputLang.addTab();
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverEndWhile()
	 */
	public void escreverEndWhile() {
		outputLang.subTab();
		outputLang.print("end; {fim enquanto} ");
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverIf(java.lang.String)
	 */
	public void escreverIf(String pseudoCodigo) {
		outputLang.print("if " + pseudoCodigo + " then begin ");
		outputLang.addTab();
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverComando(java.lang.String)
	 */
	public void escreverComando(String comando) {
		if(comando.startsWith("leia ")){
			String var = comando.substring(5);
			outputLang.print("read(" + var + ");");
		}else if(comando.startsWith("imprima ")){
			String var = comando.substring(8);
			outputLang.print("writeln("+tratarWriteLn(var)+");");
		}else{
			comando = comando.replaceFirst("=", ":=");
			outputLang.print(comando + ";");
		}
	}

	private String tratarWriteLn(String var){
		String retorno=var;
		retorno=retorno.replace("\"", "'");
		retorno=retorno.replaceAll("'([^']*)'\\s*\\+", "'$1',");
		return retorno;
	}
	
	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverComandoVazio()
	 */
	public void escreverComandoVazio() {
		outputLang.print("{comando qualquer}");
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverElse()
	 */
	public void escreverElse() {
		outputLang.subTab();
		outputLang.print("end else begin");
		outputLang.addTab();
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverFimCondicao()
	 */
	public void escreverFimCondicao() {
		outputLang.subTab();
		outputLang.print("end; { Fim condicao }");
	}

	@Override
	public String getNome() {
		return "Pascal";
	}

	@Override
	public void getInicio() {
		outputLang.print("begin {Programa principal}");
		outputLang.addTab();
	}

	@Override
	public void getFim() {
		outputLang.subTab();
		outputLang.print("end.");
	}

	private String getTipo(int x){
		switch (x) {
		case 1:
			return "string[255]";
		case 2:
			return "real";
		case 3:
			return "integer";
		case 4:
			return "boolean";
		default:
			return "??";
		}
	}
	@Override
	public void getCabecalho(Algoritmo alg) {
		String retorno = "Program Pzim ;\nvar ";
		//Organizar por tipo
		for(int i=0;i<Variavel.getTipos().size();i++){
			boolean tem=false;
			for(Variavel var:alg.getVariaveis()){
				if(var.getTipo()==i){
					if(tem)retorno+=", ";
					tem=true;
					retorno += var.getName();
				}
			}
			if(tem)	retorno+=":"+getTipo(i)+";\n";
		}
		outputLang.print(retorno);
	}

	@Override
	public String getLinguagemStyle() {
		return SyntaxConstants.SYNTAX_STYLE_DELPHI;
	}
	
	@Override
	public void escreverDo() {
		outputLang.print("repeat");
		outputLang.addTab();
	}

	@Override
	public void escreverDoWhile(String condicao) {
		outputLang.subTab();
		outputLang.print("until "+condicao+";");
	}
	@Override
	public void setOutputLang(OutputLang outputLang) {
		this.outputLang = outputLang;
	}
}
