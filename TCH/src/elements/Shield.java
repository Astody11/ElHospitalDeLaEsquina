package elements;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Stage;

import managers.AudioManager;
import screens.GameScreen;

public class Shield extends Elements {
	private Animation<TextureRegion> right;
	private Animation<TextureRegion> left;
	public float duration;
	public float timeCounter;
	public int damage;
	private GameScreen lvl;
	public float offsetX;
	public float offsetY;
	
	//offset es desplazamiento
	public Shield(float x, float y, Stage s, float w, float h, float duration, int damage, float offsetX, float offsetY, GameScreen lvl) {
		super(x, y, s, w, h);
		
		right = this.loadFullAnimation("player/Escudo.png", 1, 1, 1, false);
		left = this.loadFullAnimation("player/EscudoL.png", 1, 1, 1, false);
		this.setRectangle();
		this.lvl = lvl;
		
		this.setAnimation(right);
		
		//Caja de colisión
		float[] vertices = {0,0,w,0,w,h,0,h};
		colision = new Polygon(vertices);
		this.setSize(w, h);
		this.damage = damage;
		
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
			colide();
			
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
	
	public void colide() {
		for(Enemies e: lvl.enemies) {
			if(e.getEnabled() && this.overlaps(e)) {
				e.damage(this.damage);
				e.preventOverlap(this);
			}
		}
	}
	
	private void position() {
		if(this.lvl.player.direction == 1) {
			this.setAnimation(right);
			this.setPosition(lvl.player.getX()+this.offsetX*1.2f, lvl.player.getY()+this.offsetY);
		} else {
			this.setAnimation(left);
			this.setPosition(lvl.player.getX()-this.offsetX/2, lvl.player.getY()-this.offsetY/2);
		}
		
	}
	
	public void protect() {
		AudioManager.playSound("audio/sounds/shield.wav");
		this.setEnabled(true);
		
	}

}
