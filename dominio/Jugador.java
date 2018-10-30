package dominio;

import java.util.ArrayList;

public class Jugador implements Comparable<Jugador> {

    private String nombre;
    private String alias;
    private int edad;
    private int partidasGanadas;
    
    private String color;
    

    @Override
    public int compareTo(Jugador o) {

        int orden = o.partidasGanadas - this.partidasGanadas;
        if (orden == 0) { //si tienen las misma cantidad de partidas ganadas, ordeno por alias (alfabeticamente)

            orden = this.alias.compareTo(o.alias);
        }

        return orden;

    }
    
    public String getColor() {

        return color;
    }

    public void setColor(String unColor) {

        color = unColor;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAlias() {
        return alias;
    }

    public int getEdad() {
        return edad;
    }

    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setPartidasGanadas(int partidasGanadas) {
        this.partidasGanadas = partidasGanadas;
    }

    public Jugador() {

        this.nombre = null;
        this.edad = '0';
        this.alias = null;
        this.partidasGanadas = 0;
       
        this.color = null;
        
    }

    public Jugador(String nombre, int edad, String alias) {

        this.nombre = nombre;
        this.edad = edad;
        this.alias = alias;
        this.partidasGanadas = 0;
        this.color = null;
    }
    
    @Override
    public boolean equals(Object o) {

        boolean sonIguales = true;
        if (o == null) {
            sonIguales = false;
        } else if (!(o instanceof Jugador)) { //instanceof es usado para conocer si un objeto es de un tipo determinado.

            sonIguales = false;
        } else {
            Jugador otroJugador = (Jugador) o;
            sonIguales = this.getAlias().equalsIgnoreCase(otroJugador.getAlias());
        }

        return sonIguales;
    }

    @Override
    public String toString() {

        return "Nombre del jugador: " + this.getNombre() + "\nAlias: " + this.getAlias() + "\nEdad: " + this.getEdad() + "\nPartidas ganadas: " + this.getPartidasGanadas();

    }

}
