package visualalgol.casosdeuso;

import java.util.ArrayList;
import java.util.List;

import visualalgol.casosdeuso.langs.Linguagem;
import visualalgol.casosdeuso.langs.OutputLang;
import visualalgol.casosdeuso.langs.Portugol;
import visualalgol.entidades.Comando;
import visualalgol.entidades.ComandoDo;
import visualalgol.entidades.ComandoDoWhile;
import visualalgol.entidades.CondicaoFim;
import visualalgol.entidades.CondicaoIf;
import visualalgol.entidades.Fim;
import visualalgol.entidades.Inicio;
import visualalgol.entidades.InstrucaoGenerica;

/**
 * Conversor de fluxo para pseudo codigo definir o estilo do pseudo codigo
 */
public class Fluxo2PseudoCodigo extends CasoDeUso {

	private Linguagem linguagem = new Portugol();

	/**
	 * Navegar pelos nodes iniciando do Inicio, vamos navegar sempre pelas
	 * linhas
	 */
	@Override
	public void executar() {
		sistema.setCodigo("");
		// detecta os loops
		navegarPeloGrafo(false);
		// imprime o codigo na tela
		navegarPeloGrafo(true);
	}

	/**
	 * Navega pelas instrucoes inseridas dentro do fluxograma
	 * 
	 * @param printMode
	 *            se estiver com true ira jogar o print na tela do usuario
	 */
	private void navegarPeloGrafo(boolean printMode) {

		List<CondicaoIf> pilhaCondicao = new ArrayList<CondicaoIf>();
		Inicio inicio = sistema.getAlgoritmo().getComandoInicial();
		InstrucaoGenerica instrucao, proximaInstrucao = inicio.getLinhaSaida()
				.getDestino();
		// Zerar
		for (InstrucaoGenerica aux : sistema.getAlgoritmo().getListComando()) {
			aux.setVisitado(false);
		}
		if (printMode) {
			linguagem.setOutputLang(new OutputLang(sistema));
			linguagem.getCabecalho(sistema.getAlgoritmo());
			linguagem.getInicio();
		}
		while (true) {
			// para deixar mais claro. Ao final do loop existe o
			// instrucao.setVisitado(true);
			instrucao = proximaInstrucao;
			if (instrucao == null) {
				break;
			} else if (instrucao instanceof ComandoDoWhile) {
				ComandoDoWhile comando = (ComandoDoWhile) instrucao;
				proximaInstrucao = comando.getLinhaFalsa().getDestino();
				if (printMode) {
					linguagem.escreverDoWhile(comando.getPseudoCodigo());				
				}
			} else if (instrucao instanceof ComandoDo) {
				ComandoDo comando = (ComandoDo) instrucao;
				proximaInstrucao = comando.getLinhaSaida().getDestino();
				if (printMode) {
					linguagem.escreverDo();
				}
			} else if (instrucao instanceof CondicaoIf) {
				CondicaoIf condicao = (CondicaoIf) instrucao;
				if (printMode) {
					// modo para dar saida no pseudo codigo
					if (condicao.isLoop()) {
						if (!condicao.isVisitado()) {
							linguagem.escreverWhile(condicao.getPseudoCodigo());
						} else {
							linguagem.escreverEndWhile();
						}

					} else {
						linguagem.escreverIf(condicao.getPseudoCodigo());
					}
				}
				// pode ser um if ou um loop
				if (condicao.isVisitado()) {
					// se ja foi visitado entao so pode ser um loop
					// desempilha
					condicao = pilhaCondicao.remove(pilhaCondicao.size() - 1);
					condicao.setLoop(true);
					// andar pelo false
					proximaInstrucao = condicao.getLinhaFalsa().getDestino();
				} else {
					// nao foi visitado, indefinido
					// empilha
					pilhaCondicao.add(condicao);
					// andar pelo true
					proximaInstrucao = condicao.getLinhaVerdadeira().getDestino();
				}
			} else if (instrucao instanceof Comando) {
				// Comando
				Comando comando = (Comando) instrucao;
				proximaInstrucao = comando.getLinhaSaida().getDestino();
				if (printMode) {
					if (comando.getPseudoCodigo() != null) {
						linguagem.escreverComando(comando.getPseudoCodigo());
					} else {
						linguagem.escreverComandoVazio();
					}
				}
			} else if (instrucao instanceof CondicaoFim) {
				// Fim de Condicao, vulgo end if
				if (!instrucao.isVisitado()) {
					// desempilha
					CondicaoIf condicao = pilhaCondicao.remove(pilhaCondicao
							.size() - 1);
					condicao.setLoop(false);
					// andar pelo false
					proximaInstrucao = condicao.getLinhaFalsa().getDestino();
					if (printMode) {
						linguagem.escreverElse();
					}
				} else {
					CondicaoFim condicaoFim = (CondicaoFim) instrucao;
					proximaInstrucao = condicaoFim.getLinhaSaida().getDestino();
					if (printMode) {
						linguagem.escreverFimCondicao();
					}
				}
			} else if (instrucao instanceof Fim) {
				break;
			}
			instrucao.setVisitado(true);
		}
		if (printMode) {
			linguagem.getFim();
		}
	}

	public void setLinguagem(Linguagem linguagem) {
		this.linguagem = linguagem;
	}
}
