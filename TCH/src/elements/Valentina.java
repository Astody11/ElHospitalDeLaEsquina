package elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import game.Parametros;
import managers.AudioManager;
import managers.ResourceManager;
import screens.GameScreen;

public class Valentina extends Ghosts{

	private Animation<TextureRegion> izq;
	private Animation<TextureRegion> dcha;
	private int nDialog = 0;
	
	private Label infoLbl;
	
	public Valentina(float x, float y, Stage s, GameScreen lvl) {
		super(x, y, s, lvl);
		izq = loadFullAnimation("npcs/ValentinaFloatsT.png", 1, 10, 0.2f, true);
		dcha = loadFullAnimation("npcs/ValentinaFloatsTDcha.png", 1, 10, 0.2f, true);
		
		this.setAnimation(izq);
		
		infoLbl = new Label("", ResourceManager.npcMarcadorStyle);
		infoLbl.setPosition(this.getX() + this.getWidth()/2.5f, this.getY() + this.getHeight());
		s.addActor(infoLbl);
	}
	
	public void act(float delta) {
		super.act(delta);
		
		if(this.getEnabled()) {
			animations();
			dialog();
			
			if(this.overlaps(this.lvl.player.sensor) && Gdx.input.isKeyJustPressed(Keys.E)) {
				AudioManager.playSound("audio/sounds/npcs/holaaa.mp3");
				this.lvl.player.stayStill = true;
				this.nDialog = 1;
			}
			
			if(Parametros.ghostCompannion.equals("Valentina")) {
				position();
			}
			
			if(Gdx.input.isKeyJustPressed(Keys.S) && this.nDialog == 1) {
				this.nDialog++;
				this.lvl.player.stayStill = false;
				Parametros.ghostCompannion = "Valentina";
				Parametros.ghostPower = "Ataque x2";
				AudioManager.playSound("audio/sounds/npcs/Vamonos.mp3");
			}
			
			if(Gdx.input.isKeyJustPressed(Keys.N) && this.nDialog == 1) {
				this.nDialog++;
				this.lvl.player.stayStill = false;
				AudioManager.playSound("audio/sounds/npcs/Adioos.mp3");
			}
			
			if(this.overlaps(this.lvl.player.sensor)) {
				this.infoLbl.setText("'E'");
			} else {
				this.infoLbl.setText("");
			}
			
			if(Parametros.ghostCompannion.equals("") && Parametros.nivel == 200) {
				this.setEnabled(false);
			}
			
			if(!this.overlaps(this.lvl.player.sensor)) {
				this.lvl.dialogLabels.get("Valentina").setText("");
			}
		}
	}
	
	private void dialog() {
		
		switch(nDialog) {
		case 1:
			this.lvl.dialogLabels.get("Valentina").setText("Valentina: Mi poder os otorgará el doble de fuerza. ¿Queréis que vaya con vosotras?"
					+ "\n" + "Pulsa 'S' para sí, o 'N' para no.");
			break;
			
		default: 
			this.lvl.dialogLabels.get("Valentina").setText("");
			break;
		}
	}
	
	private void animations() {
		if(Parametros.ghostCompannion.equals("Valentina")) {
			if(this.lvl.player.direction == 1) {
				this.setAnimation(dcha);
			} else {
				this.setAnimation(izq);
			}
		} else {
			this.setAnimation(dcha);
		}
		
		
	}

}
