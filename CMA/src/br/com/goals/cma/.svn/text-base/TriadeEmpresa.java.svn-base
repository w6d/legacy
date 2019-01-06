package br.com.goals.cma;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class TriadeEmpresa extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) {
		new TriadeEmpresa();
	}
	
	public TriadeEmpresa() {
		this.setSize(800,600);
		this.setVisible(true);
	}
	
	@Override
	public void paint(Graphics g) {
		BufferedImage bi = getImage(80.33f, 80.66f, 90.0f);
		g.drawImage(bi,0,0,null);
	}
	
	public BufferedImage getImage(float corpo, float alma, float mente){
		int s=2;
		BufferedImage bi = new BufferedImage(800,600,BufferedImage.TYPE_INT_RGB);
		Graphics2D gra = (Graphics2D)bi.getGraphics();
		gra.fillRect(0, 0, 800, 600);
		gra.translate(400,300);
		gra.setColor(Color.BLACK);
		
		double rot = 2.095;
		
		Point pAlma = new Point();
		pAlma.x=0;
		pAlma.y=-(int)Math.round(s*alma);
		
		Point pMente = new Point();
		pMente.x=0;
		pMente.y=-(int)Math.round(s*mente);
		pMente = rotate(pMente, rot);
		
		Point pCorpo = new Point();
		pCorpo.x=0;
		pCorpo.y=-(int)Math.round(s*corpo);
		pCorpo = rotate(pCorpo, rot*2);
		
		gra.drawLine(pAlma.x, pAlma.y, pMente.x, pMente.y);
		gra.drawLine(pMente.x, pMente.y, pCorpo.x, pCorpo.y);
		gra.drawLine(pAlma.x, pAlma.y, pCorpo.x, pCorpo.y);
		
		gra.drawLine(0, 0, 0, -105*s);
		int y = 0;
		for(int i=20*s;i<=100*s;i+=20*s){
			y = -i;
			gra.drawLine(-10, y, 10, y);
		}
		
		gra.rotate(rot);
		gra.drawLine(0, 0, 0, -105*s);
		y = 0;
		for(int i=20*s;i<=100*s;i+=20*s){
			y = -i;
			gra.drawLine(-10, y, 10, y);
		}
		gra.rotate(rot);
		gra.drawLine(0, 0, 0, -105*s);
		y = 0;
		for(int i=20*s;i<=100*s;i+=20*s){
			y = -i;
			gra.drawLine(-10, y, 10, y);
		}
		
		//gra.
		return bi;
	}
	
	private Point rotate(Point p, double t){
		Point re = new Point();
		re.x = (int)Math.round(p.x*Math.cos(t) - p.y*Math.sin(t));
		re.y = (int)Math.round(p.x*Math.sin(t) + p.y*Math.cos(t));
		return re;
	}
}
