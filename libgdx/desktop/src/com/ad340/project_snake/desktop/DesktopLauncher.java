package com.ad340.project_snake.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ad340.project_snake.ProjectSnake;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = ProjectSnake.TITLE;
		config.width = ProjectSnake.V_WIDTH;
		config.height = ProjectSnake.V_HEIGHT - 400;
		new LwjglApplication(new ProjectSnake(), config);
	}
}
