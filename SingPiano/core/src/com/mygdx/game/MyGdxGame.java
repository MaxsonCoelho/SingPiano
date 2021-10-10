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

	private float tempoTotal;

	private int indexInf;

	private int pontos;
	
	@Override
	public void create () {
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setAutoShapeType(true);

		fileiras = new Array<Fileira>();

		fileiras.add(new Fileira(0, 0));
		fileiras.add(new Fileira(tileHeight, 1));
		fileiras.add(new Fileira(2*tileHeight, 2));

		tempoTotal = 0;

		indexInf = 0;

		pontos = 0;
	}

	@Override
	public void render () {
		input();

		update(Gdx.graphics.getDeltaTime());

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		shapeRenderer.begin();

		for(Fileira f:fileiras){
			f.draw(shapeRenderer);
		}

		shapeRenderer.end();

	}

	private void update(float deltaTime){
		tempoTotal += deltaTime;

		velAtual = velIni + tileHeight*tempoTotal/8f;

		for(int i=0;i<fileiras.size;i++){
			int retorno = fileiras.get(i).update(deltaTime);
			if(retorno != 0){
				if(retorno == 1){
					fileiras.removeIndex(i);
					i--;
					indexInf--;
					
				}
			}
		}

	}

	private void input(){
		if(Gdx.input.justTouched()){
			int x = Gdx.input.getX();
			int y = screeny - Gdx.input.getY();

			for(int i=0;i<fileiras.size;i++){
				int retorno = fileiras.get(i).toque(x, y);
				if(retorno != 0){
					if(retorno == 1 && i == indexInf){
						pontos++;
						indexInf++;
					}else if(retorno == 1) {
						finalizar();
					}else {
						finalizar();
					}
					break;
				}
			}
		}
	}

	private void finalizar(){
		Gdx.input.vibrate(200);
	}
	
	@Override
	public void dispose () {
		shapeRenderer.dispose();
	}
}
