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

public class Picture extends Interactables {
	
	private Animation<TextureRegion> small;
	private Animation<TextureRegion> big;
	private boolean zoom = false;
	private float posY;
	private float posX;
	Stage s;
	
	private Label infoLbl;
	
	public Picture(float x, float y, Stage s, GameScreen lvl) {
		super(x, y, s, lvl);
		small = loadFullAnimation("maps/images/Cuadro.png", 1, 1, 1, false);
		big = loadFullAnimation("maps/images/CuadroGrande.png", 1, 1, 1, false);
		this.setAnimation(small);
		this.posX = x;
		this.posY = y;
		
		infoLbl = new Label("'E'", ResourceManager.itemStyle);
		s.addActor(infoLbl);
		
		this.setRectangle(450, 500, 0, -100);
		this.lvl = lvl;
		this.s = s;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		if(this.getEnabled() && this.overlaps(this.lvl.player.sensor) && Gdx.input.isKeyJustPressed(Keys.E)) {
			this.setAnimation(big);
			this.setPosition(0, 0);
			this.lvl.uiStage.addActor(this);
			this.zoom = true;
			this.lvl.player.stayStill = true;
			
		} else if (this.zoom && (Gdx.input.isKeyJustPressed(Keys.ESCAPE) || Gdx.input.isKeyJustPressed(Keys.SPACE))) {
			this.setAnimation(small);
			this.setPosition(this.posX, this.posY);
			s.addActor(this);
			this.zoom = false;
			this.lvl.player.stayStill = false;
		}
		
		if(this.getEnabled() && this.overlaps(this.lvl.player.sensor)) {
			infoLbl.setPosition(this.getX() - this.getWidth()/4f, this.getY() + this.getHeight()/3);
			infoLbl.setText("'E'");
		} else {
			infoLbl.setText("");
		}
		
	}
}
