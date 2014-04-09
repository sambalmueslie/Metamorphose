package de.sambalmueslie.metamorphose.sprites;

import java.util.LinkedList;
import java.util.List;

/**
 * The {@link Sprite} manager.
 * 
 * @author Sambalmueslie
 *
 */
public class SpriteMgr {

	/**
	 * Add a new {@link PowerUpSprite}.
	 *
	 * @param powerUpSprite
	 *            the sprite
	 */
	public void addSprite(PowerUpSprite powerUpSprite) {
		powerUpSprites.add(powerUpSprite);
	}

	/**
	 * Clear the power ups.
	 */
	public void clearPowerUps() {
		powerUpSprites.clear();
	}

	/**
	 * @return the {@link PlayerSprite}.
	 */
	public PlayerSprite getPlayerSprite() {
		return playerSprite;
	}

	/**
	 * @return the {@link PowerUpSprite}s.
	 */
	public List<PowerUpSprite> getPowerUpSprites() {
		return powerUpSprites;
	}

	/**
	 * Remove a {@link PowerUpSprite}.
	 *
	 * @param powerUpSprite
	 *            the sprite
	 */
	public void removeSprite(PowerUpSprite sprite) {
		powerUpSprites.remove(sprite);
	}

	/**
	 * Set the {@link PlayerSprite}.
	 *
	 * @param playerSprite
	 *            the sprite
	 */
	public void setPlayerSprite(PlayerSprite playerSprite) {
		this.playerSprite = playerSprite;
	}

	/** the {@link PlayerSprite}. */
	private PlayerSprite playerSprite;
	/** the {@link PowerUpSprite}s. */
	private final List<PowerUpSprite> powerUpSprites = new LinkedList<>();
}
