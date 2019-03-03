package com.jkp.mariobros.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.jkp.mariobros.MarioBros;

public class Mario extends Sprite {
    public World world;
    public Body b2body;

    public Mario(World world){
        this.world = world;

        defineMario();
    }

    public void defineMario(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32/MarioBros.PPM,32/MarioBros.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);


        FixtureDef fdef = new FixtureDef();
        PolygonShape squareShape = new PolygonShape();
        squareShape.setAsBox(0.05f,0.05f);           //Square shape mario

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(5/MarioBros.PPM);

        fdef.shape = squareShape;
        fdef.friction = 0.8f;
        b2body.createFixture(fdef);
    }
}
