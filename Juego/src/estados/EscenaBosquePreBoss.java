package estados;

import java.awt.Font;
import java.util.Vector;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author Dolores
 */
public class EscenaBosquePreBoss extends BasicGameState{
    private int idEstado;
    private static final int POSICIONAVATARX = 30;
    private static final int POSICIONAVATARY = 620;
    private static final int TAMANYOAVATARX = 115;
    private static final int TAMANYOAVATARY = 115;
    //avatarDialogo.draw(30, 610, 115, 125);
    private static final int TILESIZE = 32;
    /*Texto*/
    private TrueTypeFont mensajePantalla;
    private Font tipoLetra  =new Font("Verdana", Font.PLAIN, 15);
    private String linea1="";
    private String linea2="";
    private String linea3="";
    private String linea4="";
    private String linea5="";
    /*Control de escena*/
    private Input input;
    private int estado;
    private boolean reproducirExclamacion=false;
    /*Mapa*/
    private Vector2f posicion,posicionE;
    private static final int esquinaXMapa=0;
    private static final int esquinaYMapa=0;
    /*Animaciones*/
    private SpriteSheet sheetExclamacion;
    private Animation exclamacion;
    private Animation hor,kib,mor;
    private Animation horD,kibD,morD;
    private Animation horI,kibS,morS;
    private Animation boss,bossI,bossE;
    private Animation horE;
    private Image fondo;
    /*Imagenes*/
    private Image ventanaDialogo,avatarDialogo, avatarH,avatarM, avatarK, avatarDesconocido;
    private Image salidaEscena;
    /*Sonido*/
    private Sound sonidoSelect,rugido;
    private Music battle;
    int time;//EDIT
    private TrueTypeFont texto;
    private Font letraMenu  = new Font("Arial Black", Font.PLAIN, 15); 
    
    public EscenaBosquePreBoss(int id) {
        this.idEstado=id;
    }
    @Override
    public int getID() {
        return idEstado;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        Image[] horDere={new Image("Imagenes/HeroeMundo/her10.png"),new Image("Imagenes/HeroeMundo/her11.png"),new Image("Imagenes/HeroeMundo/her12.png")};
        horD=new Animation(horDere,200);
        Image[] morDere={new Image("Imagenes/Animaciones/Sprites/mor7.png"),new Image("Imagenes/Animaciones/Sprites/mor8.png"),new Image("Imagenes/Animaciones/Sprites/mor9.png")};
        morD=new Animation(morDere,200);
        Image[] kibDere={new Image("Imagenes/Animaciones/Sprites/kib7.png"),new Image("Imagenes/Animaciones/Sprites/kib8.png"),new Image("Imagenes/Animaciones/Sprites/kib9.png")};
        kibD=new Animation(kibDere,200);
        Image[] horIzq={new Image("Imagenes/HeroeMundo/her31.png")};
        horI=new Animation(horIzq,200);
        Image[] kibF={new Image("Imagenes/Animaciones/Sprites/kib8.png")};
        kibS=new Animation(kibF,200);
        Image[] morF={new Image("Imagenes/Animaciones/Sprites/mor8.png")};
        morS=new Animation(morF,200);
        Image[] horEnfrente={new Image("Imagenes/HeroeMundo/her11.png")};
        horE=new Animation(horEnfrente,200);
        hor=horD;
        kib=kibD;
        mor=morD;
        Image[] bossMove={new Image("Imagenes/Animaciones/Sprites/arbol5.png"),new Image("Imagenes/Animaciones/Sprites/arbol6.png"),new Image("Imagenes/Animaciones/Sprites/arbol7.png"),new Image("Imagenes/Animaciones/Sprites/arbol8.png")};
        bossI=new Animation(bossMove,200);
        Image[] bossStop={new Image("Imagenes/Animaciones/Sprites/arbol6.png")};
        bossE=new Animation(bossStop,200);
        boss=bossI;
        fondo= new Image("Imagenes/Escenas/EscenaBosque1/mapaBosque.png");
        /**/
        this.sheetExclamacion= new SpriteSheet("Imagenes/Animaciones/puntos.png",32,33);
        this.exclamacion = new Animation(sheetExclamacion,200);
        /**/
        estado=0;
        this.input = gc.getInput();
        mensajePantalla= new TrueTypeFont(tipoLetra, true);
        posicion = new Vector2f(0,300);
        posicionE = new Vector2f(0,300);
        ventanaDialogo= new Image("Imagenes/Avatar/cajaMensaje.png");
        avatarH =  new Image("Imagenes/Personajes/HoraciaA.png");
        avatarM =  new Image("Imagenes/Personajes/MordeimA.png");
        avatarK =  new Image("Imagenes/Personajes/KibitoA.png");
        avatarDesconocido = new Image("Imagenes/Personajes/Arbol.png");
        avatarDialogo = avatarH;
        sonidoSelect=new Sound("Musica/Efectos/select.wav");
        rugido=new Sound("Musica/Efectos/rugido1.wav");
        texto= new TrueTypeFont(letraMenu, true);
        this.battle = new Music("Musica/BSO/Escena_Yggdrasil.wav");
        /**/
        
    }

    @Override
    //Muestra por pantalla
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException { 
        
            fondo.draw(-1800, -1184);
            
            //EDIT:Rener Mordeim
            if(reproducirExclamacion){
                this.exclamacion.draw(posicion.x-64, posicion.y+176);
            }
            if(estado>=0){
                hor.draw(posicion.x, posicion.y+240);
                mor.draw(posicion.x-64, posicion.y+272);
                //mor.draw(posicion.x-64, posicion.y+32);
                kib.draw(posicion.x-64, posicion.y+208);
                //kib.draw(posicion.x-64, posicion.y-32);
                if(estado>=1 && estado!=6){
                renderDialogo();
                }
                if(estado>=6){
                    boss.draw(posicionE.x+664, posicionE.y+212);
                }
                
            }
            texto.drawString(1000, 0, "" + estado);
    }
    @Override
    //Muestra la actualización
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        exclamacion.update(i);
        if(input.isKeyPressed(Input.KEY_ENTER)){
            if(estado!=15)
            {
                sonidoSelect.play(1, 0.2f);
                time=0;
                estado++;
                if(estado>=6){
                    battle.play();
                    if(estado<=13){
                        battle.stop();
                }
                }
            }
            
        }
        switch (estado)
        {
            case 0:
                posicion.x+=0.1f*i;
                if(posicion.x>=300){
                    estado++;
                }
                break;
            case 1:
                hor=horI;
                kib=kibS;
                mor=morS;
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="¡¿Cuándo saldremos de este maldito bosque?!";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 2:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="No lo se Mordeim, pero empiezo ha estar cansada de";
                linea2="tanto andar.";
                linea3="¿Cuánto falta para salir Kibito?";
                linea4="";
                break;
            case 3:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Claaaaaaaaaaro, como soy el mago tengo que saberlo";
                linea2="absolutamente todo de este bosque.";
                linea3="";
                linea4="";
                break;
            case 4:
                time+=i;
                if(!rugido.playing())
                {
                    rugido.play();
                }
                if(time/1000>0.4f)//
                {
                    time=0;
                    estado++;
                }
                avatarDialogo=this.avatarDesconocido;
                linea1="GRRRRRRRRRRRRRRRRRRRR!!!!!!";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 5:
                reproducirExclamacion=true;
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Para que hablaré.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 6:
                reproducirExclamacion=false;
                hor=horE;
                boss=bossI;
                posicionE.x-=0.1f*i;
                if(posicionE.x<=(-300)){
                    estado++;
                }
                break;
            case 7:
                boss=bossE;
                avatarDialogo=this.avatarDesconocido;
                //////="////////////////////////////////////////////////////////";
                linea1="¡¡¡¡¡¡FUEEERAAAAA DE ESTE BOSQUEEEEEEEEEEEEE!!!!!!";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 8:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Eres el Dios del Bosque?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 9:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Dios del Bosque o no es un enemigo, acabemos con él.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 10:
                avatarDialogo=this.avatarDesconocido;
                //////="////////////////////////////////////////////////////////";
                linea1="LAMENTAREÍS ENFRENTAROS A MI, AL GRAN YGGDRASIL!!!!";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 11:
                avatarDialogo=this.avatarH;
                linea1="Espera, Yggdrasil es el Árbol de la Vida de la";
                linea2="mitología nórdica.";
                linea3="¿Eres el Dios del Bosque o de la Vida?";
                linea4="";
                break;
                
            case 12:
                avatarDialogo=this.avatarDesconocido;
                //////="////////////////////////////////////////////////////////";
                linea1="SOY AMBAS COSAS!!!!!";
                linea2="PREPARAOS PARA MORIR!!!!!!!";
                linea3="";
                linea4="";
                break;
            case 13:
                time+=i;
                reproducirExclamacion=true;
                if(time/1000>1f)//
                {
                    reproducirExclamacion=false;
                    time=0;
                    estado++;
                }
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Quién diablos es el guionista de este juego?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 14:
                estado=0;
                sbg.enterState(VenganzaBelial.ESTADOMENUINICIO);
                break;

        }
    }
    
    private void renderDialogo()
    {
        avatarDialogo.draw(POSICIONAVATARX, POSICIONAVATARY, TAMANYOAVATARX, TAMANYOAVATARY);
        this.ventanaDialogo.draw(0, 600, 1);
        ///////////////////////////////////,"////////////////////////////////////////////////////////"/;
        mensajePantalla.drawString(160, 625,linea1);
        mensajePantalla.drawString(160, 640,linea2);
        mensajePantalla.drawString(160, 655,linea3);
        mensajePantalla.drawString(160, 670,linea4);
    }
    
}