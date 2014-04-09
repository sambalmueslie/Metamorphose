package de.sambalmueslie.metamorphose.level;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

/**
 * The level manager.
 *
 * @author Sambalmueslie
 *
 */
public class LevelMgr {

	/** the level config file name. */
	private static final String LEVEL_CONFIG_FILE_NAME = "./conf/level.conf";

	/**
	 * Constructor.
	 *
	 * @throws JsonSyntaxException
	 *             on error
	 * @throws JsonIOException
	 *             on error
	 * @throws IOException
	 *             on error
	 */
	public LevelMgr() throws JsonSyntaxException, JsonIOException, IOException {
		final LevelConfiguration configuration = loadLevelConfiguration();
		levels = new LinkedList<>(configuration.getLevels());
	}

	/**
	 * @return the current {@link Level}.
	 */
	public Level getCurrentLevel() {
		if (levelPointer >= levels.size()) {
			return null;
		}
		return levels.get(levelPointer);
	}

	/**
	 * Handle a catched power up.
	 *
	 * @param energy
	 *            the energy
	 */
	public void handlePowerUpCatched(int energy) {
		levelEnergyCounter += energy;
	}

	/**
	 * Initialize.
	 */
	public void initialize() {
		levelPointer = 0;
	}

	/**
	 * @return <code>true</code> if the level is finished.
	 */
	public boolean isLevelFinished() {
		return levelEnergyCounter >= levels.get(levelPointer).getEnergyToWin();
	}

	/**
	 * Switch to next level.
	 */
	public void switchToNextLevel() {
		levelPointer++;
		levelEnergyCounter = 0;
	}

	/**
	 * Load the {@link LevelConfiguration}s.
	 *
	 * @return the {@link LevelConfiguration}
	 * @throws JsonIOException
	 *             on error
	 * @throws JsonSyntaxException
	 *             on error
	 * @throws IOException
	 *             on error
	 */
	private LevelConfiguration loadLevelConfiguration() throws JsonSyntaxException, JsonIOException, IOException {
		final Gson gson = new GsonBuilder().setPrettyPrinting().create();
		final File configFile = new File(LEVEL_CONFIG_FILE_NAME);
		if (configFile.exists()) {
			return gson.fromJson(new FileReader(configFile), LevelConfiguration.class);
		} else {
			configFile.createNewFile();
			final LevelConfiguration configuration = new LevelConfiguration();
			configuration.createDefaultConfiguration();
			final String data = gson.toJson(configuration);
			final FileWriter writer = new FileWriter(configFile);
			writer.write(data);
			writer.close();
			return configuration;
		}
	}

	private int levelEnergyCounter;

	/** the level pointer. */
	private int levelPointer;

	/** the {@link Level}s. */
	private final List<Level> levels;
}
