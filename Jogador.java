/**
 * Created by Paulo Pocinho on 08/06/2017.
 */
public class Jogador {
    private String nome;
    private Marca marca;
    private boolean vez;
    private int pontos;

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public boolean isVez() {
        return vez;
    }

    public void setVez(boolean vez) {
        this.vez = vez;
    }

    Jogador(String nome, Marca marca) {
        this.nome = nome;
        this.marca = marca;
        this.vez = false;
        this.pontos = 0;
    }
}
