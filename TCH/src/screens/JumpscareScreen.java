package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import elements.Elements;
import game.Demo;
import game.Parametros;
import managers.AudioManager;
import managers.ResourceManager;

public class JumpscareScreen extends BScreen {

private static final int FRAME_COLS = 5, FRAME_ROWS = 14;


private Table tabla;
private Animation<TextureRegion> veggy;
SpriteBatch sb;
Texture jumpscareSheet;
float stateTime;

@Override
public void show() {
    super.show();
    stateTime = 0f; 
}

public JumpscareScreen(Demo game) {
	super(game);
	
	//initialize();
	sb = new SpriteBatch();
	//veggy = loadFullAnimation("maps/images/5x14.png",14,5,0,false);
	jumpscareSheet = new Texture(Gdx.files.internal("maps/images/5x14.png"));
	
	
	TextureRegion[][] tmp = TextureRegion.split(jumpscareSheet,
			jumpscareSheet.getWidth() / FRAME_COLS,
			jumpscareSheet.getHeight() / FRAME_ROWS);
	
	TextureRegion[] jsFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
	int index = 0;
	for(int i = 0; i < FRAME_ROWS; i++) {
		//System.out.println("i: " + i);
		for (int j = 0; j < FRAME_COLS; j++) {
			//System.out.println("j: " + j);
			jsFrames[index++] = tmp[i][j];
		}
	}
	
	veggy = new Animation<TextureRegion>(0.025f, jsFrames);
	//sb = new SpriteBatch();
	stateTime = 0f;
	/*
	tabla = new Table();
	tabla.setFillParent(true);
	tabla.setPosition(-500, 100);
	this.uiStage.addActor(tabla);
	
	TextButton resetBtn = new TextButton("Reintentar", ResourceManager.textButtonStyle);
	resetBtn.addListener(
			(Event e)->{if(!(e instanceof InputEvent))
				return false;
			
			InputEvent ie = (InputEvent) e;
			
			//Hover on
	        if (ie.getType() == Type.enter) {
	        	
	        	resetBtn.setStyle(ResourceManager.textButtonHoverStyle);
	        }

	        //Hover off
	        if (ie.getType() == Type.exit) {

	        	resetBtn.setStyle(ResourceManager.textButtonStyle);
	        }

	        // Handle click
	        if (ie.getType() == Type.touchDown) {
	            this.dispose();
	            game.setScreen(new GameScreen(game));
	        }

			return false;
		});
			
	
	tabla.add(resetBtn);
	tabla.row();
	
	TextButton botonSalir = new TextButton("Salir", ResourceManager.textButtonStyle);
	botonSalir.addListener(
			(Event e)->{if(!(e instanceof InputEvent))
				return false;
			
			InputEvent ie = (InputEvent) e;
			
			//Hover on
	        if (ie.getType() == Type.enter) {
	        	
	        	botonSalir.setStyle(ResourceManager.textButtonHoverStyle);
	        }

	        //Hover off
	        if (ie.getType() == Type.exit) {

	        	botonSalir.setStyle(ResourceManager.textButtonStyle);
	        }

	        // Handle click
	        if (ie.getType() == Type.touchDown) {
	        	this.dispose();
	            Gdx.app.exit();
	        }

			return false;
		});
	
	tabla.add(botonSalir);
	
	AudioManager.playMusic("audio/music/defeatMusic.mp3");*/
}

	@Override
	public void render(float delta) {
	    super.render(delta);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
	    // 2. Limitamos el delta time. Si el juego se congela y el delta es gigante, 
	    // lo cortamos a un máximo de 0.05 segundos para no saltarnos la animación.
	    float safeDelta = Math.min(delta, 0.05f);
	    stateTime += safeDelta;
	
	    TextureRegion currentFrame = veggy.getKeyFrame(stateTime, false);
	
	    sb.begin();
	    sb.draw(currentFrame, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	    sb.end();
	
	    uiStage.act();
	    uiStage.draw();
	
	    // Comprobación para salir del jumpscare
	    if (veggy.isAnimationFinished(stateTime)) {
	        System.out.println("El jumpscare ha terminado. Cambiar de pantalla aquí.");
	    }
	}
	
	@Override
	public void dispose() {
		sb.dispose();
		jumpscareSheet.dispose();
	}
	
}
