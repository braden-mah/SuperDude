
public class Body implements java.io.Serializable{
	private float topX;
	private float topY;
	private float botX;
	private float botY;
	private float transX;
	private float transY;
	private float mapTopX;
	private float mapTopY;
	private float mapBotX;
	private float mapBotY;
	/**
	 * Initialize the body object
	 * @param topX top x-coordinate of body
	 * @param topY top y-coordinate of body
	 * @param botX bottom x-coord. of body
	 * @param botY bottom y-coord. of body
	 */
	public Body(float topX, float topY, float botX, float botY) {
		this.topX=topX;
		this.topY=topY;
		this.botX=botX;
		this.botY=botY;
		mapTopX=topX;
		mapBotX=botX;
		mapTopY=topY;
		mapBotY=botY;
	}
	/**
	 * Translate the body on the map according to the x-axis
	 * @param transX how much to translate the body by on the x-axis
	 */
	public void transX(float transX) {
		this.transX+=transX;
		mapTopX+=transX;
		mapBotX+=transX;
	}
	/**
	 * Translate the body on the map according to the y-axis
	 * @param transY how much to translate the body by on the y-axis
	 */
	public void transY(float transY) {
		this.transY+=transY;
		mapTopY+=transY;
		mapBotY+=transY;
	}
	/**
	 * Get the top x-coord.
	 * @return the top x-coordinate
	 */
	public float getTopX() {
		return topX;
	}
	/**
	 * Get the top y-coord.
	 * @return the top y-coordinate
	 */
	public float getTopY() {
		return topY;
	}
	/**
	 * Get the bot x-coord.
	 * @return the bot x-coordinate
	 */
	public float getBotX() {
		return botX;
	}
	/**
	 * Get the bot y-coord.
	 * @return the bot y-coordinate
	 */
	public float getBotY() {
		return botY;
	}
	/**
	 * Set the top x coordinate
	 * @param topX the new topX coordinate
	 */
	public void setTopX(float topX) {
		this.topX = topX;
	}
	/**
	 * Set the top y coordinate
	 * @param topY the new topY coordinate
	 */
	public void setTopY(float topY) {
		this.topY = topY;
	}
	/**
	 * Set the bot X coordinate
	 * @param bot X the new botX coordinate
	 */
	public void setBotX(float botX) {
		this.botX = botX;
	}
	/**
	 * Set the bot Y coordinate
	 * @param botY the new botY coordinate
	 */
	public void setBotY(float botY) {
		this.botY = botY;
	}
	/**
	 * Get how much the body has been translated by on the x-axis
	 * @return the amount the body has been translated by on the x-axis
	 */
	public float getTransX() {
		return transX;
	}
	/**
	 * Get how much the body has been translated by on the y-axis
	 * @return the amount the body has been translated by on the y-axis
	 */
	public float getTransY() {
		return transY;
	}
	/**
	 * Get the current top x coordinate of the body, accounting for translations
	 * @return the current top x coordinate of the body, accounting for translations
	 */
	public float getMapTopX() {
		return mapTopX;
	}
	/**
	 * Get the current top y coordinate of the body, accounting for translations
	 * @return the current top y coordinate of the body, accounting for translations
	 */
	public float getMapTopY() {
		return mapTopY;
	}
	/**
	 * Get the current bot x coordinate of the body, accounting for translations
	 * @return the current bot xcoordinate of the body, accounting for translations
	 */
	public float getMapBotX() {
		return mapBotX;
	}
	/**
	 * Get the current bot y coordinate of the body, accounting for translations
	 * @return the current bot y coordinate of the body, accounting for translations
	 */
	public float getMapBotY() {
		return mapBotY;
	}
	/**
	 * Set how much the body should be translated by on the x-axis
	 * @param transX how much the body should be translated by on the x-axis
	 */
	public void setTransX(float transX) {
		this.transX = transX;
		mapBotX=botX+transX;
		mapTopX=topX+transX;
	}
	/**
	 * Set how much the body should be translated by on the y-axis
	 * @param transY how much the body should be translated by on the y-axis
	 */
	public void setTransY(float transY) {
		this.transY = transY;
		mapBotY=botY+transY;
		mapTopY=topY+transY;
	}
}
