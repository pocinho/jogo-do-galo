/**
 * Created by Paulo Pocinho on 04/05/2017.
 */

import java.util.Random;

public class Jogo {
    private Marca[][] tabuleiro;

    public Jogo() {
        tabuleiro = new Marca[3][3];
    }

    public void jogar(int linha, int coluna) throws IllegalArgumentException {
        int posLinha = linha - 1;
        int posColuna = coluna - 1;
        if (tabuleiro[posLinha][posColuna] != null)
            throw new IllegalArgumentException("Posição ocupada.");
        else
            tabuleiro[posLinha][posColuna] = Marca.X;
    }

    public void ia() {
        int linha = 0;
        int coluna = 0;
        do {
            Random r = new Random();
            linha = r.nextInt(3);
            coluna = r.nextInt(3);
        } while (tabuleiro[linha][coluna] != null);
        tabuleiro[linha][coluna] = Marca.O;
    }

    @Override
    public String toString() {
        String resultado = "\t1\t\t2\t\t3\n";

        for (int l = 0; l < 3; ++l) {
            resultado += (l+1) + " ";
            for (int c = 0; c < 3; ++c) {
                if (tabuleiro[l][c] != null) {
                    resultado += "\t" + tabuleiro[l][c].name() + "\t";
                    if (c == 0 || c == 1)
                        resultado += "|";
                }
                else {
                    resultado += "\t" + " " + "\t";
                    if (c == 0 || c == 1)
                        resultado += "|";
                }
            }
            if (l == 0 || l == 1)
                resultado += "\n---------------------------\n";
            else
                resultado += "\n";
        }

        return resultado;
    }
}
