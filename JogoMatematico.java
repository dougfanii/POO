import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.Random;

public class JogoMatematico {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/* Criação de variáveis */

		int tela = 0;
		// 0 = Jogador está acessando a tela do jogo pela primeira vez
		
		/* Execução do jogo com a chamada da função para pergunta por cada nível*/
	
		if(menu(0) == 1){
			System.out.println("Jogo iniciado!");
			System.out.println("*************************");			

			jogo();
		}else if (tela ==  0 && menu(0) == 0){
			System.exit(0);
		}
	}

	public static int menu(int tela){
		if(tela == 0){
			System.out.println("   Jogo de Matemática	");
			System.out.println("************************");
			System.out.println("* 1 - Novo Jogo *");
			System.out.println("* 2 - Sair *");
			System.out.println("*************************");			
		}
		else if(tela == 1){
			System.out.println("************************");
			System.out.println("* 1 - Novo Jogo *");
			System.out.println("* 2 - Menu *");
			System.out.println("*************************");						
		}
		/* Menu */

		/* Opção Menu*/

		Scanner leitor  = new Scanner(System.in);
		int opcaoMenu  = leitor.nextInt();
		if(opcaoMenu == 1 && tela == 0){
			System.out.println("Novo jogo iniciado!");
			System.out.println("*************************");			

			jogo();
		}else if (opcaoMenu == 2 && tela == 0){
			System.exit(0);
		} 
		return opcaoMenu;	
	}
	
	public static void jogo(){
		int nivel = 1;
		int vidas = 3;
		int rodada = 1;
		int pontos = 0;
		int situacao = 0;
		boolean resposta = false;
		String msg = "";
		while(vidas > 0 && nivel < 5){
			rodada = 1;
			while(rodada < 11 && vidas > 0){
				if(nivel == 1){
					resposta = acerto(nivel, rodada, vidas, pontos, 10, "+-");
				}else if(nivel == 2){
					resposta = acerto(nivel, rodada, vidas, pontos, 10, "*");
				}else if(nivel == 3){
					resposta = acerto(nivel, rodada, vidas, pontos, 10, "/");
				}else if(nivel == 4){
					resposta = acerto(nivel, rodada, vidas, pontos, 100, "+-*/");
				}
				rodada = rodada + 1;
				if(resposta == true){
					pontos = pontos + 10;
				}else if(resposta == false){
					vidas = vidas - 1;
				}
				if(vidas <= 0){
					continue;
				}

				if(rodada == 11){
					nivel = nivel + 1;
				}
				if(vidas <= 0){
					continue;
				}
			}
		}
		if (vidas == 0){
			msg = "Suas vidas acabaram!";
		}else{
			msg = "Parabéns você zerou o jogo";
		}
		System.out.println(msg);
		System.out.println("Pontuação: " + pontos + "!");
		if(menu(1) == 1){
			System.out.println("Novo jogo iniciado!");
			jogo();
		}else{
			menu(0);
		}
	}

	/* Pergunta */
	public static boolean acerto(int nivel, int rodada, int vidas, int pontos, int casa, String operacao){
		double num1 = numero(casa);
		double num2 = numero(casa);
		double n1 = num1;
		double n2 = num2;
		String n1show = Double.toString(num1);
		String n2show = Double.toString(num2);
		n1show = n1show.substring(0,n1show.length()-2);
		n2show = n2show.substring(0,n2show.length()-2);
		char opera = escolher(operacao); 
		double resultado = resultado(num1, opera, num2);
		boolean acerto = false;
		System.out.println("Nível: " + nivel + " | Rodada: " + rodada);
		System.out.println("Vidas: " + vidas + " | Pontuação: " + pontos);
		System.out.println(n1show +" "+  opera  + " " + n2show + " = ?");	
		System.out.println("Sua resposta: ");
		Scanner leitor  = new Scanner(System.in);
		double resposta = leitor.nextDouble();
		DecimalFormat df = new DecimalFormat("#.##");
		String result = df.format(resposta);
		result = result.replace(',', '.');
		resposta = Float.parseFloat(result);	
		if(resposta == resultado){
			acerto = true;
		}else{
			acerto = false;
		}
		return acerto;
	}
	
	public static double resultado(double num1, char operador, double num2){
		double resultado = 0.0;
		if (operador == '+'){
			resultado = num1 + num2;
		}else if (operador == '-'){
			resultado = num1 - num2;
		}else if (operador == '*'){
			resultado = num1 * num2;
		}else if (operador == '/' && num2 == 0){
			resultado = 0;
		}else if(operador == '/' &&  num2 != 0){
			resultado = num1 / num2;
			DecimalFormat df = new DecimalFormat("#.##");
			String result = df.format(resultado);
			result = result.replace(',', '.');
			resultado = Float.parseFloat(result);			
		}
		return resultado;	
	}
	
	/* Função para operação aleatória */
	public static char escolher(String operacoes){
		char operacao;
		Random gerador = new Random();
		operacao = operacoes.charAt(gerador.nextInt(operacoes.length()));
		return operacao;
	}
	
	/* Função para número aleatório */
	public static int numero(int casas){
		int numerox;
		Random gerador = new Random();
		numerox = gerador.nextInt(casas);
		return numerox;
	}	
	
}
