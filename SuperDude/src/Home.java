import processing.core.PApplet;
public class Home implements java.io.Serializable{
	PApplet parent;
	private float x1=1020;
	private float y1=25;
	private float x2=1080;
	private float y2=60;
	/**
	 * Initialize the home button
	 * @param p the PApplet
	 */
	public Home (PApplet p) {
		parent=p;
	}
	/**
	 * Display the home button
	 */
	public void display() {
		parent.stroke(0);
		if (parent.mouseX>=x1 && parent.mouseX<=x2 && parent.mouseY>=y1 && parent.mouseY<=y2) {
			parent.fill(174, 110, 239);
		}
		else {
			parent.fill(255);
		}
		parent.rect(x1, y1, x2-x1, y2-y1);
		parent.fill(0);
		parent.textSize(16);
		parent.text("Home",1050,50);
	}
	/**
	 * Check to see whether or not the home button has been clicked
	 * @return whether or not the home button has been clicked
	 */
	public boolean checkClick() {
		if (parent.mouseX>=x1 && parent.mouseX<=x2 && parent.mouseY>=y1 && parent.mouseY<=y2) {
			return true;
		}
		else {
			return false;
		}
	}
}
