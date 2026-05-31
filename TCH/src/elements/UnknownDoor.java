package elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import game.Parametros;
import managers.AudioManager;
import managers.ResourceManager;
import screens.DeathScreen;
import screens.GameScreen;

public class UnknownDoor extends Interactables {
	
	private Animation<TextureRegion> pomo;
	private Animation<TextureRegion> blackFrame;
	private Animation<TextureRegion> veggy;
	private float blackFrameTimer;
	private float dyingFrameTimer;
	private boolean playingAnimation = false;
	private SpriteBatch spritebatch;
	
	int nUnknownDoorDialog = 0;
	
	private Label infoLbl;
	
	public UnknownDoor(float x, float y, Stage s, GameScreen lvl) {
		super(x, y, s, lvl);
	
		pomo = this.loadFullAnimation("maps/images/PomoVeggy.png",1,1,1,false);
		blackFrame = this.loadFullAnimation("maps/images/Battlefield.jpg",1,1,1,false);
		veggy = this.loadFullAnimation("maps/images/5x14.png",14,5,0,false);
		
		spritebatch = new SpriteBatch();
		
		this.setRectangle(this.getWidth(), this.getHeight()-50, -150, -140);
		this.lvl = lvl;
		
		infoLbl = new Label("'E'", ResourceManager.itemStyle);
		s.addActor(infoLbl);
		
		this.setAnimation(pomo);
		this.blackFrameTimer = 2f;
		this.dyingFrameTimer = 5*14*0.05f;
	}
	
	public void act(float delta) {
		super.act(delta);
				
		if(Parametros.jumpscared) {
			//lvl.set
			//System.out.println(blackFrameTimer);
		}
		
		if(blackFrameTimer<=0) {
			this.dyingFrameTimer -= delta;
			if(!playingAnimation) {
				//this.setAnimation(veggy);
				veggy.setPlayMode(PlayMode.NORMAL);
				veggy.setFrameDuration(0);
				System.out.println(veggy.getKeyFrame(delta));
				if(veggy.isAnimationFinished(delta)) {
					this.dyingFrameTimer = 16*5*0.05f;
					//veggy.setPlayMode(PlayMode.REVERSED);
					veggy.setFrameDuration(0);
					spritebatch.begin(); //NO SE VE ESTA MIERDA
					spritebatch.draw(veggy.getKeyFrame(delta), 50, 50);
					spritebatch.end();
					//veggy.setPlayMode(PlayMode.LOOP);
					veggy.setFrameDuration(0.05f);
				}
					
				//veggy = this.loadFullAnimation("maps/images/VeggyAnimation.png",10,5,0.05f,false);
				
			}
			//veggy.setPlayMode(PlayMode.NORMAL);
			playingAnimation = true;
			
			if(playingAnimation && this.dyingFrameTimer<=0) {
				veggy.setFrameDuration(0);
				System.out.println("BYEEE");
				Parametros.jumpscared = false;
				Parametros.vida = 0;
				
			}
		}
		
		
		if(this.getEnabled() && this.overlaps(this.lvl.player.sensor) && Gdx.input.isKeyJustPressed(Keys.E)) {
			
			/*
			if(this.pomoUnknown.overlaps(this.player.sensor) && Gdx.input.isKeyJustPressed(Keys.E)) {
				nUnknownDoorDialog++;
				
				switch(nUnknownDoorDialog) {
					case 1:
						this.lblSophie.setText("Tengo un mal presentimiento...");
						this.player.stayStill = true;
						
						break;
						
					case 2:
						this.lblSophie.setText("Creo que debería pensármelo mejor");
						this.player.stayStill = true;
						
						break;
						
					case 3:
						this.lblSophie.setText("");
						this.lblAngy.setText("¡Sophie no abras eso!");
						this.player.stayStill = true;
						
						break;
						
					case 4:
						this.lblAngy.setText("");
						this.lblSophie.setText("Esta es mi última oportunidad");
						this.player.stayStill = true;
						
						break;
						
					case 5:
						this.lblAngy.setText("¡¡NOOOO!!");
						game.setScreen(new DeathScreen(game));
						Parametros.vida = Parametros.maxVida;
						break;
						
						default:
							this.lblSophie.setText("");
							break;
				}
			}
			
			if(this.pomoUnknown.overlaps(this.player.sensor) && Gdx.input.isKeyJustPressed(Keys.SPACE)) {
					this.lblSophie.setText("");
					this.lblAngy.setText("");
					this.player.stayStill = false;
			}*/
			
			Parametros.jumpscared = true;
			 
			/*this.setPosition(this.lvl.player.getX()-475, this.lvl.player.getY()-75);
			this.setScale(1.2f, 1.2f);
			this.setRectangle(this.getWidth(), this.getHeight(), 0, 0);
			this.lvl.player.stayStill = true;
			this.setAnimation(veggy);
			veggy.setPlayMode(PlayMode.REVERSED);*/
			
		}
	}
}
