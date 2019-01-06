package visualalgol.entidades;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Linha grafica da instrucao de origem
 * para a instrucao de destino
 */
public class Linha implements Serializable, Cloneable{
	private static final long serialVersionUID = 1L;
	private InstrucaoGenerica origem;
	private InstrucaoGenerica destino;
	private Point pontoTemporario;
	private boolean executado;
	private Long id;
	
	private List<Point> listPontos = new ArrayList<Point>();
	
	public Linha() {
	}
	
	
	/**
	 * @return the origem
	 */
	public InstrucaoGenerica getOrigem() {
		return origem;
	}
	/**
	 * @param origem the origem to set
	 */
	public void setOrigem(InstrucaoGenerica origem) {
		this.origem = origem;
	}
	/**
	 * @return the destino
	 */
	public InstrucaoGenerica getDestino() {
		return destino;
	}
	/**
	 * @param destino the destino to set
	 */
	public void setDestino(InstrucaoGenerica destino) {
		this.destino = destino;
	}
	/**
	 * @return the pontoTemporario
	 */
	public Point getPontoTemporario() {
		return pontoTemporario;
	}
	/**
	 * @param pontoTemporario the pontoTemporario to set
	 */
	public void setPontoTemporario(Point pontoTemporario) {
		this.pontoTemporario = pontoTemporario;
	}
	/**
	 * @return the listPontos
	 */
	public List<Point> getListPontos() {
		return listPontos;
	}
	/**
	 * @param listPontos the listPontos to set
	 */
	public void setListPontos(List<Point> listPontos) {
		this.listPontos = listPontos;
	}
	
	public void setExecutado(boolean executado) {
		this.executado = executado;
	}
	
	public boolean isExecutado() {
		return executado;
	}

	@Override
	public int hashCode() {
		getId();
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		getId();
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Linha other = (Linha) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	private static long unique = 0;
	public synchronized long getId(){
		if(id==null){
			id = unique++;
		}
		return id;
	}
	
	public Linha deepClone() {
		try {
			Linha linha = (Linha)this.clone();
			linha.id=null;
			linha.setListPontos(this.listPontos);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String toString() {
		return "Linha id " + getId() + "; origem = " + origem + "; destino = " + destino;
	}

	
	public void setId(Long id) {
		this.id = id;
	}
}
