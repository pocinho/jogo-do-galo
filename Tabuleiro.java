import com.sun.javaws.exceptions.InvalidArgumentException;

/**
 * Created by Paulo Pocinho on 08/06/2017.
 */
public interface Tabuleiro {
    public Marca obterMarca(int linha, int coluna);
    public void colocarMarca(int linha, int coluna, Marca marca);
    public void limparTabuleiro();
}
