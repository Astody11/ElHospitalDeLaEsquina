package elements;

import com.badlogic.gdx.scenes.scene2d.Stage;

import screens.GameScreen;

public class Interactables extends Elements{
	
	protected GameScreen lvl;
	
	public Interactables(float x, float y, Stage s, GameScreen lvl) {
		super(x, y, s);	
		this.lvl = lvl;
		
	}
}
