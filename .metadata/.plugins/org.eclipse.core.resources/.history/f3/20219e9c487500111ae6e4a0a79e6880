package elements;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import managers.AudioManager;
import screens.GameScreen;

public class Boss extends Elements {

	protected GameScreen lvl;
	public int hp = 400;
	public int damage;
	protected boolean invulnerability;
	protected float invulnerabilityCounter;
	protected float invulnerabilityDuration;
	
	//Stage = gestor de lo q sucede en pantalla
	public Boss(float x, float y, Stage s, GameScreen lvl) {
		super(x, y, s);	
		this.lvl = lvl;
		this.invulnerability = false;
		this.invulnerabilityDuration = 1;
		this.damage = 0;
		
	}
	
	public void draw(Batch batch, float parentAlpha) {
		if(this.invulnerability) {
			super.draw(batch, parentAlpha*0.5f);
		} else {
			super.draw(batch, parentAlpha);
		}
	}
	
	public void act(float delta) {
		super.act(delta);
		if(this.hp <=0) {
			this.setEnabled(false);
		}
		
		if(this.invulnerabilityCounter>0) {
			this.invulnerabilityCounter-=delta;
		} else {
			this.invulnerability = false;
		}
		
		colide();
		
	}
	
	public void damage(int damage) {
		if(!this.invulnerability) {
			this.hp -= damage;
			AudioManager.playSound("audio/sounds/bossHit.mp3");
		}
		this.invulnerability = true;
		this.invulnerabilityCounter -= this.invulnerabilityDuration;
	
	}
	
	public void colide() {
		if(this.getEnabled() && this.overlaps(this.lvl.player)) {
			
			lvl.player.damage(this.damage);
		}
		
	}
	
}