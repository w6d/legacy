package visualalgol.casosdeuso.comandos;

import java.util.ArrayList;
import java.util.List;

import org.mozilla.javascript.Scriptable;

import visualalgol.casosdeuso.Ator;
import visualalgol.casosdeuso.CasoDeUso;
import visualalgol.entidades.InstrucaoGenerica;


/**
 * Mediador dos outros interpretadores
 */
public class InterpretadorMediador extends CasoDeUso{
	private String textoDigitado;
	private InterpretadorDeComandoAbstrato interpretadorAtual = null;
	/**
	 * Chain, corrente de interpretadores 
	 */
	private List<InterpretadorDeComandoAbstrato> listaDeInterpretadores = new ArrayList<InterpretadorDeComandoAbstrato>();
	
	private InterpretadorMediador(){
		listaDeInterpretadores.add(new InterpretarWhy());
		listaDeInterpretadores.add(new InterpretarWhen());
		listaDeInterpretadores.add(new InterpretadorWho());
		listaDeInterpretadores.add(new InterpretadorWhat());
		listaDeInterpretadores.add(new InterpretadorWhere());
		listaDeInterpretadores.add(new InterpretadorHow());
	}
	
	
	@Override
	public void executar(){
		boolean interpretado = false;
		// lembrar quem foi o ultimo e jogar para ele antes de jogar para os outros
		if(interpretadorAtual!=null){
			if(interpretadorAtual.isTerminado()){//se terminou de interpretar
				interpretadorAtual=null;
			}else{
				try{
					interpretadorAtual.setTextoDigitado(textoDigitado);
					Ator.getInstance().digitouTexto(textoDigitado);
					interpretado = true;
				}catch(Exception e){
					interpretadorAtual.aoEncerrar();
					interpretadorAtual=null;
					System.err.println("Erro: " + e.getMessage());
				}
			}
		}
		if(!interpretado){
			for (InterpretadorDeComandoAbstrato interpretador : listaDeInterpretadores) {
				if(interpretador.podeTratar(textoDigitado)){
					interpretador.setSistema(sistema);
					interpretador.setTextoDigitado(textoDigitado);
					interpretador.executar();//Abre outra thread
					interpretado = true;
					interpretadorAtual = interpretador;
				}
			}
		}
		if(!interpretado && textoDigitado!=null && !textoDigitado.trim().equals("")){
			//informar exemplos de comandos
			sistema.informar("Nothing to do, please try: ");
			for (InterpretadorDeComandoAbstrato interpretador : listaDeInterpretadores) {
				sistema.informar("    " + interpretador.exemplo());
			}
		}
	}
	
	public void setTextoDigitado(String textoDigitado) {
		this.textoDigitado = textoDigitado;
	}
	
	/**
	 * Anti lazy loader overhead
	 */
	private static class Holder{
		static InterpretadorMediador instance = new InterpretadorMediador();
	}
	
	public static InterpretadorMediador getInstance(){
		return Holder.instance; 
	}
	
	/**
	 * Repassa para os interpretadores
	 * @param instrucao
	 * @param scope
	 * @param s
	 */
	public void informarComandoExecutado(InstrucaoGenerica instrucao, Scriptable scope, String s) {
		for (InterpretadorDeComandoAbstrato interpretador : listaDeInterpretadores) {
			interpretador.informarComandoExecutado(instrucao, scope, s);
		}
	}

	public void interpretadorFluxogramaIniciado() {
		for (InterpretadorDeComandoAbstrato interpretador : listaDeInterpretadores) {
			interpretador.interpretadorFluxogramaIniciado();
		}
	}


	public void informarVariavelAlterada(String name, Object object, Object value) {
		
	}
}
