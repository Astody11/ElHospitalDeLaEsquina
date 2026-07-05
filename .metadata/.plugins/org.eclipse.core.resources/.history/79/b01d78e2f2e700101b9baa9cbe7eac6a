package elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Stage;

import managers.AudioManager;
import screens.GameScreen;

public class Shield extends Elements {
	private Animation<TextureRegion> shield;
	public float duration;
	public float timeCounter;
	public int damage;
	private GameScreen lvl;
	public float offsetX;
	public float offsetY;
	
	//offset es desplazamiento
	public Shield(float x, float y, Stage s, float w, float h, float duration, int damage, float offsetX, float offsetY, GameScreen lvl) {
		super(x, y, s, w, h);
		
		shield = this.loadFullAnimation("player/Escudo.png", 1, 1, 1, false);
		this.setRectangle(0, 0, 0, 0);
		this.lvl = lvl;
		
		this.setAnimation(shield);
		
		this.duration = duration;
		this.timeCounter = 3;
		this.lvl = lvl;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		
		this.setEnabled(false);
	}
	
	public void act(float delta) {
		if(this.getEnabled()) {
			super.act(delta);

			position();
			
			if(timeCounter>0) {
				timeCounter -= delta;
			}
			
			if(timeCounter<=0) {
				this.setEnabled(false);
				this.timeCounter = duration;
			}
		
			this.lvl.player.protegida = true;
			
		} else {
			this.lvl.player.protegida = false;
		}
		
		
		
	}
	
	private void position() {
		if(this.lvl.player.direction == 1) {
			this.setPosition(lvl.player.getX()-100, lvl.player.getY());
		} else {
			this.setPosition(lvl.player.getX()-100, lvl.player.getY());
		}
		
	}
	
	public void protect() {
		AudioManager.playSound("audio/sounds/shield.wav");
		this.setEnabled(true);
		
	}

}
