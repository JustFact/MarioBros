package com.jkp.mariobros.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.jkp.mariobros.MarioBros;
import com.jkp.mariobros.Screens.PlayScreen;

public class Mario extends Sprite {
    public enum State {FALLING, JUMPING, STANDING, RUNNING};
    public State currentState;
    public State previousState;
    public World world;
    public Body b2body;
    private TextureRegion marioStand;
    private Animation marioRun;
    private Animation marioJump;
    private float stateTimer;
    private boolean runningRight;

    public Mario(World world, PlayScreen screen){
        super(screen.getAtlas().findRegion("little_mario"));
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 1; i<4;i++){            //Running animation frames
            frames.add(new TextureRegion(getTexture(),i*16,0,16,16));
        }
        marioRun = new Animation(0.1f,frames);
        frames.clear();

        for(int i = 4; i<6; i++){           //Jump animation frames
            frames.add(new TextureRegion(getTexture(),i*16,0,16,16));
        }
        marioJump = new Animation(0.1f,frames);

        marioStand = new TextureRegion(getTexture(),0,0,16,16);
        setBounds(0,0,16/MarioBros.PPM,16/MarioBros.PPM);
        setRegion(marioStand);
        this.world = world;
        defineMario();
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight()/2);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt){
        currentState = getState();
        TextureRegion region;
        switch (currentState){
            case JUMPING:
                region = (TextureRegion) marioJump.getKeyFrame(stateTimer);
                break;
            case RUNNING:
                region = (TextureRegion) marioRun.getKeyFrame(stateTimer, true);
                break;
            case FALLING:
            case STANDING:
                default:
                    region = marioStand;
                    break;
        }
        if((b2body.getLinearVelocity().x<0||!runningRight)&& !region.isFlipX()){
            region.flip(true,false);
            runningRight = false;
        }else if((b2body.getLinearVelocity().x >0 || runningRight)&&region.isFlipX()){
            region.flip(true,false);
            runningRight = true;
        }
        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;
    }

    public State getState(){
        if(b2body.getLinearVelocity().y>0 || (b2body.getLinearVelocity().y<0 && previousState == State.JUMPING)){
            return State.JUMPING;
        }else if(b2body.getLinearVelocity().y < 0){
            return State.FALLING;
        }else if(b2body.getLinearVelocity().x != 0){
            return State.RUNNING;
        }else{
            return  State.STANDING;
        }
    }

    public void defineMario(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32/MarioBros.PPM,32/MarioBros.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);


        FixtureDef fdef = new FixtureDef();
        PolygonShape squareShape = new PolygonShape();
        squareShape.setAsBox(0.05f,0.06f);           //Square shape mario

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(5/MarioBros.PPM);

        fdef.shape = squareShape;
        fdef.friction = 0.8f;
        b2body.createFixture(fdef);
    }
}
