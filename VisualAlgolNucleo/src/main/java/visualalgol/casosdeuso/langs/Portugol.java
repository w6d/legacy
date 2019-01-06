package visualalgol.casosdeuso.langs;

import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import visualalgol.entidades.Algoritmo;

public class Portugol implements Linguagem {
	private OutputLang out;
	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverWhile(java.lang.String)
	 */
	public void escreverWhile(String condicao){
		out.print("enquanto " + condicao + " faca");
		out.addTab();
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverEndWhile()
	 */
	public void escreverEndWhile() {
		out.subTab();
		out.print("fim enquanto ");
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverIf(java.lang.String)
	 */
	public void escreverIf(String pseudoCodigo) {
		out.print("se " + pseudoCodigo + " entao ");
		out.addTab();
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverComando(java.lang.String)
	 */
	public void escreverComando(String comando) {
		out.print(comando);
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverComandoVazio()
	 */
	public void escreverComandoVazio() {
		out.print("\"Um comando qualquer\"");
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverElse()
	 */
	public void escreverElse() {
		out.subTab();
		out.print("senao");
		out.addTab();
	}

	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverFimCondicao()
	 */
	public void escreverFimCondicao() {
		out.subTab();
		out.print("fim da condicao");
	}

	@Override
	public String getNome() {
		return "Portugol";
	}

	@Override
	public void getInicio() {
		out.print("Inicio");
		out.addTab();
	}

	@Override
	public void getFim() {
		out.subTab();
		out.print("Fim");
	}

	@Override
	public void getCabecalho(Algoritmo alg) {
		
	}

	@Override
	public String getLinguagemStyle() {
		return SyntaxConstants.SYNTAX_STYLE_LUA;
	}
	@Override
	public void escreverDo() {
		out.print("faca");
		out.addTab();
	}

	@Override
	public void escreverDoWhile(String condicao) {
		out.subTab();
		out.print("enquanto "+condicao);
	}
	@Override
	public void setOutputLang(OutputLang outputLang) {
		this.out = outputLang;
	}
}
