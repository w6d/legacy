package visualalgol.entidades;

import java.awt.Point;

/**
 * Representa um comando do algoritmo. Como os famosos a, b, c do Manuel.
 */
public class Comando extends InstrucaoGenerica {
	private static final long serialVersionUID = 1L;
	private Linha linhaEntrada;
	private Linha linhaSaida;
	/**
	 * Descricao do comando
	 */
	private String descricao;

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	/**
	 * @return the linhaSaida
	 */
	public Linha getLinhaSaida() {
		return linhaSaida;
	}

	/**
	 * @param linhaSaida
	 *            the linhaSaida to set
	 */
	public void setLinhaSaida(Linha linhaSaida) {
		this.linhaSaida = linhaSaida;
	}
	@Override
	public void delete() {
		//religar o que estivera ligado
		if(linhaEntrada!=null && linhaSaida!=null){
			InstrucaoGenerica temp = linhaSaida.getDestino();
			linhaEntrada.setDestino(linhaSaida.getDestino());
			temp.substituirEntrada(linhaSaida, linhaEntrada);
			
			for(Point ponto:linhaSaida.getListPontos()){
				if(!linhaEntrada.getListPontos().contains(ponto)){
					linhaEntrada.getListPontos().add(ponto);
				}
			}
		}
		getAlgoritmo().getListLinha().remove(linhaSaida);
		//getAlgoritmo().getListLinha().remove(linhaEntrada);
		super.delete();
	}

	@Override
	public void substituirEntrada(Linha procurarPor, Linha substituirPor) {
		this.linhaEntrada = substituirPor;
	}

}
