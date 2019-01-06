package visualalgol.casosdeuso.menueditar;

import java.util.List;

import visualalgol.casosdeuso.AutoArrumar;
import visualalgol.casosdeuso.CasoDeUso;
import visualalgol.entidades.Comando;
import visualalgol.entidades.InstrucaoGenerica;
import visualalgol.ferramenta.ComandoFerramenta;

public class Recortar extends BaseRecortarCopiarColar {
	
	public void executarComoThread() throws InterruptedException {
		clipBoard = null;
		List<InstrucaoGenerica> lista = sistema.getAlgoritmo().getListComando();
		for (InstrucaoGenerica instrucao : lista) {
			if (instrucao.isFoco()) {
				clipBoard = instrucao;
				instrucao.delete();
				break;// hoje somente 1 sera recortado
			}
		}
		if(clipBoard!=null){
			if(clipBoard instanceof Comando){
				sistema.informarNoRodape("Clique na linha de destino");
				sistema.setFerramenta(new ComandoFerramenta());
				InstrucaoGenerica instrucao = ator.criarInstrucao();
				instrucao.setPseudoCodigo(clipBoard.getPseudoCodigo());
				AutoArrumar.utilizar(sistema, instrucao);
			}
		}
	}
}
