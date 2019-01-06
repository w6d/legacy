package visualalgol.casosdeuso.mover;

import java.awt.Point;

import visualalgol.entidades.InstrucaoGenerica;

/**
 *	Implementa a estrategia (Strategy) de movimento dos objetos do fluxograma.
 *	ou como os objetos sao movimentados.
 *	Movimenta de forma simples
 *
 */
public class MoverUsabilidade1 implements Mover{

	public void mover(Point arrastandoPonto,InstrucaoGenerica arrastando, int x, int y){
		if (arrastandoPonto != null) {
			arrastandoPonto.x += x;
			arrastandoPonto.y += y;
		} else {
			if (arrastando != null) {
				arrastando.setX(arrastando.getX() + x);
				arrastando.setY(arrastando.getY() + y);
			}
		}
	}
}
