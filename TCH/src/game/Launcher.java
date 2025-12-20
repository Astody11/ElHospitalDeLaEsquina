package game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import org.lwjgl.opengl.Display;

public class Launcher
{
    public static void main (String[] args) {
    	Game myGame = new Demo();
	    //LwjglApplication launcher = new LwjglApplication(myGame, "El Hospital de la Esquina", Parametros.getAnchoPantalla(), Parametros.getAltoPantalla());
	    LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
	    config.title = "El Hospital de la Esquina";
	    config.height = Parametros.getAltoPantalla();
	    config.width = Parametros.getAnchoPantalla();
	    config.fullscreen = false;
	    config.resizable = false;
	    
	    LwjglApplication launcher = new LwjglApplication(myGame, config);
    }
}