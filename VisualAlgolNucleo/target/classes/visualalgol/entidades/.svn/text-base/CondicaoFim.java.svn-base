package visualalgol.entidades;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * Serve para juntar os dois caminhos de um if 
 */
public class CondicaoFim extends InstrucaoGenerica{
	private static Logger logger = Logger.getLogger(CondicaoFim.class);
	private static final long serialVersionUID = 1L;
	private Linha linhaSaida;
	private Set<Linha> listLinhaEntrada = new HashSet<Linha>();

	/**
	 * @return the linhaSaida
	 */
	public Linha getLinhaSaida() {
		return linhaSaida;
	}
	/**
	 * @param linhaSaida the linhaSaida to set
	 */
	public void setLinhaSaida(Linha linhaSaida) {
		this.linhaSaida = linhaSaida;
	}
	@Override
	public void delete() {
		for(Linha linha:listLinhaEntrada){
			getAlgoritmo().getListLinha().remove(linha);
		}
		getAlgoritmo().getListLinha().remove(linhaSaida);
		super.delete();
	}
	/**
	 * @return the listLinhaEntrada
	 */
	public Set<Linha> getListLinhaEntrada() {
		return listLinhaEntrada;
	}
	/**
	 * @param listLinhaEntrada the listLinhaEntrada to set
	 */
	public void setListLinhaEntrada(Set<Linha> listLinhaEntrada) {
		this.listLinhaEntrada = listLinhaEntrada;
	}
	@Override
	public void substituirEntrada(Linha procurarPor, Linha substituirPor) {
		if(substituirPor!=null){
			//logger.debug("procurarPor " + procurarPor.getId() + " " + substituirPor.getId());
			listLinhaEntrada.add(substituirPor);
		}
		if(procurarPor!=null){
			listLinhaEntrada.remove(procurarPor);
		}
	}
	
}
