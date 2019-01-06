package visualalgol.casosdeuso;

import visualalgol.casosdeuso.historico.Historico;
import visualalgol.entidades.InstrucaoGenerica;
import visualalgol.ferramenta.ComandoFerramenta;

public class CriarComando extends CasoDeUso{

	@Override
	public void executarComoThread() throws InterruptedException {
		sistema.setFerramenta(new ComandoFerramenta());
		while(true){
			Historico.getInstance().gravarEstado();
			sistema.informarNoRodape("Criando 'Comando': Clique em cima de uma linha...");
			InstrucaoGenerica instrucao = ator.criarInstrucao();
			EscreverPseudoCodigo.utilizar(sistema, instrucao);
			AutoArrumar.utilizar(sistema, instrucao);
		}
	}

}
