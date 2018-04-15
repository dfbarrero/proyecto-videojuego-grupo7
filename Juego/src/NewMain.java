
import enemigos.Enemigo;
import items.Arma;
import items.Armadura;
import otros.Habilidad;
import otros.Inventario;
import personajes.Horacia;
import personajes.Jugador;
import personajes.Kibito;
import personajes.Mordeim;
import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.SlickException;
import otros.Gestion;

import personajes.Horacia;
import personajes.Jugador;
import personajes.Personaje;

public class NewMain {
    static ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
    
    public static void main(String[] args) throws SlickException {

        
//        Gestion ges= new Gestion();
//        ArrayList<ArrayList<Enemigo>> enem;
//        enem= ges.cargarGrupoEnemigos();
//        for (int i = 0; i < enem.get(0).size(); i++) {
//            System.out.println(enem.get(0).get(i));    
//        }
//        System.out.println("-------");
//        ArrayList<Personaje> enemigos= new ArrayList<Personaje>();
//        enemigos.add((Personaje)enem.get(0).get(0));
//        enemigos.add((Personaje)enem.get(0).get(1));
//        enemigos.add((Personaje)enem.get(0).get(2));
//        enemigos.get(0).setHpActual(1);
//        for (int i = 0; i < enem.get(0).size(); i++) {
//            System.out.println(enem.get(0).get(i));    
//        }
//        System.out.println("-----");
//        for (int i = 0; i < enemigos.size(); i++) {
//            enemigos.get(i).setHpActual(enemigos.get(i).getHp());
//            
//        }
//        for (int i = 0; i < enem.get(0).size(); i++) {
//            System.out.println(enem.get(0).get(i));    
//        }
        
        //Horacia horacia = new Horacia();
        //jugadores.add(horacia);
        //System.out.println(jugadores.get(0).getNombre());
        //guardarJugadores();
        //cargarJugadores();
        //System.out.println(jugadores.get(0).getNombre());
        //System.out.println(jugadores.get(0).getHpActual());
        
//        BaseDatosEnemigos bd = new BaseDatosEnemigos();
//        for (int i = 0; i < bd.getEnem().size(); i++) {
//            System.out.println(bd.getEnem().get(i).size());
//            for (int j = 0; j < bd.getEnem().get(i).size(); j++) {
//                System.out.println(bd.getEnem().get(i).get(j).toString());
//            }
//        }
        //System.out.println(bd.getEnem().get(0).get(0).getNombre());
        /*BaseDatosEnemigos bd = new BaseDatosEnemigos();
        for (int i = 0; i < bd.getEnem().size(); i++) {
            System.out.println(bd.getEnem().get(i).size());
            for (int j = 0; j < bd.getEnem().get(i).size(); j++) {
                System.out.println(bd.getEnem().get(i).get(j).toString());
            }
        }*/
        Gestion ges = new Gestion();
        ges.enem = ges.cargarGrupoEnemigos("BaseDatos/enemigosBosque.dat");
        for (int i = 0; i < ges.enem.size(); i++) {
            System.out.println(ges.enem.get(i));
        }
        
        ArrayList<Personaje> partyEnemiga = new ArrayList<Personaje>();
        ArrayList<Personaje> party = new ArrayList<Personaje>();
        Random rand = new Random();
        int ini = 0; //dependiendo del nivel emepzamos a buscar en una pos u otra
        int fin = rand.nextInt(6);//Como hay 6 tipos de party elegimos entre la 0-5
        int total;
        Horacia h = new Horacia();
        party.add(h);
        int nivel=0;
        //Nos quedamos el mayor nivel
        for (int i = 0; i < party.size(); i++) {
            if (party.get(i).getNivel() > nivel)
                nivel = party.get(i).getNivel();
        }
        
        //Esto es la comprobacion dentro de bosque, en otro mapa sera distinto
        //tenemos que ver como optimizarlo
        if (nivel <= 1)
            ini = 0;
        else if (nivel <= 3)
            ini = 6;
        else
            ini = 12;
        
        //Con total sacamos la party que queremos para guardarla en una partyEnemiga
        //para que David la use en su Combate
        total = ini + fin;
        for (int i = 0; i < ges.enem.get(total).size(); i++) {
            partyEnemiga.add(ges.enem.get(total).get(i));
        }
        System.out.println("-----------------------");
        for (int i = 0; i < partyEnemiga.size(); i++) {
            System.out.println(partyEnemiga.get(i));
        }
        //Luego acordarse de darle la vida de nuevo como vimos ayer David
        
    }
    
}