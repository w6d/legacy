package visualalgol.casosdeuso.desenhistas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import org.apache.log4j.Logger;

import visualalgol.entidades.CondicaoFim;
import visualalgol.entidades.InstrucaoGenerica;
import visualalgol.entidades.Linha;

public class DesenharCondicaoFim extends DesenharInicio {
	private static Logger logger = Logger.getLogger(DesenharCondicaoFim.class);

	@Override
	public boolean podeDesenhar(InstrucaoGenerica instrucao) {
		return instrucao instanceof CondicaoFim;
	}
	
	@Override
	public void desenhar(InstrucaoGenerica instrucao, BufferedImage bi) {
		super.desenhar(instrucao, bi);
		Graphics gra = bi.getGraphics();
		CondicaoFim condicaoFim = (CondicaoFim) instrucao;
		gra.setColor(Color.BLACK);
		for(Linha linha:condicaoFim.getListLinhaEntrada()){
			DesenharSeta.desenhar(condicaoFim.getPoligono(), linha, gra);
		}
		if(logger.getLevel()!=null && (logger.getLevel()+"").toString().equals("DEBUG")){
			
			int x = 10;
			gra.setColor(Color.BLACK);
			for (Linha linha : condicaoFim.getListLinhaEntrada()) {
				gra.drawString(linha.getId() + ";", instrucao.getX() + x, 
						instrucao.getY() + 10);
				x += 22;
			}
		}
	}
}
