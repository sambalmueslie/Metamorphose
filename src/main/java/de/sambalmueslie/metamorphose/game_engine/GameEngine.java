package de.sambalmueslie.metamorphose.game_engine;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import de.sambalmueslie.metamorphose.level.Level;
import de.sambalmueslie.metamorphose.level.LevelMgr;
import de.sambalmueslie.metamorphose.resources.FontProvider;
import de.sambalmueslie.metamorphose.sprites.PlayerSprite;
import de.sambalmueslie.metamorphose.sprites.PowerUpSprite;
import de.sambalmueslie.metamorphose.sprites.SpriteMgr;

/**
 * The game engine.
 *
 * @author Sambalmueslie
 *
 */
public class GameEngine {
	/** the default horizontal velocity. */
	private static final int DEFAULT_HORIZONTAL_VELOCITIY = 10;
	/** the default vertical velocity. */
	private static final int DEFAULT_VERTICAL_VELOCITIY = 10;
	/** the max frames per second. */
	private static final int MAX_FPS = 30;

	/**
	 * Constructor.
	 *
	 * @param theGameCanvas
	 *            {@link #gameCanvas}
	 * @throws IOException
	 *             on error
	 * @throws JsonIOException
	 *             on error
	 * @throws JsonSyntaxException
	 *             on error
	 */
	public GameEngine(Canvas theGameCanvas) throws JsonSyntaxException, JsonIOException, IOException {
		gameCanvas = theGameCanvas;
		levelMgr = new LevelMgr();
		spriteMgr = new SpriteMgr();
	}

	/**
	 * Handle that a key was pressed.
	 *
	 * @param event
	 *            the {@link KeyEvent}
	 */
	public void handleKeyPressed(KeyEvent event) {
		switch (event.getCode()) {
		case LEFT:
			isLeft = true;
			break;
		case RIGHT:
			isRight = true;
			break;
		case UP:
			isUp = true;
			break;
		case DOWN:
			isDown = true;
			break;
		default:
			break;
		}
		updatePlayerVelocity();
	}

	/**
	 * Handle that a key was released.
	 *
	 * @param event
	 *            the {@link KeyEvent}
	 */
	public void handleKeyReleased(KeyEvent event) {
		switch (event.getCode()) {
		case LEFT:
			isLeft = false;
			break;
		case RIGHT:
			isRight = false;
			break;
		case UP:
			isUp = false;
			break;
		case DOWN:
			isDown = false;
			break;
		default:
			break;
		}
		updatePlayerVelocity();
	}

	/**
	 * Initialize.
	 */
	public void initialize() {
		levelMgr.initialize();
		initialisePlayer();
		points = 0;
		gameFinished = false;
		startFPS();
	}

	/**
	 * Start.
	 */
	public void start() {
		final long maxDuration = 1000000000 / MAX_FPS;
		animationTimer = new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				final long nanos = System.nanoTime();
				gameLoop();
				final long duration = nanos - System.nanoTime();
				if (duration < maxDuration) {
					final long wait = (maxDuration - duration) / 1000000;
					try {
						Thread.sleep(wait);
					} catch (final InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};

		animationTimer.start();
	}

	/**
	 * Stop.
	 */
	public void stop() {
		animationTimer.stop();
	}

	/**
	 * Check the collisions.
	 */
	private void checkCollisions() {
		final List<PowerUpSprite> powerUpSprites = spriteMgr.getPowerUpSprites();
		final PlayerSprite playerSprite = spriteMgr.getPlayerSprite();
		final double h = gameCanvas.getHeight();
		final List<PowerUpSprite> collisionList = new LinkedList<>();
		final List<PowerUpSprite> leftList = new LinkedList<>();
		powerUpSprites.forEach(sprite -> {
			if (playerSprite.getPosRect().intersects(sprite.getPosRect())) {
				collisionList.add(sprite);
			} else if (sprite.getY() > h) {
				leftList.add(sprite);
			}
		});
		collisionList.forEach(sprite -> {
			points += (sprite.getEnergy() > 0) ? 1 : -1;
			playerSprite.setEnergy(playerSprite.getEnergy() + sprite.getEnergy());
			levelMgr.handlePowerUpCatched(sprite.getEnergy());
			spriteMgr.removeSprite(sprite);
		});
		leftList.forEach(spriteMgr::removeSprite);
	}

	/**
	 * Create the power ups.
	 */
	private void createPowerUps() {
		final List<PowerUpSprite> powerUpSprites = spriteMgr.getPowerUpSprites();
		final Level currentLevel = levelMgr.getCurrentLevel();
		final int powerUpsPossible = currentLevel.getMaxPowerUps() - powerUpSprites.size();
		if (powerUpsPossible <= 0 || frames % MAX_FPS != 0) {
			return;
		}
		final double w = gameCanvas.getWidth();
		final boolean isGoodPowerUp = random.nextDouble() < currentLevel.getGoodBadPowerUpRatio();
		final int energy = (isGoodPowerUp) ? currentLevel.getPowerUpEnergyGood() : currentLevel.getPowerUpEnergyBad();
		final Image powerUpImage = (isGoodPowerUp) ? currentLevel.getPowerUpImageGood() : currentLevel.getPowerUpBadImage();
		final int x = random.nextInt((int) (w - powerUpImage.getWidth()));
		final int y = 0;

		final PowerUpSprite powerUpSprite = new PowerUpSprite(powerUpImage, x, y, energy);
		spriteMgr.addSprite(powerUpSprite);
	}

	/**
	 * Run the game loop.
	 */
	private void gameLoop() {
		updateGame();
		final PlayerSprite player = spriteMgr.getPlayerSprite();
		if (player.getEnergy() <= 0) {
			stop();
		}
		renderGame();
		frames++;
	}

	/**
	 * Handle that the game is won.
	 */
	private void handleGameWon() {
		gameFinished = true;
		animationTimer.stop();
	}

	/**
	 * Initialise the player.
	 */
	private void initialisePlayer() {
		final double w = gameCanvas.getWidth();
		final double h = gameCanvas.getHeight();
		final Image playerImage = levelMgr.getCurrentLevel().getPlayerImage();
		final int x = (int) (w / 2);
		final int y = (int) (h - playerImage.getHeight());
		final PlayerSprite playerSprite = new PlayerSprite(playerImage, x, y);
		spriteMgr.setPlayerSprite(playerSprite);
	}

	/**
	 * Move the player.
	 */
	private void movePlayer() {
		if (horizontalVelocity == 0 && verticalVelocity == 0) {
			return;
		}
		final double w = gameCanvas.getWidth();
		final double h = gameCanvas.getHeight();
		final PlayerSprite playerSprite = spriteMgr.getPlayerSprite();
		final int x = playerSprite.getX() + horizontalVelocity;
		if (x < 0) {
			playerSprite.setX(0);
		} else if (x > (w - playerSprite.getWidth())) {
			playerSprite.setX((int) (w - playerSprite.getWidth()));
		} else {
			playerSprite.setX(x);
		}
		final int y = playerSprite.getY() + verticalVelocity;
		if (y < 0) {
			playerSprite.setY(0);
		} else if (y > (h - playerSprite.getHeight())) {
			playerSprite.setY((int) h - playerSprite.getHeight());
		} else {
			playerSprite.setY(y);
		}
	}

	/**
	 * Move the power ups.
	 */
	private void movePowerUps() {
		final int powerUpSpeed = levelMgr.getCurrentLevel().getPowerUpSpeed();
		final List<PowerUpSprite> powerUpSprites = spriteMgr.getPowerUpSprites();
		powerUpSprites.forEach(sprite -> sprite.setY(sprite.getY() + powerUpSpeed));
	}

	/**
	 * Render the game.
	 */
	private void renderGame() {
		final GraphicsContext gc = gameCanvas.getGraphicsContext2D();

		final double w = gameCanvas.getWidth();
		final double h = gameCanvas.getHeight();

		final Font font = FontProvider.getInstance().getDefaultFont();
		gc.setFont(font);

		if (gameFinished) {
			final String text = "Game won with " + points + " points.";

			gc.setFill(Color.LIGHTSEAGREEN);
			gc.fillRect(0, 0, w, h);
			gc.setFill(Color.LIGHTGOLDENRODYELLOW);
			gc.fillRect(w / 2 - 170, h / 2 - 29, 325, 50);
			gc.setFill(Color.rgb(0, 0, 0));
			gc.fillText(text, w / 2 - 150, h / 2);
		} else {
			// background
			final Color backgroundColor = levelMgr.getCurrentLevel().getBackgroundColor();
			gc.setFill(backgroundColor);
			gc.fillRect(0, 0, w, h);

			// sprites
			final List<PowerUpSprite> powerUpSprites = spriteMgr.getPowerUpSprites();
			powerUpSprites.forEach(sprite -> gc.drawImage(sprite.getImage(), sprite.getX(), sprite.getY()));
			final PlayerSprite playerSprite = spriteMgr.getPlayerSprite();
			gc.drawImage(playerSprite.getImage(), playerSprite.getX(), playerSprite.getY());

			// energy bar
			gc.setFill(Color.rgb(0, 0, 0));
			gc.fillText("Energy:", w - 205, 29);
			gc.setFill(Color.rgb(0, 255, 0));
			final double el = 100 * (playerSprite.getEnergy() / (float) PlayerSprite.MAX_ENERGY);
			gc.fillRect(w - 120, 30 - font.getSize() + 2, el, font.getSize());

			gc.setFill(Color.rgb(0, 0, 0));
			gc.fillText(String.valueOf(playerSprite.getEnergy()), w - 70, 30);

			// score
			gc.setFill(Color.rgb(0, 0, 0));
			gc.fillText("Score:", w - 190, 70);
			gc.fillText(String.valueOf(points), w - 120, 70);

			// fps
			gc.fillText("FPS: " + fps, 30, 30);
		}

	}

	/**
	 * Start the frame per second measurement.
	 */
	private void startFPS() {
		final long start = System.currentTimeMillis();
		fpsTimer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				final long now = System.currentTimeMillis();
				final long execTime = now - start;
				fps = frames * 1000 / execTime;
			}
		}, 1000, 1000);

	}

	/**
	 * Update game.
	 */
	private void updateGame() {
		createPowerUps();
		movePlayer();
		movePowerUps();
		checkCollisions();
		updateLevel();
	}

	/**
	 * Update the level.
	 */
	private void updateLevel() {
		if (levelMgr.isLevelFinished()) {
			spriteMgr.clearPowerUps();
			levelMgr.switchToNextLevel();
			if (levelMgr.getCurrentLevel() == null) {
				handleGameWon();
			} else {
				initialisePlayer();
			}
		}
	}

	/**
	 * Update the player velocity.
	 */
	private void updatePlayerVelocity() {
		if (isLeft != isRight) {
			horizontalVelocity = (isLeft) ? -DEFAULT_HORIZONTAL_VELOCITIY : DEFAULT_HORIZONTAL_VELOCITIY;
		} else {
			horizontalVelocity = 0;
		}
		if (isUp != isDown) {
			verticalVelocity = (isUp) ? -DEFAULT_VERTICAL_VELOCITIY : DEFAULT_VERTICAL_VELOCITIY;
		} else {
			verticalVelocity = 0;
		}

	}

	/** the {@link AnimationTimer}. */
	private AnimationTimer animationTimer;
	/** the frames per second. */
	private long fps;
	/** the frames per second {@link Timer}. */
	private final Timer fpsTimer = new Timer();
	/** the frames. */
	private long frames;
	/** the game {@link Canvas}. */
	private final Canvas gameCanvas;
	/** the game is finished. */
	private boolean gameFinished;
	/** the current horizontal velocity. */
	private int horizontalVelocity;
	private boolean isDown;
	private boolean isLeft;
	private boolean isRight;
	private boolean isUp;
	/** the {@link LevelMgr}. */
	private final LevelMgr levelMgr;
	/** the points. */
	private int points;
	/** the {@link Random}. */
	private final Random random = new Random();
	/** the {@link SpriteMgr}. */
	private final SpriteMgr spriteMgr;
	/** the current vertical velocity. */
	private int verticalVelocity;
}
