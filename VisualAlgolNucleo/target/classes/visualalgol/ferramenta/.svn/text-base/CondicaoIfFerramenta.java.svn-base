package visualalgol.ferramenta;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;

import visualalgol.casosdeuso.Ator;
import visualalgol.entidades.CondicaoFim;
import visualalgol.entidades.CondicaoIf;
import visualalgol.entidades.InstrucaoGenerica;
import visualalgol.entidades.Linha;

public class CondicaoIfFerramenta extends Ferramenta {

	@Override
	public void mouseClicked(MouseEvent e) {
		if (getInstrucaoEm(e.getX(), e.getY()) != null){
			return;
		}
		removerTodosOsFocos();
		// pegar a linha em x y do mouse
		Linha linha = getLinhaEm(e.getX(), e.getY());
		if(linha!=null){
			CondicaoIf condicaoIf = null;
			if(linha.getPontoTemporario()!=null){//Criar antes deste ponto
				InstrucaoGenerica destino = linha.getDestino(); 
				int x = linha.getPontoTemporario().x;//alinhar o x com o ponto inferior
				int y = e.getY();
				condicaoIf = criarIf(linha, destino,x , y,linha.getPontoTemporario());
			}else{//criar antes do destino
				InstrucaoGenerica destino = linha.getDestino(); 
				int x = destino.getX();//alinhar o x com o destino
				int y = e.getY();
				condicaoIf = criarIf(linha, destino, x, y,null);
			}
			Ator.getInstance().criouInstrucao(condicaoIf);
		}
	}
	private CondicaoIf criarIf(Linha linha, InstrucaoGenerica destino, int x, int y, Point point) {
		//Criar a intrucao if
		CondicaoIf condicaoIf = new CondicaoIf();
		condicaoIf.setX(x);//alinhar o x com o destino
		condicaoIf.setY(y);
		condicaoIf.setW(100);
		condicaoIf.setH(60);
		condicaoIf.setCor(new Color(0xff, 0xf0, 0xf0).getRGB());
		getAlgoritmo().getListComando().add(condicaoIf);
		condicaoIf.setAlgoritmo(getAlgoritmo());
		setArrastando(condicaoIf);
		//indicar a linha de entrada
		condicaoIf.setLinhaEntrada(linha);
		
		//alterar o destino da linha original
		linha.setDestino(condicaoIf);
		
		//criar o end if
		CondicaoFim condicaoFim = new CondicaoFim();
		condicaoFim.setX(x);//alinhar o x com o destino
		if(point==null){
			condicaoFim.setY(destino.getY()-30);//proximo do destino
		}else{
			condicaoFim.setY(point.y-30);//proximo do destino
		}
		condicaoFim.setW(10);
		condicaoFim.setH(10);
		condicaoFim.setCor(new Color(0xff, 0xf0, 0xf0).getRGB());
		getAlgoritmo().getListComando().add(condicaoFim);
		condicaoFim.setAlgoritmo(getAlgoritmo());
		
		Linha linhaVerdadeira = new Linha();
		{//criar a linha para o true
			linhaVerdadeira.setOrigem(condicaoIf);
			linhaVerdadeira.setDestino(condicaoFim);
			getAlgoritmo().getListLinha().add(linhaVerdadeira);
			
			condicaoIf.setLinhaVerdadeira(linhaVerdadeira);
		}
		Linha linhaFalsa = new Linha();
		{//criar a linha para o false
			linhaFalsa.setOrigem(condicaoIf);
			{//criar o desvio do false
				//TODO verificar se colide com outra linha
				//se colidir, colocar a linha para outro local
				linhaFalsa.getListPontos().add(new Point(x+120,y));
				linhaFalsa.getListPontos().add(new Point(x+120,condicaoFim.getY()));
			}
			linhaFalsa.setDestino(condicaoFim);
			getAlgoritmo().getListLinha().add(linhaFalsa);
			
			condicaoIf.setLinhaFalsa(linhaFalsa);
		}
		//ligar o end if ao comando posterior, criando uma linha
		//Linha fimSeAoDestino = new Linha();
		Linha fimSeAoDestino = quebrarLinha(linha);
		fimSeAoDestino.setOrigem(condicaoFim);
		fimSeAoDestino.setDestino(destino);
		getAlgoritmo().getListLinha().add(fimSeAoDestino);
		condicaoFim.setLinhaSaida(fimSeAoDestino);
		condicaoFim.getListLinhaEntrada().add(linhaVerdadeira);
		condicaoFim.getListLinhaEntrada().add(linhaFalsa);
		
		destino.substituirEntrada(linha, fimSeAoDestino);
		return condicaoIf;
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
