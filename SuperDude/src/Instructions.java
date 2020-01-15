import processing.core.PApplet;

public class Instructions {
	private float x;
	private float y;
	private float x1;
	private float y1;
	private float x2;
	private float y2;
	private String text;
	private float diameter=20;
	private PApplet parent;
	private boolean hover=false;
	private boolean display=true;
	/**
	 * Initialize the instructions
	 * @param x the x-coordinate for the ? icon
	 * @param y the y-coordinate for the ? icon
	 * @param x1 the first x-coordinate for the text box
	 * @param y1 the first y-coordinate for the text box
	 * @param x2 the second x-coordinate for the text box
	 * @param y2 the second y-coordinate for the text box
	 * @param text the text to display
	 * @param p the PApplet
	 */
	public Instructions(float x, float y, float x1, float y1, float x2, float y2, String text, PApplet p) {
		this.x=x;
		this.y=y;
		this.x1=x1;
		this.y1=y1;
		this.x2=x2;
		this.y2=y2;
		this.text=text;
		parent=p;
	}
	/**
	 * Display tht "?" icon or the text box
	 */
	public void display() {
		if (display==true) {
			parent.fill(0);
			parent.textSize(14);
			parent.text(text,x1,y1,x2-x1,y2-y1);
		}
		if (Math.sqrt(Math.pow(x-parent.mouseX, 2) + Math.pow(y-parent.mouseY, 2))>diameter/2) {
			hover=false;
			parent.fill(14, 152, 186);
		}
		else {
			hover=true;
			parent.fill(213, 242, 24);
		}
		parent.ellipse(x, y, diameter, diameter);
		parent.fill(0);
		parent.textSize(14);
		parent.text("?",x,y+5);
	}
	/**
	 * Get whether or not the mouse is hovering over the "?" icon
	 * @return whether or not the mouse is hovering over the "?" icon
	 */
	public boolean getHover() {
		return hover;
	}
	/**
	 * Set whether or not to display the text
	 */
	public void setDisplay() {
		if (display==false){
			display=true;
		}
		else if (display==true) {
			display=false;
		}
	}
}
