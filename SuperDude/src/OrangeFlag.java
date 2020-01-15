import processing.core.PApplet;
public class OrangeFlag implements java.io.Serializable{
	private float flagPoleX,flagPoleY1,flagPoleY2,flagFlagX1,flagFlagY1,flagFlagX2,flagFlagY2,flagFlagX3,flagFlagY3;
	private boolean complete=false;
	private boolean onField=false;
	transient PApplet parent;
	/**
	 * Initialize the orange flag object
	 * @param p the PApplet
	 */
	public OrangeFlag(PApplet p) {
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
	 * Set the position of the orange flag
	 * @param x the x-position of the orange flag
	 * @param y the y-position of the orange flag
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
	 * Drag the flag when in creative mode
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
	 * Display the orange flag
	 */
	public void display() {
		parent.stroke(0);
		parent.strokeWeight(1);
		parent.line(flagPoleX, flagPoleY1, flagPoleX, flagPoleY2);
		parent.fill(226, 135, 6);
		parent.triangle(flagFlagX1, flagFlagY1, flagFlagX2, flagFlagY2, flagFlagX3, flagFlagY3);
	}	
	/**
	 * Set the PApplet in the object
	 * @param p the PApplet
	 */
	public void setPApplet(PApplet p) {
		parent=p;
	}
	/**
	 * Check to see if the avatar has completed the level by touching the flag
	 * @param x the x-coord. of the avatar
	 * @param y the y-coord. of the avatar's lower body
	 * @param y2 the y-coord. of the avatar's upper body
	 */
	public void check(float x,float y, float y2) {
		if (x>=flagPoleX-10 && x<=flagFlagX2 && y>=flagPoleY1 && y<=flagPoleY2) {
			complete=true;
		}
		else if (x>=flagPoleX-10 && x<=flagFlagX2 && y2>=flagPoleY1 && y2<=flagPoleY2) {
			complete=true;
		}
	}
	/**
	 * Get whether or not the level is complete
	 * @return whether or not the level is complete
	 */
	public boolean getComplete() {
		return complete;
	}
	/**
	 * Set whether or not the level is complete
	 * @param complete whether or not the level is complete
	 */
	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	/**
	 * Get whether or not the flag is on the field in creative mode
	 * @return whether or not the flag is on the field in creative mode
	 */
	public boolean getOnField() {
		return onField;
	}
	/**
	 * Set whether or not the flag is on the field in creative mode
	 * @param onField whether or not the flag is on the field in creative mode
	 */
	public void setOnField(boolean onField) {
		this.onField = onField;
	}
	public float getFlagPoleX() {
		return flagPoleX;
	}
	public float getFlagPoleY2() {
		return flagPoleY2;
	}
	
	
}