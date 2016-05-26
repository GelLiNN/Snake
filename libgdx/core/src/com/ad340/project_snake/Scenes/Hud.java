package com.ad340.project_snake.Scenes;

import com.ad340.project_snake.Utils.ProjectSnake;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Tim on 4/10/2016.
 */
public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport;

    private Integer score;

    Label scoreLabel;

    public Hud(SpriteBatch sb) {
        score = 0;

        // create the stage
        viewport = new FitViewport(ProjectSnake.V_WIDTH, ProjectSnake.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        // create table
        Table table = new Table();
        table.top(); // places the table at the top
        table.setFillParent(true); // makes the table the size of the stage

        // create score label
        scoreLabel = new Label("Score: " + String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.FIREBRICK));
        // add score label to table
        table.add(scoreLabel).expandX().padTop(10);

        // add table to the stage
        stage.addActor(table);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}