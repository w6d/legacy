import java.util.Scanner;
public class PseudoJava{

	private static float p1;
	private static float p2;
	private static float media;
	private static float p3;
	
	public static void main(String[] args) {//Programa principal
		Scanner sc = new Scanner(System.in);
		System.out.println("Informe um valor para p1");
		p1 = sc.nextFloat();
		System.out.println("Informe um valor para p2");
		p2 = sc.nextFloat();
		media = (p1+p2)/2;
		if (media>=7){
			System.out.println("aprovado");
		}else{
			if (0 < 1){
				System.out.println("Informe um valor para p3");
				p3 = sc.nextFloat();
				media = (media+p3)/2;
				if (media>=6){
					System.out.println("aprovado");
				}else{
					System.out.println("reprovado");
				}//fim de condicao
			}else{
				System.out.println("reprovado");
			}//fim de condicao
		}//fim de condicao
		System.out.println("sua media foi " + media);
	}//fim do bloco main
}//fim da classe
