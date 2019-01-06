package visualalgol.casosdeuso;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import visualalgol.casosdeuso.historico.Historico;
import visualalgol.entidades.Algoritmo;
import visualalgol.entidades.ArquivoRecente;
import visualalgol.entidades.Fim;
import visualalgol.entidades.Inicio;
import visualalgol.entidades.Linha;
import visualalgol.ferramenta.CondicaoIfFerramenta;
import visualalgol.swing.Messages;

public class IniciarPrograma extends CasoDeUso {

	@Override
	public void executar() {
		sistema.informarNoRodape("Iniciando...");
		sistema.setFerramenta(new CondicaoIfFerramenta());

		// Iniciar a lista de recentes
		ArquivoRecente arquivoRecente = iniciarListaDeRecentes(sistema);
		boolean arquivoAberto = false;
		try {
			if (arquivoRecente.getPaths().size() > 0) {
				AbrirAlgoritmo abrir = new AbrirAlgoritmo();
				abrir.abrirArquivo(arquivoRecente.getPaths().get(0), sistema);
				sistema.informarNoRodape(Messages.getString("aviso.abertoultimoalgoritmo"));
				arquivoAberto = true;
			}
		} catch (FileNotFoundException e) {
			sistema.informarNoRodape("Arquivo inexistente: " + arquivoRecente.getPaths().get(0));
			e.printStackTrace();
		}

		if (!arquivoAberto) {
			criarAlgoritmoVazio(sistema);
			sistema.informarNoRodape(Messages.getString("aviso.algoritmovazio"));
		}

		AtualizarTela atualizarTela = new AtualizarTela();
		atualizarTela.setSistema(sistema);
		atualizarTela.executar();
	}

	public static void criarAlgoritmoVazio(Sistema sistema) {
		Historico.getInstance().setSistema(sistema);
		Historico.getInstance().limparHistorico();
		Algoritmo algoritmo = new Algoritmo();
		sistema.setAlgoritmo(algoritmo);
		sistema.setTitle(null);

		AbrirAlgoritmo.setAlgoritmoAberto(null);

		// criar o inicio e o fim
		Inicio inicio = new Inicio();
		inicio.setX(100);
		inicio.setY(30);
		inicio.setW(24);
		inicio.setH(24);
		inicio.setCor(Color.BLACK.getRGB());
		algoritmo.setComandoInicial(inicio);
		algoritmo.getListComando().add(inicio);

		Fim fim = new Fim();
		fim.setX(100);
		fim.setY(450);
		fim.setW(24);
		fim.setH(24);
		algoritmo.setComandoFinal(fim);
		algoritmo.getListComando().add(fim);

		// ligar o inicio com o fim, conforme decisao de projeto
		Linha linha = new Linha();
		linha.setOrigem(inicio);
		linha.setDestino(fim);
		algoritmo.getListLinha().add(linha);

		inicio.setLinhaSaida(linha);
	}

	private ArquivoRecente iniciarListaDeRecentes(Sistema mainFrame) {
		ArquivoRecente retorno = new ArquivoRecente();
		File file = new File(getPastaDoPrograma(), "recentes.txt");
		if (file.exists()) {
			FileInputStream fis = null;
			ObjectInputStream in = null;
			try {
				fis = new FileInputStream(file);
				in = new ObjectInputStream(fis);
				retorno = (ArquivoRecente) in.readObject();
				mainFrame.setArquivoRecente(retorno);
				in.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			}
		} else {
			mainFrame.setArquivoRecente(retorno);
		}
		return retorno;
	}
}
