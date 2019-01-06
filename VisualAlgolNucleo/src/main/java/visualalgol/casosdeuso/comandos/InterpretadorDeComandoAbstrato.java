package visualalgol.casosdeuso.comandos;

import org.mozilla.javascript.Scriptable;

import visualalgol.casosdeuso.Ator;
import visualalgol.casosdeuso.CasoDeUso;
import visualalgol.casosdeuso.Sistema;
import visualalgol.entidades.InstrucaoGenerica;
import visualalgol.swing.MainFrame;

public abstract class InterpretadorDeComandoAbstrato extends CasoDeUso{
	protected String textoDigitado;
	
	public abstract boolean podeTratar(String comando);
	
	private boolean terminado;
	
	protected String[] tratarEntrada(String entrada){
		entrada = entrada.replaceAll("=+","=");
		entrada = entrada.replace("?", " ? ");
		entrada = entrada.replace("=", " = ");
		entrada = entrada.replaceAll("\\s+"," ");
		return entrada.split("\\s");
	}
	
	public void setTextoDigitado(String textoDigitado) {
		this.textoDigitado = textoDigitado;
	}
	
	public void setSistema(MainFrame sistema) {
		this.sistema = sistema;
	}
	
	@Override
	public final void executarComoThread() throws InterruptedException {
		terminado = false;
		try{
			interpretar(sistema,ator,textoDigitado);
		}catch(Exception e){
			System.err.println("Erro: " + e.getMessage());
		}finally{
			try{
				aoEncerrar();
			}finally{
				terminado = true;
			}
		}
	}
	
	
	public boolean isTerminado() {
		return terminado;
	}
	
	/**
	 * Principal metodo de um interpretador de comandos
	 * @param sistema
	 * @param ator
	 * @param textoDigitado2
	 * @throws InterruptedException
	 * @throws EntradaInesperadaException
	 */
	public abstract void interpretar(Sistema sistema, Ator ator, String textoDigitado) throws InterruptedException, EntradaInesperadaException;
	
	/**
	 * Chamado ao encerrar o comando
	 */
	public abstract void aoEncerrar();
	
	/**
	 * @return Fornece um exemplo de como o usuario digita o comando
	 */
	public abstract String exemplo();
	
	/**
	 * Chamado depois que uma intrucao for executada
	 * @param instrucao instrucao em questao
	 * @param scope escopo de variaveis
	 * @param s texto do comando
	 */
	public abstract void informarComandoExecutado(InstrucaoGenerica instrucao, Scriptable scope, String s);
	
	/**
	 * Chamado quando o interpretador de fluxograma for iniciado
	 */
	public abstract void interpretadorFluxogramaIniciado();
}
