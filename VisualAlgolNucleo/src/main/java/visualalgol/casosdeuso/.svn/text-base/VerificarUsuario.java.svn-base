package visualalgol.casosdeuso;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

import visualalgol.swing.MainFrame;

/**
 * Verifica se o usuario esta logado
 * se possui uma chave
 * cadastra a chave
 * 
 * @author Fabio Issamu Oshiro
 * 
 */
public class VerificarUsuario extends CasoDeUso {
	@Override
	public void executar() {
		//verificar se existe uma chave no arquivo de configuracao
		String chave = lerChaveNoArquivo();
		if(chave==null){
			//pedir um email e senha
			//ator.
		}
		if(chave!=null){
			try {
				URL url;
				url = new URL("http://www.goals.com.br/"+MainFrame.PROGNAME+"/?key="+chave);
				StringBuffer sb = new StringBuffer();
                InputStream instream = url.openConnection().getInputStream();
                int l;
                byte[] tmp = new byte[2048];
                while ((l = instream.read(tmp)) != -1) {
                    sb.append(new String(tmp).toCharArray(),0,l);
                }
                
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private String lerChaveNoArquivo(){
		String chave=null;
		File arqChave = new File(getPastaDoPrograma(),"chave.txt");
		if(arqChave.exists()){
			try {
				chave = FileUtils.readFileToString(arqChave, "utf-8");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return chave;
	}
}
