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

public class Girasol extends Interactables{
	
	private Label infoLbl;
	
	public Girasol(float x, float y, Stage s, GameScreen lvl) {
		super(x, y, s, lvl);
		loadFullAnimation("maps/images/girasol.png", 1, 1, 1, false);
		this.lvl = lvl;
		
		infoLbl = new Label("'E'", ResourceManager.itemStyle);
		s.addActor(infoLbl);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		if(this.getEnabled() && this.overlaps(this.lvl.player.sensor) && Gdx.input.isKeyJustPressed(Keys.E)) {
			AudioManager.playSound("audio/sounds/npcs/oyeTu.mp3");
			Parametros.sunflower = true;
		}
		
		if(this.getEnabled() && this.overlaps(this.lvl.player.sensor) && !Parametros.sunflower) {
			infoLbl.setPosition(this.getX() + this.getWidth()/2.5f, this.getY() + this.getHeight()*1.4f);
			infoLbl.setText("'E'");
		} else {
			infoLbl.setText("");
		}
		
	}

}
