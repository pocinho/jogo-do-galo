/**
 * Created by Paulo Pocinho on 04/05/2017.
 */

import java.util.Random;
import java.util.Scanner;

public class JogoGalo implements Jogo {
    private TabuleiroGalo tabuleiro;
    private Jogador jogador1;
    private Jogador jogador2;
    private boolean ai;
    private boolean melhorDeTres;
    private boolean vitoria;
    private Scanner s = new Scanner(System.in);

    public JogoGalo() {
        tabuleiro = new TabuleiroGalo();
        ai = false;
        melhorDeTres = false;
        vitoria = false;
    }

    public void iniciar() {
        int tipoJogo = 0;
        String nome1 = "";
        String marca1 = "";
        String nome2 = "";
        String marca2 = "";

        do {
            System.out.println("Opções disponíveis:");
            System.out.println("1. Melhor de 3 - Player vs Player");
            System.out.println("2. Melhor de 3 - Player vs AI");
            System.out.println("3. Simples - Player vs Player");
            System.out.println("4. Simples - Player vs AI");
            if (s.hasNextInt())
                tipoJogo = Integer.parseInt(s.nextLine());
        } while (tipoJogo < 1 || tipoJogo > 4);

        System.out.println("Introduza o nome do primeiro jogador:");
        nome1 = s.nextLine();

        do {
            System.out.println("Escolha a marca do primeiro jogador:");
            marca1 += s.nextLine().charAt(0);
        } while (!marca1.equals("X") && !marca1.equals("O"));

        jogador1 = new Jogador(nome1, Marca.valueOf(marca1));

        if (tipoJogo == 1 || tipoJogo == 3) {
            System.out.println("Introduza o nome do segundo jogador:");
            nome2 = s.nextLine();
            if (marca1.equalsIgnoreCase("x"))
                marca2 += "O";
            else
                marca2 += "X";

            jogador2 = new Jogador(nome2, Marca.valueOf(marca2));

        } else {
            if (marca1.equalsIgnoreCase("X"))
                marca2 += "O";
            else
                marca2 += "X";

            jogador2 = new Jogador("IA", Marca.valueOf(marca2));
        }
        Random r = new Random();
        int vez = r.nextInt(2);
        if (vez == 1) {
            jogador1.setVez(true);
        } else {
            jogador2.setVez(true);
        }
        switch (tipoJogo) {
            case 1:
                jogarPvp(3);
                break;
            case 2:
                jogarIa(3);
                break;
            case 3:
                jogarPvp(1);
                break;
            case 4:
                jogarIa(1);
                break;
            default:
                System.out.println("Algo correu mal ao iniciar o jogo.");
                break;
        }
    }

    private void jogarPvp(int jogadas) {
        int totalJogos = 1;
        int numeroDeJogos = jogadas;
        int jogadasDisponiveis = 9;

        while (numeroDeJogos > 0) {
            while (vitoria().isEmpty()) {
                System.out.println("Ronda " + numeroDeJogos);
                System.out.println(imprimirTabuleiro());
                int linha = 0;
                int coluna = 0;
                String nome = "";
                Marca marca = Marca.VAZIO;
                if (jogador1.isVez()) {
                    nome = jogador1.getNome();
                    marca = jogador1.getMarca();
                    jogador1.setVez(false);
                    jogador2.setVez(true);
                } else {
                    nome = jogador2.getNome();
                    marca = jogador2.getMarca();
                    jogador1.setVez(true);
                    jogador2.setVez(false);
                }
                System.out.println("Jogador: " + nome);
                System.out.println("Introduza posição linha: ");
                linha = validarInput(s);
                System.out.println("Introduza posição coluna: ");
                coluna = validarInput(s);
                try {
                    validarJogada(linha, coluna, marca);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    if (jogador1.isVez()) {
                        jogador1.setVez(false);
                        jogador2.setVez(true);
                    } else {
                        jogador1.setVez(true);
                        jogador2.setVez(false);
                    }
                }
            }
            System.out.println(imprimirTabuleiro());
            String nome = "";
            if (jogador1.getMarca().toString().equals(vitoria())) {
                nome = jogador1.getNome();
                jogador1.setPontos(jogador1.getPontos() + 1);
                System.out.println("O jogador " + nome + " com a marca " + vitoria() + " ganhou a ronda " + totalJogos + " de " + jogadas + " com " + jogador1.getPontos() + " pontos!\n");
            } else if (jogador2.getMarca().toString().equals(vitoria())) {
                nome = jogador2.getNome();
                jogador2.setPontos(jogador2.getPontos() + 1);
                System.out.println("O jogador " + nome + " com a marca " + vitoria() + " ganhou a ronda " + totalJogos + " de " + jogadas + " com " + jogador2.getPontos() + " pontos!\n");
            } else if (jogadasDisponiveis == 0) {
                System.out.println("Empate.");
                jogadasDisponiveis = 9;
            }
            tabuleiro.limparTabuleiro();
            totalJogos++;
            numeroDeJogos--;
        }
        if (jogador1.getPontos() > jogador2.getPontos()) {
            System.out.println("O Jogador " + jogador1.getNome() + " ganhou!");
        } else if (jogador1.getPontos() < jogador2.getPontos()) {
            System.out.println("O Jogador " + jogador2.getNome() + " ganhou!");
        } else {
            System.out.println("Empate.");
        }
    }

    private void jogarIa(int jogadas) {
        int totalJogos = 1;
        int numeroDeJogos = jogadas;
        int jogadasDisponiveis = 9;

        while (numeroDeJogos > 0) {
            while (vitoria().isEmpty() && jogadasDisponiveis > 0) {
                System.out.println("Ronda " + numeroDeJogos);
                System.out.println(imprimirTabuleiro());
                int linha = 0;
                int coluna = 0;
                String nome = "";
                Marca marca = Marca.VAZIO;
                if (jogador1.isVez()) {
                    nome = jogador1.getNome();
                    marca = jogador1.getMarca();
                    jogador1.setVez(false);
                    jogador2.setVez(true);
                } else {
                    nome = jogador2.getNome();
                    marca = jogador2.getMarca();
                    jogador1.setVez(true);
                    jogador2.setVez(false);
                }

                if (!jogador1.isVez()) {
                    System.out.println("Jogador: " + nome);
                    System.out.println("Introduza posição linha: ");
                    linha = validarInput(s);
                    System.out.println("Introduza posição coluna: ");
                    coluna = validarInput(s);
                    try {
                        validarJogada(linha, coluna, marca);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        if (jogador1.isVez()) {
                            jogador1.setVez(false);
                            jogador2.setVez(true);
                        } else {
                            jogador1.setVez(true);
                            jogador2.setVez(false);
                        }
                    }
                } else {
                    jogador1.setVez(true);
                    jogador2.setVez(false);
                    ia(jogador2.getMarca());
                }
                jogadasDisponiveis--;
            }
            System.out.println(imprimirTabuleiro());
            String nome = "";
            if (jogador1.getMarca().toString().equals(vitoria())) {
                nome = jogador1.getNome();
                jogador1.setPontos(jogador1.getPontos() + 1);
                System.out.println("O jogador " + nome + " com a marca " + vitoria() + " ganhou a ronda " + totalJogos + " de " + jogadas + " com " + jogador1.getPontos() + " pontos!\n");
            } else if (jogador2.getMarca().toString().equals(vitoria())) {
                nome = jogador2.getNome();
                jogador2.setPontos(jogador2.getPontos() + 1);
                System.out.println("O jogador " + nome + " com a marca " + vitoria() + " ganhou a ronda " + totalJogos + " de " + jogadas + " com " + jogador2.getPontos() + " pontos!\n");
            } else if (jogadasDisponiveis == 0){
                System.out.println("Empate.");
                jogadasDisponiveis = 9;
            }
            tabuleiro.limparTabuleiro();
            totalJogos++;
            numeroDeJogos--;
        }
        if (jogador1.getPontos() > jogador2.getPontos()) {
            System.out.println("O Jogador " + jogador1.getNome() + " ganhou!");
        } else if (jogador1.getPontos() < jogador2.getPontos()) {
            System.out.println("O Jogador " + jogador2.getNome() + " ganhou!");
        } else {
            System.out.println("Empate.");
        }
    }

    private void validarJogada(int linha, int coluna, Marca m) throws IllegalArgumentException {
        int posLinha = linha - 1;
        int posColuna = coluna - 1;
        if (jogadaValida(posLinha, posColuna) && tabuleiro.obterMarca(posLinha, posColuna) == Marca.VAZIO)
            tabuleiro.colocarMarca(posLinha, posColuna, m);
        else
            throw new IllegalArgumentException("Posição inválida.");
    }

    private String avaliarLinhas() {
        String resultado = "";
        for (int i = 0; i < 3; ++i) {
            if (tabuleiro.obterMarca(i, 0) == Marca.O && tabuleiro.obterMarca(i, 1) == Marca.O && tabuleiro.obterMarca(i, 2) == Marca.O) {
                resultado = "O";
                break;
            } else if (tabuleiro.obterMarca(i, 0) == Marca.X && tabuleiro.obterMarca(i, 1) == Marca.X && tabuleiro.obterMarca(i, 2) == Marca.X) {
                resultado = "X";
                break;
            }
        }
        return resultado;
    }

    private String avaliarColunas() {
        String resultado = "";
        for (int i = 0; i < 3; ++i) {
            if (tabuleiro.obterMarca(0, i) == Marca.O && tabuleiro.obterMarca(1, i) == Marca.O && tabuleiro.obterMarca(2, i) == Marca.O) {
                resultado = "O";
                break;
            } else if (tabuleiro.obterMarca(0, i) == Marca.X && tabuleiro.obterMarca(1, i) == Marca.X && tabuleiro.obterMarca(2, i) == Marca.X) {
                resultado = "X";
                break;
            }
        }
        return resultado;
    }

    private String avaliarDiagonais() {
        String resultado = "";
        if (tabuleiro.obterMarca(0, 0) == Marca.O && tabuleiro.obterMarca(1, 1) == Marca.O && tabuleiro.obterMarca(2, 2) == Marca.O) {
            resultado = "O";
        } else if (tabuleiro.obterMarca(0, 2) == Marca.O && tabuleiro.obterMarca(1, 1) == Marca.O && tabuleiro.obterMarca(2, 0) == Marca.O) {
            resultado = "O";
        } else if (tabuleiro.obterMarca(0, 0) == Marca.X && tabuleiro.obterMarca(1, 1) == Marca.X && tabuleiro.obterMarca(2, 2) == Marca.X) {
            resultado = "X";
        } else if (tabuleiro.obterMarca(0, 2) == Marca.X && tabuleiro.obterMarca(1, 1) == Marca.X && tabuleiro.obterMarca(2, 0) == Marca.X) {
            resultado = "X";
        }
        return resultado;
    }

    private String vitoria() {
        String resultado = avaliarLinhas();
        if (resultado.isEmpty()) {
            resultado = avaliarColunas();
        }
        if (resultado.isEmpty()) {
            resultado = avaliarDiagonais();
        }
        return resultado;
    }

    private void ia(Marca marcaIa) {
        Marca oponente = Marca.VAZIO;

        if (marcaIa.equals("X"))
            oponente = Marca.O;
        else
            oponente = Marca.X;

        boolean jogado = false;

        if (tabuleiro.obterMarca(0,0) == oponente) {
            if (tabuleiro.obterMarca(0,1) == oponente) {
                if (tabuleiro.obterMarca(0,2) == Marca.VAZIO) {
                    tabuleiro.colocarMarca(0,2, marcaIa);
                    jogado = true;
                }
            }
            if (tabuleiro.obterMarca(1,0) == oponente) {
                if (tabuleiro.obterMarca(2,0) == Marca.VAZIO) {
                    tabuleiro.colocarMarca(2,0, marcaIa);
                    jogado = true;
                }
            }
            if (!jogado && tabuleiro.obterMarca(2,2) == Marca.VAZIO) {
                tabuleiro.colocarMarca(2,2, marcaIa);
                jogado = true;
            }
            if (!jogado && tabuleiro.obterMarca(2,0) == Marca.VAZIO) {
                tabuleiro.colocarMarca(2,0, marcaIa);
                jogado = true;
            }
            if (!jogado && tabuleiro.obterMarca(1,0) == Marca.VAZIO) {
                tabuleiro.colocarMarca(1,0, marcaIa);
                jogado = true;
            }
            if (!jogado && tabuleiro.obterMarca(2,2) == oponente) {
                if (tabuleiro.obterMarca(1,1) == Marca.VAZIO) {
                    tabuleiro.colocarMarca(1,1, marcaIa);
                    jogado = true;
                } else if (tabuleiro.obterMarca(0,0) == oponente) {
                    tabuleiro.colocarMarca(1,1,marcaIa);
                    jogado = true;
                } else if (tabuleiro.obterMarca(1,2) == Marca.VAZIO) {
                    tabuleiro.colocarMarca(0,2, marcaIa);
                    jogado = true;
                }
            }
        }

        if (!jogado && tabuleiro.obterMarca(1,0) == oponente) {
            if (tabuleiro.obterMarca(1,1) == oponente) {
                if (tabuleiro.obterMarca(1,2) == Marca.VAZIO) {
                    tabuleiro.colocarMarca(1,2,marcaIa);
                    jogado = true;
                }
            }
        }

        if (!jogado && tabuleiro.obterMarca(2,0) == oponente) {
            if (tabuleiro.obterMarca(2,1) == oponente) {
                if (tabuleiro.obterMarca(2,2) == Marca.VAZIO) {
                    tabuleiro.colocarMarca(2,2, marcaIa);
                    jogado = true;
                }
            }
        }

        if (!jogado && tabuleiro.obterMarca(0,2) == oponente) {
            if (tabuleiro.obterMarca(0,0) == oponente) {
                if (tabuleiro.obterMarca(0,1) == Marca.VAZIO) {
                    tabuleiro.colocarMarca(0,1, marcaIa);
                    jogado = true;
                }
            }
        }

        if (!jogado && tabuleiro.obterMarca(2,2) == oponente) {
            if (tabuleiro.obterMarca(2,0) == oponente) {
                if (tabuleiro.obterMarca(2,1) == Marca.VAZIO) {
                    tabuleiro.colocarMarca(2,1,marcaIa);
                    jogado = true;
                }
            }
        }

        if (!jogado && tabuleiro.obterMarca(2,2) == oponente) {
            if (tabuleiro.obterMarca(2,1) == oponente) {
                if (tabuleiro.obterMarca(2,0) == Marca.VAZIO) {
                    tabuleiro.colocarMarca(2,0, marcaIa);
                    jogado = true;
                }
            }
        }

        if (!jogado && tabuleiro.obterMarca(1,1) == oponente) {
            if (tabuleiro.obterMarca(1,0) == oponente) {
                if (tabuleiro.obterMarca(1,2) == Marca.VAZIO) {
                    tabuleiro.colocarMarca(1,2, marcaIa);
                    jogado = true;
                }
            }
        }

        if (!jogado && tabuleiro.obterMarca(1,2) == oponente) {
            if (tabuleiro.obterMarca(1,0) == oponente) {
                if (tabuleiro.obterMarca(1,1) == Marca.VAZIO) {
                    tabuleiro.colocarMarca(1,1,marcaIa);
                    jogado = true;
                }
            } else if (!jogado && tabuleiro.obterMarca(1,1) == oponente) {
                if (tabuleiro.obterMarca(1,0) == Marca.VAZIO) {
                    tabuleiro.colocarMarca(1,0,marcaIa);
                    jogado = true;
                }
            }
        }

        if (!jogado && tabuleiro.obterMarca(0,2) == oponente) {
            if (tabuleiro.obterMarca(0,0) == oponente) {
                if (tabuleiro.obterMarca(0,1) == Marca.VAZIO) {
                    tabuleiro.colocarMarca(0,1,marcaIa);
                    jogado = true;
                }
            }
        }

        if (!jogado && tabuleiro.obterMarca(0,2) == oponente) {
            if (tabuleiro.obterMarca(1,2) == oponente) {
                if (tabuleiro.obterMarca(2,2) == Marca.VAZIO) {
                    tabuleiro.colocarMarca(2,2,marcaIa);
                    jogado = true;
                }
            }
        }

        if (!jogado && tabuleiro.obterMarca(1,2) == oponente) {
            if (tabuleiro.obterMarca(2,2) == oponente) {
                if (tabuleiro.obterMarca(1,2) == Marca.VAZIO) {
                    tabuleiro.colocarMarca(1,2,marcaIa);
                    jogado = true;
                }
            }
        }

        if (!jogado && tabuleiro.obterMarca(1,2) == oponente) {
            if (tabuleiro.obterMarca(0,2) == oponente) {
                if (tabuleiro.obterMarca(2,2) == Marca.VAZIO) {
                    tabuleiro.colocarMarca(2,2,marcaIa);
                    jogado = true;
                }
            }
        }

        if (!jogado && tabuleiro.obterMarca(1,1) == oponente) {
            if (tabuleiro.obterMarca(2,2) == oponente) {
                if (tabuleiro.obterMarca(0,0) == Marca.VAZIO) {
                    tabuleiro.colocarMarca(0,0,marcaIa);
                    jogado = true;
                }
            }
        }

        if (!jogado && tabuleiro.obterMarca(1,1) == oponente) {
            if (tabuleiro.obterMarca(2,1) == oponente) {
                if (tabuleiro.obterMarca(0,1) == Marca.VAZIO) {
                    tabuleiro.colocarMarca(0,1,marcaIa);
                    jogado = true;
                }
            }
        }

        if (!jogado && tabuleiro.obterMarca(1,1) == oponente) {
            if (tabuleiro.obterMarca(0,2) == oponente) {
                if (tabuleiro.obterMarca(2,0) == Marca.VAZIO) {
                    tabuleiro.colocarMarca(2,0,marcaIa);
                    jogado = true;
                }
            }
        }

        if (!jogado && tabuleiro.obterMarca(1,2) == oponente) {
            if (tabuleiro.obterMarca(2,2) == oponente) {
                if (tabuleiro.obterMarca(0,2) == Marca.VAZIO) {
                    tabuleiro.colocarMarca(0,2,marcaIa);
                    jogado = true;
                }
            }
        }

        if (!jogado) {
            int linha = 0;
            int coluna = 0;
            do {
                Random r = new Random();
                linha = r.nextInt(3);
                coluna = r.nextInt(3);
            } while (tabuleiro.obterMarca(linha,coluna) != Marca.VAZIO);
            tabuleiro.colocarMarca(linha,coluna,marcaIa);
            jogado = true;
        }
    }

    public int validarInput(Scanner s) {
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

    public String imprimirTabuleiro() {
        String resultado = "\t1\t\t2\t\t3\n";

        for (int l = 0; l < 3; ++l) {
            resultado += (l+1) + " ";
            for (int c = 0; c < 3; ++c) {
                if (tabuleiro.obterMarca(l, c) != Marca.VAZIO) {
                    resultado += "\t" + tabuleiro.obterMarca(l, c).name() + "\t";
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

    @Override
    public boolean jogadaValida(int linha, int coluna) {
        boolean resultado = true;
        if (linha < 0 || linha > 2 || coluna < 0 || coluna > 2) {
            resultado = false;
        }
        return resultado;
    }
}
