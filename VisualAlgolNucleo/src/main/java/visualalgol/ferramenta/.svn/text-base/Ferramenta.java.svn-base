package visualalgol.ferramenta;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.List;

import visualalgol.casosdeuso.Sistema;
import visualalgol.casosdeuso.historico.Historico;
import visualalgol.casosdeuso.mover.Mover;
import visualalgol.casosdeuso.mover.MoverUsabilidade3;
import visualalgol.casosdeuso.mover.MoverUsabilidade5;
import visualalgol.entidades.Algoritmo;
import visualalgol.entidades.Comando;
import visualalgol.entidades.InstrucaoGenerica;
import visualalgol.entidades.Linha;

public abstract class Ferramenta implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {
	private Algoritmo algoritmo;
	private InstrucaoGenerica arrastando;
	private Point arrastandoPonto;
	protected Linha linha;
	protected Sistema sistema;
	
	private Mover mover = new MoverUsabilidade3();
	
	private int ultimoX, ultimoY;
	
	public void finalizar(){
		
	}
	
	public Algoritmo getAlgoritmo() {
		return algoritmo;
	}

	public void setAlgoritmo(Algoritmo algoritmo) {
		this.algoritmo = algoritmo;
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	private boolean salvarHistoricoMovimento=true;
	@Override
	public final void mouseReleased(MouseEvent e) {
		salvarHistoricoMovimento=true;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

	}

	private Mover moverDireita = new MoverUsabilidade5();
	@Override
	public void mouseDragged(MouseEvent e) {
		if(salvarHistoricoMovimento){
			Historico.getInstance().gravarEstado();
			salvarHistoricoMovimento=false;
		}
		int difX = e.getX() - ultimoX;
		int difY = e.getY() - ultimoY;
		if(mouseButtonPressed==MouseEvent.BUTTON3){//nao retorna direito o e.getButton(), por isso guardamos o mouseButtonPressed
			moverDireita.mover(arrastandoPonto, arrastando, difX, difY);
		}else{
			mover.mover(arrastandoPonto, arrastando, difX, difY);
		}
		ultimoX = e.getX();
		ultimoY = e.getY();
	}

	private int mouseButtonPressed=0;
	
	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		// desenhar um quadrado do hit do mouse
		Polygon p = new Polygon();
		p.addPoint(x - 5, y - 5);
		p.addPoint(x + 5, y - 5);
		p.addPoint(x + 5, y + 5);
		p.addPoint(x - 5, y + 5);

		// listar os join points das linhas
		for (Linha lin : getAlgoritmo().getListLinha()) {
			for (Point point : lin.getListPontos()) {
				if (p.contains(point)) {
					arrastandoPonto = point;
					ultimoX = x;
					ultimoY = y;
					setArrastando(null);
					linha = lin;
					return;
				}
			}
		}
		arrastandoPonto = null;
		
		ultimoX = e.getX();
		ultimoY = e.getY();
		// verificar se esta selecionando um dos comandos
		setArrastando(getInstrucaoEm(ultimoX, ultimoY));
		mouseButtonPressed = e.getButton();
		if(e.getButton()==MouseEvent.BUTTON3 && arrastando!=null){
			moverDireita = new MoverUsabilidade5(arrastando.getX());
			//Botao direito
			if(arrastando instanceof Comando){
				// abrir editor de comando?
			}
		}
	}

	public Linha getLinhaEm(int x, int y){
		Rectangle rec = new Rectangle(x-5,y-5,10,10);
		List<Linha> linhas = getAlgoritmo().getListLinha();
		for(Linha linha:linhas){
			Point ultimo = new Point(linha.getOrigem().getX(),linha.getOrigem().getY());
			List<Point> pontos = linha.getListPontos();
			for(Point ponto:pontos){
				if(rec.intersectsLine(ultimo.x, ultimo.y, ponto.x, ponto.y)){
					//Colocar a instrucao aqui
					linha.setPontoTemporario(ponto);
					return linha;
				}
				ultimo = ponto;
			}
			Point pontoDest = new Point(linha.getDestino().getX(),linha.getDestino().getY());
			if(rec.intersectsLine(ultimo.x, ultimo.y, pontoDest.x, pontoDest.y)){
				//Colocar a instrucao aqui
				linha.setPontoTemporario(null);
				return linha;
			}
		}
		return null;
	}
	
	public InstrucaoGenerica getInstrucaoEm(int x, int y) {
		List<InstrucaoGenerica> instrucoes = getAlgoritmo().getListComando();
		for (int i = instrucoes.size() - 1; i >= 0; i--) {
			InstrucaoGenerica instrucao = instrucoes.get(i);
			if (instrucao.getPoligono().contains(x, y)) {
				return instrucao;
			}
		}
		return null;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_DELETE) {
			if (linha != null && arrastandoPonto != null) {
				Historico.getInstance().gravarEstado();
				linha.getListPontos().remove(arrastandoPonto);
			}else if (arrastando != null) {
				Historico.getInstance().gravarEstado();
				arrastando.delete();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	/**
	 * @return the arrastando
	 */
	public InstrucaoGenerica getArrastando() {
		return arrastando;
	}

	/**
	 * @param arrastando the arrastando to set
	 */
	public void setArrastando(InstrucaoGenerica arrastando) {
		if(this.arrastando!=null)this.arrastando.setFoco(false);
		if(arrastando!=null) arrastando.setFoco(true);
		this.arrastando = arrastando;
	}
	
	/**
	 * Quebra a linha em duas no pontoTemporario
	 * @param linhaEntrada linha
	 * @return novaLinha
	 */
	public Linha quebrarLinha(Linha linhaEntrada){
		Linha linhaSaida = new Linha();
		for(int i=linhaEntrada.getListPontos().size()-1;i>=0;i--){
			Point point = linhaEntrada.getListPontos().get(i);
			if(!linhaEntrada.getListPontos().remove(point)) throw new RuntimeException("Nao removido");
			linhaSaida.getListPontos().add(0,point);
			if(point==linhaEntrada.getPontoTemporario()){
				break;
			}
		}
		return linhaSaida;
	}

	
	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}
	
	protected void removerTodosOsFocos() {
		for(InstrucaoGenerica instrucao:sistema.getAlgoritmo().getListComando()){
			instrucao.setFoco(false);
		}
	}
}
