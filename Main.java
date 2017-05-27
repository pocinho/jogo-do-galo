/**
 * Created by Paulo Pocinho on 05/05/2017.
 */
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        Jogo j = new Jogo();
        char opcao = '\0';

        do {
            System.out.println(j);
            menu();
            opcao = s.nextLine().charAt(0);
            switch (opcao) {
                case 'n':
                    j = new Jogo();
                    break;
                case 's':
                    System.out.println("Adeus, volte sempre!");
                    break;
                case 'j':
                    jogar(j, s);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 's');
    }

    public static void menu() {
        System.out.println("j - jogar; s - sair; n - novo jogo");
    }

    public static void jogar(Jogo j, Scanner s) {
        boolean podeJogar = true;
        System.out.print("Introduza posição linha: ");
        int linha = validarInput(s);
        System.out.print("Introduza posição coluna: ");
        int coluna = validarInput(s);
        try {
            j.jogar(linha, coluna);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            podeJogar = false;
        }
        if (podeJogar && j.vitoria().isEmpty()) {
            j.ia();
        }
        if (!j.vitoria().isEmpty()) {
            System.out.println("O jogador com a marca " + j.vitoria() + " ganhou!");
        }
    }

    public static int validarInput(Scanner s) {
        int resultado = 0;
        do {
            for (;;) {
                if (!s.hasNextInt()) {
                    System.out.println("Input invalido.");
                    s.nextLine();
                } else {
                    resultado = Integer.parseInt(s.nextLine());
                    if (resultado < 1 || resultado > 3)
                        System.out.println("Input invalido.");
                    break;
                }
            }
        } while (resultado < 1 || resultado > 3);

        return resultado;
    }
}
