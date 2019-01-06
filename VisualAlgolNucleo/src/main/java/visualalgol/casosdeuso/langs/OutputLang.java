package visualalgol.casosdeuso.langs;

import visualalgol.casosdeuso.Sistema;

public class OutputLang {
	private Sistema sistema;

	public OutputLang() {
	}

	public OutputLang(Sistema sistema) {
		this.sistema = sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	/**
	 * Contador de tabs para edentar o codigo
	 */
	private int nTabs = 0;

	/**
	 * Trata do print dentro da tela do Usuario, cuida dos tabs tamb&eacute;m
	 * 
	 * @param string
	 *            codigo para colocar na tela
	 */
	public void print(String string) {
		StringBuilder tabs = new StringBuilder();
		for (int i = 0; i < nTabs; i++)
			tabs.append('\t');

		string = string.replace("\n", "\n" + tabs.toString());
		sistema.appendCodigo(tabs.toString() + string + "\n");
	}

	public void addTab() {
		nTabs++;
	}

	public void subTab() {
		nTabs--;
	}
}
