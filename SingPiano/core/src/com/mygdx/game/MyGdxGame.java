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

import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {

	private ShapeRenderer shapeRenderer;

	private Array<Fileira> fileiras;

	private float tempoTotal;

	private int indexInf;

	private int pontos;

	private Random rand;

	private int estado;

	private SpriteBatch batch;

	private Texture textIniciar;
	
	@Override
	public void create () {
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setAutoShapeType(true);

		batch = new SpriteBatch();

		fileiras = new Array<Fileira>();

		rand = new Random();

		textIniciar = new Texture("iniciar.png");

		iniciar();
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
		if(estado == 0){
			batch.begin();

			batch.draw(textIniciar, 0, tileHeight/4, screenx, tileHeight/2);

			batch.end();
		}
	}

	private void update(float deltaTime){
		if(estado == 1){
			tempoTotal += deltaTime;

			velAtual = velIni + tileHeight*tempoTotal/8f;

			for(int i=0;i<fileiras.size;i++){
				int retorno = fileiras.get(i).update(deltaTime);
				fileiras.get(i).anim(deltaTime);
				if(retorno != 0){
					if(retorno == 1){
						fileiras.removeIndex(i);
						i--;
						indexInf--;
						adicionar();
					}else if(retorno == 2){
						finalizar(1);
					}
				}
			}
		}else if(estado == 2){
			for(Fileira f:fileiras){
				f.anim(deltaTime);
			}
		}
	}

	private void input(){
		if(Gdx.input.justTouched()){
			int x = Gdx.input.getX();
			int y = screeny - Gdx.input.getY();
			if(estado == 0){
				estado = 1;
			}
			if(estado == 1){
				for(int i=0;i<fileiras.size;i++){
					int retorno = fileiras.get(i).toque(x, y);
					if(retorno != 0){
						if(retorno == 1 && i == indexInf){
							pontos++;
							indexInf++;
						}else if(retorno == 1) {
							fileiras.get(indexInf).erro();
							finalizar(0);
						}else {
							finalizar(0);
						}
						break;
					}
				}
			}else if(estado == 2) iniciar();
		}
	}

	private void adicionar(){
		float y = fileiras.get(fileiras.size-1).y + tileHeight;
		fileiras.add(new Fileira(y, rand.nextInt(4)));
	}

	private void iniciar(){
		tempoTotal = 0;
		indexInf = 0;
		pontos = 0;

		fileiras.clear();
		fileiras.add(new Fileira(tileHeight, rand.nextInt(4)));
		adicionar();
		adicionar();
		adicionar();
		adicionar();

		estado = 0;
	}

	private void finalizar(int opt){

		Gdx.input.vibrate(200);
		estado = 2;
		if(opt == 1){
			for(Fileira f:fileiras){
				f.y += tileHeight;
			}
		}
	}
	
	@Override
	public void dispose () {
		shapeRenderer.dispose();
		batch.dispose();
		textIniciar.dispose();
	}
}
