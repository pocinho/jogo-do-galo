/**
 * Created by Paulo Pocinho on 05/05/2017.
 */
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        char opcao = '\0';
        JogoGalo j = new JogoGalo();

        System.out.println("Bem-Vindo ao Jogo-do-Galo!");

        do {
            menu();
            opcao = s.nextLine().charAt(0);
            switch (opcao) {
                case 'n':
                    j.iniciar();
                    break;
                case 's':
                    System.out.println("Adeus, volte sempre!");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 's');
    }

    public static void menu() {
        System.out.println("n - novo jogo; s - sair");
    }
}
