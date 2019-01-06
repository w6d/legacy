package visualalgol.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import visualalgol.casosdeuso.AbrirAlgoritmo;
import visualalgol.casosdeuso.CasoDeUso;
import visualalgol.casosdeuso.CriarComando;
import visualalgol.casosdeuso.CriarCondicao;
import visualalgol.casosdeuso.CriarFacaEnquanto;
import visualalgol.casosdeuso.CriarWhile;
import visualalgol.casosdeuso.DesfazerAcao;
import visualalgol.casosdeuso.EscreverPseudoCodigo;
import visualalgol.casosdeuso.FecharVisuAlgo;
import visualalgol.casosdeuso.Fluxo2PseudoCodigo;
import visualalgol.casosdeuso.IniciarPrograma;
import visualalgol.casosdeuso.InterpretarFluxograma;
import visualalgol.casosdeuso.RefazerAcao;
import visualalgol.casosdeuso.SalvarAlgoritmo;
import visualalgol.casosdeuso.Sistema;
import visualalgol.casosdeuso.comandos.InterpretadorMediador;
import visualalgol.casosdeuso.langs.C;
import visualalgol.casosdeuso.langs.Java;
import visualalgol.casosdeuso.langs.JavaScript;
import visualalgol.casosdeuso.langs.Linguagem;
import visualalgol.casosdeuso.langs.Pascal;
import visualalgol.casosdeuso.langs.Portugol;
import visualalgol.casosdeuso.menueditar.Colar;
import visualalgol.casosdeuso.menueditar.Copiar;
import visualalgol.casosdeuso.menueditar.Recortar;
import visualalgol.entidades.Algoritmo;
import visualalgol.entidades.ArquivoRecente;
import visualalgol.entidades.InstrucaoGenerica;
import visualalgol.ferramenta.CondicaoFimFerramenta;
import visualalgol.ferramenta.EscreverFerramenta;
import visualalgol.ferramenta.Ferramenta;
import visualalgol.ferramenta.LigarBlocosFerramenta;
import visualalgol.swing.console.Console;
import visualalgol.swing.console.OnEnter;

public class MainFrame extends JFrame implements AbrirRecenteListener, Sistema{
	private static final long serialVersionUID = 1L;
	public static final String PROGNAME="VisuAlgo";
	private IconesFluxogramaToolBar iconesFluxogramaToolBar;
	private TelaDesenhoFluxograma telaDesenhoFluxograma;
	private Ferramenta ferramentaAtual;
	private Algoritmo algoritmo;
	private MenuPrincipal menuPrincipal;
	private TelaPseudoCodigo telaPseudoCodigo;
	private EscreverFerramenta escreverFerramenta;
	private Console console;
	private JLabel saidaDialogo;
	private VariaveisView variaveisView;
	public MainFrame() {
		// Instanciando...
		menuPrincipal = new MenuPrincipal();
		this.setJMenuBar(menuPrincipal);
		iconesFluxogramaToolBar = new IconesFluxogramaToolBar();
		telaDesenhoFluxograma = new TelaDesenhoFluxograma();
		menuPrincipal = new MenuPrincipal();
		telaPseudoCodigo = new TelaPseudoCodigo();
		escreverFerramenta = new EscreverFerramenta();
		console = new Console();
		saidaDialogo = new JLabel();
		variaveisView = new VariaveisView();

		// Configurando...
		console.setPersistirEmArquivo(new File(CasoDeUso.getPastaDoPrograma(), "comandos.txt"));
		telaDesenhoFluxograma.addListener(escreverFerramenta);
		menuPrincipal.setAbrirRecenteListener(this);
		
		map2(iconesFluxogramaToolBar.getBtnCondicao(),CriarCondicao.class);
		map2(iconesFluxogramaToolBar.getBtnFacaEnquanto(),CriarFacaEnquanto.class);
		map2(iconesFluxogramaToolBar.getBtnWhile(),CriarWhile.class);
		map2(iconesFluxogramaToolBar.getBtnComando(),CriarComando.class);
		iconesFluxogramaToolBar.getBtnFimDecisao().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFerramenta(new CondicaoFimFerramenta());
			}
		});
		iconesFluxogramaToolBar.getBtnLigarBlocos().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setFerramenta(new LigarBlocosFerramenta());
			}
		});
		iconesFluxogramaToolBar.getBtnEscrever().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setFerramenta(new EscreverFerramenta());
			}
		});
		menuPrincipal.getEscrever().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EscreverPseudoCodigo.utilizar(MainFrame.this,escreverFerramenta.getArrastando());
			}
		});
		map2(menuPrincipal.getRecortar(),Recortar.class);
		map2(menuPrincipal.getColar(),Colar.class);
		map2(menuPrincipal.getCopiar(),Copiar.class);
		map2(menuPrincipal.getSalvarMenuItem(),SalvarAlgoritmo.class);
		map2(menuPrincipal.getAbrirMenuItem(),AbrirAlgoritmo.class);
		map2(menuPrincipal.getVerPseudoCodigo(),Fluxo2PseudoCodigo.class);
		map2(menuPrincipal.getSairMenuItem(),FecharVisuAlgo.class);
		map2(menuPrincipal.getRodar(),InterpretarFluxograma.class);
		map2SemHistorico(menuPrincipal.getDesfazerMenuItem(),DesfazerAcao.class);
		map2SemHistorico(menuPrincipal.getRefazerMenuItem(),RefazerAcao.class);
		menuPrincipal.getNovo().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				IniciarPrograma.criarAlgoritmoVazio(MainFrame.this);
			}
		});
		//linguagens
		adicionarMenuLinguagem(new Portugol());
		adicionarMenuLinguagem(new JavaScript());
		adicionarMenuLinguagem(new Pascal());
		adicionarMenuLinguagem(new Java());
		adicionarMenuLinguagem(new C());
		
		console.addOnEnterListener(new OnEnter() {
			public void textoDigitado(String texto) {
				InterpretadorMediador interpretador = InterpretadorMediador.getInstance();
				interpretador.setTextoDigitado(texto);
				interpretador.setSistema(MainFrame.this);
				try {
					interpretador.executar();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		// Layout
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Console",console);
		tabbedPane.addTab("Variaveis",variaveisView);
		
		JSplitPane splitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,telaPseudoCodigo.getScrollPane(),tabbedPane);
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,new JScrollPane(telaDesenhoFluxograma),splitPane2);
		splitPane.setDividerLocation(.5);
		splitPane.setDividerLocation(500);
		splitPane2.setDividerLocation(.5);
		splitPane2.setDividerLocation(400);
		
		saidaDialogo.setHorizontalAlignment(SwingConstants.RIGHT);
			
		this.setTitle("");
		this.setSize(800, 600);
		
		this.setJMenuBar(menuPrincipal);
		
		iconesFluxogramaToolBar.setOrientation(JToolBar.VERTICAL);
		this.add(iconesFluxogramaToolBar, BorderLayout.WEST);
		
		this.add(splitPane, BorderLayout.CENTER);		
		this.add(saidaDialogo,BorderLayout.SOUTH);

		//Toolkit tool = Toolkit.getDefaultToolkit();
        //this.setSize(tool.getScreenSize());
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		IniciarPrograma iniciarPrograma = new IniciarPrograma();
		iniciarPrograma.setSistema(this);
		iniciarPrograma.executar();
		
		escreverFerramenta.setAlgoritmo(algoritmo);
		escreverFerramenta.setSistema(this);
		
		this.setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	}

	private void map2SemHistorico(AbstractButton abstractButton,
			Class<?> class1) {
		abstractButton.addActionListener(new StrongAdapter(this,class1,false));
	}

	private void map2(AbstractButton abstractButton,Class<?> class1) {
		abstractButton.addActionListener(new StrongAdapter(this,class1));		
	}

	@Override
	public void setTitle(String title) {
		if(title!=null && !title.equals("")){
			super.setTitle(title + " - " + PROGNAME);
		}else{
			super.setTitle(PROGNAME);
		}
	}
	
	
	public void setFerramenta(Ferramenta ferramenta) {
		if (ferramentaAtual != null) {
			ferramentaAtual.setAlgoritmo(null);
			ferramentaAtual.finalizar();
			telaDesenhoFluxograma.removeListener(ferramentaAtual);
		}
		telaDesenhoFluxograma.addListener(ferramenta);
		escreverFerramenta.setAlgoritmo(algoritmo);
		ferramenta.setAlgoritmo(algoritmo);
		ferramenta.setSistema(this);
		ferramentaAtual = ferramenta;
	}

	@Override
	public void setAlgoritmo(Algoritmo algoritmo) {
		this.algoritmo = algoritmo;
		getFerramentaAtual().setAlgoritmo(algoritmo);
		getEscreverFerramenta().setAlgoritmo(algoritmo);
		getVariaveisView().setAlgoritmo(algoritmo);
	}

	/**
	 * @return the iconesFluxogramaToolBar
	 */
	public IconesFluxogramaToolBar getIconesFluxogramaToolBar() {
		return iconesFluxogramaToolBar;
	}

	/**
	 * @param iconesFluxogramaToolBar
	 *            the iconesFluxogramaToolBar to set
	 */
	public void setIconesFluxogramaToolBar(IconesFluxogramaToolBar iconesFluxogramaToolBar) {
		this.iconesFluxogramaToolBar = iconesFluxogramaToolBar;
	}

	/**
	 * @return the telaDesenhoFluxograma
	 */
	public TelaDesenhoFluxograma getTelaDesenhoFluxograma() {
		return telaDesenhoFluxograma;
	}

	/**
	 * @param telaDesenhoFluxograma
	 *            the telaDesenhoFluxograma to set
	 */
	public void setTelaDesenhoFluxograma(TelaDesenhoFluxograma telaDesenhoFluxograma) {
		this.telaDesenhoFluxograma = telaDesenhoFluxograma;
	}

	/**
	 * @return the ferramentaAtual
	 */
	public Ferramenta getFerramentaAtual() {
		return ferramentaAtual;
	}

	/**
	 * @param ferramentaAtual
	 *            the ferramentaAtual to set
	 */
	public void setFerramentaAtual(Ferramenta ferramentaAtual) {
		this.ferramentaAtual = ferramentaAtual;
	}

	/**
	 * @return the algoritmo
	 */
	public Algoritmo getAlgoritmo() {
		return algoritmo;
	}
	public MenuPrincipal getMenuPrincipal() {
		return menuPrincipal;
	}
	public void setMenuPrincipal(MenuPrincipal menuPrincipal) {
		this.menuPrincipal = menuPrincipal;
	}
	public TelaPseudoCodigo getTelaPseudoCodigo() {
		return telaPseudoCodigo;
	}

	@Override
	public void abrirArquivoRecente(String path) {
		AbrirAlgoritmo abrirAlgoritmo = new AbrirAlgoritmo();
		try {
			abrirAlgoritmo.abrirArquivo(path,this);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, "Inexistente " + path);
		}
	}

	public void informar(String string){
		console.write(string);
	}
	
	public void informarNoRodape(String string) {
		saidaDialogo.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
		saidaDialogo.setText(string);
	}
	
	public EscreverFerramenta getEscreverFerramenta() {
		return escreverFerramenta;
	}

	public void apontarPara(InstrucaoGenerica instrucao) {
		if(instrucao==null){
			telaDesenhoFluxograma.apontarPara(-1,-1);
		}else{
			telaDesenhoFluxograma.apontarPara(instrucao.getX(),instrucao.getY());
		}
	}
	
	private void adicionarMenuLinguagem(final Linguagem linguagem ){
		JMenuItem menuItem = new JMenuItem(linguagem.getNome());
		menuPrincipal.getCodigo().add(menuItem);
		menuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Fluxo2PseudoCodigo flux = new Fluxo2PseudoCodigo();
				flux.setLinguagem(linguagem);
				flux.setSistema(MainFrame.this);
				flux.executar();
				telaPseudoCodigo.setLinguagemStyle(linguagem.getLinguagemStyle());
			}
		});
	}
	
	public VariaveisView getVariaveisView() {
		return variaveisView;
	}

	@Override
	public Component getComponent() {
		return this;
	}

	/**
	 * Delegate
	 */
	@Override
	public ArquivoRecente getArquivoRecente() {
		return menuPrincipal.getArquivoRecente();
	}

	/**
	 * Delegate
	 */
	@Override
	public void setArquivoRecente(ArquivoRecente obj) {
		menuPrincipal.setArquivoRecente(obj);
		
	}

	/**
	 * Delegate
	 */
	@Override
	public void setCodigo(String string) {
		telaPseudoCodigo.setText(string);
	}
	
	/**
	 * Delegate
	 */
	@Override
	public void appendCodigo(String string) {
		telaPseudoCodigo.append(string);
	}

	@Override
	public int getTelaFluxogramaHeight() {
		return telaDesenhoFluxograma.getHeight();
	}

	@Override
	public int getTelaFluxogramaWidth() {
		return telaDesenhoFluxograma.getWidth();
	}

	@Override
	public void setFluxogramaImage(BufferedImage bi) {
		telaDesenhoFluxograma.setImage(bi);
	}
}
