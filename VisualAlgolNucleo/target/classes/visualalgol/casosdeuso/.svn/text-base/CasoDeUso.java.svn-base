package visualalgol.casosdeuso;

import java.io.File;

public abstract class CasoDeUso {
	protected Sistema sistema;
	private static File pastaDoPrograma;
	protected Ator ator = Ator.getInstance();
	private static Thread thread = null;
	static {
		File f=new File(System.getProperty("user.home"));
		pastaDoPrograma = new File(f,".visualgo");
		if(!pastaDoPrograma.exists()){
			pastaDoPrograma.mkdirs();
		}
	}
	
	/**
	 * Pasta para guardar configuracoes do programa
	 * @return "user.home" + ".visualgo"
	 */
	public static File getPastaDoPrograma() {
		return pastaDoPrograma;
	}
	
	public void executarComoThread() throws InterruptedException{
		
	}
	
	public void executar(){
		if(thread!=null){
			thread.interrupt();
		}
		thread = new Thread(){
			@Override
			public void run() {
				try {
					executarComoThread();
				} catch (InterruptedException e) {
					System.out.println("Abortado...");
				}
			}
		};
		thread.start();
	}
	public final void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}
}
