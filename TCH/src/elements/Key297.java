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

public class Key297 extends Interactables {

	private Animation<TextureRegion> key;
	private Label infoLbl;
	
	public Key297(float x, float y, Stage s, GameScreen lvl) {
		super(x, y, s, lvl);
	
		key = loadFullAnimation("maps/images/Llave297.png",1,1,1,false);
		this.setRectangle(200, 0, 0, -300);
		this.lvl = lvl;
		
		infoLbl = new Label("Para interactuar pulsa 'E'", ResourceManager.itemStyle);
		s.addActor(infoLbl);
	}
	
	public void act(float delta) {
		super.act(delta);
		
		if(Parametros.key297) {
			this.setEnabled(false);
		}
		
		if(this.overlaps(this.lvl.player.sensor) && !Parametros.key297) {
			infoLbl.setPosition(this.getX() - this.getWidth()/4, this.getY() + this.getHeight()*1.4f);
			infoLbl.setText("Para interactuar" + "\n" + "pulsa 'E'");
		} else {
			infoLbl.setText("");
		}
				
		
		if(this.getEnabled() && this.overlaps(this.lvl.player.sensor) && Gdx.input.isKeyJustPressed(Keys.E)) {
			Parametros.key297 = true;
			AudioManager.playSound("audio/sounds/YEsto.mp3");
			AudioManager.playSound("audio/sounds/pickup.mp3");
		}
		
	}

}
