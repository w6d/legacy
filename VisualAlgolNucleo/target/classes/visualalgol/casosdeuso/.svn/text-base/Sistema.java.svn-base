package visualalgol.casosdeuso;

import java.awt.Component;
import java.awt.image.BufferedImage;

import visualalgol.entidades.Algoritmo;
import visualalgol.entidades.ArquivoRecente;
import visualalgol.entidades.InstrucaoGenerica;
import visualalgol.ferramenta.Ferramenta;

public interface Sistema {
	public void setAlgoritmo(Algoritmo algoritmo);
	public void informar(String texto);
	public void informarNoRodape(String texto);
	public void apontarPara(InstrucaoGenerica instrucao);
	public Component getComponent();
	public Algoritmo getAlgoritmo();
	public void setFerramenta(Ferramenta comandoFerramenta);
	public void setTitle(String path);
	public ArquivoRecente getArquivoRecente();
	public void setArquivoRecente(ArquivoRecente obj);
	public void setCodigo(String string);
	public void appendCodigo(String string);
	public int getTelaFluxogramaWidth();
	public int getTelaFluxogramaHeight();
	public void setFluxogramaImage(BufferedImage bi);
}
