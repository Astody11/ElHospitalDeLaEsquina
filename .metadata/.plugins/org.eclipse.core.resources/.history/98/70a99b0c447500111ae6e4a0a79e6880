package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

public class EndScreen extends BScreen {
	
private Table tabla;
SpriteBatch sb;
Texture bgImg;

public EndScreen(Demo game) {
	super(game);
	
	//initialize();
	sb = new SpriteBatch();
	bgImg = new Texture(Gdx.files.internal("maps/images/endScreen.jpg"));
	tabla = new Table();
	tabla.setFillParent(true);
	tabla.setPosition(-450, 10);
	this.uiStage.addActor(tabla);
	
	TextButton resetBtn = new TextButton("Volver a jugar", ResourceManager.textButtonStyle);
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
	
	AudioManager.playMusic("audio/music/AngysRoom.mp3");
}


@Override
public void render(float delta) {
	super.render(delta);
	sb.begin();
	sb.draw(bgImg, 0, 0);
	sb.end();
	uiStage.act();
	uiStage.draw();
	}
	
}
