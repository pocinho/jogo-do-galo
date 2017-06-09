import com.sun.javaws.exceptions.InvalidArgumentException;

/**
 * Created by Paulo Pocinho on 08/06/2017.
 */
public class TabuleiroGalo implements Tabuleiro {
    private Marca[][] tabuleiro;

    TabuleiroGalo() {
        tabuleiro = new Marca[3][3];
        limparTabuleiro();
    }

    @Override
    public Marca obterMarca(int linha, int coluna) {
        return tabuleiro[linha][coluna];
    }

    @Override
    public void colocarMarca(int linha, int coluna, Marca marca) {
        tabuleiro[linha][coluna] = marca;
    }

    @Override
    public void limparTabuleiro() {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                tabuleiro[i][j] = Marca.VAZIO;
            }
        }
    }
}
