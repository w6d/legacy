package visualalgol.ferramenta;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;

import visualalgol.casosdeuso.Ator;
import visualalgol.entidades.ComandoDo;
import visualalgol.entidades.ComandoDoWhile;
import visualalgol.entidades.CondicaoIf;
import visualalgol.entidades.InstrucaoGenerica;
import visualalgol.entidades.Linha;

//TODO colocar um modo para bloquear a criacao de comando na linha verdadeira
public class FacaEnquantoFerramenta extends Ferramenta {

	@Override
	public void mouseClicked(MouseEvent e) {
		if (getInstrucaoEm(e.getX(), e.getY()) != null){
			return;
		}
		removerTodosOsFocos();
		// pegar a linha em x y do mouse
		Linha linha = getLinhaEm(e.getX(), e.getY());
		if(linha!=null){
			ComandoDoWhile comandoDoWhile = null;
			if(linha.getPontoTemporario()!=null){//Criar antes deste ponto
				InstrucaoGenerica destino = linha.getDestino(); 
				int x = linha.getPontoTemporario().x;//alinhar o x com o ponto inferior
				int y = e.getY();
				comandoDoWhile = criarDoWhile(linha, destino,x , y,linha.getPontoTemporario());
			}else{//criar antes do destino
				InstrucaoGenerica destino = linha.getDestino(); 
				int x = destino.getX();//alinhar o x com o destino
				int y = e.getY();
				comandoDoWhile = criarDoWhile(linha, destino, x, y,null);
			}
			Ator.getInstance().criouInstrucao(comandoDoWhile);
		}
	}
	private ComandoDoWhile criarDoWhile(Linha linhaEntrada, InstrucaoGenerica destino, int x, int y, Point point) {
		//Criar a intrucao if
		ComandoDo comandoDo = new ComandoDo();
		ComandoDoWhile comandoDoWhile = new ComandoDoWhile();
		comandoDoWhile.setX(x);//alinhar o x com o destino
		comandoDo.setY(y);
		comandoDoWhile.setW(80);
		comandoDoWhile.setH(60);
		comandoDoWhile.setCor(new Color(0xff, 0xf0, 0xf0).getRGB());
		getAlgoritmo().getListComando().add(comandoDoWhile);
		comandoDoWhile.setAlgoritmo(getAlgoritmo());
		setArrastando(comandoDoWhile);
		
		//alterar o destino da linha original
		linhaEntrada.setDestino(comandoDo);
		
		comandoDo.setX(x);//alinhar o x com o destino
		if(point==null){
			comandoDoWhile.setY(destino.getY()-60);//proximo do destino
		}else{
			comandoDoWhile.setY(point.y-60);//proximo do destino
		}
		comandoDo.setW(10);
		comandoDo.setH(10);
		comandoDo.setCor(new Color(0xff, 0xf0, 0xf0).getRGB());
		getAlgoritmo().getListComando().add(comandoDo);
		comandoDo.setAlgoritmo(getAlgoritmo());
		
		Linha linhaVerdadeira = new Linha();
		{//criar a linha para o true
			linhaVerdadeira.setOrigem(comandoDoWhile);
			linhaVerdadeira.setDestino(comandoDo);
			getAlgoritmo().getListLinha().add(linhaVerdadeira);
			linhaVerdadeira.getListPontos().add(new Point(x-80,comandoDoWhile.getY()));
			linhaVerdadeira.getListPontos().add(new Point(x-80,comandoDo.getY()));
			comandoDoWhile.setLinhaVerdadeira(linhaVerdadeira);
		}
		Linha linhaExec = new Linha();
		{//criar a linha para o false
			linhaExec.setOrigem(comandoDo);
			
			linhaExec.setDestino(comandoDoWhile);
			getAlgoritmo().getListLinha().add(linhaExec);
			
			
		}
		//ligar o end if ao comando posterior, criando uma linha
		Linha linhaFalsa = quebrarLinha(linhaEntrada);
		linhaFalsa.setOrigem(comandoDoWhile);
		linhaFalsa.setDestino(destino);
		
		comandoDoWhile.setLinhaEntrada(linhaExec);
		comandoDoWhile.setLinhaFalsa(linhaFalsa);
		getAlgoritmo().getListLinha().add(linhaFalsa);
		comandoDo.setLinhaSaida(linhaExec);
		comandoDo.getListLinhaEntrada().add(linhaVerdadeira);
		//indicar a linha de entrada
		comandoDo.getListLinhaEntrada().add(linhaEntrada);
		
		destino.substituirEntrada(linhaEntrada, linhaFalsa);
		return comandoDoWhile;
	}
	private void implementacaoAntiga(MouseEvent e){
		if (getInstrucaoEm(e.getX(), e.getY()) == null) {
			CondicaoIf condicaoIf = new CondicaoIf();
			condicaoIf.setX(e.getX());
			condicaoIf.setY(e.getY());
			condicaoIf.setW(100);
			condicaoIf.setH(60);
			condicaoIf.setCor(new Color(0xff, 0xf0, 0xf0).getRGB());
			getAlgoritmo().getListComando().add(condicaoIf);
			condicaoIf.setAlgoritmo(getAlgoritmo());
			setArrastando(condicaoIf);
		}
	}

}
