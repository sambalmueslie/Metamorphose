package de.sambalmueslie.metamorphose.sprites;

import javafx.scene.image.Image;

public class PowerUpSprite extends Sprite {

	public PowerUpSprite(Image powerUpImage, int x, int y, int energy) {
		super(powerUpImage, x, y);
		this.energy = energy;
	}

	public int getEnergy() {
		return energy;
	}

	private final int energy;

}
