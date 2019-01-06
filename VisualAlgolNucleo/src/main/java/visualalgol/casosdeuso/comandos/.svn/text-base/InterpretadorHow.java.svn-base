package visualalgol.casosdeuso.comandos;

import org.mozilla.javascript.Scriptable;

import visualalgol.casosdeuso.Ator;
import visualalgol.casosdeuso.Sistema;
import visualalgol.entidades.InstrucaoGenerica;

public class InterpretadorHow extends InterpretadorDeComandoAbstrato{

	@Override
	public void aoEncerrar() {
	}

	@Override
	public String exemplo() {
		return "How to reuse commons-io?";
	}

	@Override
	public void informarComandoExecutado(InstrucaoGenerica instrucao,
			Scriptable scope, String s) {
		
	}

	@Override
	public void interpretadorFluxogramaIniciado() {
		
	}

	@Override
	public void interpretar(Sistema sistema, Ator ator, String textoDigitado)
			throws InterruptedException, EntradaInesperadaException {
		sistema.informar("FileUtils.readFileToString(new File(\"yourfile.txt\"), \"utf-8\");");
	}

	@Override
	public boolean podeTratar(String comando) {
		return comando.startsWith("how ");
	}

}
