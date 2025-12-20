package elements;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import game.Parametros;
import managers.ResourceManager;
import screens.GameScreen;

public class Sanson extends Boss {

		private float tThrow;
		private float tThrowOnARow;
		private float throwCount;
		private float tHit;
		private float tHitOnARow;
		private float hitCount;
		private ArrayList<CetroEnAccion> scepters;
		private int nScepters = 1;
		private int currentScepter;
		private ArrayList<CetroEnAccion2> scepters2;
		private int nScepters2 = 1;
		private int currentScepter2;
		private float angle = 0.0f;
		private float vAngle = 0.02f;
		private float velocidadMaxima;
		private int onARow = 3;
		private int hitsOnARow = 2;
		Label lblHp;
				
		public Sanson(float x, float y, Stage s, GameScreen lvl) {
			super(x, y, s, lvl);
			
			this.lvl = lvl;
			this.loadFullAnimation("npcs/SansonFloatsT.png", 1, 15, 0.2f, true);
			this.hp = 650;
			this.setRectangle(300, 270, 300, 600);
			
			currentScepter = 0;
			scepters = new ArrayList<CetroEnAccion>();
			currentScepter2 = 0;
			scepters2 = new ArrayList<CetroEnAccion2>();
			CetroEnAccion cetro;
			this.tThrow = 36;
			this.tThrowOnARow = 5;
			this.throwCount = this.tThrowOnARow;
			this.tHit = 29;
			this.tHitOnARow = 8;
			this.hitCount = tHit;
			velocidadMaxima = 300;
				
			for(int i=0; i<nScepters; i++) {
				cetro = new CetroEnAccion(0,0,s, lvl);
				
				scepters.add(cetro);
			}
			CetroEnAccion2 cetro2;
			for(int i=0; i<nScepters2; i++) {
				cetro2 = new CetroEnAccion2(0,0,s, lvl);
				
				scepters2.add(cetro2);
			}
			
			
			
			lblHp = new Label("" + this.hp, ResourceManager.bossStyle);
			s.addActor(lblHp);
			
			invulnerability = false;
			invulnerabilityDuration = 1;
			invulnerabilityCounter = invulnerabilityDuration;
		
		}
		
		@Override
		public void act(float dt) {
			super.act(dt);
			
			if(this.getEnabled()) {
				double x = 20 * Math.cos(angle) + this.getX();
		        double y = 0.02f * Math.sin(angle) + this.getY();
		        setPosition((float)x, (float)y);
				angle +=vAngle;

				velocity.add(acceleration.x * dt, acceleration.y * dt);
				velocity.x = MathUtils.clamp(velocity.x, -velocidadMaxima, velocidadMaxima);
				velocity.y = MathUtils.clamp(velocity.y, -velocidadMaxima, velocidadMaxima);

				acceleration.set(0, 0);
				moveBy(velocity.x * dt, velocity.y * dt);
			}
			
			if(this.getEnabled() && throwCount<=0 && this.onARow>0) {
				
	        	throwScepter();
	        	throwCount = tThrowOnARow;
	        	this.onARow--;
	        } else if(this.getEnabled() && throwCount<=0 && this.onARow==0) {
	        	throwScepter();
	        	throwCount = tThrow;
	        	this.onARow = 3;
	        	
	        } else {
	        	throwCount-=dt;
	        }
			if(this.getEnabled() && hitCount<=0 && this.hitsOnARow>0) {
	        	scepterHit();
	        	hitCount = tHitOnARow;
	        	this.hitsOnARow--;
	        } else if(this.getEnabled() && hitCount<=0 && this.hitsOnARow==0) {
	        	scepterHit();
	        	hitCount = tHit;
	        	this.hitsOnARow = 3;
	        }
			else {
	        	hitCount-=dt;
	        }
			
			if(this.hp<=0) {
				this.setEnabled(false);
			}
			
			if(Parametros.nivel == 201 && !this.getEnabled()) {
				this.lvl.player.win = true;
			}
			
			lblHp.setPosition(this.getX() + this.getWidth()/2, this.getY() + this.getHeight()/1.1f);
			actualizarLabel();
			
		}
		
		
		private void throwScepter() {
			
			this.throwCount = this.tThrow;
			this.scepters.get(currentScepter).throwScepter(1, 680);
			this.currentScepter = (this.currentScepter+1)%this.nScepters;
		}
		
		private void scepterHit() {
			
			this.hitCount = this.tHit;
			this.scepters2.get(currentScepter2).scepterHit(1, 220);
			this.currentScepter2 = (this.currentScepter2+1)%this.nScepters2;
		}
		
		private void actualizarLabel() {
			if(this.hp>0) {
				lblHp.setText("" + this.hp);
			} else {
				lblHp.setText("");
			}
			
		}

	}

