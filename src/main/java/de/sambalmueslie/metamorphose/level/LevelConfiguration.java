package de.sambalmueslie.metamorphose.level;

import java.util.LinkedList;
import java.util.List;

import javafx.scene.paint.Color;

/**
 * The level configuration.
 *
 * @author Sambalmueslie
 *
 */
public class LevelConfiguration {

	/**
	 * @return the {@link List} of {@link Level}s.
	 */
	public List<Level> getLevels() {
		return levels;
	}

	/**
	 * Create the default configuration.
	 */
	void createDefaultConfiguration() {
		levels.add(new Level(Color.LIGHTGREEN, 0.8, 5, 120, "Catterpillar.png", 100, "DamageLeaf.png", -10, 10, "HealthLeaf.png", 5));
		levels.add(new Level(Color.DARKGREEN, 1.0, 6, 90, "Butterfly.png", 100, "DamageLeaf.png", -10, 10, "Flower.png", 10));
		levels.add(new Level(Color.LIGHTGRAY, 0.6, 7, 60, "Car.png", 100, "Limit.png", -10, 10, "Fuel.png", 10));
		levels.add(new Level(Color.LIGHTBLUE, 1.0, 8, 30, "Helicopter.png", 100, "DamageLeaf.png", -10, 10, "Balloon.png", 5));
	}

	/** the {@link Level}s. */
	private final List<Level> levels = new LinkedList<>();

}
