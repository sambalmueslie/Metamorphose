package de.sambalmueslie.metamorphose.sprites;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public abstract class Sprite {

	/**
	 * Constructor.
	 *
	 * @param theX
	 *            {@link #x}
	 * @param theY
	 *            {@link #y}
	 */
	public Sprite(Image theImage, int theX, int theY) {
		image = theImage;
		x = theX;
		y = theY;
	}

	public int getHeight() {
		return (int) image.getHeight();
	}

	public Image getImage() {
		return image;
	}

	public Rectangle2D getPosRect() {
		return new Rectangle2D(x, y, getWidth(), getHeight());
	}

	public int getWidth() {
		return (int) image.getWidth();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	private final Image image;
	private int x;
	private int y;
}
