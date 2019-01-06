package visualalgol.casosdeuso.desenhistas;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import visualalgol.entidades.InstrucaoGenerica;
import visualalgol.entidades.Linha;

public class DesenharSeta {
	
	private static Point2D.Float getIntersectionPoint(Line2D.Float line1, Line2D.Float line2) {
	    if (! line1.intersectsLine(line2) ) return null;
	      double px = line1.getX1(),
	            py = line1.getY1(),
	            rx = line1.getX2()-px,
	            ry = line1.getY2()-py;
	      double qx = line2.getX1(),
	            qy = line2.getY1(),
	            sx = line2.getX2()-qx,
	            sy = line2.getY2()-qy;
	 
	      double det = sx*ry - sy*rx;
	      if (det == 0) {
	        return null;
	      } else {
	        double z = (sx*(qy-py)+sy*(px-qx))/det;
	        if (z==0 ||  z==1) return null;  // intersection at end point!
	        return new Point2D.Float(
	          (float)(px+z*rx), (float)(py+z*ry));
	      }
	 } // end intersection line-line
	
	static double sen = 0.5;
	static double cos = Math.sqrt(3)/2;
	public static void desenhar(Polygon poligono, Linha linhaEntrada, Graphics gra) {
		if(linhaEntrada==null)return;
		
		Point2D.Float a = new Point2D.Float(poligono.xpoints[0],poligono.ypoints[0]);
		int tot = linhaEntrada.getListPontos().size();
		InstrucaoGenerica dest = linhaEntrada.getDestino(); 
		
		Point2D.Float pontoAnterior = new Point2D.Float(dest.getX(),dest.getY());
		Point pontoAtual = new Point(linhaEntrada.getOrigem().getX(),linhaEntrada.getOrigem().getY());
		for(int j=tot-1;j>=-1;j--){//loop da linha
			if(j==-1){
				pontoAtual = new Point(linhaEntrada.getOrigem().getX(),linhaEntrada.getOrigem().getY());
			}else{
				pontoAtual = linhaEntrada.getListPontos().get(j);
			}
			Line2D.Float lineLinha = new Line2D.Float(pontoAnterior, pontoAtual);
			for(int i=1;i<poligono.npoints;i++){//loop do poligono
				Point2D.Float b=new Point2D.Float(poligono.xpoints[i],poligono.ypoints[i]);
				Line2D.Float linePol = new Line2D.Float(a, b);
				a=b;
				Point2D.Float inter=getIntersectionPoint(lineLinha,linePol);
				
				if(inter!=null){
					double dist = pontoAtual.distance(inter);
					double distRel = 6 / dist;
					double x2 = (pontoAtual.x - inter.x) * distRel;
					double y2 = (pontoAtual.y - inter.y) * distRel;
					
					{
						double novoX = x2 * cos + y2 * sen + inter.x;
						double novoY = y2 * cos - x2 * sen + inter.y;
						gra.drawLine((int) inter.getX(), (int) inter.getY(),(int)Math.round(novoX), (int)Math.round(novoY));
					}
					{
						double novoX = x2 * cos + y2 * -sen + inter.x;
						double novoY = y2 * cos - x2 * -sen + inter.y;
						gra.drawLine((int) inter.getX(), (int) inter.getY(),(int)Math.round(novoX), (int)Math.round(novoY));
					}
					return;
				}
			}
			pontoAnterior=new Point2D.Float(pontoAtual.x,pontoAtual.y);
		}
	}
}
