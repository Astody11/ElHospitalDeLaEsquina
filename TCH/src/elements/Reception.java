package elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

import game.Parametros;
import screens.GameScreen;

public class Reception extends Interactables {
	
	private Animation<TextureRegion> key;
	private Animation<TextureRegion> noKey;
	
	public Reception(float x, float y, Stage s, GameScreen lvl) {
		super(x, y, s, lvl);
		key = this.loadFullAnimation("maps/images/recibidorLlave.png", 1, 1, 1, false);
		noKey = this.loadFullAnimation("maps/images/recibidor.png", 1, 1, 1, false);
		this.setRectangle();
		this.lvl = lvl;
		this.setAnimation(key);
	}
	
	public void act(float delta) {
		super.act(delta);
		
		if(this.getEnabled() && this.overlaps(this.lvl.player) && Gdx.input.isKeyJustPressed(Keys.E)) {
			this.setAnimation(noKey);
			Parametros.key297 = true;
		}
		
		if(Parametros.key297) {
			this.setAnimation(noKey);
		}
		
	}
}
