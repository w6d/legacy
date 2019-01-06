package visualalgol.casosdeuso.menueditar;

import java.util.List;

import visualalgol.entidades.InstrucaoGenerica;

public class Colar extends BaseRecortarCopiarColar {
	public void executarComoThread() throws InterruptedException {
		InstrucaoGenerica destino = null;
		if (clipBoard != null) {
			List<InstrucaoGenerica> lista = sistema.getAlgoritmo().getListComando();
			for (InstrucaoGenerica instrucao : lista) {
				if (instrucao.isFoco()) {
					destino = instrucao;
					break;// somente 1 destino
				}
			}
			if(destino!=null){
				destino.setPseudoCodigo(clipBoard.getPseudoCodigo());
			}
		}
	}
}
