package visualalgol.entidades;

/**
 * O If tem um resultado true e outro false Serve para o if e para o loop
 */
public class CondicaoIf extends InstrucaoGenerica {
	private static final long serialVersionUID = 1L;
	private Linha linhaEntrada;
	/**
	 * Volta do Loop, existe somente em Loops
	 */
	private Linha linhaEntradaLoopBack;
	private Linha linhaVerdadeira;
	private Linha linhaFalsa;
	private boolean loop;
	public boolean isLoop() {
		return loop;
	}

	public void setLoop(boolean loop) {
		this.loop = loop;
	}

	/**
	 * Resultado do if
	 */
	private boolean resultado;

	public Linha getLinhaEntradaLoopBack() {
		return linhaEntradaLoopBack;
	}

	public void setLinhaEntradaLoopBack(Linha linhaEntradaLoopBack) {
		this.linhaEntradaLoopBack = linhaEntradaLoopBack;
	}

	/**
	 * Descricao da condicional
	 */
	private String descricaoCondicao;


	/**
	 * @return the linhaVerdadeira
	 */
	public Linha getLinhaVerdadeira() {
		return linhaVerdadeira;
	}

	/**
	 * @param linhaVerdadeira
	 *            the linhaVerdadeira to set
	 */
	public void setLinhaVerdadeira(Linha linhaVerdadeira) {
		this.linhaVerdadeira = linhaVerdadeira;
	}

	/**
	 * @return the resultado
	 */
	public boolean isResultado() {
		return resultado;
	}

	/**
	 * @param resultado
	 *            the resultado to set
	 */
	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}

	/**
	 * @return the descricaoCondicao
	 */
	public String getDescricaoCondicao() {
		return descricaoCondicao;
	}

	/**
	 * @param descricaoCondicao
	 *            the descricaoCondicao to set
	 */
	public void setDescricaoCondicao(String descricaoCondicao) {
		this.descricaoCondicao = descricaoCondicao;
	}

	/**
	 * @return the linhaFalsa
	 */
	public Linha getLinhaFalsa() {
		return linhaFalsa;
	}

	/**
	 * @param linhaFalsa
	 *            the linhaFalsa to set
	 */
	public void setLinhaFalsa(Linha linhaFalsa) {
		this.linhaFalsa = linhaFalsa;
	}

	/**
	 * @return the linhaEntrada
	 */
	public Linha getLinhaEntrada() {
		return linhaEntrada;
	}

	/**
	 * @param linhaEntrada
	 *            the linhaEntrada to set
	 */
	public void setLinhaEntrada(Linha linhaEntrada) {
		this.linhaEntrada = linhaEntrada;
	}

	@Override
	public void delete() {
		getAlgoritmo().getListLinha().remove(linhaEntrada);
		getAlgoritmo().getListLinha().remove(linhaFalsa);
		getAlgoritmo().getListLinha().remove(linhaVerdadeira);
		getAlgoritmo().getListLinha().remove(linhaEntradaLoopBack);
		if(linhaVerdadeira!=null && linhaVerdadeira.getDestino()!=null)
			linhaVerdadeira.getDestino().substituirEntrada(linhaVerdadeira, null);
		if(linhaFalsa!=null && linhaFalsa.getDestino()!=null)
			linhaFalsa.getDestino().substituirEntrada(linhaFalsa, null);
		super.delete();
	}

	@Override
	public void substituirEntrada(Linha procurarPor, Linha substituirPor) {
		if(procurarPor.equals(linhaEntrada))linhaEntrada = substituirPor;
		if(procurarPor.equals(linhaEntradaLoopBack))linhaEntradaLoopBack = substituirPor;
	}


}
