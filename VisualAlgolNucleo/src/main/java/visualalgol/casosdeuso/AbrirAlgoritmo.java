package visualalgol.casosdeuso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import visualalgol.casosdeuso.historico.Historico;
import visualalgol.entidades.Algoritmo;
import visualalgol.entidades.InstrucaoGenerica;
import visualalgol.entidades.Linha;

public class AbrirAlgoritmo extends SalvarAlgoritmo {
	private static File algoritmoAberto;

	@Override
	public void executar() {
		int returnVal = fc.showOpenDialog(sistema.getComponent());
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			try {
				abrirArquivo(file, sistema);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(sistema.getComponent(), "Erro: Arquivo inexistente.");
			}
		}
	}

	private void abrirArquivo(File file, Sistema sistema) throws FileNotFoundException{
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(file);
			in = new ObjectInputStream(fis);
			Algoritmo algoritmo = (Algoritmo) in.readObject();
			
			in.close();
			
			//lembrar quem foi o ultimo aberto
			algoritmoAberto = file;

			{//limpar execucao
				for(InstrucaoGenerica instrucao:algoritmo.getListComando()){
					instrucao.setExecutado(false);
					instrucao.setFoco(false);//remover o foco, pois rola um problema quando recortamos
				}
				for(Linha linha:algoritmo.getListLinha()){
					linha.setExecutado(false);
					linha.setId(null);
				}
				Historico.getInstance().setSistema(sistema);
				Historico.getInstance().limparHistorico();
			}
			sistema.setAlgoritmo(algoritmo);
			sistema.setTitle(file.getPath());
			
			
			// Colocar na lista de recentes
			List<String> lista = sistema.getArquivoRecente().getPaths();
			//remove uma possivel entrada duplicada
			lista.remove(file.getAbsolutePath());
			//coloca em primeiro
			lista.add(0, file.getAbsolutePath());
			// retirar o excesso
			for (int i = 10; i < lista.size(); i++) {
				lista.remove(i);
			}
			salvarRecentes(sistema);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch(FileNotFoundException e){
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
		}catch(ClassCastException e){
			e.printStackTrace();
		}
	}

	private void salvarRecentes(Sistema sistema) {
		File file = new File(getPastaDoPrograma(), "recentes.txt");
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(file);
			out = new ObjectOutputStream(fos);
			out.writeObject(sistema.getArquivoRecente());
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void abrirArquivo(String path, Sistema sistema) throws FileNotFoundException {
		abrirArquivo(new File(path), sistema);
	}

	public static File getAlgoritmoAberto() {
		return algoritmoAberto;
	}
	public static void setAlgoritmoAberto(File algoritmoAberto) {
		AbrirAlgoritmo.algoritmoAberto = algoritmoAberto;
	}
}
