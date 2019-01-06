package visualalgol.swing.console;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Console extends JPanel{
	private static final long serialVersionUID = 1L;
	private JTextArea textAreaOutConsole;
	private JTextField entrada;
	private List<OnEnter> listOnEnter = new ArrayList<OnEnter>();
	private List<String> ultimosComandos = new ArrayList<String>();
	private int iUltimos = 0;
	private File persistirEmArquivo;
	private static final int MAX=20;
	private String bkpEntrada;
	private JScrollPane scrollPane;
	public Console() {
		textAreaOutConsole = new JTextArea();
		entrada = new JTextField();
		scrollPane = new JScrollPane(textAreaOutConsole);
		
		entrada.addKeyListener(new KeyListener(){ 
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					String textoDigitado = entrada.getText();
					write(textoDigitado);
					if(listOnEnter!=null){
						for(OnEnter onEnter:listOnEnter){
							onEnter.textoDigitado(textoDigitado);
						}
					}
					iUltimos = 0;
					//verificar se o ultimo nao eh igual
					if(ultimosComandos.size()==0 || (ultimosComandos.size()>0 && !ultimosComandos.get(ultimosComandos.size()-1).equals(textoDigitado))){
						ultimosComandos.add(textoDigitado);
						persistir(ultimosComandos);
					}
					entrada.setText("");
				}
			}
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_UP){
					if(bkpEntrada==null) bkpEntrada = entrada.getText();
					iUltimos++;
					int i = ultimosComandos.size()-iUltimos;
					if(i>=0 && i<ultimosComandos.size()){
						entrada.setText(ultimosComandos.get(i));
					}else{
						iUltimos--;
					}
				}else if(e.getKeyCode()==KeyEvent.VK_DOWN){
					iUltimos--;
					int i = ultimosComandos.size()-iUltimos;
					if(i>=0 && i<ultimosComandos.size()){
						entrada.setText(ultimosComandos.get(i));
					}
					if(i>=ultimosComandos.size()){
						entrada.setText(bkpEntrada);
						bkpEntrada = null;
						iUltimos=0;
					}
				}
			}
		});
		this.setLayout(new BorderLayout());
		this.add(scrollPane,BorderLayout.CENTER);
		this.add(entrada,BorderLayout.SOUTH);
		
		
		
	}
	
	private void persistir(List<String> ultimosComandos){
		if (persistirEmArquivo==null) return;
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(persistirEmArquivo);
			out = new ObjectOutputStream(fos);
			if(ultimosComandos.size()>MAX){
				List<String> uc = new ArrayList<String>();
				for(int i=ultimosComandos.size()-MAX;i<ultimosComandos.size();i++){
					uc.add(ultimosComandos.get(i));
				}
				//copiar somente os ultimos x
				out.writeObject(uc);
			}else{
				out.writeObject(ultimosComandos);
			}
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void write(String texto){
		textAreaOutConsole.append(texto + "\n");
		Thread t = new Thread(){
			@Override
			public void run() {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
				}
				JScrollBar vBar = scrollPane.getVerticalScrollBar();
				//if (vBar.getValue() == (vBar.getMaximum() - vBar.getVisibleAmount())) {
					vBar.setValue(vBar.getMaximum());
				//}
			}
		};
		t.start();
	}
	
	public JTextField getEntrada() {
		return entrada;
	}
	
	public void addOnEnterListener(OnEnter onEnter){
		listOnEnter.add(onEnter);
	}
	
	/**
	 * Local para persistir os comandos
	 * @param persistirEmArquivo
	 */
	@SuppressWarnings("unchecked")
	public void setPersistirEmArquivo(File persistirEmArquivo) {
		this.persistirEmArquivo = persistirEmArquivo;
		if(persistirEmArquivo.exists()){
			FileInputStream fis = null;
			ObjectInputStream in = null;
			try {
				fis = new FileInputStream(persistirEmArquivo);
				in = new ObjectInputStream(fis);
				ultimosComandos = (List<String>) in.readObject();
				in.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
