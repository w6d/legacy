package visualalgol.casosdeuso.desenhistas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import visualalgol.entidades.Comando;
import visualalgol.entidades.InstrucaoGenerica;

public class DesenharComando implements Desenhista {

	@Override
	public void desenhar(InstrucaoGenerica instrucao, BufferedImage bi) {
		Graphics gra = bi.getGraphics();
		
		int larg = 0;
		int w = instrucao.getW();
		
		if(instrucao.getPseudoCodigo()!=null){
			larg = gra.getFontMetrics().stringWidth(instrucao.getPseudoCodigo());
			if(larg>w){
				w=larg+6;
			}
		}
		int wPor2 = w / 2;
		int h = instrucao.getH();
		int hPor2 = h / 2;
		
		// Retangulo com pontos A=top left,B=top right,C=bottom right,D=bottom left
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
		gra.setColor(new Color(instrucao.getCor()));
		gra.fillPolygon(p);
		gra.setColor(Color.BLACK);
		if(instrucao.getPseudoCodigo()!=null){
			gra.drawString(instrucao.getPseudoCodigo(), d.x+((w-larg)/2),d.y-15);
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
		Comando comando = (Comando)instrucao;
		DesenharSeta.desenhar(comando.getPoligono(),comando.getLinhaEntrada(),gra);
	}

	@Override
	public boolean podeDesenhar(InstrucaoGenerica instrucao) {
		return instrucao instanceof Comando;
	}

}
