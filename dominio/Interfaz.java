package dominio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Iterator;



public class Interfaz {

    public static void main(String[] args) {
        
        Sistema miSistema = new Sistema();
        System.out.println();
        System.out.println("+++SUMAS+++");
        System.out.println();
        menu(miSistema); //menu principal del juego
    }

    private static void menu(Sistema miSistema) { //menu principal del juego        
        int opcion;
        do {
            String opciones = "\n1. Registro de jugador\n2. Jugar pàrtida\n3. Replicar partida\n4. Ranking\n5. Salir\n";

            opcion = rangoNumero("Ingrese una opcion: \n" + opciones + "\n", 1, 5, "ERROR. Debe seleccionar una de las opciones");
            procesarOpcion(opcion, miSistema);
        } while (opcion != 5);
    }

    private static void procesarOpcion(int opcion, Sistema miSistema) {
        switch (opcion) {
            case 1:
                registrarJugador(miSistema);
                break;
            case 2:
                crearPartida(miSistema);
                break;
            case 3:
                replicarPartida(miSistema);
                break;
            case 4:
                ranking(miSistema);
                break;
            case 5:
                System.out.println("Saliendo del juego...");
                break;
            default:
                break;
        }
    }

    private static void registrarJugador(Sistema miSistema) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("REGISTRAR NUEVO JUGADOR");
        System.out.println();
        boolean salir = false;
        while (!salir) {
            String nombre = leerLinea("Ingrese el nombre: ", 1, 200, "ERROR. Este campo no puede quedar vacio. Vuelva a intentarlo.");
            int edad = rangoNumero("Ingrese la edad: ", 12, 100, "ERROR. La edad ingresada no es valida. Vuelva a intentarlo.");
            String alias = leerLinea("Ingrese el alias: ", 1, 100, "ERROR. Este campo no puede quedar vacio. Vuelva a intentarlo.");
            while (miSistema.existeJugador(alias)) {
                System.out.println("El alias ingresado ya existe. Pruebe ingresando otro.");
                alias = leerLinea("Ingrese el alias: ", 1, 100, "ERROR. Este campo no puede quedar vacio. Vuelva a intentarlo.");
            }
            miSistema.crearJugador(nombre, alias, edad);
            System.out.println();
            System.out.println("El jugador " + alias + " fue creado correctamente.");
            boolean salirRegistroJugador = false;
            while (!salirRegistroJugador) {
                System.out.println("Desea registrar otro jugador? (S/N)");
                String respuesta = entrada.nextLine();
                if (respuesta.equalsIgnoreCase("s")) {
                    salirRegistroJugador = true;
                    salir = false;
                }
                if (respuesta.equalsIgnoreCase("n")) {
                    salir = true;
                    salirRegistroJugador = true;
                }
                if ((!respuesta.equalsIgnoreCase("n")) && (!respuesta.equalsIgnoreCase("s"))) {
                    System.out.println("\033[31mOpción incorrecta. Vuelva a intentar");
                    salirRegistroJugador = false;
                }
            }
        }
    }
    
    //lee linea con rango controlado.
    public static String leerLinea(String mensaje, int desde, int hasta, String msjError) { //metodo generico para lectura de cadenas con rangos controlados       
        Scanner entrada = new Scanner(System.in);
        System.out.println(mensaje);
        String linea = entrada.nextLine();
        while (linea.trim().length() < desde || linea.trim().length() > hasta) {
            System.out.println(msjError);
            linea = entrada.nextLine();
            System.out.println();
        }
        return linea;
    }

    private static void ranking(Sistema miSistema) {

        if (miSistema.getListaJugadores().isEmpty()) {
            System.out.println("No hay jugadores registrados.");
        } else {
            System.out.println("Ranking de los mejores jugadores");
            System.out.println();
            for (int i = 0; i < miSistema.ordenPartidasGanadas().size(); i++) {
                Jugador jugadorAux = miSistema.ordenPartidasGanadas().get(i);
                System.out.println((i + 1) + " Jugador: " + jugadorAux.getAlias() + "\nPartidas ganadas: " + jugadorAux.getPartidasGanadas());
                System.out.println();
            }
        }
    }

    //metodo que controla el rango de numeros
    public static int rangoNumero(String mensaje, int min, int max, String msjError) {

        int opcion = 0;
        boolean error;
        System.out.println(mensaje);
        do {
            int ingreso = soloNumero();

            if (rango(min, max, ingreso)) {
                error = false;
                opcion = ingreso;

            } else {
                System.out.println("\n" + msjError);
                System.out.println(mensaje);
                error = true;

            }
        } while (error);

        return opcion;
    }
    
    
    //metodo que acepta solo el ingreso de numeros.
    public static int soloNumero() {

        Scanner entrada = new Scanner(System.in);
        int numero = 0;
        boolean hayNum = false;
        do {

            try {
                numero = Integer.parseInt(entrada.nextLine());
                hayNum = true;
                if (numero == 0 || numero < 0) {
                    System.out.println("El cero o un numero negativo no es valido");
                }
            } catch (InputMismatchException ex) {
                System.out.println("ERROR. Tipo de dato ingresado no valido.");
            }

        } while (!hayNum || numero == 0 || numero < 0);

        return numero;
    }
    
    //booleano que controla el rango de numeros
    public static boolean rango(int unMin, int unMax, int ingreso) {

        return ingreso >= unMin && ingreso <= unMax;
    }

    private static void crearPartida(Sistema miSistema) {
        if (miSistema.getListaJugadores().isEmpty()) {
            System.out.println("No hay suficientes jugadores para una partida.");
        } else {
            System.out.println("CREAR NUEVA PARTIDA");
            System.out.println("---------------------------");
            System.out.println("Seleccion de jugadores");
            System.out.println();
            Jugador jugadorRojo = (Jugador) seleccionarDeLista(miSistema.getListaJugadores(), "Seleccione el jugador 1: ");
            Jugador jugadorAzul = (Jugador) seleccionarDeLista(miSistema.getListaJugadores(), "Seleccione el jugador 2: ");
            while (jugadorRojo.equals(jugadorAzul)) {
                System.out.println("Ese jugador fue seleccionado antes. Debe elegir otro");
                jugadorAzul = (Jugador) seleccionarDeLista(miSistema.getListaJugadores(), "Seleccione el jugador 2: ");
            }
            System.out.println();
            boolean seleccionarJugador = false;
            String tipo;
            while (!seleccionarJugador) {
                System.out.println("Seleccione el tipo de partida: \n");
                System.out.println("1. Limite de movimientos");
                System.out.println("2. El que llegue con una ficha");
                System.out.println("3. El que llegue con todas las fichas\n");
                int opcionTipo = soloNumero();
                switch (opcionTipo) {
                    case (1):
                        tipo = "(1) Limite de movimientos";
                        jugarPartida(jugadorRojo, jugadorAzul, tipo, miSistema);
                        seleccionarJugador = true;
                        break;
                    case (2):
                        tipo = "(2) El que llegue con una ficha";
                        jugarPartida(jugadorRojo, jugadorAzul, tipo, miSistema);
                        seleccionarJugador = true;
                        break;
                    case (3):
                        tipo = "(3) El que llegue con todas las fichas";
                        jugarPartida(jugadorRojo, jugadorAzul, tipo, miSistema);
                        seleccionarJugador = true;
                        break;
                    default:
                        System.out.println();
                        System.out.println("Opcion no disponible. Vuelva a intentarlo.");
                        seleccionarJugador = false;
                        break;
                }
            }
        }

    }

    public static void imprimirTablero(Tablero unTablero, String unaVisualizacion) {

        if (unaVisualizacion.equalsIgnoreCase("VERR")) {
            unTablero.tableroReducido(unTablero.getTablero());
        } else {
            unTablero.tableroNormal(unTablero.getTablero());
        }
    }

    public static boolean movimientoEsValido(String mov) { //VER SI ESTE METODO ESTA BIEN

        boolean ok = true;
        mov = mov.trim();
        if (mov.length() == 2) {
            char numFicha = mov.charAt(0);
            if (Character.isDigit(numFicha)) {
                int num = Character.getNumericValue(numFicha);
                if (!rango(1, 8, num)) {
                    ok = false;
                }
                char direccion = mov.toUpperCase().charAt(1);
                if (direccion != 'A' && direccion != 'I' && direccion != 'D') {
                    ok = false;
                }
            } else {
                ok = false;
            }
        } else {
            if (mov.length() == 4) {
                if (!mov.equals("VERR") && !mov.equals("VERN")) {
                    ok = false;
                }
            }
            ok = false;
        }
        return ok;
    }

    public static Object seleccionarDeLista(ArrayList unaLista, String elMensaje) {

        for (int i = 0; i < unaLista.size(); i++) {
            Object elemento = unaLista.get(i);
            if (elemento instanceof Jugador) {
                System.out.print((i + 1) + " - ");
                System.out.println(((Jugador) elemento).getAlias());
            } else if (elemento instanceof Partida) {
                Partida p = (Partida) elemento;
                System.out.println((i + 1) + " - ");
                System.out.println("\nJugador rojo: " + p.getJugadorRojo().getAlias() + "\nJugador azul: " + p.getJugadorAzul().getAlias() + "\nGanador: " + p.getGanador() + "\nTipo de partida: " + p.getTipo());
            }
        }
        int dato = rangoNumero(elMensaje, 1, unaLista.size(), "ERROR. Ingrese un numero dentro del rango.");

        return unaLista.get(dato - 1);
    }

    public static int obtenerNumeroFicha(String mov) {

        int valorFicha = Character.getNumericValue(mov.charAt(0));

        return valorFicha;

    }

    private static char obtenerDireccionMovimiento(String mov) {

        char direccion = mov.toUpperCase().charAt(1);

        return direccion;
    }

    private static String mostrarFichasPosibles(ArrayList fichasPosibles, Partida unaPartida) {
        
        String respuesta = "";

        Scanner entrada = new Scanner(System.in);

        if (fichasPosibles.isEmpty()) {

            respuesta = "";

        } else {
            System.out.println("Los nuevos movimientos posibles son de de la/s ficha/s :");
            for (int i = 0; i < fichasPosibles.size(); i++) {

                System.out.print(fichasPosibles.get(i) + " ");
            }
            System.out.println();
            System.out.println("Desea seguir moviendo? (S: Seguir Moviendo, N: pasar el turno)");
            boolean salir = false;
            while (!salir) {
                respuesta = entrada.nextLine();
                if (respuesta.equalsIgnoreCase("s")) {
                    respuesta = "s";
                    salir = true;
                }
                if (respuesta.equalsIgnoreCase("n")) {
                    respuesta = "n";
                    salir = true;
                }
                if ((!respuesta.equalsIgnoreCase("n")) && (!respuesta.equalsIgnoreCase("s"))) {
                    System.out.println("Opción incorrecta. Vuelva a intentar");
                    salir = false;
                }
            }
        }

        return respuesta;
    }

    private static void jugarPartida(Jugador jugadorRojo, Jugador jugadorAzul, String tipo, Sistema miSistema) {

        
    }

    public static void movimientoUno(Partida unaPartida, String visualizacionTablero) {

        
    }

    public static void movimientoN(Partida unaPartida, String visualizacion, ArrayList movimientosPosibles) {

        
    }

    private static void darVictoriaAGanador(Partida nuevaPartida){

        
               
    }

    private static void replicarPartida(Sistema miSistema) {

        if (!miSistema.getListaPartidas().isEmpty()) {

            System.out.println("\nReplicar partidas");
            Scanner entrada = new Scanner(System.in);
            Partida partidaSeleccionada = (Partida) seleccionarDeLista(miSistema.getListaPartidas(), "\nSeleccione la partida a replicar: \n");

            //colores de los movimientos
            Iterator<String> itColores = partidaSeleccionada.getMovimientosPorColor().iterator();
            //movimientos
            Iterator<String> itMov = partidaSeleccionada.getMovimientos().iterator();
            //genero un tablero para replicarlas
            Tablero tab = new Tablero();

            tab.tableroNormal(tab.getTablero());
            System.out.println("\nPara comenzar presione (Enter)");
            while (itColores.hasNext()) {

                while (itMov.hasNext()) {

                    String visualizacion = "VERN";
                    String colorFicha = itColores.next();
                    String mov = itMov.next();

                    int valorFicha = Character.getNumericValue(mov.charAt(0));

                    boolean termine = false;

                    if (mov.length() == 2) {// si el largo es dos es porque es un movimiento de ficha

                        Ficha fichaAmover = new Ficha(valorFicha, colorFicha, 0, 0);// solo le pongo valor de fichay color por el equals.. porque me la identidfica igual
                        char direccion = mov.toUpperCase().charAt(1);

                        for (int fila = 0; fila < tab.getTablero().length; fila++) {// recorro el tablero para ver si encuentro la ficha

                            for (int col = 0; col < tab.getTablero()[0].length; col++) {

                                Ficha ficha = tab.getTablero()[fila][col];

                                fichaAmover.setPosicionFila(ficha.getPosicionFila());
                                fichaAmover.setPosicionColumna(ficha.getPosicionColumna());

                                if (ficha.equals(fichaAmover) && !termine) { // si encuentra la ficha la mueve y si mueve se detiene y sigue con otro movimiento.

                                    ficha.moverFicha(direccion, fichaAmover.getColor(), tab);

                                    System.out.println("Presione (Enter) para el siguiente movimiento\n");
                                    String opcion = entrada.nextLine();
                                    while (!opcion.isEmpty()) {
                                        System.out.println("\n(ERROR) -> Solo presione (Enter) ");
                                        opcion = entrada.nextLine();
                                    }
                                    if (opcion.isEmpty()) {

                                        System.out.println("Se mueve la ficha: " + fichaAmover + ", Color: " + colorFicha);
                                        imprimirTablero(tab, visualizacion);

                                    }
                                    termine = true;
                                }

                            }
                        }

                    } else if (mov.length() == 1) {// si el largo es uno es porque es X
                        if (partidaSeleccionada.getGanador().equals(partidaSeleccionada.getJugadorRojo().getAlias())) {

                            System.out.println("Partida terminada por Jugador: " + partidaSeleccionada.getJugadorAzul().getAlias());
                        } else {

                            System.out.println("Partida terminada por Jugador: " + partidaSeleccionada.getJugadorRojo().getAlias());

                        }

                    } else { // si no es ninguna de las dos anteriores es un cambio de visualizacion.

                        visualizacion = mov;
                        System.out.println("Cambio visualizacion por jugador: " + "(" + colorFicha + ")");
                        imprimirTablero(tab, visualizacion);

                    }

                }

            }
            System.out.println("Replica de la partida finalizada.");
            System.out.println("Ganador de la partida: " + partidaSeleccionada.getGanador());

        } else {
            System.out.println("No hay partidas registradas.");
        }

    }
}
