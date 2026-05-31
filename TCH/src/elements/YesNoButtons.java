package elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import game.Parametros;
import managers.AudioManager;
import managers.ResourceManager;
import screens.GameScreen;

public class YesNoButtons {

	TextButton yesBtn;
	TextButton noBtn;
	public Table yesNoTable;
	GameScreen lvl;
	public YesNoButtons(float x, float y, Stage s, GameScreen lvl) {
		
		this.lvl = lvl;
		
		yesNoTable= new Table();
		yesNoTable.setFillParent(true);
		yesNoTable.setPosition(350, -250);
		this.lvl.uiStage.addActor(yesNoTable);
		
		this.yesBtn = new TextButton("Sí", ResourceManager.textButtonStyle);
		this.yesBtn.addListener(
				(Event e)->{if(!(e instanceof InputEvent))
					return false;
				
				InputEvent ie = (InputEvent) e;
				
				//Hover on
		        if (ie.getType() == Type.enter) {
		        	
		        	this.yesBtn.setStyle(ResourceManager.textButtonHoverStyle);
		        }

		        //Hover off
		        if (ie.getType() == Type.exit) {

		        	this.yesBtn.setStyle(ResourceManager.textButtonStyle);
		        }

		        // Handle click
		        if (ie.getType() == Type.touchDown) {
		            this.lvl.player.agreeToCompannion = "YES";
		        }

				return false;
			});
		
		yesNoTable.add(this.yesBtn);
		yesNoTable.row();
		
		
		this.noBtn = new TextButton("No", ResourceManager.textButtonStyle);
		this.noBtn.addListener(
				(Event e)->{if(!(e instanceof InputEvent))
					return false;
				
				InputEvent ie = (InputEvent) e;
				
				//Hover on
		        if (ie.getType() == Type.enter) {
		        	
		        	this.noBtn.setStyle(ResourceManager.textButtonHoverStyle);
		        }

		        //Hover off
		        if (ie.getType() == Type.exit) {

		        	this.noBtn.setStyle(ResourceManager.textButtonStyle);
		        }

		        // Handle click
		        if (ie.getType() == Type.touchDown) {
		            this.lvl.player.agreeToCompannion = "NO";
		        }

				return false;
			});
		
		yesNoTable.add(this.noBtn);
		
		this.yesNoTable.setVisible(false);
		
		
	}

	/*public void act(float delta) {
		super.act(delta);
		
		
			
		
		
	}*/
	
	public void yesNoTableAppear(boolean appear) {
		this.yesNoTable.setVisible(appear);
	}
}
