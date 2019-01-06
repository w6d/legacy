package visualalgol.casosdeuso;

import visualalgol.entidades.InstrucaoGenerica;
import visualalgol.ferramenta.CondicaoIfFerramenta;

public class CriarCondicao extends CasoDeUso{
	@Override
	public void executarComoThread() throws InterruptedException {
		sistema.setFerramenta(new CondicaoIfFerramenta());
		sistema.informarNoRodape("Criando 'Se': Clique em cima de uma linha...");
		InstrucaoGenerica instrucao = ator.criarInstrucao();
		EscreverPseudoCodigo.utilizar(sistema, instrucao);
		AutoArrumar.utilizar(sistema, instrucao);
	}
}
