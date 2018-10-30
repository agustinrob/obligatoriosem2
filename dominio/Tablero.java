package dominio;

public class Tablero {

    //atributo de instancia
    private Ficha[][] tablero;

    public Ficha[][] getTablero() { //esto me devuelve la matriz, el tablero, matriz tipo ficha

        return tablero;
    }

    public Tablero() {
        Ficha[][] nuevoTablero = new Ficha[8][9];

        for (int i = 0; i < nuevoTablero.length; i++) {
            for (int j = 0; j < nuevoTablero[0].length; j++) {

                Ficha nuevaFicha = new Ficha();
                nuevoTablero[i][j] = nuevaFicha;
                nuevaFicha.setPosicionFila(i);
                nuevaFicha.setPosicionColumna(j);
            }
        }
        //fichas azules
        int fila = 0;
        int col = 1;

        while (col < nuevoTablero[0].length) {

            nuevoTablero[fila][col].setValor(col);
            nuevoTablero[fila][col].setColor("Azul");
            nuevoTablero[fila][col].setPosicionFila(fila);
            nuevoTablero[fila][col].setPosicionColumna(col);

            col++;
        }
        //fichas Rojas
        fila = nuevoTablero.length - 1;
        col = 0;
        int variableColumna = nuevoTablero[0].length - 1;

        while (col < nuevoTablero[0].length - 1) {
            nuevoTablero[fila][col].setValor(variableColumna);
            nuevoTablero[fila][col].setColor("Rojo");
            nuevoTablero[fila][col].setPosicionFila(fila);
            nuevoTablero[fila][col].setPosicionColumna(col);
            variableColumna--;
            col++;
        }
        this.tablero = nuevoTablero;
    }

    public void tableroReducido(Ficha[][] unTablero) {

        for (int i = 0; i < unTablero.length; i++) {
            for (int j = 0; j < unTablero[0].length; j++) {
                if (unTablero[i][j].getValor() != 0) {
                    if (unTablero[i][j].getColor().equals("Rojo")) {
                        System.out.print("\033[31m" + unTablero[i][j] + " ");
                    } else {
                        System.out.print("\033[34m" + unTablero[i][j] + " ");
                    }

                } else {
                    System.out.print("\033[30m" + "- ");
                }
            }
            System.out.println();
        }
    }

    public void tableroNormal(Ficha[][] unTablero) {

        for (int i = 0; i < unTablero.length; i++) {
            System.out.println("+-+-+-+-+-+-+-+-+-+");
            for (int j = 0; j < unTablero[0].length; j++) {
                if (unTablero[i][j].getValor() > 0) {
                    if (unTablero[i][j].getColor().equals("Rojo")) {
                        System.out.print("\033[30m" + "|" + "\033[30m" + "\033[31m" + unTablero[i][j]);
                    } else {
                        System.out.print("\033[30m" + "|" + "\033[34m" + unTablero[i][j]);
                    }
                } else {
                    System.out.print("\033[30m" + "|" + " ");
                }

            }
            System.out.print("\033[30m" + "|");
            System.out.println();
        }
        System.out.println("+-+-+-+-+-+-+-+-+-+");
    }
}
