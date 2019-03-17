package com.jkp.mariobros.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.jkp.mariobros.MarioBros;
import com.jkp.mariobros.Sprites.Brick;
import com.jkp.mariobros.Sprites.Coin;

public class B2WorldCreator {
    public B2WorldCreator(World world, TiledMap map){
        BodyDef bdef = new BodyDef();
        PolygonShape shape  = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //Ground
        for(MapObject object: map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.getWidth()/2)/ MarioBros.PPM,(rect.getY()+rect.getHeight()/2)/MarioBros.PPM);
            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth()/2/MarioBros.PPM,rect.getHeight()/2/MarioBros.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        //Pipes
        for(MapObject object: map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.getWidth()/2)/MarioBros.PPM,(rect.getY()+rect.getHeight()/2)/MarioBros.PPM);
            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth()/2/MarioBros.PPM,rect.getHeight()/2/MarioBros.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        //Coins
        for(MapObject object: map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Coin(world, map, rect);
        }

        //Bricks
        for(MapObject object: map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Brick(world, map, rect);
        }
    }
}
