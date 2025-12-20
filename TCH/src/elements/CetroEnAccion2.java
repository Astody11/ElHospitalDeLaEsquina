package elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;

import game.Parametros;
import managers.AudioManager;
import screens.GameScreen;

public class CetroEnAccion2 extends Interactables{
	private Animation<TextureRegion> follow;
	private Animation<TextureRegion> hit;
	protected float timeOnAir;
	protected float cuentaTiempo;
	protected float throwSpeed;
	protected float hitTime = 8;
	protected float hitCounter;
	protected boolean isFollowing;
	protected int direccion = 1;
	public int damage;
	private float aceleracion;
	
	public CetroEnAccion2(float x, float y, Stage s, GameScreen lvl) {
		super(x, y, s, lvl);
		
		this.lvl = lvl;
		hit = this.loadFullAnimation("maps/images/cetroHit.png", 1, 1, 1, true);
		follow = this.loadFullAnimation("maps/images/cetroFollows.png", 1, 1, 1, true);
		
		timeOnAir = 10;
		hitCounter = hitTime;
		this.damage = 0;
		aceleracion = 150;
		throwSpeed=150;
		this.setEnabled(false);
		this.setAnimation(follow);
	}
		
		
		
		@Override
		public void act(float dt) {
			super.act(dt);
			if(this.getEnabled()) {
				
				moveBy(velocity.x*dt, velocity.y*dt);
			
				hitCounter -= dt;
				if(hitCounter>2 && hitCounter<=6) {
						AudioManager.playSound("audio/sounds/scepterHit.mp3");
				}
				if(hitCounter>0 && hitCounter<1) {
					this.setAnimation(hit);
					this.damage = 225;
				}
				
				if(this.hitCounter>=1) {
					this.damage=0;
					this.setAnimation(follow);
				} else if(this.hitCounter<=0) {
					hitCounter = this.hitTime;
				}
				
				if(this.overlaps(this.lvl.player.hitBox) && this.hitCounter<1) {
					this.lvl.player.damage(this.damage);
				}
				
				if(isFollowing) {
					follow(lvl.player.getX());
				} else {
					notFollow(this.getX());
				}
				
				cuentaTiempo-=dt;
			
				if(cuentaTiempo<=0) {
					this.setEnabled(false);
				}
				
				velocity.add(acceleration.x * dt, 1);
				velocity.x = MathUtils.clamp(velocity.x, -300, 300);
				velocity.y = 1;

				acceleration.set(0, 0);
				moveBy(velocity.x * dt, velocity.y * dt);
			
			}
		}


		@Override
		public void draw(Batch batch, float parentAlpha) {
			if (getEnabled()) super.draw(batch, parentAlpha);
		}
		
		
		public void scepterHit(float posx, float posy) {
			this.setEnabled(true);
			cuentaTiempo = timeOnAir;
			this.setPosition(this.lvl.player.getX(), 150);
			isFollowing = true;
			this.damage = 0;
			if(hitCounter <=3) {
				this.isFollowing = false;
				this.acceleration.x = 0;
				this.velocity.x = 0;
				this.velocity.y = 0;
			}
			
			
		}
		
		private void follow(float x) {
			if (x < this.getX()) {
				acceleration.add(-aceleracion*3, 0);

			} else if (x > this.getX()) {
				acceleration.add(aceleracion*3, 0);
			}
		}
		
		private void notFollow(float x) {
			acceleration.add(0, 0);
			this.velocity.x = 0;
			this.velocity.y = 0;
		}


}


