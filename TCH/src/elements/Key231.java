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

public class Key231 extends Interactables {

	private Animation<TextureRegion> key;
	
	private Label infoLbl;
	
	//Tiempo de retardo
	private float pickUpCoolDown = 1.2f;
	
	public Key231(float x, float y, Stage s, GameScreen lvl) {
		super(x, y, s, lvl);
	
		key = loadFullAnimation("maps/images/Llave231.png",1,1,1,false);
		this.setRectangle();
		this.lvl = lvl;
		this.setEnabled(false);
		
		infoLbl = new Label("'E'", ResourceManager.itemStyle);
		s.addActor(infoLbl);
	}
	
	public void act(float delta) {
		super.act(delta);
		
		if(this.pickUpCoolDown >0) {
			this.pickUpCoolDown -= delta;
		}
		
		if(Parametros.chestPuzzleSolved) {
			this.setEnabled(true);
		}
		
		if(this.getEnabled() && this.overlaps(this.lvl.player.sensor)) {
			infoLbl.setPosition(this.getX() + this.getWidth()/2.5f, this.getY() + this.getHeight()*1.4f);
			infoLbl.setText("'E'");
		} else {
			infoLbl.setText("");
		}
		
		if(Parametros.key231) {
			this.setEnabled(false);
			infoLbl.setText("");
		}
				
		if(this.getEnabled() && this.overlaps(this.lvl.player.sensor) && Gdx.input.isKeyJustPressed(Keys.E) && this.pickUpCoolDown<=0) {
			AudioManager.playSound("audio/sounds/pickup.mp3");
			Parametros.key231 = true;
		}
		
	}

}
