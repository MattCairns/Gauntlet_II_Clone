package com.matthewcairns.flameblade;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by Matthew Cairns on 06/05/2014.
 * All rights reserved.
 */
public class MainMenuScreen implements Screen {
    final Flameblade game;

    Stage stage;

    //Load UI texture atlas from assets.
    TextureAtlas atlas = new TextureAtlas("ui/ui_spritesheet.txt");
    Skin skin;

    TextButton titleButton;
    TextButton playButton;
    TextButton.TextButtonStyle style;

    OrthographicCamera camera;

    Music music;

    public MainMenuScreen(final Flameblade gam) {

        game = gam;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        stage = new Stage(new FitViewport(800, 480));
        Gdx.input.setInputProcessor(stage);

        //Add the atlas to a skin for the buttons.
        skin = new Skin();
        skin.addRegions(atlas);
        style = new TextButton.TextButtonStyle();
        style.up = skin.getDrawable("buttonLong_brown");
        style.down = skin.getDrawable("buttonLong_brown_pressed");

        style.font = game.font;
        titleButton = new TextButton("Gauntlet II Clone", style);
        titleButton.setPosition(400-95, 350);
        playButton = new TextButton("Click to Play", style);
        playButton.setPosition(400-95, 250);

        playButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                gam.setScreen(new MainGame(game));
                music.stop();
            }
        });

        stage.addActor(titleButton);
        stage.addActor(playButton);

        music = Gdx.audio.newMusic(Gdx.files.internal("music/thesagabegins.ogg"));
        music.setVolume(0.5f);
        music.play();

    }

    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(0.247f, 0.486f, 0.714f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        stage.draw();
    }

    @Override
    public void resume() {}
    @Override
    public void pause() {}
    @Override
    public void resize(int width, int height) {}
    @Override
    public void hide() {}
    @Override
    public void show() {}
    @Override
    public void dispose() {
        stage.dispose();
        music.dispose();
    }
}
