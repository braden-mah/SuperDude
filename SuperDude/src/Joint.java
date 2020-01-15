
public class Joint implements java.io.Serializable{
	private float transX;
	private float transY;
	private float bodyX;
	private float bodyY;
	private float endX;
	private float endY;
	private float mapBodyX;
	private float mapBodyY;
	private float mapEndX;
	private float mapEndY;
	/**
	 * Initialize the joint object
	 * @param bodyX the x-coord. of the part of the joint attached to the body
	 * @param bodyY the y-coord. of the part of the joint attached to the body
	 * @param endX the x-coord. of the end point of the joint
	 * @param endY the y-coord. of the end point of the joint
	 */
	public Joint(float bodyX, float bodyY, float endX, float endY) {
		this.bodyX=bodyX;
		this.bodyY=bodyY;
		this.endX=endX;
		this.endY=endY;
		mapBodyX=bodyX;
		mapEndX=endX;
		mapBodyY=bodyY;
		mapEndY=endY;
		
	}
	/**
	 * Translate the joint on the map according to the x-axis
	 * @param transX how much to translate the joint by on the x-axis
	 */
	public void transX(float transX) {
		this.transX+=transX;
		mapBodyX+=transX;
		mapEndX+=transX;
	}
	/**
	 * Translate the joint on the map according to the y-axis
	 * @param transY how much to translate the joint by on the y-axis
	 */
	public void transY(float transY) {
		this.transY+=transY;
		mapBodyY+=transY;
		mapEndY+=transY;
	}
	/**
	 * Get the x-coord of the part of the joint attached to the body
	 * @return the x-coord. of the part of the joint attached to the body
	 */
	public float getBodyX() {
		return bodyX;
	}
	/**
	 * Get the y-coord of the part of the joint attached to the body
	 * @return the y-coord. of the part of the joint attached to the body
	 */
	public float getBodyY() {
		return bodyY;
	}
	/**
	 * Get the x-coord. of the end point of the joint
	 * @return the x-coord. of the end point of the joint
	 */
	public float getEndX() {
		return endX;
	}
	/**
	 * Get the y-coord of the end point of the joint
	 * @return the y-coord of the end point of the joint
	 */
	public float getEndY() {
		return endY;
	}
	/**
	 * Set the x-coord. of the part of the joint attached to the body
	 * @param bodyX the x-coord. of the part of the joint attached to the body
	 */
	public void setBodyX(float bodyX) {
		this.bodyX = bodyX;
	}
	/**
	 * Set the y-coord. of the part of the joint attached to the boy
	 * @param bodyY the y-coord. of the part of the joint attached to the body
	 */
	public void setBodyY(float bodyY) {
		this.bodyY = bodyY;
	}
	/**
	 * Set the x-coord. of the end point of the joint
	 * @param endX the x-coord. of the end point of the joint
	 */
	public void setEndX(float endX) {
		this.endX = endX;
	}
	/**
	 * Set the y-coord. of the end point of the joint
	 * @param endY the y-coord. of the end point of the joint
	 */
	public void setEndY(float endY) {
		this.endY = endY;
	}
	/**
	 * Get how much the joint has been translated by on the x-axis
	 * @return how much the joint has been translated by on the x-axis
	 */
	public float getTransX() {
		return transX;
	}
	/**
	 * Get how much the joint has been translated by on the y-axis
	 * @return how much the joint has been translated by on the y-axis
	 */
	public float getTransY() {
		return transY;
	}
	/**
	 * Get the x-coord. of the part of the joint attached to the body (including translations)
	 * @return the x-coord. of the part of the joint attached to the body (including translations)
	 */
	public float getMapBodyX() {
		return mapBodyX;
	}
	/**
	 * Get the y-coord. of the part of the joint attached to the body (including translations)
	 * @return the y-coord. of the part of the joint attached to the body (including translations)
	 */
	public float getMapBodyY() {
		return mapBodyY;
	}
	/**
	 * Get the x-coord. of the end point of the joint (including translations)
	 * @return the x-coord. of the end point of the joint (including translations)
	 */
	public float getMapEndX() {
		return mapEndX;
	}
	/**
	 * Get the y-coord. of the end point of the joint (including translations)
	 * @return the y-coord. of the end point of the joint (including translations)
	 */
	public float getMapEndY() {
		return mapEndY;
	}
	/**
	 * Set how much to translate the joint by on the x-axis
	 * @param transX how much to translate the joint by on the x-axis
	 */
	public void setTransX(float transX) {
		this.transX = transX;
		mapBodyX=bodyX+transX;
		mapEndX=endX+transX;
	}
	/**
	 * Set how much to translate the joint by on the y-axis
	 * @param transY how much to translate the joint by on the y-axis
	 */
	public void setTransY(float transY) {
		this.transY = transY;
		mapBodyY=bodyY+transY;
		mapEndY=endY+transY;
	}
	
}
