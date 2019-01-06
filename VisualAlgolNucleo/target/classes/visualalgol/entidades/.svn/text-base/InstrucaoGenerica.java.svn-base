package visualalgol.entidades;

import java.awt.Polygon;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class InstrucaoGenerica implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean foco;
	private boolean executado;
	private int cor;
	private Polygon poligono = new Polygon();
	private int w;
	private int h;
	private Algoritmo algoritmo;
	
	private List<Variavel> variaveis = new ArrayList<Variavel>();

	/**
	 * Coordenada X para a posicao no fluxograma
	 */
	private int x;

	/**
	 * Coordenada Y para a posicao no fluxograma
	 */
	private int y;

	/**
	 * Flag para saber se ja foi visitado
	 */
	private boolean visitado;

	private InstrucaoGenerica instrucaoAnterior;
	
	private String pseudoCodigo;

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the visitado
	 */
	public boolean isVisitado() {
		return visitado;
	}

	/**
	 * @param visitado
	 *            the visitado to set
	 */
	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}

	public InstrucaoGenerica getInstrucaoAnterior() {
		return instrucaoAnterior;
	}

	public void setInstrucaoAnterior(InstrucaoGenerica comandoAnterior) {
		this.instrucaoAnterior = comandoAnterior;
	}

	/**
	 * @return the w
	 */
	public int getW() {
		return w;
	}

	/**
	 * @param w
	 *            the w to set
	 */
	public void setW(int w) {
		this.w = w;
	}

	/**
	 * @return the h
	 */
	public int getH() {
		return h;
	}

	/**
	 * @param h
	 *            the h to set
	 */
	public void setH(int h) {
		this.h = h;
	}

	/**
	 * @return the poligono
	 */
	public Polygon getPoligono() {
		return poligono;
	}

	/**
	 * @param poligono
	 *            the poligono to set
	 */
	public void setPoligono(Polygon poligono) {
		this.poligono = poligono;
	}

	/**
	 * @return the cor
	 */
	public int getCor() {
		return cor;
	}

	/**
	 * @param cor
	 *            the cor to set
	 */
	public void setCor(int cor) {
		this.cor = cor;
	}

	/**
	 * @return the foco
	 */
	public boolean isFoco() {
		return foco;
	}

	/**
	 * @param foco
	 *            the foco to set
	 */
	public void setFoco(boolean foco) {
		this.foco = foco;
	}

	/**
	 * @return the algoritmo
	 */
	public Algoritmo getAlgoritmo() {
		return algoritmo;
	}

	/**
	 * @param algoritmo
	 *            the algoritmo to set
	 */
	public void setAlgoritmo(Algoritmo algoritmo) {
		this.algoritmo = algoritmo;
	}

	public void delete() {
		getAlgoritmo().getListComando().remove(this);
	}

	public void setPseudoCodigo(String pseudoCodigo) {
		this.pseudoCodigo = pseudoCodigo;
	}

	public String getPseudoCodigo() {
		return pseudoCodigo;
	}

	/**
	 * @return the executado
	 */
	public boolean isExecutado() {
		return executado;
	}

	/**
	 * @param executado
	 *            the executado to set
	 */
	public void setExecutado(boolean executado) {
		this.executado = executado;
	}

	public abstract void substituirEntrada(Linha procurarPor, Linha substituirPor);
	
	/**
	 * Guarda as variaveis atribuidas
	 * @param key nome da variavel
	 * @param value valor da variavel
	 * @param passo indice da execucao
	 */
	public void put(String key,Object value, int passo){
		if(variaveis==null){//serialization bug
			variaveis = new ArrayList<Variavel>();
		}
		Variavel var = new Variavel(key,value.toString(),passo);
		variaveis.add(var);
	}
	
	public int contemVariavel(String nomeVariavel, String valor, int passo) {
		if(variaveis==null) return -1;//Serializable bug
		for(int i=0;i<variaveis.size();i++){
			Variavel var = variaveis.get(i);
			if(var.getName().equals(nomeVariavel) && 
					(var.getValue().equals(valor) || (valor.matches("^[0-9][0-9]*$") && var.getValue().equals(valor+".0")))
					&& var.getPasso()==passo){
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * @param passo qual passo de execucao
	 * @return variaveis do passo
	 */
	public List<Variavel> getVariaveis(int passo) {
		List<Variavel> retorno = new ArrayList<Variavel>();
		if(variaveis==null) return retorno;//Serializable bug
		for(Variavel var:variaveis){
			if(var.getPasso()==passo){//esta no passo de execucao que queremos
				retorno.add(var);//colocamos na resposta
			}
		}
		return retorno;
	}
	public List<Variavel> getVariaveis() {
		return variaveis;
	}

	public void setVariaveis(List<Variavel> variaveis) {
		this.variaveis = variaveis;
	}
	@Override
	public String toString() {
		return pseudoCodigo;
	}
}
