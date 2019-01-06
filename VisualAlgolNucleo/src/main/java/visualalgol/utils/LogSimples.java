package visualalgol.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Log simples
 * @author fabio
 *
 */
public class LogSimples {
	private File path;
	public LogSimples() {
	}
	public void apagar(){
		boolean ok = path.delete();
		if(!ok){
			System.out.println("Erro");
		}
	}
	public void append(String texto){
		FileWriter arq = null;
		try {
			arq = new FileWriter(this.path, true);
			arq.write(texto);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				arq.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void setPath(File file) {
		this.path = file;
	}
	public File getPath() {
		return path;
	}
}
