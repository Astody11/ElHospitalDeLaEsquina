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

public class Oven extends Interactables{
	
	private Animation<TextureRegion> closedOven;
	private Animation<TextureRegion> openOven;
	
	private Label infoLbl;
	
	public Oven(float x, float y, Stage s, GameScreen lvl) {
		super(x, y, s, lvl);
		
		closedOven = loadFullAnimation("maps/images/ClosedOven.png", 1, 1, 1, false);
		openOven = loadFullAnimation("maps/images/OpenOven.png", 1, 1, 1, false);
		
		infoLbl = new Label("'E'", ResourceManager.itemStyle);
		s.addActor(infoLbl);
		
		this.setAnimation(closedOven);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		if(this.getEnabled() && this.overlaps(this.lvl.player.sensor) && Gdx.input.isKeyJustPressed(Keys.E) && !Parametros.cats) {
			if(Parametros.ghostCompannion.equals("Jeremy")) {
				AudioManager.playSound("audio/sounds/ovenDoor.mp3");
				this.setAnimation(openOven);
				Parametros.cats = true;
				Parametros.ghostCompannion = "";
			} else {
				AudioManager.playSound("audio/sounds/noAbre.mp3");
			}
			
		}
		
		if(this.getEnabled() && this.overlaps(this.lvl.player.sensor) && !Parametros.cats) {
			infoLbl.setPosition(this.getX() + this.getWidth()/2.5f, this.getY() + this.getHeight()*1.2f);
			infoLbl.setText("'E'");
		} else {
			infoLbl.setText("");
		}
		
	}

}
