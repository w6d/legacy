package visualalgol.casosdeuso.desenhistas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import visualalgol.entidades.CondicaoIf;
import visualalgol.entidades.InstrucaoGenerica;

/**
 * O nome da classe deve comecar com Desenhar e o fim dela deve ser o nome da
 * entidade neste caso CondicaoIf
 */
public class DesenharCondicaoIf implements Desenhista {

	@Override
	public void desenhar(InstrucaoGenerica instrucao, BufferedImage bi) {
		Graphics gra = bi.getGraphics();
		int larg = 0;
		int w = instrucao.getW();
		
		if(instrucao.getPseudoCodigo()!=null){
			larg = gra.getFontMetrics().stringWidth(instrucao.getPseudoCodigo());
			if(larg>w){
				w=larg+20;
			}
		}
		
		int wPor2 = w / 2;
		int h = instrucao.getH();
		int hPor2 = h / 2;
		
		// losangulo com pontos A=Norte,B=Leste,C=Sul,D=Oeste
		Point a = new Point(instrucao.getX(), instrucao.getY()-hPor2);
		Point b = new Point(a.x + wPor2, a.y + hPor2);
		Point c = new Point(a.x, a.y + h);
		Point d = new Point(a.x - wPor2, a.y + hPor2);
		Polygon p = new Polygon();
		p.addPoint(a.x, a.y);
		p.addPoint(b.x, b.y);
		p.addPoint(c.x, c.y);
		p.addPoint(d.x, d.y);
		p.addPoint(a.x, a.y);
		instrucao.setPoligono(p);

		gra.setColor(new Color(instrucao.getCor()));
		gra.fillPolygon(p);
		gra.setColor(Color.BLACK);
		if(instrucao.getPseudoCodigo()!=null){
			gra.drawString(instrucao.getPseudoCodigo(), d.x+((w-larg)/2),d.y+5);
		}
		if(instrucao.isExecutado()){
			gra.setColor(Color.GREEN);
		}
		gra.drawPolygon(p);
		if(instrucao.isFoco()){
			gra.setColor(Color.BLACK);
			for(int i=0;i<p.npoints;i++){
				gra.fillRect(p.xpoints[i]-2, p.ypoints[i]-2, 5, 5);
			}
		}
		CondicaoIf condicaoIf = (CondicaoIf)instrucao;
		DesenharSeta.desenhar(condicaoIf.getPoligono(),condicaoIf.getLinhaEntrada(),gra);
		DesenharSeta.desenhar(condicaoIf.getPoligono(),condicaoIf.getLinhaEntradaLoopBack(),gra);
	}

	@Override
	public boolean podeDesenhar(InstrucaoGenerica instrucao) {
		return instrucao instanceof CondicaoIf;
	}

}
