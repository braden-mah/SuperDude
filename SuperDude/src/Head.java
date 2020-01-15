
public class Head implements java.io.Serializable{
	private float x;
	private float y;
	private float transX;
	private float transY;
	private float radius;
	private float mapX;
	private float mapY;
	/**
	 * Initialize the head object
	 * @param x the x-coordinate of the head
	 * @param y the y-coordinate of the head
	 * @param radius the radius of the head
	 */
	public Head(float x, float y, float radius) {
		this.x=x;
		this.y=y;
		this.radius=radius;
		mapX=x;
		mapY=y;
	}
	/**
	 * Translate the head along the x-axis
	 * @param transX how much to translate the head by on the x-axis
	 */
	public void transX(float transX) {
		this.transX+=transX;
		mapX+=transX;
	}
	/**
	 * Translate the head along the y-axis
	 * @param transY how much to translate the head by on the y-axis
	 */
	public void transY(float transY) {
		this.transY+=transY;
		mapY+=transY;
	}
	/**
	 * Set the radius of the head
	 * @param radius the radius of the head
	 */
	public void setRadius(float radius) {
		this.radius = radius;
	}
	/**
	 * Get the x-coord. of the head
	 * @return the x-coordinate of the head
	 */
	public float getX() {
		return x;
	}
	/**
	 * Get the y-coord. of the head
	 * @return the y-coordinate of the head
	 */
	public float getY() {
		return y;
	}
	/**
	 * Get the radius of the had
	 * @return the radius of the head
	 */
	public float getRadius() {
		return radius;
	}
	/**
	 * Get how much to translate the head by on the x-axis
	 * @return how much to translate the head by on the x-axis
	 */
	public float getTransX() {
		return transX;
	}
	/**
	 * Get how much to translate the head by on the y-axis
	 * @return how much to tranlsate the head by on the y-axis
	 */
	public float getTransY() {
		return transY;
	}
	/**
	 * Get the x-coordinate of the head (including translations)
	 * @return the x-coordination of the head (including translations)
	 */
	public float getMapX() {
		return mapX;
	}
	/**
	 * Get the y-coordinate of the head (including translations)
	 * @return the y-coordinate of the head (including translations)
	 */
	public float getMapY() {
		return mapY;
	}
	/**
	 * Set how much to translate the head by on the x-axis
	 * @param transX how much to translate the head by on the x-axis
	 */
	public void setTransX(float transX) {
		this.transX = transX;
		mapX=x+transX;
	}
	/**
	 * Set how much to translate the head by on the y-axis
	 * @param transY how much to translate the head by on the y-axis
	 */
	public void setTransY(float transY) {
		this.transY = transY;
		mapY=y+transY;
	}

	
}
