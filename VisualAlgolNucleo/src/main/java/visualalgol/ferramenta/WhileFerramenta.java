package visualalgol.ferramenta;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;

import visualalgol.casosdeuso.Ator;
import visualalgol.entidades.CondicaoIf;
import visualalgol.entidades.InstrucaoGenerica;
import visualalgol.entidades.ComandoWhile;
import visualalgol.entidades.Linha;

public class WhileFerramenta extends Ferramenta {

	@Override
	public void mouseClicked(MouseEvent e) {
		
		if (getInstrucaoEm(e.getX(), e.getY()) != null){
			return;
		}
		removerTodosOsFocos();
		
		// pegar a linha em x y do mouse
		Linha linha = getLinhaEm(e.getX(), e.getY());
		if(linha!=null){
			ComandoWhile comandoWhile = null;
			if(linha.getPontoTemporario()!=null){//Criar antes deste ponto
				InstrucaoGenerica destino = linha.getDestino(); 
				linha.getListPontos().remove(linha.getPontoTemporario());
				int x = linha.getPontoTemporario().x;//alinhar o x com o ponto inferior
				int y = e.getY();
				comandoWhile = criarWhile(linha, destino,x , y,linha.getPontoTemporario());
			}else{//criar antes do destino
				InstrucaoGenerica destino = linha.getDestino(); 
				int x = destino.getX();//alinhar o x com o destino
				int y = e.getY();
				comandoWhile = criarWhile(linha, destino, x, y,null);
			}
			Ator.getInstance().criouInstrucao(comandoWhile);
		}
	}
	
	/**
	 * 
	 * @param linha linha que foi quebrada em dois
	 * @param destino
	 * @param x
	 * @param y
	 * @param point
	 */
	private ComandoWhile criarWhile(Linha linha, InstrucaoGenerica destino, int x, int y, Point point) {
		//Criar a intrucao While
		ComandoWhile comandoWhile = new ComandoWhile();
		comandoWhile.setX(x);//alinhar o x com o destino
		comandoWhile.setY(y);
		comandoWhile.setW(100);
		comandoWhile.setH(60);
		comandoWhile.setCor(new Color(0xff, 0xf0, 0xf0).getRGB());
		getAlgoritmo().getListComando().add(comandoWhile);
		comandoWhile.setAlgoritmo(getAlgoritmo());
		setArrastando(comandoWhile);
		//indicar a linha de entrada
		comandoWhile.setLinhaEntrada(linha);
		
		//alterar o destino da linha original
		linha.setDestino(comandoWhile);
			
		Linha linhaVerdadeira = new Linha();
		{//criar a linha para o true
			linhaVerdadeira.setOrigem(comandoWhile);
			//TODO criar a volta
			linhaVerdadeira.getListPontos().add(new Point(x,destino.getY()-60));
			linhaVerdadeira.getListPontos().add(new Point(x-80,destino.getY()-60));
			linhaVerdadeira.getListPontos().add(new Point(x-80,y));
			linhaVerdadeira.setDestino(comandoWhile);
			getAlgoritmo().getListLinha().add(linhaVerdadeira);
			comandoWhile.setLinhaVerdadeira(linhaVerdadeira);
			comandoWhile.setLinhaEntradaLoopBack(linhaVerdadeira);
		}
		Linha linhaFalsa = new Linha();
		{//criar a linha para o false
			linhaFalsa.setOrigem(comandoWhile);
			{//criar o desvio do false
				//TODO verificar se colide com outra linha
				//se colidir, colocar a linha para outro local
				linhaFalsa.getListPontos().add(new Point(x+120,y));
				linhaFalsa.getListPontos().add(new Point(x+120,destino.getY()));
			}
			linhaFalsa.setDestino(destino);
			getAlgoritmo().getListLinha().add(linhaFalsa);
			
			comandoWhile.setLinhaFalsa(linhaFalsa);
		}
		
		return comandoWhile;
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
