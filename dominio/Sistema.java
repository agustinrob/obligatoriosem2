package dominio;


import java.util.ArrayList;
import java.util.Collections;


public class Sistema {
    
    private ArrayList <Jugador> listaJugadores;
    private ArrayList <Partida> listaPartidas; 
    
    public ArrayList getListaJugadores() {
        return listaJugadores;
    }

    public ArrayList getListaPartidas() {
        return listaPartidas;
    }
    
    public Sistema() {
        
        this.listaJugadores = new ArrayList<>();
        this.listaPartidas = new ArrayList<>();
    }
    
    public void crearJugador(String nombre,String alias,int edad){
               
        Jugador nJugador = new Jugador (nombre,edad,alias);
        this.listaJugadores.add(nJugador); 
        
    }
    
    public void crearPartida(Partida unaPartida){
              
        this.listaPartidas.add(unaPartida);     
    }
    
    public boolean existeJugador(String unAlias){
        Jugador jugadorAux = new Jugador (null,0,unAlias); 
        return listaJugadores.contains(jugadorAux);
    }
    
    public ArrayList <Jugador> ordenPartidasGanadas(){
         
        ArrayList<Jugador> ranking = this.getListaJugadores();
        Collections.sort(ranking);
        
        return ranking;
    
    }
    public ArrayList <String> nombresRanking(){
        ArrayList<String> aliases = new ArrayList<>();
        for(Jugador jugadores : this.listaJugadores){
            aliases.add(jugadores.getAlias());
        }
        return aliases;
    }
}

