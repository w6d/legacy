package visualalgol.casosdeuso.langs;

import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import visualalgol.entidades.Algoritmo;
import visualalgol.entidades.Variavel;

public class Java extends C {
	private Algoritmo alg;
	
	private int getTipo(String nome){
		for(Variavel var: alg.getVariaveis()){
			if(var.getName().equals(nome)){
				return var.getTipo();
			}
		}
		return 0;
	}
	/* (non-Javadoc)
	 * @see visualalgol.casosdeuso.langs.Linguagem#escreverComando(java.lang.String)
	 */
	public void escreverComando(String comando) {
		if(comando.startsWith("leia ")){
			String var = comando.substring(5);
			String vars[] = var.split(",");
			for(int i=0;i<vars.length;i++){
				var = vars[i];
				//achar o tipo da variavel
							
				int tipo = getTipo(var);
				if(tipo==2){//Real
					outputLang.print("System.out.println(\"Informe um valor para "+var+"\");\n"+var+" = sc.nextFloat();");
				}else if(tipo==3){//inteiro
					outputLang.print("System.out.println(\"Informe um valor para "+var+"\");\n"+var+" = sc.nextInt();");
				}else if(tipo==4){//boolean
					outputLang.print("System.out.println(\"Informe um valor para "+var+" (1=true 0=false)\");\n"+var+" = (sc.nextInt() == 1);");
				}else{//string?
					outputLang.print("System.out.println(\"Informe um valor para "+var+"\");\n"+var+" = sc.next();");
				}
			}
		}else if(comando.startsWith("imprima ")){
			String var = comando.substring(8);//complexo transformar em C
			//achar o tipo da variavel
			int tipo = getTipo(var);
			if(tipo==2){//Real
				outputLang.print("System.out.println("+ var + ");");
			}else if(tipo==3){//inteiro
				outputLang.print("System.out.println("+ var + ");");
			}else{
				outputLang.print("System.out.println("+ var + ");");
			}
			
		}else{
			/*
			 * se houver uma atribuicao e o ultimo comando
			 * terminar com uma divisao com um inteiro, transformar o inteiro para flutuante
			 */
			if(comando.matches("^[a-zA-Z][a-zA-Z0-9]*=.*/[0-9][0-9]*$")){
				comando+=".0";
			}
			comando=comando.replaceAll("=true$", "=1");
			comando=comando.replaceAll("=false$", "=0");
			outputLang.print(comando + ";");
		}
	}

	@Override
	public String getNome() {
		return "Java";
	}

	@Override
	public void getInicio() {
		outputLang.print("public static void main(String[] args) {//Programa principal");
		outputLang.addTab();
		outputLang.print("Scanner sc = new Scanner(System.in);");
	}

	@Override
	public void getFim() {
		outputLang.subTab();
		outputLang.print("}//fim do bloco main");
		outputLang.subTab();
		outputLang.print("}//fim da classe");
	}

	private String getTipo(int x){
		switch (x) {
		case 1:
			return "private static String ";
		case 2:
			return "private static float ";
		case 3:
			return "private static int ";
		case 4:
			return "private static String ";
		default:
			return "??";
		}
	}
	@Override
	public void getCabecalho(Algoritmo alg) {
		this.alg = alg;
		String retorno = "";
		//Organizar por tipo
		for(int i=0;i<Variavel.getTipos().size();i++){
			for(Variavel var:alg.getVariaveis()){
				if(var.getTipo()==i){
					retorno +=getTipo(i) + var.getName();
					retorno+=";\n";
				}
			}
		}
		outputLang.print("import java.util.Scanner;\npublic class PseudoJava{\n");
		outputLang.addTab();
		outputLang.print(retorno);
		
	}

	public String getLinguagemStyle() {
		return SyntaxConstants.SYNTAX_STYLE_JAVA;
	}
}
