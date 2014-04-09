package de.sambalmueslie.metamorphose.resources;

import javafx.scene.text.Font;

public class FontProvider {
	private static FontProvider instance;

	public static FontProvider getInstance() {
		if (instance == null) {
			instance = new FontProvider();
		}
		return instance;
	}

	private FontProvider() {
		defaultFont = Font.loadFont("Gamegirl.ttf", 40);
		dialogFont = Font.loadFont("Gamegirl.ttf", 24);
	}

	public Font getDefaultFont() {
		return (defaultFont == null) ? Font.font(24) : defaultFont;
	}

	public Font getDialogFont() {
		return (defaultFont == null) ? Font.font(16) : dialogFont;
	}

	private final Font defaultFont;

	private final Font dialogFont;
}
