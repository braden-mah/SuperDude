import processing.core.PApplet;
public class Platform implements java.io.Serializable{
	private float x1,x2,y,xTemp;
	private float x1Distance, x2Distance, yDistance;
	private float leftDistance;
	private float rightDistance;
	private float diameter=12;
	private char dragPlatform='n';
	private boolean chosen=false;
	transient PApplet parent;
	/**
	 * Initialize the platform
	 * @param y the y-coord. of the platform
	 * @param x1 the first x-coord. of the platform (left side)
	 * @param x2 the second x-coord. of the platform (right side)
	 * @param p the PApplet
	 */
	public Platform(float y, float x1, float x2, PApplet p) {
		this.x1=x1;
		this.x2=x2;
		this.y=y;
		parent=p;
	}
	/**
	 * Displays the platform
	 */
	public void displayPlatform() {
		parent.strokeWeight(2);
		parent.stroke(0);
		if (chosen==true) {
			parent.stroke(60, 196, 19);
		}
		parent.line(x1, y, x2, y);
	}
	/**
	 * Displays the joint circles which can be pulled and manipulated to change the platform length in creative mode
	 */
	public void displayJoints() {
		parent.strokeWeight(1);
		parent.stroke(0);
		parent.fill(60, 196, 19, 127);
		parent.ellipse(x1, y, diameter, diameter);
		parent.ellipse(x2, y, diameter, diameter);
	}
	/**
	 * Adjust the length of the platforms in creative mode
	 */
	public void shift() {
		if (dragPlatform=='l') {
			x1=parent.mouseX;
		}
		else if (dragPlatform=='r') {
			x2=parent.mouseX;
		}
		if (chosen==true) {
			y=parent.mouseY;
			x1=parent.mouseX-leftDistance;
			x2=parent.mouseX+rightDistance;
		}
	}
	/**
	 * Check to see whether or not the joint circles are being clicked, allowing for manipulation of platform length
	 */
	public void checkClick() {
		x1Distance=x1-parent.mouseX;
		x2Distance=x2-parent.mouseX;
		yDistance=y-parent.mouseY;
		if (Math.sqrt(((Math.pow(x1Distance,2))+(Math.pow(yDistance,2))))<=diameter/2){
			dragPlatform='l';
			System.out.println(true);
		}
		else if (Math.sqrt(((Math.pow(x2Distance,2))+(Math.pow(yDistance,2))))<=diameter/2){
			dragPlatform='r';
		}
		if ((y+5)>parent.mouseY && (y-5)<parent.mouseY && (x1+6)<parent.mouseX && (x2-6)>parent.mouseX) {
			if (chosen==false) {
				chosen=true;
				leftDistance=parent.mouseX-x1;
				rightDistance=x2-parent.mouseX;
			}
			else if (chosen==true) {
				chosen=false;
			}
		}
		System.out.println(chosen);
	}
	/**
	 * Get what side of the platform is being dragged
	 * @return what side of the platform is being dragged
	 */
	public char getDragPlatform() {
		return dragPlatform;
	}
	/**
	 * Set what side of the platform is being dragged
	 * @param c what side of the platform is being dragged
	 */
	public void setDragPlatform(char c) {
		dragPlatform=c;
		if (x1>x2) {
			xTemp=x2;
			x2=x1;
			x1=xTemp;
		}
	}
	/**
	 * Get the left x-coordinate of the platform
	 * @return the left x-coordinate of the platform
	 */
	public float getX1() {
		return x1;
	}
	/**
	 * Set the left x-coordinate of the platform
	 * @param x1 the left x-coordinate of the platform
	 */
	public void setX1(float x1) {
		this.x1 = x1;
	}
	/**
	 * Get the right x-coordinate of the platform
	 * @return the right x-coordinate of the platform
	 */
	public float getX2() {
		return x2;
	}
	/**
	 * Set the right x-coordinate of the platform
	 * @param x2 the right x-coordinate of the platform
	 */
	public void setX2(float x2) {
		this.x2 = x2;
	}
	/**
	 * Get the y-coordinate of the platform
	 * @return the y-coordinate of the platform
	 */
	public float getY() {
		return y;
	}
	/**
	 * Set the y-coordinate of the platform
	 * @param y the y-coordinate of the platform
	 */
	public void setY(float y) {
		this.y = y;
	}
	/**
	 * Get the mouse to drag the wall in creative mode
	 */
	public void followMouse() {
		y=parent.mouseY;
		x1=parent.mouseX-50;
		x2=parent.mouseX+50;
	}
	/**
	 * Set the PApplet of the object
	 * @param p the PApplet
	 */
	public void setPApplet(PApplet p) {
		parent=p;
	}
	public boolean getChosen() {
		return chosen;
	}
	public void setChosen(boolean bool) {
		chosen=bool;
	}
}