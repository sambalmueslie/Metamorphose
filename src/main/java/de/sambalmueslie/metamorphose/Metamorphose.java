package de.sambalmueslie.metamorphose;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import org.apache.log4j.xml.DOMConfigurator;

import de.sambalmueslie.metamorphose.game_engine.GameEngine;

public class Metamorphose extends Application {

	/**
	 * @param args
	 *            the args
	 */
	public static void main(String[] args) {
		DOMConfigurator.configureAndWatch("log4j.xml", 60 * 1000);
		launch(args);
	}

	/**
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		final Canvas gameCanvas = new Canvas(800, 600);
		final Group gameNode = new Group(gameCanvas);
		final Scene gameScene = new Scene(gameNode);

		gameScene.setOnKeyPressed(event -> handleKeyPressed(event));
		gameScene.setOnKeyReleased(event -> handleKeyReleased(event));

		// primaryStage.setFullScreen(true);
		primaryStage.setScene(gameScene);
		primaryStage.show();

		gameEngine = new GameEngine(gameCanvas);
		gameEngine.initialize();
		gameEngine.start();

	}

	/**
	 * Handle key pressed.
	 *
	 * @param event
	 *            the {@link KeyEvent}
	 */
	private void handleKeyPressed(KeyEvent event) {
		switch (event.getCode()) {
		case LEFT:
		case RIGHT:
		case UP:
		case DOWN:
			gameEngine.handleKeyPressed(event);
			break;

		case ESCAPE:
			gameEngine.stop();
			System.exit(0);
			break;
		default:
			break;
		}
	}

	/**
	 * Handle key released.
	 *
	 * @param event
	 *            the {@link KeyEvent}
	 */
	private void handleKeyReleased(KeyEvent event) {
		switch (event.getCode()) {
		case LEFT:
		case RIGHT:
		case UP:
		case DOWN:
			gameEngine.handleKeyReleased(event);
			break;
		case ESCAPE:
			gameEngine.stop();
			Platform.exit();
			break;
		default:
			break;
		}
	}

	/** the {@link GameEngine}. */
	private GameEngine gameEngine;

}
