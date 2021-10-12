package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static com.mygdx.game.Cons.*;

public class Fileira {
    public float y;

    private int correta;

    private int pos;

    private boolean ok;

    public Fileira(float y, int correta){
        this.y = y;
        this.correta = correta;
        ok = false;
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

    public int update(float time){
        y -= time*velAtual;
        if(y < 0 - tileHeight){
            if(ok){
                return 1;
            }else {
                return 2;
            }
        }
        return  0;
    }

    public int toque(int tx, int ty){
        if(ty >= y && ty <= y + tileHeight){
            pos = tx/tileWidth;
            if(pos == correta){
                ok = true;
                return 1;
            } else{
                ok = false;
                return 2;
            }
        }
        return 0;
    }

}
