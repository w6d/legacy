package visualalgol.casosdeuso.historico;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import visualalgol.casosdeuso.CasoDeUso;
import visualalgol.casosdeuso.SalvarAlgoritmo;
import visualalgol.entidades.Algoritmo;
import visualalgol.entidades.InstrucaoGenerica;

/**
 * TODO lembrar de zerar o historico quando abrir um arquivo
 */
public class Historico extends CasoDeUso{
	
	private static int passo=0;
	
	private static class Holder{
		static Historico instance = new Historico();
	}
	
	private static File getFile(int passo){
		return new File(CasoDeUso.getPastaDoPrograma(),"historico-"+passo);
	}
	
	public static Historico getInstance(){
		return Holder.instance;
	}
	
	public synchronized void limparHistorico(){
		File files[] = getPastaDoPrograma().listFiles();
		for(File f:files){
			if(f.getName().matches("^historico-[0-9]*$")){
				f.delete();
			}
		}
		passo=0;
	}
	
	public synchronized void gravarEstado(){
		File file = getFile(passo);
		passo++;
		SalvarAlgoritmo.salvar(sistema.getAlgoritmo(), file);
	}

	/**
	 * Refazer
	 */
	public synchronized void refazer(){
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			passo++;
			File file = getFile(passo);
			if(file.exists()){
				fis = new FileInputStream(file);
				in = new ObjectInputStream(fis);
				Algoritmo algoritmo = (Algoritmo) in.readObject();
				for(InstrucaoGenerica instrucao:algoritmo.getListComando()){
					instrucao.setFoco(false);//remover o foco, pois rola um problema quando recortamos
				}
				sistema.setAlgoritmo(algoritmo);
				in.close();
			}else{
				passo--;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Desfazer
	 */
	public synchronized void desfazer(){
		if(passo<=0) return;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			passo--;
			File file = getFile(passo);
			if(file.exists()){
				fis = new FileInputStream(file);
				in = new ObjectInputStream(fis);
				Algoritmo algoritmo = (Algoritmo) in.readObject();
				for(InstrucaoGenerica instrucao:algoritmo.getListComando()){
					instrucao.setFoco(false);//remover o foco, pois rola um problema quando recortamos
				}
				sistema.setAlgoritmo(algoritmo);
				in.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
