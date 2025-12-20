package elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import game.Parametros;
import managers.AudioManager;
import managers.ResourceManager;
import screens.GameScreen;

public class HealthPotion extends Interactables{

	private float appearanceTime = 30;
	private float appearanceCounter;
	private Label infoLbl;
	
	public HealthPotion(float x, float y, Stage s, GameScreen lvl) {
		super(x, y, s, lvl);
		this.lvl = lvl;
		this.loadFullAnimation("maps/images/healthPotion.png", 1, 1, 1, false);
		
		if(Parametros.nivel != 201) {
			appearanceCounter = 0;
		} else {
			appearanceCounter = appearanceTime;
		}
		
		
		this.setEnabled(false);
		
		infoLbl = new Label("'E'", ResourceManager.itemStyle);
		s.addActor(infoLbl);
	}

	public void act(float delta) {
		super.act(delta);
		
		if(this.getEnabled() && this.overlaps(this.lvl.player.sensor)) {
			infoLbl.setPosition(this.getX() + this.getWidth()/2.5f, this.getY() + this.getHeight());
			infoLbl.setText("'E'");
		} else {
			infoLbl.setText("");
		}
		
		if(this.getEnabled() && this.overlaps(this.lvl.player.sensor) && Gdx.input.isKeyJustPressed(Keys.E)) {
			this.setEnabled(false);
			AudioManager.playSound("audio/sounds/heal.mp3");
			switch(Parametros.nivel) {
			case 0:
				Parametros.vida += 100;
				Parametros.bottleKitchen = true;
				break;
				
			case 201:
				Parametros.vida += 200;
				appearanceCounter = appearanceTime;
				break;
				
			case 204:
				Parametros.vida += 100;
				Parametros.bottle204 = true;
				break;
				
			case 224:
				Parametros.vida += 100;
				Parametros.bottle224 = true;
				break;
				
			case 231:
				Parametros.vida += 100;
				Parametros.bottle231 = true;
				break;
				
			case 243:
				Parametros.vida += 100;
				Parametros.bottle243 = true;
				break;
				
			case 297:
				Parametros.vida += 100;
				Parametros.bottle297 = true;
				break;
			}
			
		}
		
		if((appearanceCounter<=0 && Parametros.nivel == 201) || (Parametros.nivel == 297 && !Parametros.bottle297) ||
				(Parametros.nivel == 243 && !Parametros.bottle243) || (Parametros.nivel == 231 && !Parametros.bottle231) || 
				(Parametros.nivel == 224 && !Parametros.bottle224) || (Parametros.nivel == 204 && !Parametros.bottle204) ||
				(Parametros.nivel == 0 && !Parametros.bottleKitchen)) {
			this.setEnabled(true);
			
        } else if(appearanceCounter>0 && Parametros.nivel == 201){
			infoLbl.setText("");
			appearanceCounter-=delta;
			
		}
		
	}
}
