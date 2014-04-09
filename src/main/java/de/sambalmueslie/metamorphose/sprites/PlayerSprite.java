package de.sambalmueslie.metamorphose.sprites;

import javafx.scene.image.Image;

public class PlayerSprite extends Sprite {

	public static int MAX_ENERGY = 100;

	public PlayerSprite(Image playerImage, int x, int y) {
		super(playerImage, x, y);
		energy = MAX_ENERGY;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
		if (this.energy > MAX_ENERGY) {
			this.energy = MAX_ENERGY;
		} else if (this.energy < 0) {
			this.energy = 0;
		}
	}

	/** the energy. */
	private int energy;
}
