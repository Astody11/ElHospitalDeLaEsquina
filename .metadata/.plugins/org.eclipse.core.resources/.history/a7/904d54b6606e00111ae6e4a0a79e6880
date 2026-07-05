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

public class Casandra extends Ghosts{

	private Animation<TextureRegion> floating;
	private int nDialog = 0;
	
	private Label infoLbl;
	
	public Casandra(float x, float y, Stage s, GameScreen lvl) {
		super(x, y, s, lvl);
		floating = loadFullAnimation("npcs/CasandraFloatsT.png", 1, 10, 0.2f, true);
		
		infoLbl = new Label("", ResourceManager.npcMarcadorStyle);
		infoLbl.setPosition(this.getX() + this.getWidth()/2.5f, this.getY() + this.getHeight());
		s.addActor(infoLbl);
	}
	
	public void act(float delta) {
		super.act(delta);
		
		if(this.getEnabled()) {
			dialog();
			
			if(this.overlaps(this.lvl.player.sensor) && Gdx.input.isKeyJustPressed(Keys.E)) {
				AudioManager.playSound("audio/sounds/npcs/Ugh.mp3");
				this.lvl.player.stayStill = true;
				this.nDialog = 1;
			}
			
			if(Gdx.input.isKeyJustPressed(Keys.SPACE) && nDialog == 1) {
				this.nDialog++;
				if(nDialog >= 2) {
					this.lvl.dialogLabels.get("Casandra").setText("");
					this.lvl.player.stayStill = false;
				}
				
			}
			
			if(this.overlaps(this.lvl.player.sensor)) {
				this.infoLbl.setText("'E'");
			} else {
				this.infoLbl.setText("");
			}
			
			if(!this.overlaps(this.lvl.player.sensor)) {
				this.lvl.dialogLabels.get("Casandra").setText("");
			}
		}
	}
	
	private void dialog() {
		
		switch(nDialog) {
		case 1:
			this.lvl.dialogLabels.get("Casandra").setText("Casandra: Piérdete.");
			break;
			
		default: 
			this.lvl.dialogLabels.get("Casandra").setText("");
			break;
		}
	}

}
