package elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

public class Panel extends Interactables{

	private Animation<TextureRegion> panel;
	private Animation<TextureRegion> closeLook;
	private boolean solvingPuzzle = false;
	
	private boolean active1 = false;
	private boolean active2 = false;
	private boolean active3 = false;
	private boolean active4 = false;
	private boolean active5 = false;
	private boolean active6 = false;
	private boolean active7 = false;
	private boolean active8 = false;
	private boolean active9 = false;
	private boolean active10 = false;
	private boolean active11 = false;
	private boolean active12 = false;
	private boolean active13 = false;
	private boolean active14 = false;
	private boolean active15 = false;
	private boolean active16 = false;
	
	TextButton btn1 = new TextButton("",ResourceManager.nonActiveTearTxt);
	TextButton btn2 = new TextButton("", ResourceManager.nonActiveTearTxt);
	TextButton btn3 = new TextButton("",ResourceManager.nonActiveTearTxt);
	TextButton btn4 = new TextButton("", ResourceManager.nonActiveTearTxt);
	TextButton btn5 = new TextButton("",ResourceManager.nonActiveTearTxt);
	TextButton btn6 = new TextButton("", ResourceManager.nonActiveTearTxt);
	TextButton btn7 = new TextButton("",ResourceManager.nonActiveTearTxt);
	TextButton btn8 = new TextButton("", ResourceManager.nonActiveTearTxt);
	TextButton btn9 = new TextButton("",ResourceManager.nonActiveTearTxt);
	TextButton btn10 = new TextButton("", ResourceManager.nonActiveTearTxt);
	TextButton btn11 = new TextButton("",ResourceManager.nonActiveTearTxt);
	TextButton btn12 = new TextButton("", ResourceManager.nonActiveTearTxt);
	TextButton btn13 = new TextButton("",ResourceManager.nonActiveTearTxt);
	TextButton btn14 = new TextButton("", ResourceManager.nonActiveTearTxt);
	TextButton btn15 = new TextButton("",ResourceManager.nonActiveTearTxt);
	TextButton btn16 = new TextButton("", ResourceManager.nonActiveTearTxt);
	
	private float inicioX;
	private float inicioY;
	
	private Label infoLbl;
	
	public Panel(float x, float y, Stage s, GameScreen lvl) {
		super(x, y, s, lvl);
		
		panel = loadFullAnimation("maps/images/panel.png", 1, 1, 1, false);
		closeLook = loadFullAnimation("maps/images/PuzzleAscensor.png", 1, 1, 1, false);
		this.setRectangle();
		this.inicioX = x;
		this.inicioY = y;
		this.lvl = lvl;
		this.setRectangle(80, -580, 280, -200);
		this.setAnimation(panel);
		
		infoLbl = new Label("'E'", ResourceManager.itemStyle);
		s.addActor(infoLbl);
	}
	
	public void act(float delta) {
		super.act(delta);	
		
		if(this.getEnabled() && this.overlaps(this.lvl.player.sensor)) {
			if(!Parametros.elevatorPuzzleSolved) {
				infoLbl.setPosition(this.getX() + this.getWidth()/5, this.getY() + this.getHeight()*1.4f);
				infoLbl.setText("'E'");
			} else if(Parametros.elevatorPuzzleSolved) {
				infoLbl.setPosition(this.getX() - this.getWidth(), this.getY() + this.getHeight()/4);
				infoLbl.setText(" '2' \n '1' \n '0'");
			}
			
		} else {
			infoLbl.setText("");
		}
		
		
		if(this.getEnabled() && this.overlaps(this.lvl.player.sensor) && !Parametros.elevatorPuzzleSolved && !this.solvingPuzzle && Gdx.input.isKeyJustPressed(Keys.E)) {
			this.setPosition(0, 0);
			this.setAnimation(closeLook);
			this.lvl.uiStage.addActor(this);
			this.solvingPuzzle = true;
			this.lvl.player.stayStill = true;
			this.showPuzzle();
		}
		
		if((this.getEnabled() && this.solvingPuzzle && !Parametros.elevatorPuzzleSolved && (Gdx.input.isKeyJustPressed(Keys.SPACE) || Gdx.input.isKeyJustPressed(Keys.ESCAPE))) || Parametros.elevatorPuzzleSolved) {
			this.setPosition(this.inicioX, this.inicioY);
			this.setAnimation(panel);
			this.solvingPuzzle = false;
			
			this.active1 = false;
			this.active2 = false;
			this.active3 = false;
			this.active4 = false;
			this.active5 = false;
			this.active6 = false;
			this.active7 = false;
			this.active8 = false;
			this.active9 = false;
			this.active10 = false;
			this.active11 = false;
			this.active12 = false;
			this.active13 = false;
			this.active14 = false;
			this.active15 = false;
			this.active16 = false;
			
			this.btn1.clearListeners();
			this.btn2.clearListeners();
			this.btn3.clearListeners();
			this.btn4.clearListeners();
			this.btn5.clearListeners();
			this.btn6.clearListeners();
			this.btn7.clearListeners();
			this.btn8.clearListeners();
			this.btn9.clearListeners();
			this.btn10.clearListeners();
			this.btn11.clearListeners();
			this.btn12.clearListeners();
			this.btn13.clearListeners();
			this.btn14.clearListeners();
			this.btn15.clearListeners();
			this.btn16.clearListeners();
			
			this.btn1.setVisible(false);
			this.btn2.setVisible(false);
			this.btn3.setVisible(false);
			this.btn4.setVisible(false);
			this.btn5.setVisible(false);
			this.btn6.setVisible(false);
			this.btn7.setVisible(false);
			this.btn8.setVisible(false);
			this.btn9.setVisible(false);
			this.btn10.setVisible(false);
			this.btn11.setVisible(false);
			this.btn12.setVisible(false);
			this.btn13.setVisible(false);
			this.btn14.setVisible(false);
			this.btn15.setVisible(false);
			this.btn16.setVisible(false);
			
			
			this.lvl.player.stayStill = false;
		}
		
		if(this.active2 && this.active4 && this.active6 && this.active8 && this.active10 && this.active11 && this.active13 && this.active15) {
			AudioManager.playSound("audio/sounds/wellDone.wav");
			Parametros.elevatorPuzzleSolved = true;
		}
		
		if(Parametros.elevatorPuzzleSolved) {
			if(this.getEnabled() && this.overlaps(this.lvl.player.sensor) && Parametros.nivel != 1 && Gdx.input.isKeyJustPressed(Keys.NUM_1)) {
				AudioManager.playSound("audio/sounds/button.mp3");
				Parametros.puerta = 1;
				lvl.backToFirstFloor();
			} else if(this.getEnabled() && this.overlaps(this.lvl.player.sensor) && Parametros.nivel != 2 && Gdx.input.isKeyJustPressed(Keys.NUM_2)) {
				AudioManager.playSound("audio/sounds/button.mp3");
				Parametros.puerta = 0;
				lvl.backToSecondFloor();
			} else if(this.getEnabled() && this.overlaps(this.lvl.player.sensor) && Parametros.nivel != 0 && Gdx.input.isKeyJustPressed(Keys.NUM_0)) {
				AudioManager.playSound("audio/sounds/button.mp3");
				Parametros.puerta = 0;
				lvl.toTheKitchenWeGo();
			}
		}
		
	}
	
	private void showPuzzle() {
		
		btn1.setVisible(true);
		btn2.setVisible(true);
		btn3.setVisible(true);
		btn4.setVisible(true);
		btn5.setVisible(true);
		btn6.setVisible(true);
		btn7.setVisible(true);
		btn8.setVisible(true);
		btn9.setVisible(true);
		btn10.setVisible(true);
		btn11.setVisible(true);
		btn12.setVisible(true);
		btn13.setVisible(true);
		btn14.setVisible(true);
		btn15.setVisible(true);
		btn16.setVisible(true);
		
		
		btn1.setStyle(ResourceManager.nonActiveTearTxt);
		
		btn1.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
				return false;
					
				AudioManager.playSound("audio/sounds/button.mp3");
				if(!this.active1) {
					btn1.setStyle(ResourceManager.activeTearTxt);
					this.active1 = true;
					btn2.setStyle(ResourceManager.nonActiveTearTxt);
					this.active2 = false;
				} else {
					btn1.setStyle(ResourceManager.nonActiveTearTxt);
					this.active1 = false;
				}
			
				return false;
				
				});
		btn1.setPosition(565, 390);
		
		btn2.setStyle(ResourceManager.nonActiveTearTxt);
		btn2.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
				return false;
					
				AudioManager.playSound("audio/sounds/button.mp3");
				if(!this.active2) {
					btn2.setStyle(ResourceManager.activeTearTxt);
					this.active2 = true;
					btn1.setStyle(ResourceManager.nonActiveTearTxt);
					this.active1 = false;
				} else {
					btn2.setStyle(ResourceManager.nonActiveTearTxt);
					this.active2 = false;
				}
				
				return false;
				
				});
		btn2.setPosition(625, 390);
		
		
		btn3.setStyle(ResourceManager.nonActiveTearTxt);
		btn3.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
				return false;
					
				AudioManager.playSound("audio/sounds/button.mp3");
				if(!this.active3) {
					btn3.setStyle(ResourceManager.activeTearTxt);
					this.active3 = true;
					btn4.setStyle(ResourceManager.nonActiveTearTxt);
					this.active4 = false;
				} else {
					btn3.setStyle(ResourceManager.nonActiveTearTxt);
					this.active3 = false;
				}
				
				return false;
				
				});
		btn3.setPosition(725, 390);
		
		btn4.setStyle(ResourceManager.nonActiveTearTxt);
		btn4.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
				return false;
					
				AudioManager.playSound("audio/sounds/button.mp3");
				if(!this.active4) {
					btn4.setStyle(ResourceManager.activeTearTxt);
					this.active4 = true;
					btn3.setStyle(ResourceManager.nonActiveTearTxt);
					this.active3 = false;
				} else {
					btn4.setStyle(ResourceManager.nonActiveTearTxt);
					this.active4 = false;
				}
				
				return false;
				
				});
		
		btn4.setPosition(785, 390);
		
		
		btn5.setStyle(ResourceManager.nonActiveTearTxt);
		btn5.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
				return false;
					
				AudioManager.playSound("audio/sounds/button.mp3");
				if(!this.active5) {
					btn5.setStyle(ResourceManager.activeTearTxt);
					this.active5 = true;
					btn6.setStyle(ResourceManager.nonActiveTearTxt);
					this.active6 = false;
				} else {
					btn5.setStyle(ResourceManager.nonActiveTearTxt);
					this.active5 = false;
				}
				
				return false;
				
				});
		
		btn5.setPosition(565, 320);
		
		btn6.setStyle(ResourceManager.nonActiveTearTxt);
		btn6.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
				return false;
					
				AudioManager.playSound("audio/sounds/button.mp3");
				if(!this.active6) {
					btn6.setStyle(ResourceManager.activeTearTxt);
					this.active6 = true;
					btn5.setStyle(ResourceManager.nonActiveTearTxt);
					this.active5 = false;
				} else {
					btn6.setStyle(ResourceManager.nonActiveTearTxt);
					this.active6 = false;
				}
				
				return false;
				
				});
		
		btn6.setPosition(625, 320);
		
		btn7.setStyle(ResourceManager.nonActiveTearTxt);
		btn7.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
				return false;
					
				AudioManager.playSound("audio/sounds/button.mp3");
				if(!this.active7) {
					btn7.setStyle(ResourceManager.activeTearTxt);
					this.active7 = true;
					btn8.setStyle(ResourceManager.nonActiveTearTxt);
					this.active8 = false;
				} else {
					btn7.setStyle(ResourceManager.nonActiveTearTxt);
					this.active7 = false;
				}
				
				return false;
				
				});
		
		btn7.setPosition(725, 320);
		
		
		btn8.setStyle(ResourceManager.nonActiveTearTxt);
		btn8.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
				return false;
					
				AudioManager.playSound("audio/sounds/button.mp3");
				if(!this.active8) {
					btn8.setStyle(ResourceManager.activeTearTxt);
					this.active8 = true;
					btn7.setStyle(ResourceManager.nonActiveTearTxt);
					this.active7 = false;
				} else {
					btn8.setStyle(ResourceManager.nonActiveTearTxt);
					this.active8 = false;
				}
				
				return false;
				
		});
		
		btn8.setPosition(785, 320);
		
		btn9.setStyle(ResourceManager.nonActiveTearTxt);
		btn9.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
				return false;
					
				AudioManager.playSound("audio/sounds/button.mp3");
				if(!this.active9) {
					btn9.setStyle(ResourceManager.activeTearTxt);
					this.active9 = true;
					btn10.setStyle(ResourceManager.nonActiveTearTxt);
					this.active10 = false;
				} else {
					btn9.setStyle(ResourceManager.nonActiveTearTxt);
					this.active9 = false;
				}
				
				return false;
				
		});
		
		btn9.setPosition(565, 250);
		
		btn10.setStyle(ResourceManager.nonActiveTearTxt);
		btn10.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
				return false;
					
				AudioManager.playSound("audio/sounds/button.mp3");
				if(!this.active10) {
					btn10.setStyle(ResourceManager.activeTearTxt);
					this.active10 = true;
					btn9.setStyle(ResourceManager.nonActiveTearTxt);
					this.active9 = false;
				} else {
					btn10.setStyle(ResourceManager.nonActiveTearTxt);
					this.active10 = false;
				}
				
				return false;
				
				});
		
		btn10.setPosition(625, 250);
		
		btn11.setStyle(ResourceManager.nonActiveTearTxt);
		btn11.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
				return false;
					
				AudioManager.playSound("audio/sounds/button.mp3");
				if(!this.active11) {
					btn11.setStyle(ResourceManager.activeTearTxt);
					this.active11 = true;
					btn12.setStyle(ResourceManager.nonActiveTearTxt);
					this.active12 = false;
				} else {
					btn11.setStyle(ResourceManager.nonActiveTearTxt);
					this.active11 = false;
				}
				
				return false;
				
				});
		
		btn11.setPosition(725, 250);
		
		btn12.setStyle(ResourceManager.nonActiveTearTxt);
		btn12.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
				return false;
					
				AudioManager.playSound("audio/sounds/button.mp3");
				if(!this.active12) {
					btn12.setStyle(ResourceManager.activeTearTxt);
					this.active12 = true;
					btn11.setStyle(ResourceManager.nonActiveTearTxt);
					this.active11 = false;
				} else {
					btn12.setStyle(ResourceManager.nonActiveTearTxt);
					this.active12 = false;
				}
				
				return false;
				
				});
		
		btn12.setPosition(785, 250);
		
		btn13.setStyle(ResourceManager.nonActiveTearTxt);
		btn13.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
				return false;
					
				AudioManager.playSound("audio/sounds/button.mp3");
				if(!this.active13) {
					btn13.setStyle(ResourceManager.activeTearTxt);
					this.active13 = true;
					btn14.setStyle(ResourceManager.nonActiveTearTxt);
					this.active14 = false;
				} else {
					btn13.setStyle(ResourceManager.nonActiveTearTxt);
					this.active13 = false;
				}
				
				return false;
				
		});
		
		btn13.setPosition(565, 180);
		
		btn14.setStyle(ResourceManager.nonActiveTearTxt);
		btn14.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
				return false;
					
				AudioManager.playSound("audio/sounds/button.mp3");
				if(!this.active14) {
					btn14.setStyle(ResourceManager.activeTearTxt);
					this.active14 = true;
					btn13.setStyle(ResourceManager.nonActiveTearTxt);
					this.active13 = false;
				} else {
					btn14.setStyle(ResourceManager.nonActiveTearTxt);
					this.active14 = false;
				}
				
				return false;
				
				});
		
		btn14.setPosition(625, 180);
		
		btn15.setStyle(ResourceManager.nonActiveTearTxt);
		btn15.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
				return false;
					
				AudioManager.playSound("audio/sounds/button.mp3");
				if(!this.active15) {
					btn15.setStyle(ResourceManager.activeTearTxt);
					this.active15 = true;
					btn16.setStyle(ResourceManager.nonActiveTearTxt);
					this.active16 = false;
				} else {
					btn15.setStyle(ResourceManager.nonActiveTearTxt);
					this.active15 = false;
				}
				
				return false;
				
				});
		
		btn15.setPosition(725, 180);
		
		btn16.setStyle(ResourceManager.nonActiveTearTxt);
		btn16.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
				return false;
					
				AudioManager.playSound("audio/sounds/button.mp3");
				if(!this.active16) {
					btn16.setStyle(ResourceManager.activeTearTxt);
					this.active16 = true;
					btn15.setStyle(ResourceManager.nonActiveTearTxt);
					this.active15 = false;
				} else {
					btn16.setStyle(ResourceManager.nonActiveTearTxt);
					this.active16 = false;
				}
				
				return false;
				
				});
		btn16.setPosition(785, 180);
		
		this.lvl.uiStage.addActor(btn1);
		this.lvl.uiStage.addActor(btn2);
		this.lvl.uiStage.addActor(btn3);
		this.lvl.uiStage.addActor(btn4);
		this.lvl.uiStage.addActor(btn5);
		this.lvl.uiStage.addActor(btn6);
		this.lvl.uiStage.addActor(btn7);
		this.lvl.uiStage.addActor(btn8);
		this.lvl.uiStage.addActor(btn9);
		this.lvl.uiStage.addActor(btn10);
		this.lvl.uiStage.addActor(btn11);
		this.lvl.uiStage.addActor(btn12);
		this.lvl.uiStage.addActor(btn13);
		this.lvl.uiStage.addActor(btn14);
		this.lvl.uiStage.addActor(btn15);
		this.lvl.uiStage.addActor(btn16);
		
	}

}
