package elements;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import game.Parametros;
import managers.AudioManager;
import managers.ResourceManager;
import screens.GameScreen;

public class Partidos extends Enemies {
	
	private Animation<TextureRegion> patrol;
	private Animation<TextureRegion> alert;
	private Animation<TextureRegion> patrolIzq;
	private Animation<TextureRegion> alertIzq;
	boolean persiguiendo;//flag que indica si está persiguiendo al jugador
	private float angle = 0.0f;
	private float vAngle = 0.02f;
	private float aceleracion;
	private float velocidadMaxima;
	
	private float distanciaVision;
	private float tPoison = 2;
	private float tPoisonCounter = 0;
	
	Label lblHp;

	public Partidos(float x, float y, Stage s, GameScreen lvl) {

		super(x, y, s, lvl);
		this.lvl=lvl;
		
		
		persiguiendo = false;
		
		patrol = loadFullAnimation("enemies/PartidoFloats.png", 1, 6, 0.3f, true);
		alert = loadFullAnimation("enemies/PartidoAlert.png", 1, 6, 0.3f, true);
		patrolIzq = loadFullAnimation("enemies/PartidoFloatsIzq.png", 1, 6, 0.3f, true);
		alertIzq = loadFullAnimation("enemies/PartidoAlertIzq.png", 1, 6, 0.3f, true);
		velocidadMaxima = 350;
		aceleracion = 280;
		
		this.hp = 140;
		
		determineDamage();
		
		distanciaVision=750;
		
		enemyHitBox = new Elements(0, 0, s, this.getWidth(), this.getHeight()/8);
		enemyHitBox.setRectangle();
		positionEnemyHitBox();
		
		this.setAnimation(patrol);
		
		lblHp = new Label("" + this.hp, ResourceManager.enemyStyle);
		s.addActor(lblHp);

	}

	@Override
	public void act(float dt) {
		
		super.act(dt);
		
		if(Parametros.jumpscared == true) {
			this.setEnabled(false);
		}
			
		if(this.getEnabled() && this.enemyHitBox.overlaps(this.lvl.player.hitBox) && this.lvl.player.invulnerabilityTime<=0) {
			AudioManager.playSound("audio/sounds/sword.mp3");
		}
		
		if (this.getEnabled() && !this.lvl.player.stayStill) {
			if (this.persiguiendo) {
				this.setAnimation(alert);
				perseguir(lvl.player.getX(),lvl.player.getY(), true);
				
			} else {
				if (Math.sqrt(Math.pow(this.getX() - lvl.player.getX(), 2)
						+ Math.pow(this.getY() - lvl.player.getY(), 2)) < distanciaVision) {
					persiguiendo = true;
				} else {
					double x = 2 * Math.cos(angle) + this.getX();
			        double y = 4 * Math.sin(angle) + this.getY();
			        setPosition((float)x, (float)y);
					angle +=vAngle;
				}
			}

			velocity.add(acceleration.x * dt, acceleration.y * dt);
			velocity.x = MathUtils.clamp(velocity.x, -velocidadMaxima, velocidadMaxima);
			velocity.y = MathUtils.clamp(velocity.y, -velocidadMaxima, velocidadMaxima);

			acceleration.set(0, 0);
			moveBy(velocity.x * dt, velocity.y * dt);
		}
		
		if(this.hp<120) {
			this.persiguiendo = !Parametros.ghostCompannion.equals("Jeremy");
		}
		
		if(!this.persiguiendo) {
			if(this.lvl.player.getX()>this.getX()) {		
				this.setAnimation(patrol);
			} else {
				this.setAnimation(patrolIzq);
			}
		} else {
			if(this.lvl.player.getX()>this.getX()) {
				this.setAnimation(alert);
			} else {
				this.setAnimation(alertIzq);
			}
		}
		
		if(this.tPoisonCounter>0) {
			this.tPoisonCounter-=dt;
		}
		
		if(this.hp<140) {
			if(this.tPoisonCounter<=0 && Parametros.ghostCompannion.equals("Marcos")) {
				this.hp-=8;
				this.tPoisonCounter = this.tPoison;
			} else if(Parametros.ghostCompannion.equals("Hiroaki")) {
				this.velocidadMaxima = 231;
				this.aceleracion = 185;
			} else if(Parametros.ghostCompannion.equals("Aitor")) {
				this.velocidadMaxima = 175;
				this.aceleracion = 140;
			}
		}
		
		lblHp.setPosition(this.getX()+this.getWidth()/2.4f , this.getY() + this.getHeight());
		actualizarLabel();
		
		if(this.lvl.player.protegida) {
			this.damage = 5;
		} else {
			determineDamage();
		}
	}

	
	private void perseguir(float x, float y, boolean accurate) {
		float margen=0;
		if(!accurate) {
			margen=30;
		}
		
		if (x+margen < this.getX()) {
			acceleration.add(-aceleracion, 0);

		} else if (x - margen > this.getX()) {
			acceleration.add(aceleracion, 0);
		}
		if (y + margen< this.getY()) {
			acceleration.add(0, -aceleracion);

		} else if (y - margen> this.getY()) {
			acceleration.add(0, aceleracion);
		}
	}
	
	private void actualizarLabel() {
		if(this.hp>0) {
			lblHp.setText("" + this.hp);
		} else {
			lblHp.setText("");
		}
		
	}
	
	private void determineDamage() {
		if(Parametros.ghostCompannion.equals("Selena")) {
			this.damage = 21;
		} else if(Parametros.ghostCompannion.equals("Mireya")) {
			this.damage = 30;
		} else {
			this.damage = 42;
		}
	}
}
