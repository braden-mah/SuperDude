import processing.core.PApplet;
public class Wall implements java.io.Serializable{
	private float x,y1,y2,yTemp;
	private float diameter=12;
	private float xDistance,y1Distance,y2Distance;
	private float upDistance;
	private float downDistance;
	private char dragWall='n';
	private boolean chosen=false;
	transient PApplet parent;
	/**
	 * Initialize the wall object
	 * @param x the x-coord. of the wall object
	 * @param y1 the top y-coord. of the wall object
	 * @param y2 the bot y-coord. of the wall object
	 * @param p the PApplet
	 */
	public Wall(float x, float y1, float y2, PApplet p) {
		this.x=x;
		this.y1=y1;
		this.y2=y2;
		parent=p;
	}
	/**
	 * Display the wall
	 */
	public void displayWall() {
		parent.strokeWeight(2);
		parent.stroke(0);
		if (chosen==true) {
			parent.stroke(60, 196, 19, 127);
		}
		parent.line(x, y1, x, y2);
	}
	/**
	 * Display the circle joints of the wall which allow the wall height to be adjusted in creative mode
	 */
	public void displayJoints() {
		parent.strokeWeight(1);
		parent.stroke(0);
		parent.fill(60, 196, 19, 127);
		parent.ellipse(x, y1, diameter, diameter);
		parent.ellipse(x, y2, diameter, diameter);
	}
	/**
	 * Adjust the y-coordinates of the wall in creative mode
	 */
	public void shift() {
		if (dragWall=='u') {
			y1=parent.mouseY;
		}
		else if (dragWall=='d') {
			y2=parent.mouseY;
		}
		if (chosen==true) {
			x=parent.mouseX;
			y1=parent.mouseY-upDistance;
			y2=parent.mouseY+downDistance;
		}
	}
	/**
	 * Determine if the joint circles have been clicked to allow for height adjustment 
	 */
	public void checkClick() {
		xDistance=x-parent.mouseX;
		y1Distance=y1-parent.mouseY;
		y2Distance=y2-parent.mouseY;
		if (Math.sqrt(((Math.pow(xDistance,2))+(Math.pow(y1Distance,2))))<=diameter/2){
			dragWall='u';
		}
		else if (Math.sqrt(((Math.pow(xDistance,2))+(Math.pow(y2Distance,2))))<=diameter/2){
			dragWall='d';
		}
		if ((x+5)>parent.mouseX && (x-5)<parent.mouseX && (y1+6)<parent.mouseY && (y2-6)>parent.mouseY) {
			if (chosen==false) {
				chosen=true;
				upDistance=parent.mouseY-y1;
				downDistance=y2-parent.mouseY;
			}
			else if (chosen==true) {
				chosen=false;
			}
		}
		System.out.println(chosen);
	}
	/**
	 * Get what wall joint is being adjusted
	 * @return the wall joint that is being adjusted
	 */
	public char getDragWall() {
		return dragWall;
	}
	/**
	 * Set what wall joint is being adjusted
	 * @param c what wall joint is being adjusted
	 */
	public void setDragWall(char c) {
		dragWall=c;
		if (y1>y2) {
			yTemp=y2;
			y2=y1;
			y1=yTemp;
		}
	}
	/**
	 * Get the x-coord. of the wall
	 * @return the x-coord. of the wall
	 */
	public float getX() {
		return x;
	}
	/**
	 * Get the top y-coord. of the wall
	 * @return the top y-coord. of the wall
	 */
	public float getY1() {
		return y1;
	}
	/**
	 * Get the bottom y-coord. of the wall
	 * @return the bottom y-coord. of the wall
	 */
	public float getY2() {
		return y2;
	}
	/**
	 * Drag the wall in creative mode
	 */
	public void followMouse() {
		this.x=parent.mouseX;
		this.y1=parent.mouseY-50;
		this.y2=parent.mouseY+50;
	}
	/**
	 * Set the PApplet for the object
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
