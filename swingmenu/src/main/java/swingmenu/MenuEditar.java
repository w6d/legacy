package swingmenu;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

public class MenuEditar extends JMenu{
	private static final long serialVersionUID = 1L;
	private JMenuItem copiar;
	private JMenuItem recortar;
	private JMenuItem colar;
	private JMenuItem desfazer;
	private JMenuItem refazer;
	
	public MenuEditar(){
		this.setText("Editar");
		this.setMnemonic('E');
		copiar = new JMenuItem("Copiar");
		copiar.setMnemonic('p');
		recortar = new JMenuItem("Recortar");
		recortar.setMnemonic('R');
		colar = new JMenuItem("Colar");
		colar.setMnemonic('C');
		desfazer = new JMenuItem("Desfazer");
		desfazer.setMnemonic('z');
		refazer = new JMenuItem("Refazer");
		refazer.setMnemonic('e');
		recortar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		copiar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		colar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		
		desfazer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		refazer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
		
		this.add(recortar);
		this.add(copiar);
		this.add(colar);
		this.add(new JSeparator());
		this.add(desfazer);
		this.add(refazer);
	}
	
	public JMenuItem getCopiar() {
		return copiar;
	}

	public void setCopiar(JMenuItem copiar) {
		this.copiar = copiar;
	}

	public JMenuItem getRecortar() {
		return recortar;
	}

	public void setRecortar(JMenuItem recortar) {
		this.recortar = recortar;
	}

	public JMenuItem getColar() {
		return colar;
	}

	public void setColar(JMenuItem colar) {
		this.colar = colar;
	}

	public JMenuItem getDesfazer() {
		return desfazer;
	}

	public void setDesfazer(JMenuItem desfazer) {
		this.desfazer = desfazer;
	}

	public JMenuItem getRefazer() {
		return refazer;
	}

	public void setRefazer(JMenuItem refazer) {
		this.refazer = refazer;
	}
}
