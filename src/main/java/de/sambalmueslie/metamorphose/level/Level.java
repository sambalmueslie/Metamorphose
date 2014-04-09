package de.sambalmueslie.metamorphose.level;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * The level.
 *
 * @author Sambalmueslie
 *
 */
public class Level {

	/**
	 * Constructor.
	 */
	public Level() {
		backgroundColor = Color.GREY;
		goodBadPowerUpRatio = 0.5;
		maxPowerUps = 5;
		maxTimeInSeconds = 60;
		playerImageName = "Catterpillar.png";
		energyToWin = 100;
		powerUpBadImageName = "DamageLeaf.png";
		powerUpEnergyBad = -10;
		powerUpEnergyGood = 20;
		powerUpGoodImageName = "HealthLeaf.png";
		powerUpSpeed = 100;
	}

	/**
	 * Constructor.
	 *
	 * @param backgroundColor
	 *            {@link #backgroundColor}
	 * @param goodBadPowerUpRatio
	 *            {@link #goodBadPowerUpRatio}
	 * @param maxPowerUps
	 *            {@link #maxPowerUps}
	 * @param maxTimeInSeconds
	 *            {@link #maxTimeInSeconds}
	 * @param playerImageName
	 *            {@link #playerImageName}
	 * @param energyToWin
	 *            {@link #energyToWin}
	 * @param powerUpBadImageName
	 *            {@link #powerUpBadImageName}
	 * @param powerUpEnergyBad
	 *            {@link #powerUpEnergyBad}
	 * @param powerUpEnergyGood
	 *            {@link #powerUpEnergyGood}
	 * @param powerUpGoodImageName
	 *            {@link #powerUpGoodImageName}
	 * @param powerUpSpeed
	 *            {@link #powerUpSpeed}
	 */
	public Level(Color backgroundColor, double goodBadPowerUpRatio, int maxPowerUps, int maxTimeInSeconds, String playerImageName,
			int energyToWin, String powerUpBadImageName, int powerUpEnergyBad, int powerUpEnergyGood, String powerUpGoodImageName,
			int powerUpSpeed) {
		this.backgroundColor = backgroundColor;
		this.goodBadPowerUpRatio = goodBadPowerUpRatio;
		this.maxPowerUps = maxPowerUps;
		this.maxTimeInSeconds = maxTimeInSeconds;
		this.playerImageName = playerImageName;
		this.energyToWin = energyToWin;
		this.powerUpBadImageName = powerUpBadImageName;
		this.powerUpEnergyBad = powerUpEnergyBad;
		this.powerUpEnergyGood = powerUpEnergyGood;
		this.powerUpGoodImageName = powerUpGoodImageName;
		this.powerUpSpeed = powerUpSpeed;
	}

	/**
	 * @return the {@link #backgroundColor}.
	 */
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * @return {@link #energyToWin}.
	 */
	public int getEnergyToWin() {
		return energyToWin;
	}

	/**
	 * @return the {@link #goodBadPowerUpRatio}.
	 */
	public double getGoodBadPowerUpRatio() {
		return goodBadPowerUpRatio;
	}

	/**
	 * @return {@link #maxPowerUps}
	 */
	public int getMaxPowerUps() {
		return maxPowerUps;
	}

	/**
	 * @return {@link #maxTimeInSeconds}.
	 */
	public int getMaxTimeInSeconds() {
		return maxTimeInSeconds;
	}

	/**
	 * @return the player {@link Image}.
	 */
	public Image getPlayerImage() {
		return new Image(playerImageName);
	}

	/**
	 * @return the bad power up {@link Image}.
	 */
	public Image getPowerUpBadImage() {
		return new Image(powerUpBadImageName);
	}

	/**
	 * @return {@link #powerUpEnergyBad}.
	 */
	public int getPowerUpEnergyBad() {
		return powerUpEnergyBad;
	}

	/**
	 * @return {@link #powerUpEnergyGood}.
	 */
	public int getPowerUpEnergyGood() {
		return powerUpEnergyGood;
	}

	/**
	 * @return the good power up {@link Image}.
	 */
	public Image getPowerUpImageGood() {
		return new Image(powerUpGoodImageName);
	}

	/**
	 * @return {@link #powerUpSpeed}
	 */
	public int getPowerUpSpeed() {
		return powerUpSpeed;
	}

	/** the background color. */
	private final Color backgroundColor;
	/** the needed energy to win. */
	private final int energyToWin;
	/** the ratio between good and bad power ups. */
	private final double goodBadPowerUpRatio;
	/** the max power ups at the same time. */
	private final int maxPowerUps;
	/** the max time in seconds. */
	private final int maxTimeInSeconds;
	/** the name of the player image. */
	private final String playerImageName;
	/** the name of the 'bad' power up image. */
	private final String powerUpBadImageName;
	/** the energy of the 'bad' power up (must be below zero to be bad). */
	private final int powerUpEnergyBad;
	/** the energy of the 'good' power up (must be above zero to be bad). */
	private final int powerUpEnergyGood;
	/** the name of the 'good' power up image. */
	private final String powerUpGoodImageName;
	/** the speed in pixel per second. */
	private final int powerUpSpeed;
}
