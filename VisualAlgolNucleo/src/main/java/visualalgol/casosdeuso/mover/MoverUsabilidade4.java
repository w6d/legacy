package visualalgol.casosdeuso.mover;

import java.awt.Point;
import java.util.HashSet;

import visualalgol.entidades.Comando;
import visualalgol.entidades.CondicaoFim;
import visualalgol.entidades.CondicaoIf;
import visualalgol.entidades.Inicio;
import visualalgol.entidades.InstrucaoGenerica;
import visualalgol.entidades.Linha;

/**
 *	Implementa a estrategia (Strategy) de movimento dos objetos do fluxograma.
 *	ou como os objetos sao movimentados.
 *	
 */
public class MoverUsabilidade4 implements Mover{

	private HashSet<InstrucaoGenerica> visitado;
	private HashSet<Linha> linhasVisitadas = new HashSet<Linha>();
	
	public void setVisitado(HashSet<InstrucaoGenerica> visitado) {
		this.visitado = visitado;
	}
	/**
	 * Implementa um movimento complexo
	 */
	public void mover(Point arrastandoPonto,InstrucaoGenerica arrastando,final int x,final int y){
		visitado.add(arrastando);
		mover(arrastandoPonto, arrastando, x, y,visitado);
	}
	
	private synchronized void moverDelayLine(final Linha linha,final int x,final int y,final HashSet<InstrucaoGenerica> visitado){
		if(linhasVisitadas.contains(linha)) return;
		linhasVisitadas.add(linha);
		
		final InstrucaoGenerica prox = linha.getDestino();
		final InstrucaoGenerica ant = linha.getOrigem();
		Thread t = new Thread(){
			public void run() {
				try {Thread.sleep(200);} catch (InterruptedException e) {}
				//mover os pontos da linha
				for(Point p: linha.getListPontos()){
					try {Thread.sleep(200);} catch (InterruptedException e) {}
					mover(p, null, x, y, visitado);
				}
				if(!visitado.contains(ant)){
					visitado.add(ant);
					mover(null, ant, x, y,visitado);
				}
				
				if(!visitado.contains(prox)){
					visitado.add(prox);
					mover(null, prox, x, y,visitado);
				}
			}
		};
		t.start();
	}
	
	private void mover(Point arrastandoPonto,InstrucaoGenerica arrastando,final int x,final int y, HashSet<InstrucaoGenerica> visitado){
		if (arrastandoPonto != null) {
			arrastandoPonto.x += x;
			arrastandoPonto.y += y;
		} else {
			if (arrastando != null) {
				int xLinhaDivisoria = arrastando.getX();
				int yLinhaDivisoria = arrastando.getY();
				arrastando.setX(arrastando.getX() + x);
				arrastando.setY(arrastando.getY() + y);
				if(arrastando instanceof Inicio){
					final Inicio inicio = (Inicio) arrastando;
					moverDelayLine(inicio.getLinhaSaida(), x, y, new HashSet<InstrucaoGenerica>());
				}else if(arrastando instanceof Comando){
					Comando comando = (Comando) arrastando;
					if(visitado!=null){
						moverDelayLine(comando.getLinhaSaida(), x ,y , visitado);
						moverDelayLine(comando.getLinhaEntrada(), x, y, visitado);
					}else{
						for(Point point:comando.getLinhaSaida().getListPontos()){
							if(point.x==xLinhaDivisoria){
								point.x += x;
							}
						}
						for(Point point:comando.getLinhaEntrada().getListPontos()){
							if(point.x==xLinhaDivisoria){
								point.x += x;
							}
						}
					}
				}else if(arrastando instanceof CondicaoIf){
					CondicaoIf condicaoIf = (CondicaoIf) arrastando;
					if(visitado!=null){
						moverDelayLine(condicaoIf.getLinhaFalsa(), x, y, visitado);
						moverDelayLine(condicaoIf.getLinhaVerdadeira(), x, y, visitado);
						moverDelayLine(condicaoIf.getLinhaEntrada(), x, y, visitado);
					}else{
						for(Point point:condicaoIf.getLinhaFalsa().getListPontos()){
							if(point.y==yLinhaDivisoria){
								point.y += y;
							}
						}
					}
				}else if(arrastando instanceof CondicaoFim){
					CondicaoFim condicaoFim = (CondicaoFim) arrastando;
					if(visitado!=null){
						moverDelayLine(condicaoFim.getLinhaSaida(),x, y, visitado);
						for(Linha linha: condicaoFim.getListLinhaEntrada()){
							moverDelayLine(linha,x, y, visitado);
						}
					}else{
						for (Linha linha : condicaoFim.getListLinhaEntrada()) {
							for (Point point : linha.getListPontos()) {
								if (point.y == yLinhaDivisoria) {
									point.y += y;
								}
							}
						}
						for(Point point:condicaoFim.getLinhaSaida().getListPontos()){
							if(point.x==xLinhaDivisoria){
								point.x += x;
							}
						}
					}
				}
				
			}
		}
	}
}
