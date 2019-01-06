package visualalgol.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Variavel implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private String value;
	private int tipo;
	private static List<String> tipos = new ArrayList<String>();
	static{
		tipos.add("Selecione");//indefinido
		tipos.add("Texto");
		tipos.add("Real (Ex.: 3,14)");
		tipos.add("Inteiro (Ex.: 42)");
		tipos.add("Booleano (Ex.: true ou false)");
	}
	
	/**
	 * 0 = Selecione ou Indefinido<br>
	 * 1 = Texto ou String<br>
	 * 2 = Real ou Float<br>
	 * 3 = Inteiro<br>
	 * 4 = Booleano<br>
	 * @return um dos valores acima
	 */
	public static List<String> getTipos() {
		return tipos;
	}
	
	private int passo=-1;
	
	public Variavel(){
		
	}
	public int getTipo() {
		return tipo;
	}
	/**
	 * string, double ou int por enquanto
	 * @param tipo tipo
	 */
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public Variavel(String name, String value, int passo) {
		super();
		this.name = name;
		this.value = value;
		this.passo = passo;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

	/**
	 * @return the passo
	 */
	public int getPasso() {
		return passo;
	}

	/**
	 * @param passo the passo to set
	 */
	public void setPasso(int passo) {
		this.passo = passo;
	}
}
