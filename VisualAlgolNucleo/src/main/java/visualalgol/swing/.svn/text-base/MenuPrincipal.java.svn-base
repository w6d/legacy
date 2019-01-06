package visualalgol.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import swingmenu.MenuEditar;
 
import visualalgol.entidades.ArquivoRecente;

public class MenuPrincipal extends JMenuBar{
	private static final long serialVersionUID = 1L;

	private JMenuItem salvarMenuItem;
	private JMenuItem abrirMenuItem;

	private AbrirRecenteListener abrirRecenteListener;
	private JMenuItem verPseudoCodigo;
	private JMenuItem rodar;
	private JMenuItem sairMenuItem;
	
	private ArquivoRecente arquivoRecente;
	private JMenu arquivo; 
	private JMenu recentes;
	private JMenu compilar;
	private JMenuItem novo;
	private JMenu codigo;
	private JMenuItem escrever;
	private JMenu ajuda;
	private JMenuItem topicosDaAjuda;
	private JMenuItem sobre;
	/**
	 * Do outro projeto, meio buggado ainda
	 */
	private MenuEditar menuEditar;
	public MenuPrincipal() {
		//instancia
		salvarMenuItem = new JMenuItem(Messages.getString("label.salvar")); //$NON-NLS-1$
		abrirMenuItem = new JMenuItem(Messages.getString("label.abrir")); //$NON-NLS-1$
		sairMenuItem= new JMenuItem(Messages.getString("label.sair")); //$NON-NLS-1$
		codigo = new JMenu(Messages.getString("label.codigo")); //$NON-NLS-1$
		verPseudoCodigo = new JMenuItem(Messages.getString("label.verPseudoCodigo")); //$NON-NLS-1$
		rodar = new JMenuItem(Messages.getString("label.executar")); //$NON-NLS-1$
		arquivo = new JMenu(Messages.getString("label.arquivo")); //$NON-NLS-1$
		recentes = new JMenu(Messages.getString("label.recentes")); //$NON-NLS-1$
		compilar = new JMenu(Messages.getString("label.compilar")); //$NON-NLS-1$
		novo = new JMenuItem(Messages.getString("label.novo")); //$NON-NLS-1$
		escrever = new JMenuItem("Escrever");
		menuEditar = new MenuEditar();
		ajuda = new JMenu(Messages.getString("label.ajuda"));
		ajuda.setMnemonic('u');
		topicosDaAjuda = new JMenuItem(Messages.getString("label.topicosDaAjuda"));
		sobre = new JMenuItem("Sobre o "+MainFrame.PROGNAME);
		//configuracao
		escrever.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2,0));
		escrever.setMnemonic('E');
		
		novo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		novo.setMnemonic('N');
		
		salvarMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		abrirMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		rodar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F9,0));
		
		topicosDaAjuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirAjudaOnline();
			}
		});
		
		//layout
		arquivo.add(abrirMenuItem);
		arquivo.add(novo);
		arquivo.add(recentes);
		arquivo.add(salvarMenuItem);
		arquivo.add(sairMenuItem);
		this.add(arquivo);
		this.add(menuEditar);
		codigo.add(escrever);
		codigo.add(new JSeparator());
		this.add(codigo);
		
		compilar.add(rodar);
		this.add(compilar);
		ajuda.add(topicosDaAjuda);
		ajuda.add(new JSeparator());
		ajuda.add(sobre);
		this.add(ajuda);
	}
	
	private void criarRecentes(){
		List<String> paths = arquivoRecente.getPaths();
		for(int i=0;i<paths.size();i++){
			File file = new File(paths.get(i));
			if(file.exists()){
				JMenuItem recente = new JMenuItem(file.getName());
				recentes.add(recente);
				recente.setActionCommand(paths.get(i));
				recente.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						abrirRecenteListener.abrirArquivoRecente(e.getActionCommand());
					}
				});
			}
		}
	}
	
	/**
	 * @return the salvarMenuItem
	 */
	public JMenuItem getSalvarMenuItem() {
		return salvarMenuItem;
	}
	/**
	 * @param salvarMenuItem the salvarMenuItem to set
	 */
	public void setSalvarMenuItem(JMenuItem salvarMenuItem) {
		this.salvarMenuItem = salvarMenuItem;
	}
	/**
	 * @return the abrirMenuItem
	 */
	public JMenuItem getAbrirMenuItem() {
		return abrirMenuItem;
	}
	/**
	 * @param abrirMenuItem the abrirMenuItem to set
	 */
	public void setAbrirMenuItem(JMenuItem abrirMenuItem) {
		this.abrirMenuItem = abrirMenuItem;
	}
	
	public JMenuItem getVerPseudoCodigo() {
		return verPseudoCodigo;
	}
	public void setVerPseudoCodigo(JMenuItem verPseudoCodigo) {
		this.verPseudoCodigo = verPseudoCodigo;
	}
	public ArquivoRecente getArquivoRecente() {
		return arquivoRecente;
	}
	public void setArquivoRecente(ArquivoRecente arquivoRecente) {
		this.arquivoRecente = arquivoRecente;
		criarRecentes();
	}
	public JMenuItem getSairMenuItem() {
		return sairMenuItem;
	}
	

	/**
	 * @return the abrirRecenteListener
	 */
	public AbrirRecenteListener getAbrirRecenteListener() {
		return abrirRecenteListener;
	}

	/**
	 * @param abrirRecenteListener the abrirRecenteListener to set
	 */
	public void setAbrirRecenteListener(AbrirRecenteListener abrirRecenteListener) {
		this.abrirRecenteListener = abrirRecenteListener;
	}

	public JMenuItem getEscrever() {
		return escrever;
	}
	
	/**
	 * Run F9
	 * @return the rodar
	 */
	public JMenuItem getRodar() {
		return rodar;
	}
	public JMenuItem getNovo() {
		return novo;
	}
	public JMenu getCodigo() {
		return codigo;
	}
	
	
	public JMenuItem getRecortar(){
		return menuEditar.getRecortar();
	}
	/**
	 * Delegate 2 menuEditar.getDesfazer()
	 * @return menuEditar.getDesfazer()
	 */
	public JMenuItem getDesfazerMenuItem() {
		
		return menuEditar.getDesfazer();
	}
	
	private void abrirAjudaOnline() {
		String url = "http://code.google.com/p/visualalgol/wiki/AjudaGeral";
		if (!java.awt.Desktop.isDesktopSupported()) {
			JOptionPane.showInputDialog("Abra a url: (Erro ajuda 01)", url);
		}
		java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
		if (!desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
			JOptionPane.showInputDialog("Abra a url: (Erro ajuda 02)", url);
		}
		try {
			java.net.URI uri = new java.net.URI(url);
			desktop.browse(uri);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Erro ajuda 03:" + e.getMessage());
		}
	}

	public AbstractButton getColar() {
		return menuEditar.getColar();
	}

	public AbstractButton getCopiar() {
		return menuEditar.getCopiar();
	}

	public AbstractButton getRefazerMenuItem() {
		return menuEditar.getRefazer();
	}
}
