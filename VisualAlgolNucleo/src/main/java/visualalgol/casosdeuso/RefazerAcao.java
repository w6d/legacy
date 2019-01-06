package visualalgol.casosdeuso;

import visualalgol.casosdeuso.historico.Historico;

public class RefazerAcao extends CasoDeUso{
	@Override
	public void executar() {
		Historico.getInstance().refazer();
	}
}
