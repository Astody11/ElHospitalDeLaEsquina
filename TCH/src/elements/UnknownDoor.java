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
	
	private float blackFrameTimer;
	private boolean openedDoor = false;
	
	int nUnknownDoorDialog = 0;
	
	private Label infoLbl;
	
	public UnknownDoor(float x, float y, Stage s, GameScreen lvl) {
		super(x, y, s, lvl);
	
		pomo = this.loadFullAnimation("maps/images/PomoVeggy.png",1,1,1,false);
		blackFrame = this.loadFullAnimation("maps/images/BlackFrame.png",1,1,1,false);
		
		this.setRectangle(this.getWidth(), this.getHeight()-50, -150, -140);
		this.lvl = lvl;
		
		infoLbl = new Label("'E'", ResourceManager.itemStyle);
		s.addActor(infoLbl);
		
		this.setAnimation(pomo);
		this.blackFrameTimer = 1f;
	}
	
	public void act(float delta) {
		super.act(delta);
				
		if(openedDoor) {
			blackFrameTimer -= delta;
			this.setPosition(this.lvl.player.getX()-475, this.lvl.player.getY()-75);
			this.setScale(1.2f, 1.2f);
			this.setRectangle(this.getWidth(), this.getHeight(), 0, 0);
			this.lvl.player.stayStill = true;
			this.setAnimation(blackFrame);
		}
		
		if(blackFrameTimer<=0) {
			Parametros.jumpscared = true;
		}
		
		
		if(this.getEnabled() && this.overlaps(this.lvl.player.sensor) && Gdx.input.isKeyJustPressed(Keys.E)) {
			nUnknownDoorDialog++;
			
			switch(nUnknownDoorDialog) {
				case 1:
					this.lvl.lblSophie.setText("Tengo un mal presentimiento...");
					this.lvl.player.stayStill = true;
					
					break;
					
				case 2:
					this.lvl.lblSophie.setText("Creo que debería pensármelo mejor");
					this.lvl.player.stayStill = true;
					
					break;
					
				case 3:
					this.lvl.lblSophie.setText("");
					this.lvl.lblAngy.setText("¡Sophie no abras eso!");
					this.lvl.player.stayStill = true;
					
					break;
					
				case 4:
					this.lvl.lblAngy.setText("");
					this.lvl.lblSophie.setText("Esta es mi última oportunidad");
					this.lvl.player.stayStill = true;
					
					break;
					
				case 5:
					this.lvl.lblAngy.setText("¡¡NOOOO!!");
					openedDoor = true;
					break;
					
					default:
						this.lvl.lblSophie.setText("");
						break;
			}
		}
		
		if(this.overlaps(this.lvl.player.sensor) && Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			this.lvl.lblSophie.setText("");
			this.lvl.lblAngy.setText("");
			this.lvl.player.stayStill = false;
		}
	}
}
