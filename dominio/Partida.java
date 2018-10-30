package dominio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


//arreglar lo de la hora de creacion.
public class Partida implements Comparable <Partida> {

    private Jugador jugadorRojo;
    private Jugador jugadorAzul;
    private Jugador ganador;
    private int id;
    private Jugador jugadorActual; 
    private String tipoPartida;
    private Tablero tablero;
    private int cantidadMovimientos;
    private ArrayList<String> movimientos; //para replicar partida.
    private ArrayList<String> movimientosPorColor;//para replicar partida.
    private String turnoColor;    
    private int cantTurno;
    private Date date;
    
    public  int getId(){
        
        return id;
    }
    
    public void setId(int unaId){
        
        this.id = unaId;        
    }
    
    public boolean equals(Partida o){
        
        boolean sonIguales = true;
        
        if (o == null) {
            sonIguales = false;
        } else if (!(o instanceof Partida)) { //instanceof es usado para conocer si un objeto es de un tipo determinado.

            sonIguales = false;
        } else {
            Partida otraPartida = (Partida) o;
            sonIguales = this.getId() == otraPartida.getId();
        }

        return sonIguales;
    }
    
    
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public int getCantidadTurno(){
        return cantTurno;
    }
    
    public void setCantTurno(int unTurno ){
        
        this.cantTurno = unTurno;
    }

    public ArrayList<String> getMovimientosPorColor() {
        return movimientosPorColor;
    }
       
    public String getTurnoColor() {
        return turnoColor;
    }

    public void setTurnoColor(String unTurnoColor) {

        turnoColor = unTurnoColor;
    }

    public void setJugadorRojo(Jugador unJugador) {

        jugadorRojo = unJugador;
    }

    public void setCantidadMovimientos(int cantidad) {

        cantidadMovimientos = cantidad;
    }

    public int getCantidadMovimientos() {

        return cantidadMovimientos;
    }

    public void setJugadorAzul(Jugador unJugador) {

        jugadorAzul = unJugador;
    }

    public void setTablero(Tablero unTablero) {

        tablero = unTablero;
    }

    public void setGanador(Jugador unGanador) {

        ganador = unGanador;
    }

    public void setTipo(String unTipo) {

        tipoPartida = unTipo;
    }

    public void setJugadorActual(Jugador unJugador) {

        jugadorActual = unJugador;
    }
    
    @Override //ordenamiento por hora de creacion.
    public int compareTo (Partida o){
        return this.date.compareTo(o.getDate());
    }

    public Jugador getJugadorActual() {

        return jugadorActual;
    }

    public ArrayList getMovimientos() {

        return movimientos;
    }

    public Jugador getJugadorRojo() {

        return jugadorRojo;
    }

    public Tablero getTablero() {

        return tablero;
    }

    public void agregarMovimiento(String unMovimiento) {
        this.movimientos.add(unMovimiento);
    }

    public Jugador getJugadorAzul() {

        return jugadorAzul;

    }

    public Jugador getGanador() {

        return ganador;
    }

    public String getTipo() {

        return tipoPartida;
    }

    //Constructor
    public Partida(Jugador jugadorRojo, Jugador jugadorAzul, String tipoPartida, Tablero unTablero, int cantidadMovimientos, ArrayList<String> movimientos, ArrayList<String> movsPorColor) {
        this.jugadorRojo = jugadorRojo;
        this.jugadorAzul = jugadorAzul;
        this.tipoPartida = tipoPartida;
        this.tablero = unTablero;
        this.movimientos = movimientos;
        this.cantidadMovimientos = cantidadMovimientos;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");  
        this.date=new Date();
        this.movimientosPorColor = movsPorColor;
    }

    public ArrayList<Integer> sumaMovimiento(Ficha[][] tablero, Ficha fichaSeleccionada) {

        int largoFilas = tablero.length;
        int largoColumnas = tablero[0].length;
        char direccionD = 'D';
        char direccionI = 'I';
        char direccionA = 'A';
        ArrayList<Integer> fichasPosibles = new ArrayList<>(); //guardo las posibles fichas que el jugador va a poder mover      

        //Obtengo ubicacion de la ficha
        int posicionFila = fichaSeleccionada.getPosicionFila();
        int posicionColumna = fichaSeleccionada.getPosicionColumna();

        //DIAGONAL IZQUIERDA
        int sumaDiagonalIzquierda = fichaSeleccionada.getValor();
        int iIzquierda = posicionFila;
        int jIzquierda = posicionColumna;
        iIzquierda--;
        jIzquierda--;
        while (iIzquierda >= 0 && jIzquierda >= 0) {

            if (tablero[iIzquierda][jIzquierda].getValor() != 0) {

                sumaDiagonalIzquierda += tablero[iIzquierda][jIzquierda].getValor();
            }
            iIzquierda--;
            jIzquierda--;
        }
        int iIzquierda2 = posicionFila;
        int jIzquierda2 = posicionColumna;
        iIzquierda2++;
        jIzquierda2++;
        while (iIzquierda2 < largoFilas && jIzquierda2 < largoColumnas) {

            if (tablero[iIzquierda2][jIzquierda2].getValor() != 0) {

                sumaDiagonalIzquierda += tablero[iIzquierda2][jIzquierda2].getValor();
            }
            iIzquierda2++;
            jIzquierda2++;
        }
        Ficha fichaSumaDiagonalIzquierda = this.seleccionarFicha(sumaDiagonalIzquierda, this.getTablero(), this.getJugadorActual().getColor());
        if (sumaDiagonalIzquierda != fichaSeleccionada.getValor()) {
            if (sumaDiagonalIzquierda > 0 && sumaDiagonalIzquierda <= 8) {
                if (!fichasPosibles.contains(sumaDiagonalIzquierda)) {
                    if (this.puedeMover(fichaSumaDiagonalIzquierda, this.getTablero(), direccionD) || this.puedeMover(fichaSumaDiagonalIzquierda, this.getTablero(), direccionA) || this.puedeMover(fichaSumaDiagonalIzquierda, this.getTablero(), direccionI)) {
                        fichasPosibles.add(sumaDiagonalIzquierda);

                    }
                }
            }
        }
        //DIAGONAL DERECHA
        int sumaDiagonalDerecha = fichaSeleccionada.getValor();
        int iDerecha = posicionFila;
        int jDerecha = posicionColumna;
        iDerecha++;
        jDerecha--;
        while (iDerecha < largoFilas && jDerecha >= 0) {

            if (tablero[iDerecha][jDerecha].getValor() != 0) {

                sumaDiagonalDerecha = sumaDiagonalDerecha + tablero[iDerecha][jDerecha].getValor();

            }
            iDerecha++;
            jDerecha--;
        }
        int iDerecha2 = posicionFila;
        int jDerecha2 = posicionColumna;
        iDerecha2--;
        jDerecha2++;
        while (iDerecha2 >= 0 && jDerecha2 < largoColumnas) {

            if (tablero[iDerecha2][jDerecha2].getValor() != 0) {
                sumaDiagonalDerecha = sumaDiagonalDerecha + tablero[iDerecha2][jDerecha2].getValor();

            }
            iDerecha2--;
            jDerecha2++;
        }
        Ficha fichaDiagonalDerecha = this.seleccionarFicha(sumaDiagonalDerecha, this.getTablero(), this.getJugadorActual().getColor());
        if (sumaDiagonalDerecha != fichaSeleccionada.getValor()) {
            if (sumaDiagonalDerecha > 0 && sumaDiagonalDerecha <= 8) {
                if (!fichasPosibles.contains(sumaDiagonalDerecha)) {
                    if (this.puedeMover(fichaDiagonalDerecha, this.getTablero(), direccionD) || this.puedeMover(fichaDiagonalDerecha, this.getTablero(), direccionA) || this.puedeMover(fichaDiagonalDerecha, this.getTablero(), direccionI)) {
                        fichasPosibles.add(sumaDiagonalDerecha);

                    }
                }
            }
        }
        //VERTICAL       
        int sumaVertical = fichaSeleccionada.getValor();
        int iVertical = posicionFila;
        int jVertical = posicionColumna;
        iVertical--;
        while (iVertical >= 0) {

            if (tablero[iVertical][jVertical].getValor() != 0) {

                sumaVertical = sumaVertical + tablero[iVertical][jVertical].getValor();
            }
            iVertical--;
        }
        int iVertical2 = posicionFila;
        int jVertical2 = posicionColumna;
        iVertical2++;
        while (iVertical2 < largoFilas) {

            if (tablero[iVertical2][jVertical2].getValor() != 0) {

                sumaVertical = sumaVertical + tablero[iVertical2][jVertical2].getValor();
            }
            iVertical2++;
        }
        Ficha fichaSumaVertical = this.seleccionarFicha(sumaVertical, this.getTablero(), this.getJugadorActual().getColor());
        if (sumaVertical != fichaSeleccionada.getValor()) {
            if (sumaVertical > 0 && sumaVertical <= 8) {
                if (!fichasPosibles.contains(sumaVertical)) {
                    if (this.puedeMover(fichaSumaVertical, this.getTablero(), direccionD) || this.puedeMover(fichaSumaVertical, this.getTablero(), direccionA) || this.puedeMover(fichaSumaVertical, this.getTablero(), direccionI)) {
                        fichasPosibles.add(sumaVertical);

                    }
                }
            }
        }
        //HORIZONTAL
        int sumaHorizontal = fichaSeleccionada.getValor();
        int iHorizontal = posicionFila;
        int jHorizontal = posicionColumna;
        jHorizontal--;
        while (jHorizontal >= 0) {
            if (tablero[iHorizontal][jHorizontal].getValor() != 0) {

                sumaHorizontal += tablero[iHorizontal][jHorizontal].getValor();
            }
            jHorizontal--;
        }
        int iHorizontal2 = posicionFila;
        int jHorizontal2 = posicionColumna;
        jHorizontal2++;
        while (jHorizontal2 < largoColumnas) {

            if (tablero[iHorizontal2][jHorizontal2].getValor() != 0) {

                sumaHorizontal += tablero[iHorizontal2][jHorizontal2].getValor();
            }
            jHorizontal2++;
        }
        Ficha fichaSumaHorizontal = this.seleccionarFicha(sumaHorizontal, this.getTablero(), this.getJugadorActual().getColor());

        if (sumaHorizontal != fichaSeleccionada.getValor()) {
            if (sumaHorizontal > 0 && sumaHorizontal <= 8) {
                if (!fichasPosibles.contains(sumaHorizontal)) {
                    if (this.puedeMover(fichaSumaHorizontal, this.getTablero(), direccionD) || this.puedeMover(fichaSumaHorizontal, this.getTablero(), direccionA) || this.puedeMover(fichaSumaHorizontal, this.getTablero(), direccionI)) {
                        fichasPosibles.add(sumaHorizontal);
                    }
                }
            }
        }
        return fichasPosibles;    //retorno de arraylist con los movimientos posibles
    }

    public Ficha seleccionarFicha(int valorFicha, Tablero unTablero, String turnoColor) {

        Ficha fichaAMover = new Ficha();
        
        for (int i = 0; i < unTablero.getTablero().length; i++) {
            for (int j = 0; j < unTablero.getTablero()[0].length; j++) {

                Ficha unaFicha = unTablero.getTablero()[i][j];
                if (unaFicha.getColor().equals(turnoColor) && unaFicha.getValor() == valorFicha) {

                    fichaAMover = unaFicha;
                }
            }
        }
        return fichaAMover;
    }

    public boolean puedeMover(Ficha unaFicha, Tablero unTablero, char unaDireccion) {

        boolean mueve = false;

        int largoFilas = unTablero.getTablero().length;
        int largoColumnas = unTablero.getTablero()[0].length;

        int posicionFilaFicha = unaFicha.getPosicionFila();
        int posicionColFicha = unaFicha.getPosicionColumna();

        if (unaFicha.getColor().equals("Rojo")) { //valido movimiento para las fichas rojas
            if (unaDireccion == 'A') {

                if ((posicionFilaFicha - 1) >= 0) {

                    if (unTablero.getTablero()[posicionFilaFicha - 1][posicionColFicha].getValor() == 0) {

                        mueve = true;
                    }
                }
            }
            if (unaDireccion == 'D') {

                if (((posicionFilaFicha - 1) >= 0) && ((posicionColFicha + 1) < largoColumnas)) {

                    if (unTablero.getTablero()[posicionFilaFicha - 1][posicionColFicha + 1].getValor() == 0) {

                        mueve = true;
                    }
                }

            }
            if (unaDireccion == 'I') {
                if ((posicionFilaFicha - 1) >= 0 && (posicionColFicha - 1) >= 0) {
                    if (unTablero.getTablero()[posicionFilaFicha - 1][posicionColFicha - 1].getValor() == 0) {

                        mueve = true;
                    }
                }
            }
        } else { //fichas azules
            if (unaDireccion == 'A') {

                if ((posicionFilaFicha + 1) < largoFilas) {
                    if (unTablero.getTablero()[posicionFilaFicha + 1][posicionColFicha].getValor() == 0) {

                        mueve = true;
                    }
                }
            }
            if (unaDireccion == 'D') {

                if ((posicionFilaFicha + 1) < largoFilas && (posicionColFicha + 1) < largoColumnas) {
                    if (unTablero.getTablero()[posicionFilaFicha + 1][posicionColFicha + 1].getValor() == 0) {

                        mueve = true;
                    }
                }
            }
            if (unaDireccion == 'I') {

                if ((posicionFilaFicha + 1) < largoFilas && (posicionColFicha - 1) >= 0) {
                    if (unTablero.getTablero()[posicionFilaFicha + 1][posicionColFicha - 1].getValor() == 0) {

                        mueve = true;
                    }
                }
            }
        }

        return mueve;
    }

    public void cambiarTurno() {

        if (this.jugadorActual.equals(this.jugadorRojo)) {
            this.setJugadorActual(this.jugadorAzul);

        } else {
            this.setJugadorActual(this.jugadorRojo);
        }
    }

    public String determinarGanador() {

        String ganadorPartida = "Empate";
        int cantidadFichasRojas = 0;
        int cantidadFichasAzules = 0;
        for (int j = 0; j < this.tablero.getTablero()[0].length; j++) {
            if (this.tablero.getTablero()[0][j].getColor().equals("Rojo")) {
                cantidadFichasRojas = cantidadFichasRojas + this.tablero.getTablero()[0][j].getValor();
            }
            if (this.tablero.getTablero()[7][j].getColor().equals("Azul")) {
                cantidadFichasAzules = cantidadFichasAzules + this.tablero.getTablero()[7][j].getValor();
            }
        }
        if (cantidadFichasRojas > cantidadFichasAzules) {
            ganadorPartida = this.jugadorRojo.getAlias();
        }
        if (cantidadFichasAzules > cantidadFichasRojas) {
            ganadorPartida = this.jugadorAzul.getAlias();
        }

        return ganadorPartida;
    }

    public boolean puedeMoverAlguna(Jugador unJugador) { //determina si al menos un jugador puede mover alguna.

        Ficha[][] tablero = this.tablero.getTablero();
        boolean puede = false;

        String colorJugador = unJugador.getColor();

        if (colorJugador.equals("Azul")) {

            for (int fila = 0; fila < tablero.length; fila++){

                for (int col = 0; col < tablero[0].length && !puede; col++) {//le agrego la condicion de que busque hasta que encuentre que se pueda mover al menos una.

                    Ficha ficha = tablero[fila][col];

                    if (ficha.getColor().equals(colorJugador)) {

                        if (ficha.getPosicionFila() < 7){ //adelante
                        
                            if (tablero[ficha.getPosicionFila() + 1][col].getValor() == 0) {
                                puede = true;
                            }
                        }
                        //diagonal izquierda
                        if (ficha.getPosicionColumna() > 0) {
                            if (tablero[ficha.getPosicionFila() + 1][ficha.getPosicionColumna() - 1].getValor() == 0) {
                                puede = true;
                            }
                        }
                        //diagonal derecha
                        if (ficha.getPosicionColumna() < 8) {
                            if (tablero[ficha.getPosicionFila() + 1][ficha.getPosicionColumna() + 1].getValor() == 0) {

                                puede = true;
                            }
                        }
                    }

                }
            }

        } else {

            for (int fila = 0; fila < tablero.length; fila++) {

                for (int col = 0; col < tablero[0].length && !puede; col++) {//le agrego la condicion de que busque hasta que encuentre que se pueda mover al menos una.

                    Ficha ficha = tablero[fila][col];

                    if (ficha.getColor().equals(colorJugador)) {

                        //adelante
                        if (ficha.getPosicionFila() > 0) {

                            if (tablero[ficha.getPosicionFila() - 1][col].getValor() == 0) {
                                puede = true;

                            }
                            //diagonal izquierda
                            if (ficha.getPosicionColumna() > 0) {
                                if (tablero[ficha.getPosicionFila() - 1][ficha.getPosicionColumna() - 1].getValor() == 0) {
                                    puede = true;
                                }
                            }
                            //diagonal derecha
                            if (ficha.getPosicionColumna() < 8) {
                                if (tablero[ficha.getPosicionFila() - 1][ficha.getPosicionColumna() + 1].getValor() == 0) {
                                }
                                puede = true;
                            }
                        }
                    }
                }
            }
        }
        return puede;
    }

    public boolean estaEnElLadoOpuesto(Ficha unaFicha) {

        boolean esta = false;
        if (this.turnoColor.equals("Rojo")) {
            if (unaFicha.getPosicionFila() == 0) {
                esta = true;
            }
        } 
        else {
            if (unaFicha.getPosicionFila() == 7) {
                esta = true;
            }
        }
        return esta;
    }

    public boolean estanTodasEnElLadoOpuesto(Tablero unTablero) {

        boolean estan = false;
        //veo si estan las fichas azules en la fila de partida de las fichas rojas
        if (this.turnoColor.equals("Azul")) {

            Ficha[] filaRoja = unTablero.getTablero()[7];//esta es la fila Roja del tablero
            int cant = 0;

            for (Ficha ficha : filaRoja) {

                if (ficha.getColor().equals("Azul")) {//por cada ficha pregunto si corresponde a una de color Azul

                    cant++;
                }
            }
            if (cant == 8) {// si se registra una cantidad de 8 correspondencias quiere decir que estan todas

                estan = true;
            }
        }
        else { //fichas rojas
            //veo si estan las fichas rojas en la fila de partida de las fichas azules                 
            Ficha[] filaAzul = unTablero.getTablero()[0];//esta es la fila Azul del tablero
            int cant = 0;
            for (Ficha ficha : filaAzul) {

                if (ficha.getColor().equals("Rojo")) {//por cada ficha pregunto si corresponde a una de color Roja

                    cant++;
                }
            }
            if (cant == 8) {// si se registra una cantidad de 8 correspondencias quiere decir que estan todas
                estan = true;
            }
        }
        return estan;
    }
    
    
    //VER QUE CODIGOS DEJAMOS.
    
    /*public boolean llegaronTodas(Ficha[][] unTablero) {
        boolean todos = false;
        int contadorRojos = 0;
        int contadorAzules = 0;
        for (int i = 0; i < 9; i++) {
            if (unTablero[0][i] != null && unTablero[0][i].getColor().equalsIgnoreCase("Rojo")) {
                contadorRojos++;
            } else if (unTablero[7][i]!=null && unTablero[7][i].getColor().equalsIgnoreCase("Azul")) {
                contadorAzules++;
            }
        }
        if (contadorRojos == 8 || contadorAzules == 8) {
            todos = true;
        }
        return todos;
    }*/
    
    /*private static boolean llegoUnaFicha(Ficha[][] unTablero) {
        boolean termino = false;
        for (int i = 0; i < 9; i++) {
            if ((unTablero[0][i]!=null && unTablero[0][i].getColor().equals("Rojo")) ||( unTablero[7][i]!=null && unTablero[7][i].getColor().equals("Azul"))) {
                termino = true;
            }
        }
        return termino;
    }*/
    
    

    @Override
    public String toString() {
        System.out.println("*********************************************");
        return "Datos de la partida: "+ "\nJugador rojo: " + this.getJugadorRojo() + "\nJugador azul: " + this.getJugadorAzul() + "\nGanador: " + this.getGanador() + "\nTipo de partida: " + this.getTipo();

    }

}
