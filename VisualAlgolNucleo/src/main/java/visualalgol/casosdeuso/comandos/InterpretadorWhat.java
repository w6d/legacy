package visualalgol.casosdeuso.comandos;

import org.mozilla.javascript.Scriptable;

import visualalgol.casosdeuso.Ator;
import visualalgol.casosdeuso.Sistema;
import visualalgol.entidades.InstrucaoGenerica;

public class InterpretadorWhat extends InterpretadorDeComandoAbstrato {

	@Override
	public void aoEncerrar() {
		
	}

	@Override
	public String exemplo() {
		return "what plays mp3?";
	}

	@Override
	public void informarComandoExecutado(InstrucaoGenerica instrucao,Scriptable scope, String s) {
	}

	@Override
	public void interpretadorFluxogramaIniciado() {
	}

	@Override
	public void interpretar(Sistema sistema, Ator ator, String textoDigitado)
			throws InterruptedException, EntradaInesperadaException {
		if(textoDigitado.startsWith("what ")){
			sistema.informar("32 components and 1 framework. Wanna play ogg?");
			textoDigitado = ator.digitarTexto();
			sistema.informar("15 artifacts: wanna start audio in arbitrary position?");
			textoDigitado = ator.digitarTexto();
			sistema.informar("8 artifacts: wanna play flv?");
			textoDigitado = ator.digitarTexto();
			sistema.informar("3 artifacts: jlayer, vorbis, JMF.");
			textoDigitado = ator.digitarTexto();
			sistema.informar("import javazoom.jlayer.Sound;\n" +
					"....\n" +
					"Sound sound = new Sound(\"file.mp3\");\n" +
					"sound.play();"
			);
		}
	}

	@Override
	public boolean podeTratar(String comando) {
		return comando.startsWith("what ");
	}

}
