package dominio;

public class Ficha {

    private int valor;
    private String color;
    private int posicionFila;
    private int posicionColumna;

    public int getPosicionFila() {
        return posicionFila;
    }

    public int getPosicionColumna() {
        return posicionColumna;
    }

    public void setPosicionFila(int posicionFila) {
        this.posicionFila = posicionFila;
    }

    public void setPosicionColumna(int posicionColumna) {
        this.posicionColumna = posicionColumna;
    }

    public int getValor() {

        return valor;
    }

    public String getColor() {
        return color;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) { //dos fichas son iguales si tienen el mismo color y el mismo valor!

        return (this.getColor().equals(((Ficha) o).getColor()) && this.getValor() == ((Ficha) o).getValor());

    }

    public Ficha() {
        
        this.valor = 0;
        this.color = "";
        this.posicionColumna = 0;
        this.posicionFila = 0;

    }

    public Ficha(int unValor, String unColor, int posicionFila, int posicionColumna) {

        this.valor = unValor;
        this.color = unColor;
        this.posicionFila = posicionFila;
        this.posicionColumna = posicionColumna;
    }

    /*
    metodo generico de mover ficha, segun la direccion a donde vaya llama a los metodos correspondientes.
     */
    public Ficha moverFicha(char direccion, String turnoColor, Tablero unTablero) {

        int filaActual = this.posicionFila;
        int columnaActual = this.posicionColumna;
        int valorFicha = this.valor;

        Ficha destino = new Ficha();

        if (direccion == 'D') {

            destino = this.moverDiagonalDerecha(turnoColor, unTablero, filaActual, columnaActual, valorFicha);
        }
        if (direccion == 'I') {

            destino = this.moverDiagonalIzquierda(turnoColor, unTablero, filaActual, columnaActual, valorFicha);
        }

        if (direccion == 'A') {

            destino = this.moverAdelante(turnoColor, unTablero, filaActual, columnaActual, valorFicha);
        }

        return destino;
    }

    public Ficha moverAdelante(String turnoColor, Tablero unTablero, int filaActual, int columnaActual, int valorFicha) {

        Ficha destino;

        if (turnoColor.equals("Rojo")) { // muevo fichas rojas

            destino = unTablero.getTablero()[filaActual - 1][columnaActual];

            destino.setValor(valorFicha);

            destino.setColor("Rojo");
            destino.setPosicionColumna(columnaActual);
            destino.setPosicionFila(filaActual - 1);
            
        } else { //muevo fichas azules

            destino = unTablero.getTablero()[filaActual + 1][columnaActual];

            destino.setValor(valorFicha);
            destino.setColor("Azul");
            destino.setPosicionColumna(columnaActual);
            destino.setPosicionFila(filaActual + 1);
        }
        
        this.establecerFichaVacia(filaActual, columnaActual, unTablero); // pongo en cero la ficha que voy a mover.
        
        return destino;
    }

    public Ficha moverDiagonalDerecha(String turnoColor, Tablero unTablero, int filaActual, int columnaActual, int valorFicha) {

        Ficha destino;

        if (turnoColor.equals("Rojo")) { //muevo fichas rojas

            destino = unTablero.getTablero()[filaActual - 1][columnaActual + 1];

            destino.setValor(valorFicha);

            destino.setColor("Rojo");
            destino.setPosicionColumna(columnaActual + 1);
            destino.setPosicionFila(filaActual - 1);
        } else { // muevo fichas azules

            destino = unTablero.getTablero()[filaActual + 1][columnaActual + 1];

            destino.setValor(valorFicha);
            destino.setColor("Azul");
            destino.setPosicionColumna(columnaActual + 1);
            destino.setPosicionFila(filaActual + 1);
        }
        this.establecerFichaVacia(filaActual, columnaActual, unTablero); //pongo en cero la ficha que voy a mover.
        return destino;
    }

    public Ficha moverDiagonalIzquierda(String turnoColor, Tablero unTablero, int filaActual, int columnaActual, int valorFicha) {

        Ficha destino;

        if (turnoColor.equals("Rojo")) {

            destino = unTablero.getTablero()[filaActual - 1][columnaActual - 1];

            destino.setValor(valorFicha);
            destino.setColor("Rojo");
            destino.setPosicionColumna(columnaActual - 1);
            destino.setPosicionFila(filaActual - 1);
        } else {
            destino = unTablero.getTablero()[filaActual + 1][columnaActual - 1];

            destino.setValor(valorFicha);
            destino.setColor("Azul");
            destino.setPosicionColumna(columnaActual - 1);
            destino.setPosicionFila(filaActual + 1);

        }

        this.establecerFichaVacia(filaActual, columnaActual, unTablero); //pongo en cero la ficha que voy a mover.

        return destino;
    }

    /*
    Este metodo establece en cero la posicion de la ficha que se va a mover.
     */
    public void establecerFichaVacia(int fila, int columna, Tablero unTablero) {

        unTablero.getTablero()[fila][columna].setValor(0);
        unTablero.getTablero()[fila][columna].setColor("");
        unTablero.getTablero()[fila][columna].setPosicionColumna(0);
        unTablero.getTablero()[fila][columna].setPosicionFila(0);
    }
    
    /*@Override
    public String toString() {
        String retorno = "null";
        if (this.getColor().equals("Rojo")) {
            retorno = "\033[31m" + this.getValor()+ "\033[0;30m";
        } else {
            retorno = "\033[34m" + this.getValor()+ "\033[0;30m";
        }
        return retorno;
    }*/

    @Override
    public String toString() {

        return "" + this.getValor();

    }
}
