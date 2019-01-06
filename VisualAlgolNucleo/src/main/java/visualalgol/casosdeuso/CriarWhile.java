package visualalgol.casosdeuso;

import visualalgol.entidades.InstrucaoGenerica;
import visualalgol.ferramenta.WhileFerramenta;

public class CriarWhile extends CasoDeUso{
	@Override
	public void executarComoThread() throws InterruptedException {
		sistema.informarNoRodape("Criando 'Se': Clique em cima de uma linha...");
		sistema.setFerramenta(new WhileFerramenta());
		InstrucaoGenerica instrucao = ator.criarInstrucao();
		EscreverPseudoCodigo.utilizar(sistema, instrucao);
		AutoArrumar.utilizar(sistema, instrucao);
	}
}
