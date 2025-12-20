package elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import game.Parametros;
import managers.AudioManager;
import managers.ResourceManager;
import screens.GameScreen;

public class Key243 extends Interactables {
	
	private Label infoLbl;
	
	public Key243(float x, float y, Stage s, GameScreen lvl) {
		super(x, y, s, lvl);
	
		this.loadFullAnimation("maps/images/Llave243.png",1,1,1,false);
		this.setRectangle();
		this.lvl = lvl;
		
		this.setEnabled(false);
		
		infoLbl = new Label("'E'", ResourceManager.itemStyle);
		s.addActor(infoLbl);
		
	}
	
	public void act(float delta) {
		super.act(delta);
		
		if(Parametros.chestPuzzleSolved) {
			
			this.setEnabled(true);
		}
		
		if(this.getEnabled() && Parametros.key243) {
			this.setEnabled(false);
			infoLbl.setText("");
		}
		
		if(this.getEnabled() && this.overlaps(this.lvl.player.sensor)) {
			infoLbl.setPosition(this.getX() + this.getWidth()/2.5f, this.getY() + this.getHeight()*1.4f);
			infoLbl.setText("'E'");
		} else {
			infoLbl.setText("");
		}
				
		
		if(this.getEnabled() && this.overlaps(this.lvl.player.sensor) && Gdx.input.isKeyJustPressed(Keys.E)) {
			AudioManager.playSound("audio/sounds/pickup.mp3");
			Parametros.key243 = true;
			
		}
		
	}

}
