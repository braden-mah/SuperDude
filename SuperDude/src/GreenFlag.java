import processing.core.PApplet;

public class GreenFlag implements java.io.Serializable{
	private float flagPoleX,flagPoleY1,flagPoleY2,flagFlagX1,flagFlagY1,flagFlagX2,flagFlagY2,flagFlagX3,flagFlagY3;
	private boolean onField=false;
	transient PApplet parent;
	/**
	 * Initialize the green flag object's values
	 * @param p the PApplet
	 */
	public GreenFlag(PApplet p) {
		flagPoleX=0;
		flagPoleY1=-50;
		flagPoleY2=0;
		flagFlagX1=0;
		flagFlagY1=-50;
		flagFlagX2=10;
		flagFlagY2=-40;
		flagFlagX3=0;
		flagFlagY3=-30;
		parent=p;	
	}
	/**
	 * Set the flags x and y coordinates
	 * @param x x-coordinate of flag
	 * @param y y-coordinate of flag
	 */
	public void setFlag(float x, float y) {
		flagPoleX=x;
		flagPoleY1=y-25f;
		flagPoleY2=y+35f;
		flagFlagX1=x;
		flagFlagY1=y-25f;
		flagFlagX2=x+20;
		flagFlagY2=y-15f;
		flagFlagX3=x;
		flagFlagY3=y-5f;
	}
	/**
	 * Get the flag x coordinate (at the pole)
	 * @return the flag's x-coordinate (at the pole)
	 */
	public float getFlagPoleX() {
		return flagPoleX;
	}
	/**
	 * Get the flag's y coordinate (at the bottom of the pole)
	 * @return the flag's y-coordinate (at the bottom of the pole)
	 */
	public float getFlagPoleY2() {
		return flagPoleY2;
	}
	/**
	 * Get the flag to follow the mouse when dragged out in creative mode
	 */
	public void followMouse() {
		flagPoleX=parent.mouseX;
		flagPoleY1=parent.mouseY-25f;
		flagPoleY2=parent.mouseY+35f;
		flagFlagX1=parent.mouseX;
		flagFlagY1=parent.mouseY-25f;
		flagFlagX2=parent.mouseX+20;
		flagFlagY2=parent.mouseY-15f;
		flagFlagX3=parent.mouseX;
		flagFlagY3=parent.mouseY-5f;
	}
	/**
	 * Display the flag
	 */
	public void display() {
		parent.stroke(0);
		parent.strokeWeight(1);
		parent.line(flagPoleX, flagPoleY1, flagPoleX, flagPoleY2);
		parent.fill(11, 186, 25);
		parent.triangle(flagFlagX1, flagFlagY1, flagFlagX2, flagFlagY2, flagFlagX3, flagFlagY3);
	}
	/**
	 * Set the PApplet for the object
	 * @param p the PApplet
	 */
	public void setPApplet(PApplet p) {
		parent=p;
	}
	/**
	 * Get whether or not the flag is on the field
	 * @return whether or not the flag is on the field
	 */
	public boolean getOnField() {
		return onField;
	}
	/**
	 * Set whether or not the flag is on the field
	 * @param onField whether or not the flag is on the field
	 */
	public void setOnField(boolean onField) {
		this.onField = onField;
	}
	
	
}
