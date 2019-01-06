package visualalgol.swing;

import java.awt.Component;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

import visualalgol.ferramenta.Ferramenta;

/**
 * TODO como resolver o key DELETE e o LookAndFeel? 
 * @author Fabio Issamu Oshiro
 *
 */
public class TelaDesenhoFluxograma extends JButton{
	private static final long serialVersionUID = 1L;

	private BufferedImage bi = null;
	public TelaDesenhoFluxograma(){
		//this.setBorder(null);
		this.setFocusable(true);
	}

	public void removeListener(Ferramenta ferramenta) {
		this.removeMouseListener(ferramenta);
		this.removeMouseMotionListener(ferramenta);
		this.removeMouseWheelListener(ferramenta);
		this.removeKeyListener(ferramenta);
	}

	public void addListener(Ferramenta ferramenta) {
		this.addMouseListener(ferramenta);
		this.addMouseMotionListener(ferramenta);
		this.addMouseWheelListener(ferramenta);
		this.addKeyListener(ferramenta);
	}
	
	private int x=-1,y=-1;
	public void apontarPara(int x, int y) {
		this.x=x;this.y=y;
	}
	public void setImage(BufferedImage bi) {
		if(x>0 && y>0){
			BufferedImage img = ImageIO.read(new File("images/indicador.png"));
			bi.getGraphics().drawImage(img, x+30, y-40, null);
		}
		//setIcon(new ImageIcon(bi));
		this.bi = bi;
		this.repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		//super.paint(g);
		g.drawImage(bi, 0,0, null);
	}
	
}
