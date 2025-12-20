package elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

import game.Parametros;
import managers.AudioManager;
import screens.GameScreen;

public class CetroEnAccion extends Interactables{

	protected float timeOnAir;
	protected float cuentaTiempo;
	protected int direccion = 1;
	public int damage;
	
	public CetroEnAccion(float x, float y, Stage s, GameScreen lvl) {
		super(x, y, s, lvl);
		
		this.lvl = lvl;
		this.loadFullAnimation("maps/images/scepterThrow.png", 1, 8, 0.05f, true);
		timeOnAir = 20;
		this.damage = 150;
		this.setEnabled(false);
		this.setRectangle(80, 80, 280, 280);
	}
		
		@Override
		public void act(float dt) {
			super.act(dt);
			if(this.getEnabled()) {
				
				moveBy(velocity.x*dt, velocity.y*dt);	
				
				cuentaTiempo-=dt;
			
				if(cuentaTiempo<=0) {
					this.setEnabled(false);
				}
			
			}
			
			if(this.getEnabled() && this.overlaps(this.lvl.player.hitBox)) {
				this.lvl.player.damage(this.damage);
			}
		}


		@Override
		public void draw(Batch batch, float parentAlpha) {
			if (getEnabled()) super.draw(batch, parentAlpha);
		}


		public void throwScepter(float posx, float posy) {
			AudioManager.playSound("audio/sounds/cetro.mp3");
			this.setEnabled(true);
			cuentaTiempo = timeOnAir;
			this.setPosition(this.lvl.player.getX(), 1500);
			this.velocity.x = 0;
			this.velocity.y = -posx*posy;
		}
		
}


