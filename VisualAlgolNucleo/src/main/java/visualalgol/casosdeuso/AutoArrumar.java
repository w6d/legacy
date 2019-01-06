package visualalgol.casosdeuso;

import java.awt.Rectangle;

import visualalgol.casosdeuso.mover.MoverUsabilidade5;
import visualalgol.entidades.InstrucaoGenerica;

/**
 * Tenta auto posicionar os desenhos depois de gerar um codigo 
 *
 */
public class AutoArrumar extends CasoDeUso{
	private InstrucaoGenerica instrucao;
	public void setInstrucao(InstrucaoGenerica instrucao) {
		this.instrucao = instrucao;
	}
	@Override
	public void executar() {
		int x = instrucao.getX()-instrucao.getW()/2;
		int y = instrucao.getY()-instrucao.getH()/2;
		Rectangle rec = new Rectangle(x,y,instrucao.getW(),instrucao.getH());
		
		Integer xCorte = null;
		InstrucaoGenerica batidoEm = null;
		//verificar se colidiu com alguem da esquerda
		for(InstrucaoGenerica aux:sistema.getAlgoritmo().getListComando()){
			if(aux.getX()<instrucao.getX() && aux!=instrucao){
				if(aux.getPoligono().intersects(rec)){
					//bateu!! Definir o ponto para cortar
					xCorte = instrucao.getX()-10;
					batidoEm = aux;
					System.out.println("batidoEm " + batidoEm.getPseudoCodigo());
					System.out.println("xCorte " + xCorte);
					break;
				}
			}
		}
		
		if(xCorte!=null){//bateu
			MoverUsabilidade5 mover = new MoverUsabilidade5(xCorte);
			for(int i=0;i<5;i++){
				mover.mover(null, instrucao, 10, 0);
			}
		}
		
		//verificar se colidiu com alguem da direita
	}
	public static void utilizar(Sistema sistema, InstrucaoGenerica instrucao){
		AutoArrumar autoArrumar = new AutoArrumar();
		autoArrumar.sistema = sistema;
		autoArrumar.instrucao = instrucao;
		autoArrumar.executar();
	}
}
