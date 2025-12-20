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

public class Aitor extends Ghosts{
	
	private Animation<TextureRegion> dcha;
	private Animation<TextureRegion> izq;
	private int nDialog = 0;
	
	private Label infoLbl;
	
	public Aitor(float x, float y, Stage s, GameScreen lvl) {
		super(x, y, s, lvl);
		
		dcha = loadFullAnimation("npcs/AitorFloatsTDcha.png", 1, 10, 0.2f, true);
		izq = loadFullAnimation("npcs/AitorFloatsT.png", 1, 10, 0.2f, true);
		this.setAnimation(izq);
		
		infoLbl = new Label("", ResourceManager.npcStyle);
		infoLbl.setPosition(this.getX() + this.getWidth()/2.4f, this.getY() + this.getHeight()*1.05f);
		s.addActor(infoLbl);
	}
	
	public void act(float delta) {
		super.act(delta);
		
		if(this.getEnabled()) {
			animations();
			dialog();
			
			if(this.overlaps(this.lvl.player.sensor) && Gdx.input.isKeyJustPressed(Keys.E)) {
				AudioManager.playSound("audio/sounds/npcs/holaChicas.mp3");
				this.lvl.player.stayStill = true;
				this.nDialog = 1;
			}
			
			if(Parametros.ghostCompannion.equals("Aitor")) {
				position();
			}
			
			if(Gdx.input.isKeyJustPressed(Keys.S) && this.nDialog == 1) {
				this.nDialog++;
				this.lvl.player.stayStill = false;
				Parametros.ghostCompannion = "Aitor";
				Parametros.ghostPower = "Vel. enemigo -50%";
				AudioManager.playSound("audio/sounds/npcs/vamos.mp3");
			}
			
			if(Gdx.input.isKeyJustPressed(Keys.N) && this.nDialog == 1) {
				this.nDialog++;
				this.lvl.player.stayStill = false;
				AudioManager.playSound("audio/sounds/npcs/noMeMuevo.mp3");
			}
			
			if(Parametros.ghostCompannion.equals("") && Parametros.nivel == 200) {
				this.setEnabled(false);
			}
			
			if(this.overlaps(this.lvl.player.sensor)) {
				this.infoLbl.setText("'E'");
			} else {
				this.infoLbl.setText("");
			}
			
			if(!this.overlaps(this.lvl.player.sensor)) {
				this.lvl.dialogLabels.get("Aitor").setText("");
			}
		}
	}
	
	private void dialog() {
		
		switch(nDialog) {
		case 1:
			this.lvl.dialogLabels.get("Aitor").setText("Aitor: Mi poder reduce la velocidad de los enemigos a la mitad. ¿Puedo ir con vosotras?"
					+ "\n" + "Pulsa 'S' para sí 'N' para no.");
			break;
			
		default:
			this.lvl.dialogLabels.get("Aitor").setText("");
			break;
		}
	}
	
	private void animations() {
		if(Parametros.ghostCompannion.equals("Aitor")) {
			if(this.lvl.player.direction == 1) {
				this.setAnimation(dcha);
			} else {
				this.setAnimation(izq);
			}
		} else {
			this.setAnimation(izq);
		}
		
		
	}

}
