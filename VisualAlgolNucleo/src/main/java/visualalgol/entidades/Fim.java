package visualalgol.entidades;

/**
 * Fim do algoritimo
 *
 */
public class Fim extends InstrucaoGenerica{
	private static final long serialVersionUID = 1L;
	private Linha linhaEntrada;

	/**
	 * @return the linhaEntrada
	 */
	public Linha getLinhaEntrada() {
		return linhaEntrada;
	}

	/**
	 * @param linhaEntrada the linhaEntrada to set
	 */
	public void setLinhaEntrada(Linha linhaEntrada) {
		this.linhaEntrada = linhaEntrada;
	}

	@Override
	public void substituirEntrada(Linha procurarPor, Linha substituirPor) {
		this.linhaEntrada = substituirPor;
	}
}
