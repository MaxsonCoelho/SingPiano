package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import static com.mygdx.game.Cons.*;

public class MyGdxGame extends ApplicationAdapter {

	private ShapeRenderer shapeRenderer;

	private Array<Fileira> fileiras;
	
	@Override
	public void create () {
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setAutoShapeType(true);

		fileiras = new Array<Fileira>();

		fileiras.add(new Fileira(0, 0));
		fileiras.add(new Fileira(tileHeight, 1));
		fileiras.add(new Fileira(2*tileHeight, 2));

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		shapeRenderer.begin();

		for(Fileira f:fileiras){
			f.draw(shapeRenderer);
		}

		shapeRenderer.end();

	}
	
	@Override
	public void dispose () {
		shapeRenderer.dispose();
	}
}
