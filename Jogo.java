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

    private String avaliarLinhas() {
        String resultado = "";
        for (int i = 0; i < 3; ++i) {
            if (tabuleiro[i][0] == Marca.O && tabuleiro[i][1] == Marca.O && tabuleiro[i][2] == Marca.O) {
                resultado = "O";
                break;
            } else if (tabuleiro[i][0] == Marca.X && tabuleiro[i][1] == Marca.X && tabuleiro[i][2] == Marca.X)
            {
                resultado = "X";
                break;
            }
        }
        return resultado;
    }

    private String avaliarColunas() {
        String resultado = "";
        for (int i = 0; i < 3; ++i) {
            if (tabuleiro[0][i] == Marca.O && tabuleiro[1][i] == Marca.O && tabuleiro[2][i] == Marca.O) {
                resultado = "O";
                break;
            } else if (tabuleiro[0][i] == Marca.X && tabuleiro[1][i] == Marca.X && tabuleiro[2][i] == Marca.X)
            {
                resultado = "X";
                break;
            }
        }
        return resultado;
    }

    private String avaliarDiagonais() {
        String resultado = "";
        if (tabuleiro[0][0] == Marca.O && tabuleiro[1][1] == Marca.O && tabuleiro[2][2] == Marca.O) {
            resultado = "O";
        } else if (tabuleiro[0][2] == Marca.O && tabuleiro[1][1] == Marca.O && tabuleiro[2][0] == Marca.O) {
            resultado = "O";
        } else if (tabuleiro[0][0] == Marca.X && tabuleiro[1][1] == Marca.X && tabuleiro[2][2] == Marca.X) {
            resultado = "X";
        } else if (tabuleiro[0][2] == Marca.X && tabuleiro[1][1] == Marca.X && tabuleiro[2][0] == Marca.X) {
            resultado = "X";
        }
        return resultado;
    }

    public String vitoria() {
        String resultado = avaliarLinhas();
        if (resultado.isEmpty()) {
            resultado = avaliarColunas();
        }
        if (resultado.isEmpty()) {
            resultado = avaliarDiagonais();
        }
        return resultado;
    }

    public void ia() {
        boolean jogado = false;
        if (tabuleiro[0][0] == Marca.X) {
            if (tabuleiro[0][1] == Marca.X) {
                if (tabuleiro[0][2] == null) {
                    tabuleiro[0][2] = Marca.O;
                    jogado = true;
                }
            }
            if (tabuleiro[1][0] == Marca.X) {
                if (tabuleiro[2][0] == null) {
                    tabuleiro[2][0] = Marca.O;
                    jogado = true;
                }
            }
            if (!jogado && tabuleiro[2][2] == null) {
                tabuleiro[2][2] = Marca.O;
                jogado = true;
            }
            if (!jogado && tabuleiro[2][0] == null) {
                tabuleiro[2][0] = Marca.O;
                jogado = true;
            }
            if (!jogado && tabuleiro[1][0] == null) {
                tabuleiro[1][0] = Marca.O;
                jogado = true;
            }
            if (!jogado && tabuleiro[2][2] == Marca.X) {
                if (tabuleiro[1][1] == null) {
                    tabuleiro[1][1] = Marca.O;
                    jogado = true;
                } else if (tabuleiro[0][0] == Marca.X) {
                    tabuleiro[1][1] = Marca.O;
                    jogado = true;
                } else if (tabuleiro[1][2] == null) {
                    tabuleiro[0][2] = Marca.O;
                    jogado = true;
                }
            }
        }
        if (!jogado && tabuleiro[1][0] == Marca.X) {
            if (tabuleiro[1][1] == Marca.X) {
                if (tabuleiro[1][2] == null) {
                    tabuleiro[1][2] = Marca.O;
                    jogado = true;
                }
            }
        }

        if (!jogado && tabuleiro[2][0] == Marca.X) {
            if (tabuleiro[2][1] == Marca.X) {
                if (tabuleiro[2][2] == null) {
                    tabuleiro[2][2] = Marca.O;
                    jogado = true;
                }
            }
        }

        if (!jogado && tabuleiro[0][2] == Marca.X) {
            if (tabuleiro[0][0] == Marca.X) {
                if (tabuleiro[0][1] == null) {
                    tabuleiro[0][1] = Marca.O;
                    jogado = true;
                }
            }
        }

        if (!jogado && tabuleiro[2][2] == Marca.X) {
            if (tabuleiro[2][0] == Marca.X) {
                if (tabuleiro[2][1] == null) {
                    tabuleiro[2][1] = Marca.O;
                    jogado = true;
                }
            }
        }

        if (!jogado && tabuleiro[2][2] == Marca.X) {
            if (tabuleiro[2][1] == Marca.X) {
                if (tabuleiro[2][0] == null) {
                    tabuleiro[2][0] = Marca.O;
                    jogado = true;
                }
            }
        }

        if (!jogado && tabuleiro[1][1] == Marca.X) {
            if (tabuleiro[1][0] == Marca.X) {
                if (tabuleiro[1][2] == null) {
                    tabuleiro[1][2] = Marca.O;
                    jogado = true;
                }
            }
        }

        if (!jogado && tabuleiro[1][2] == Marca.X) {
            if (tabuleiro[1][0] == Marca.X) {
                if (tabuleiro[1][1] == null) {
                    tabuleiro[1][1] = Marca.O;
                    jogado = true;
                }
            } else if (!jogado && tabuleiro[1][1] == Marca.X) {
                if (tabuleiro[1][0] == null) {
                    tabuleiro[1][0] = Marca.O;
                    jogado = true;
                }
            }
        }

        if (!jogado && tabuleiro[0][2] == Marca.X) {
            if (tabuleiro[0][0] == Marca.X) {
                if (tabuleiro[0][1] == null) {
                    tabuleiro[0][1] = Marca.O;
                    jogado = true;
                }
            }
        }

        if (!jogado && tabuleiro[0][2] == Marca.X) {
            if (tabuleiro[1][2] == Marca.X) {
                if (tabuleiro[2][2] == null) {
                    tabuleiro[2][2] = Marca.O;
                    jogado = true;
                }
            }
        }

        if (!jogado && tabuleiro[1][2] == Marca.X) {
            if (tabuleiro[2][2] == Marca.X) {
                if (tabuleiro[1][2] == null) {
                    tabuleiro[1][2] = Marca.O;
                    jogado = true;
                }
            }
        }

        if (!jogado && tabuleiro[1][2] == Marca.X) {
            if (tabuleiro[0][2] == Marca.X) {
                if (tabuleiro[2][2] == null) {
                    tabuleiro[2][2] = Marca.O;
                    jogado = true;
                }
            }
        }

        if (!jogado && tabuleiro[1][1] == Marca.X) {
            if (tabuleiro[2][2] == Marca.X) {
                if (tabuleiro[0][0] == null) {
                    tabuleiro[0][0] = Marca.O;
                    jogado = true;
                }
            }
        }

        if (!jogado && tabuleiro[1][1] == Marca.X) {
            if (tabuleiro[2][1] == Marca.X) {
                if (tabuleiro[0][1] == null) {
                    tabuleiro[0][1] = Marca.O;
                    jogado = true;
                }
            }
        }

        if (!jogado && tabuleiro[1][1] == Marca.X) {
            if (tabuleiro[0][2] == Marca.X) {
                if (tabuleiro[2][0] == null) {
                    tabuleiro[2][0] = Marca.O;
                    jogado = true;
                }
            }
        }

        if (!jogado && tabuleiro[1][2] == Marca.X) {
            if (tabuleiro[2][2] == Marca.X) {
                if (tabuleiro[0][2] == null) {
                    tabuleiro[0][2] = Marca.O;
                    jogado = true;
                }
            }
        }

        if (!jogado){
            int linha = 0;
            int coluna = 0;
            do {
                Random r = new Random();
                linha = r.nextInt(3);
                coluna = r.nextInt(3);
            } while (tabuleiro[linha][coluna] != null);
            tabuleiro[linha][coluna] = Marca.O;
        }
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
