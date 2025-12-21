package elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import game.Parametros;
import managers.AudioManager;
import managers.ResourceManager;
import screens.GameScreen;

public class Chest extends Interactables {

	private Animation<TextureRegion> chest;
	private Animation<TextureRegion> closeChest;
	private float posX;
	private float posY;
	private boolean solvingPuzzle = false;
	private float degrees1 = 0;
	private float degrees2 = 0;
	private float degrees3 = 0;
	private float degrees4 = 0;
	private float degrees5 = 0;
	private float degrees6 = 0;
	
	Button btn1 = new Button(ResourceManager.circle1);
	TextButton btn2 = new TextButton("", ResourceManager.circle2);
	TextButton btn3 = new TextButton("",ResourceManager.circle3);
	TextButton btn4 = new TextButton("", ResourceManager.circle4);
	TextButton btn5 = new TextButton("",ResourceManager.circle5);
	TextButton btn6 = new TextButton("", ResourceManager.circle6);
	
	private Label infoLbl;
	
	public Chest(float x, float y, Stage s, GameScreen lvl) {
		super(x, y, s, lvl);
		chest = this.loadFullAnimation("maps/images/cofreMapa.png", 1, 1, 1, false); //De lejos
		closeChest = this.loadFullAnimation("maps/images/Cofre.png", 1, 1, 1, false); //De cerca
		this.lvl = lvl;
		this.posX = x;
		this.posY = y;
		this.setAnimation(chest);
		btn1.setTransform(true);
		btn2.setTransform(true);
		btn3.setTransform(true);
		btn4.setTransform(true);
		btn5.setTransform(true);
		btn6.setTransform(true);
		
		infoLbl = new Label("'E'", ResourceManager.itemStyle);
		s.addActor(infoLbl);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		if(this.getEnabled() && !Parametros.chestPuzzleSolved && this.overlaps(this.lvl.player.sensor) && Gdx.input.isKeyJustPressed(Keys.E)) {
			this.setPosition(0, 0);
			this.setAnimation(closeChest);
			this.lvl.uiStage.addActor(this);
			showPuzzle();
			this.solvingPuzzle = true;
			this.lvl.player.stayStill = true;
		} 
		
		if(this.getEnabled() && this.overlaps(this.lvl.player.sensor) && !Parametros.chestPuzzleSolved) {
			infoLbl.setPosition(this.getX() + this.getWidth()/2.5f, this.getY() + this.getHeight()*1.4f);
			infoLbl.setText("'E'");
		} else {
			infoLbl.setText("");
		}
		
		if((this.getEnabled() && !Parametros.chestPuzzleSolved && this.solvingPuzzle && (Gdx.input.isKeyJustPressed(Keys.SPACE) || Gdx.input.isKeyJustPressed(Keys.ESCAPE))) || Parametros.chestPuzzleSolved) {
			this.setAnimation(chest);
			
			this.solvingPuzzle = false;
			this.lvl.player.stayStill = false;
			
			this.btn1.clearListeners();
			this.btn2.clearListeners();
			this.btn3.clearListeners();
			this.btn4.clearListeners();
			this.btn5.clearListeners();
			this.btn6.clearListeners();
			
			this.btn1.setVisible(false);
			this.btn2.setVisible(false);
			this.btn3.setVisible(false);
			this.btn4.setVisible(false);
			this.btn5.setVisible(false);
			this.btn6.setVisible(false);
			this.setPosition(posX, posY);
		}
		
		if(this.degrees1 == -360) {
			this.degrees1 = 0;
		}
		if(this.degrees2 == -360) {
			this.degrees2 = 0;
		}
		if(this.degrees3 == -360) {
			this.degrees3 = 0;
		}
		if(this.degrees4 == -360) {
			this.degrees4 = 0;
		}
		if(this.degrees5 == -360) {
			this.degrees5 = 0;
		}
		if(this.degrees6 == -360) {
			this.degrees6 = 0;
		}
		
		if(this.degrees1 == -225 && this.degrees2 == -45 && this.degrees3 == -90 && this.degrees4 == -225 && this.degrees5 == -315 && this.degrees6 == -90) {
			this.degrees1 = 0;
			AudioManager.playSound("audio/sounds/wellDone.wav");
			Parametros.chestPuzzleSolved = true;
		}
	}
	
	
	
	private void showPuzzle() {
		
		btn1.setVisible(true);
		btn2.setVisible(true);
		btn3.setVisible(true);
		btn4.setVisible(true);
		btn5.setVisible(true);
		btn6.setVisible(true);
		
		this.degrees1 = 0;
		this.degrees2 = 0;
		this.degrees3 = 0;
		this.degrees4 = 0;
		this.degrees5 = 0;
		this.degrees6 = 0;
		
		btn1.setStyle(ResourceManager.circle1);
		
		btn1.setRotation(0);
		btn2.setRotation(0);
		btn3.setRotation(0);
		btn4.setRotation(0);
		btn5.setRotation(0);
		btn6.setRotation(0);
		
		btn1.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
					
				return false;
				
				AudioManager.playSound("audio/sounds/button.mp3");
				this.degrees1 -= 45;
				btn1.setOrigin(63, 63);
				btn1.setRotation(this.degrees1);
				return false;
				
		});
		btn1.setPosition(435, 395);
		
		btn2.setStyle(ResourceManager.circle2);
		btn2.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
				return false;
				
				AudioManager.playSound("audio/sounds/button.mp3");
				degrees2 -= 45;
				btn2.setOrigin(63, 63);
				btn2.setRotation(degrees2);
				
			
				return false;
				
		});
		btn2.setPosition(585, 395);
		
		btn3.setStyle(ResourceManager.circle3);
		btn3.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
				return false;
				
				AudioManager.playSound("audio/sounds/button.mp3");
				degrees3 -= 45;
				btn3.setOrigin(63, 63);
				btn3.setRotation(degrees3);
			
				return false;
				
		});
		btn3.setPosition(735, 395);
		
		btn4.setStyle(ResourceManager.circle4);
		btn4.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
				return false;
				
				AudioManager.playSound("audio/sounds/button.mp3");
				degrees4 -= 45;
				btn4.setOrigin(63, 63);
				btn4.setRotation(degrees4);
			
				return false;
				
		});
		btn4.setPosition(435, 260);
		
		btn5.setStyle(ResourceManager.circle5);
		btn5.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
				return false;
				
				AudioManager.playSound("audio/sounds/button.mp3");
				degrees5 -= 45;
				btn5.setOrigin(63, 63);
				btn5.setRotation(degrees5);
			
				return false;
				
		});
		btn5.setPosition(585, 260);
		
		btn6.setStyle(ResourceManager.circle6);
		btn6.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
				return false;
				
				AudioManager.playSound("audio/sounds/button.mp3");
				degrees6 -= 45;
				btn6.setOrigin(63, 63);
				btn6.setRotation(degrees6);
			
				return false;
				
		});
		btn6.setPosition(735, 260);
		
		this.lvl.uiStage.addActor(btn1);
		this.lvl.uiStage.addActor(btn2);
		this.lvl.uiStage.addActor(btn3);
		this.lvl.uiStage.addActor(btn4);
		this.lvl.uiStage.addActor(btn5);
		this.lvl.uiStage.addActor(btn6);
	}
	
}
