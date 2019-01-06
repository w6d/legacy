package visualalgol.casosdeuso;

import javax.swing.JOptionPane;

import visualalgol.entidades.InstrucaoGenerica;

public class Ator {
	private static Ator instance;
	private boolean aguardandoCriarInstrucao = false;
	private boolean aguardandoDigitarTexto = false;
	private String textoDigitado;
	private InstrucaoGenerica instrucao;

	public static Ator getInstance() {
		if (instance == null)
			instance = new Ator();
		return instance;
	}

	public InstrucaoGenerica criarInstrucao() throws InterruptedException {
		aguardandoCriarInstrucao = true;
		synchronized (this) {
			this.wait();
		}
		return this.instrucao;
	}

	public void criouInstrucao(InstrucaoGenerica instrucao) {
		synchronized (this) {
			if (aguardandoCriarInstrucao)
				this.notify();
			aguardandoCriarInstrucao = false;
			this.instrucao = instrucao;
		}
	}

	public String digitarTexto() throws InterruptedException{
		aguardandoDigitarTexto = true;
		synchronized (this) {
			this.wait();
		}
		return this.textoDigitado;
	}
	
	public void digitouTexto(String textoDigitado) {
		this.textoDigitado = textoDigitado;
		synchronized (this) {
			if (aguardandoDigitarTexto){
				this.notify();
			}else{
				throw new RuntimeException("Nao estava aguardando texto");
			}
			aguardandoDigitarTexto = false;
		}
	}

	public String digitarPseudoCodigo(InstrucaoGenerica instrucao) {
		return JOptionPane.showInputDialog("Digite o pseudo codigo",instrucao.getPseudoCodigo());
	}
}
