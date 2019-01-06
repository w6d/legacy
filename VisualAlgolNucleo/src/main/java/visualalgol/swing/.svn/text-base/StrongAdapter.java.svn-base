package visualalgol.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import visualalgol.casosdeuso.CasoDeUso;
import visualalgol.casosdeuso.historico.Historico;

/**
 * Strong Adapter Pattern 
 */
public class StrongAdapter implements ActionListener{
	private Class<?> claz;
	private MainFrame sistema;
	boolean historico = true;
	public StrongAdapter(MainFrame sistema,Class<?> claz) {
		this.sistema = sistema;
		this.claz = claz;
	}
	public StrongAdapter(MainFrame mainFrame, Class<?> class1, boolean historico) {
		this(mainFrame,class1);
		this.historico = historico;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			CasoDeUso caso = (CasoDeUso)claz.newInstance();
			caso.setSistema(sistema);
			if(historico){
				//salvar control z
				Historico.getInstance().gravarEstado();
			}
			caso.executar();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
	}
}
