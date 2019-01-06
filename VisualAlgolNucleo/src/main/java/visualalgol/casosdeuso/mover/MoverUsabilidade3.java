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
public class MoverUsabilidade3 implements Mover{

	/**
	 * Implementa um movimento complexo
	 */
	public void mover(Point arrastandoPonto,InstrucaoGenerica arrastando,final int x,final int y){
		mover(arrastandoPonto, arrastando, x, y,null);
	}
	
	private void moverDelayLine(final Linha linha,final int x,final int y,final HashSet<InstrucaoGenerica> visitado){
		final InstrucaoGenerica prox = linha.getDestino();
		//mover os pontos da linha
		Thread t = new Thread(){
			@Override
			public void run() {
				for(Point p: linha.getListPontos()){
					try {Thread.sleep(200);} catch (InterruptedException e) {}
					mover(p, null, x, y, visitado);
				}
				try {Thread.sleep(200);} catch (InterruptedException e) {}
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
				if(arrastando instanceof Inicio){
					final Inicio inicio = (Inicio) arrastando;
					moverDelayLine(inicio.getLinhaSaida(), x, y, new HashSet<InstrucaoGenerica>());
				}else if(arrastando instanceof Comando){
					Comando comando = (Comando) arrastando;
					if(visitado!=null){
						moverDelayLine(comando.getLinhaSaida(), x ,y , visitado);
					}else{
						if(comando.getLinhaSaida()!=null){
							for(Point point:comando.getLinhaSaida().getListPontos()){
								if(point.x==xLinhaDivisoria){
									point.x += x;
								}
							}
						}
						if(comando.getLinhaEntrada()!=null){
							for(Point point:comando.getLinhaEntrada().getListPontos()){
								if(point.x==xLinhaDivisoria){
									point.x += x;
								}
							}
						}
					}
				}else if(arrastando instanceof CondicaoIf){
					CondicaoIf condicaoIf = (CondicaoIf) arrastando;
					if(visitado!=null){
						moverDelayLine(condicaoIf.getLinhaFalsa(), x, y, visitado);
						moverDelayLine(condicaoIf.getLinhaVerdadeira(), x, y, visitado);
					}else{
						if(condicaoIf.getLinhaFalsa()!=null){
							for(Point point:condicaoIf.getLinhaFalsa().getListPontos()){
								if(point.y==yLinhaDivisoria){
									point.y += y;
								}
							}
						}
						if(condicaoIf.getLinhaEntrada()!=null){
							for(Point point:condicaoIf.getLinhaEntrada().getListPontos()){
								if(point.x==xLinhaDivisoria){
									point.x += x;
								}
							}
						}
					}
				}else if(arrastando instanceof CondicaoFim){
					CondicaoFim condicaoFim = (CondicaoFim) arrastando;
					if(visitado!=null){
						moverDelayLine(condicaoFim.getLinhaSaida(),x, y, visitado);
					}else{
						for (Linha linha : condicaoFim.getListLinhaEntrada()) {
							for (Point point : linha.getListPontos()) {
								if (point.y == yLinhaDivisoria) {
									point.y += y;
								}
							}
						}
						if(condicaoFim.getLinhaSaida()!=null){
							for(Point point:condicaoFim.getLinhaSaida().getListPontos()){
								if(point.x==xLinhaDivisoria){
									point.x += x;
								}
							}
						}
					}
				}
				arrastando.setX(arrastando.getX() + x);
				arrastando.setY(arrastando.getY() + y);
			}
		}
	}
}
