package elements;

import com.badlogic.gdx.scenes.scene2d.Stage;

import screens.GameScreen;

public class Ghosts extends Elements {
	
	protected GameScreen lvl;
	
	public Ghosts(float x, float y, Stage s, GameScreen lvl) {
		super(x, y, s);	
		this.lvl = lvl;
		
	}
	
	protected void position() {
		if(this.lvl.player.direction == 1) {
			this.setPosition(lvl.player.getX()-580, lvl.player.getY()+50);
		} else {
			this.setPosition(lvl.player.getX()+700, lvl.player.getY()+50);
		}	
	}
}
