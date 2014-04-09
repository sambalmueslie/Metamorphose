package de.sambalmueslie.metamorphose.resources;

import javafx.scene.image.Image;

public class ImageProvider {
	private static ImageProvider instance;

	public static ImageProvider getInstance() {
		if (instance == null) {
			instance = new ImageProvider();
		}
		return instance;
	}

	private ImageProvider() {
		damageLeaf = new Image("DamageLeaf.png");
		healthLeaf = new Image("HealthLeaf.png");
		catterpillar = new Image("Catterpillar.png");
		butterfly = new Image("Butterfly.png");
		flower = new Image("Flower.png");
		car = new Image("Car.png");
		fuel = new Image("Fuel.png");
		limit = new Image("Limit.png");
		balloons = new Image("Balloons.png");
		helicopter = new Image("Helicopter.png");
	}

	public Image getBalloons() {
		return balloons;
	}

	public Image getButterfly() {
		return butterfly;
	}

	public Image getCar() {
		return car;
	}

	public Image getCatterpillar() {
		return catterpillar;
	}

	public Image getDamageLeaf() {
		return damageLeaf;
	}

	public Image getFlower() {
		return flower;
	}

	public Image getFuel() {
		return fuel;
	}

	public Image getHealthLeaf() {
		return healthLeaf;
	}

	public Image getHelicopter() {
		return helicopter;
	}

	public Image getImage(String imageName) {
		return new Image(imageName);
	}

	public Image getLimit() {
		return limit;
	}

	private final Image balloons;
	private final Image butterfly;
	private final Image car;
	private final Image catterpillar;
	private final Image damageLeaf;
	private final Image flower;
	private final Image fuel;
	private final Image healthLeaf;

	private final Image helicopter;

	private final Image limit;
}
