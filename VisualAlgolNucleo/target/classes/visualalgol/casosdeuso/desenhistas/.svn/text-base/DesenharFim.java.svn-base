package visualalgol.casosdeuso.desenhistas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import visualalgol.entidades.Fim;
import visualalgol.entidades.InstrucaoGenerica;

public class DesenharFim implements Desenhista{

	@Override
	public void desenhar(InstrucaoGenerica instrucao, BufferedImage bi) {
		int w = instrucao.getW();
		int wPor2 = w / 2;
		int h = instrucao.getH();
		int hPor2 = h / 2;
		Graphics gra = bi.getGraphics();
		gra.setColor(Color.BLACK);
		gra.fillOval(instrucao.getX()-wPor2+2, instrucao.getY()-hPor2+2,w-4, h-4);
		gra.drawOval(instrucao.getX()-wPor2, instrucao.getY()-hPor2,w, h);
		//fazendo o "HIT"
		Point a = new Point(instrucao.getX() - wPor2, instrucao.getY()-hPor2);
		Point b = new Point(a.x + w, a.y);
		Point c = new Point(b.x, b.y + h);
		Point d = new Point(a.x, a.y + h);
		Polygon p = new Polygon();
		p.addPoint(a.x,a.y);
		p.addPoint(b.x,b.y);
		p.addPoint(c.x,c.y);
		p.addPoint(d.x,d.y);
		p.addPoint(a.x,a.y);
		instrucao.setPoligono(p);
	}

	@Override
	public boolean podeDesenhar(InstrucaoGenerica instrucao) {
		return instrucao instanceof Fim;
	}

}
