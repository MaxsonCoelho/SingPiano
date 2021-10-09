package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static com.mygdx.game.Cons.*;

public class Fileira {
    private float y;
    private int correta;

    public Fileira(float y, int correta){
        this.y = y;
        this.correta = correta;
    }

    public void draw(ShapeRenderer shapeRenderer){

        shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(verde);

        shapeRenderer.rect(correta*tileWidth, y, tileWidth, tileHeight);

        shapeRenderer.set(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.GRAY);

        for(int i=0;i<=3;i++){
            shapeRenderer.rect(i*tileWidth, y, tileWidth, tileHeight);
        }
    }
}
