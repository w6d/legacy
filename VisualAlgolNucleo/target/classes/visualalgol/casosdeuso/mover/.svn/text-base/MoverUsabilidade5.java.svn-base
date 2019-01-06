package visualalgol.casosdeuso.mover;

import java.awt.Point;
import java.util.HashSet;

import visualalgol.entidades.InstrucaoGenerica;

public class MoverUsabilidade5 implements Mover{
	private Integer divisao;
	
	public MoverUsabilidade5() {
	}
	public MoverUsabilidade5(int divisao) {
		this.divisao = divisao;
	}
	HashSet<InstrucaoGenerica> visitados;
	@SuppressWarnings("unchecked")
	@Override
	public void mover(Point arrastandoPonto, InstrucaoGenerica arrastando, int x, int y) {
		if(visitados==null){
			int xCorte = divisao==null?arrastando.getX()-30:divisao;
			visitados = new HashSet<InstrucaoGenerica>();
			for(InstrucaoGenerica aux:arrastando.getAlgoritmo().getListComando()){
				if(aux.getX()<xCorte){
					System.out.println("visitados.add " + aux.getPseudoCodigo());
					visitados.add(aux);
				}
			}
		}
		MoverUsabilidade4 mover = new MoverUsabilidade4();
		mover.setVisitado((HashSet<InstrucaoGenerica>)visitados.clone());
		try {Thread.sleep(50);} catch (InterruptedException e) {}
		mover.mover(null,arrastando, x, y);
	}

}
