package visualalgol.casosdeuso.desenhistas;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import visualalgol.entidades.InstrucaoGenerica;

/**
 * <h1>Classe que coordena os desenhistas</h1>
 * 
 * Marchand(e) é um termo de origem francesa que designa o profissional que tem
 * como atribuição intervir no processo de distribuição da produção de um
 * artista. Não é dado a ele o direito legal de compra e venda; este
 * profissional é apenas o representante do artista encarregado da intermediação
 * comercial da obra de arte, sendo mais um tipo de corretor especializado ou
 * consultor, que representa, promove e assessora o artista junto ao mercado e
 * as instituições públicas e privadas, museus, fundações culturais e possíveis
 * compradores. É uma profissão para a qual não há formação acadêmica
 * específica. Deriva da prática de intermediação e assessoria, que geralmente
 * demanda certo conhecimento e experiência do mundo da arte e da cultura,
 * incluindo o mercado de arte. No Brasil, segundo a terminologia do Ministerio
 * do Trabalho, o profissional é designado como Agenciador de Obras de Arte, um
 * tipo específico de prestador de serviços autônomo, com registro legal e
 * exigências normativas desenvolvidas pelas prefeituras de cada municipio.
 * 
 * @author Fabio Issamu Oshiro
 * 
 */
public class Marchand {

	private List<Desenhista> pintores = new ArrayList<Desenhista>();
	public Marchand() {
		pintores.add(new DesenharComando());
		pintores.add(new DesenharComandoDo());
		pintores.add(new DesenharComandoDoWhile());
		pintores.add(new DesenharComandoWhile());
		pintores.add(new DesenharCondicaoFim());
		pintores.add(new DesenharCondicaoIf());
		pintores.add(new DesenharFacaEnquanto());
		pintores.add(new DesenharFim());
		pintores.add(new DesenharInicio());
	}
	public void mandarPintar(InstrucaoGenerica instrucao, BufferedImage bi) {
		for(Desenhista desenhista:pintores){
			if(desenhista.podeDesenhar(instrucao)){
				desenhista.desenhar(instrucao, bi);
				break;
			}
		}
	}
}
