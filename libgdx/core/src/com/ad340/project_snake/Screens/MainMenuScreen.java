package com.ad340.project_snake.Screens;

import com.ad340.project_snake.Utils.ProjectSnake;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

/**
 * Created by austin on 4/26/16.
 */
public class MainMenuScreen implements Screen {
    ProjectSnake game;

    // The skin is used on the button
    Skin skin;
    Stage stage;
    SpriteBatch batch;
    private OrthographicCamera menuCam;
    private BitmapFont font;

    public MainMenuScreen(final ProjectSnake game) {
        this.game = game;

        menuCam = new OrthographicCamera();
        // MenuCam sets the resolution of the display
        menuCam.setToOrtho(false, 1080, 1920);

        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        skin = new Skin();
        // Information about the button
        Pixmap pixmap = new Pixmap(100, 100, Format.RGBA8888);
        pixmap.setColor(Color.GREEN);
        pixmap.fill();

        skin.add("white", new Texture(pixmap));

        // Creates a new bitmap font to be displayed on the button
        BitmapFont bfont=new BitmapFont();
        bfont.getData().setScale(1);
        skin.add("default",bfont);

        // Creates a new button
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);

        textButtonStyle.font = skin.getFont("default");

        skin.add("default", textButtonStyle);

        // Set the text of the button and the location
        final TextButton textButton=new TextButton("PLAY",textButtonStyle);
        textButton.setPosition(200, 200);
        stage.addActor(textButton);
        stage.addActor(textButton);
        stage.addActor(textButton);

        // Listener for the button
        textButton.setBounds(200, 200, 100, 100);
        textButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                // This is what happens when the button is clicked
                game.setScreen(new PlayScreen(game));
                return true;
            }
        });


    }

    @Override
    // Adds the elements to the screen (the button in this case)
    public void render (float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void dispose () {
        stage.dispose();
        skin.dispose();
    }


    @Override
    public void show() {

    }

    @Override
    public void resize (int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}