package visualalgol.casosdeuso.menueditar;

import java.util.List;

import visualalgol.entidades.InstrucaoGenerica;

public class Copiar extends BaseRecortarCopiarColar {
	public void executarComoThread() throws InterruptedException {
		clipBoard = null;
		List<InstrucaoGenerica> lista = sistema.getAlgoritmo().getListComando();
		for (InstrucaoGenerica instrucao : lista) {
			if (instrucao.isFoco()) {
				clipBoard = instrucao;
				break;// hoje somente 1 sera recortado
			}
		}
	}
}
