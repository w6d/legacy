package visualalgol.ferramenta;

import java.awt.event.MouseEvent;

import visualalgol.casosdeuso.EscreverPseudoCodigo;
import visualalgol.entidades.InstrucaoGenerica;

/**
 * Escreve o comando do comando ou condicao do if
 */
public class EscreverFerramenta extends Ferramenta {
	private InstrucaoGenerica instrucao;

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			instrucao = getInstrucaoEm(e.getX(), e.getY());
			if (instrucao != null) {
				EscreverPseudoCodigo escreverPseudoCodigo = new EscreverPseudoCodigo();
				escreverPseudoCodigo.setSistema(sistema);
				escreverPseudoCodigo.setInstrucao(instrucao);
				escreverPseudoCodigo.executar();
			}
		}
	}
	public void mouseDragged(MouseEvent e) {}
}
