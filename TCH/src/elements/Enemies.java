package elements;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import game.Parametros;
import managers.AudioManager;
import screens.GameScreen;

public class Enemies extends Elements {

	protected GameScreen lvl;
	public int hp;
	public boolean invulnerability;
	private float invulnerabilityTime;
	private float invulnerabilityDuration;
	public int damage;
	public Elements enemyHitBox;
	
	//Stage = gestor de lo q sucede en pantalla
	public Enemies(float x, float y, Stage s, GameScreen lvl) {
		super(x, y, s);	
		this.lvl = lvl;
		this.invulnerability = false;
		this.invulnerabilityDuration = 1.25f;
		this.invulnerabilityTime = 0;
		this.damage = 25;
		
	}
	
	public void draw(Batch batch, float parentAlpha) {
		if(this.invulnerability) {
			super.draw(batch, parentAlpha*0.5f);
		} else {
			super.draw(batch, parentAlpha);
		}
		
	}
	
	public void act(float delta) {
		//Los tiempos siempre aquí
		super.act(delta);
		
		if(this.hp <=0) {
			Parametros.firstEnemyKey = true;
			this.setEnabled(false);
		}
		
		if(this.invulnerabilityTime>0) {
			this.invulnerabilityTime-=delta;
		} else {
			this.invulnerability = false;
		}
		colide();
		positionEnemyHitBox();
		
	}
	
	public void positionEnemyHitBox() {
		this.enemyHitBox.setPosition(this.getX(), this.getY());	
	}
	
	public void colide() {
		if(this.getEnabled() && this.enemyHitBox.overlaps(this.lvl.player.hitBox)) {
			lvl.player.damage(this.damage);
		}
		
	}
	
	public void damage(int damage) {
		if(this.hp>0 && !this.invulnerability) {
			AudioManager.playSound("audio/sounds/enemyHit.mp3");
			this.hp -= damage;
			this.invulnerability = true;
			this.invulnerabilityTime -= this.invulnerabilityDuration;
		}
	}
	
}
