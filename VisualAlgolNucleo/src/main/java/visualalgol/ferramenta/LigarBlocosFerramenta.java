package visualalgol.ferramenta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import visualalgol.entidades.Comando;
import visualalgol.entidades.CondicaoFim;
import visualalgol.entidades.CondicaoIf;
import visualalgol.entidades.Fim;
import visualalgol.entidades.Inicio;
import visualalgol.entidades.InstrucaoGenerica;
import visualalgol.entidades.Linha;
import visualalgol.swing.Messages;

public class LigarBlocosFerramenta extends Ferramenta {
	private JPopupMenu popupMenuVerdadeiroFalso;
	private JPopupMenu popupMenuEntradaOuVoltaDeCondicao;
	private InstrucaoGenerica instrucaoOrigem = null;
	private InstrucaoGenerica instrucaoDestino = null;
	private boolean caminhoValor;
	
	//private int ultimoX, ultimoY;

	public LigarBlocosFerramenta() {
		popupMenuEntradaOuVoltaDeCondicao = new JPopupMenu();
		JMenuItem menuItemVoltaDeCondicao = new JMenuItem(Messages.getString("label.voltaloop"));
		JMenuItem menuItemEntradaDeCondicao = new JMenuItem(Messages.getString("label.entradacondicao"));

		popupMenuVerdadeiroFalso = new JPopupMenu();
		JMenuItem menuItemVerdadeiro = new JMenuItem("Verdadeiro");
		JMenuItem menuItemFalso = new JMenuItem("Falso");
		menuItemVerdadeiro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				caminhoValor = true;
				iniciarLinha();
			}
		});
		menuItemFalso.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				caminhoValor = false;
				iniciarLinha();
			}
		});
		popupMenuVerdadeiroFalso.add(menuItemVerdadeiro);
		popupMenuVerdadeiroFalso.add(menuItemFalso);

		popupMenuEntradaOuVoltaDeCondicao.add(menuItemVoltaDeCondicao);
		popupMenuEntradaOuVoltaDeCondicao.add(menuItemEntradaDeCondicao);
		menuItemVoltaDeCondicao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				definirComoVoltaDeRepeticao();
			}
		});
		menuItemEntradaDeCondicao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				definirComoEntrada();
			}
		});
	}

	private void definirComoEntrada(){
		CondicaoIf condicao = (CondicaoIf) instrucaoDestino;
		if (condicao.getLinhaEntrada() != null) {
			// remover a entrada antiga
			getAlgoritmo().getListLinha().remove(condicao.getLinhaEntrada());
		}
		condicao.setLinhaEntrada(linha);
	}
	
	private void definirComoVoltaDeRepeticao(){
		CondicaoIf condicao = (CondicaoIf) instrucaoDestino;
		if (condicao.getLinhaEntradaLoopBack() != null) {
			// remover a entrada antiga
			getAlgoritmo().getListLinha().remove(condicao.getLinhaEntradaLoopBack());
		}
		condicao.setLinhaEntradaLoopBack(linha);
	}
	
	private void iniciarLinha() {
		linha = new Linha();
		linha.setOrigem(instrucaoOrigem);
		if (instrucaoOrigem instanceof CondicaoIf) {
			CondicaoIf condicaoIf = (CondicaoIf) instrucaoOrigem;
			if (caminhoValor) {
				if (condicaoIf.getLinhaVerdadeira() != null) {
					// apagar a linha antiga
					getAlgoritmo().getListLinha().remove(condicaoIf.getLinhaVerdadeira());
				}
				condicaoIf.setLinhaVerdadeira(linha);
			} else {
				if (condicaoIf.getLinhaFalsa() != null) {
					// apagar a linha antiga
					getAlgoritmo().getListLinha().remove(condicaoIf.getLinhaFalsa());
				}
				condicaoIf.setLinhaFalsa(linha);
			}
		} else if (instrucaoOrigem instanceof Comando) {
			// comando
			Comando comando = (Comando) instrucaoOrigem;
			if (comando.getLinhaSaida() != null) {
				// apagar a linha antiga
				getAlgoritmo().getListLinha().remove(comando.getLinhaSaida());
			}
			comando.setLinhaSaida(linha);
		} else if (instrucaoOrigem instanceof CondicaoFim) {
			// fim de condicao (end if)
			CondicaoFim condicaoFim = (CondicaoFim) instrucaoOrigem;
			if (condicaoFim.getLinhaSaida() != null) {
				// apagar a linha antiga
				getAlgoritmo().getListLinha().remove(condicaoFim.getLinhaSaida());
			}
			condicaoFim.setLinhaSaida(linha);
		} else if (instrucaoOrigem instanceof Inicio) {
			// fim de condicao (end if)
			Inicio condicaoFim = (Inicio) instrucaoOrigem;
			if (condicaoFim.getLinhaSaida() != null) {
				// apagar a linha antiga
				getAlgoritmo().getListLinha().remove(condicaoFim.getLinhaSaida());
			}
			condicaoFim.setLinhaSaida(linha);
		}
		getAlgoritmo().getListLinha().add(linha);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount()!=1) return;
		if (instrucaoOrigem == null) {
			if(e.getButton()==MouseEvent.BUTTON1){
				// tentar iniciar a linha
				instrucaoOrigem = getInstrucaoEm(e.getX(), e.getY());
				if (instrucaoOrigem != null) {
					if (instrucaoOrigem instanceof CondicaoIf) {
						// abrir opcoes de true ou false
						popupMenuVerdadeiroFalso.show(e.getComponent(), e.getX(), e.getY());
					} else if (instrucaoOrigem instanceof Fim) {
						// nope
					} else {
						iniciarLinha();
					}
				}
			}else if(e.getButton()==MouseEvent.BUTTON3){
				// mostrar as opcoes da instrucao
				
			}
		} else {
			// ligando
			finalizarLinha(e);
		}
	}

	private void finalizarLinha(MouseEvent e) {
		InstrucaoGenerica instrucao = getInstrucaoEm(e.getX(), e.getY());
		if (instrucao == null) {
			// ponto no vazio
			if (linha != null) {
				linha.getListPontos().add(e.getPoint());
			}
		} else {
			if (instrucaoOrigem == instrucao) {
				// cancelar
				if (linha != null) {
					getAlgoritmo().getListLinha().remove(linha);
				}
			} else {
				// ponto em uma instrucao
				if (linha != null) {
					if (instrucao instanceof CondicaoFim) {
						CondicaoFim condicaoFim = (CondicaoFim) instrucao;
						condicaoFim.getListLinhaEntrada().add(linha);
					} else if (instrucao instanceof Inicio) {
						// do nothing
						getAlgoritmo().getListLinha().remove(linha);
					} else if (instrucao instanceof CondicaoIf) {

						instrucaoDestino = instrucao;
						// perguntar se eh um loop
						popupMenuEntradaOuVoltaDeCondicao.show(e.getComponent(), e.getX(), e.getY());
						
					} else if (instrucao instanceof Comando) {
						Comando comando = (Comando) instrucao;
						if (comando.getLinhaEntrada() != null) {
							// remover a entrada antiga
							getAlgoritmo().getListLinha().remove(comando.getLinhaEntrada());
						}
						comando.setLinhaEntrada(linha);
					} else if (instrucao instanceof Fim) {
						Fim fim = (Fim) instrucao;
						if (fim.getLinhaEntrada() != null) {
							// remover a entrada antiga
							getAlgoritmo().getListLinha().remove(fim.getLinhaEntrada());
						}
						fim.setLinhaEntrada(linha);
					}
					linha.setDestino(instrucao);
				}
			}
			// zerar
			instrucaoOrigem = null;
			linha = null;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (instrucaoOrigem != null) {
			if (linha != null) {
				linha.setPontoTemporario(e.getPoint());
			}
		}
	}

	@Override
	public void finalizar() {
		if (linha != null) {
			linha.setPontoTemporario(null);
		}
		super.finalizar();
	}
}
