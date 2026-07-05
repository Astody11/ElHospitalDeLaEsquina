package elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import game.Parametros;
import managers.ResourceManager;
import screens.GameScreen;

public class Mireya extends Ghosts{

	private Animation<TextureRegion> dcha;
	private Animation<TextureRegion> izq;
	private int nDialog = 0;
	
	private Label infoLbl;
	
	public Mireya(float x, float y, Stage s, GameScreen lvl) {
		super(x, y, s, lvl);
		dcha = loadFullAnimation("npcs/MireyaFloatsTDcha.png", 1, 10, 0.2f, true);
		izq = loadFullAnimation("npcs/MireyaFloatsT.png", 1, 10, 0.2f, true);
		this.setAnimation(izq);
		
		infoLbl = new Label("", ResourceManager.npcMarcadorStyle);
		infoLbl.setPosition(this.getX() + this.getWidth()/4, this.getY() + this.getHeight());
		s.addActor(infoLbl);
		
	}
	
	public void act(float delta) {
		super.act(delta);
		
		if(this.getEnabled()) {
			animations();
			
			if(Parametros.nivel == 224) {
				dialog();
			}
			
			if(this.overlaps(this.lvl.player.sensor) && Gdx.input.isKeyJustPressed(Keys.E)) {
				this.lvl.player.stayStill = true;
				this.nDialog = 1;
			}
			
			if(Parametros.ghostCompannion.equals("Mireya")) {
				position();
			}
			
			if(Gdx.input.isKeyJustPressed(Keys.SPACE) && this.nDialog == 1) {
				this.nDialog++;
			}
			
			if(this.lvl.player.agreeToCompannion.equals("YES") && this.nDialog == 2) {
				this.nDialog++;
				this.lvl.player.stayStill = false;
				Parametros.ghostCompannion = "Mireya";
				Parametros.ghostPower = "- Ataque +25%" +
						"\n" + "          - Defensa +25%";
				this.lvl.yesNoBtns.yesNoTableAppear(false);
				this.lvl.player.agreeToCompannion = "";
			}
			
			if(this.lvl.player.agreeToCompannion.equals("NO") && this.nDialog == 2) {
				this.nDialog++;
				this.lvl.player.stayStill = false;
				this.lvl.yesNoBtns.yesNoTableAppear(false);
				this.lvl.player.agreeToCompannion = "";
			}
			
			if(this.overlaps(this.lvl.player.sensor)) {
				this.infoLbl.setText("'E'");
			} else {
				this.infoLbl.setText("");
			}
			
			if(Parametros.ghostCompannion.equals("") && Parametros.nivel == 200) {
				this.setEnabled(false);
			}
			
			if(!this.overlaps(this.lvl.player.sensor) && (Parametros.nivel == 224)) {
				this.lvl.dialogLabels.get("Mireya").setText("");
				this.lvl.lblAngy.setText("");
			}
		}
	}
	
	private void dialog() {
		
		switch(nDialog) {
		case 1:
			this.lvl.dialogLabels.get("Mireya").setText("Mireya: *Habla en lengua de signos*");
			break;
		case 2:
			this.lvl.dialogLabels.get("Mireya").setText("");
			this.lvl.lblAngy.setText("Angy: Mireya dice que su poder nos ayudará a tener un 25% más tanto de ataque como defensa.");
			this.lvl.yesNoBtns.yesNoTableAppear(true);
			break;
			
		default: 
			this.lvl.dialogLabels.get("Mireya").setText("");
			this.lvl.lblAngy.setText("");
			break;
		}
	}
	
	private void animations() {
		if(Parametros.ghostCompannion.equals("Mireya")) {
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
