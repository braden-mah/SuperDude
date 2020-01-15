import processing.core.PApplet;

public class Brawl implements java.io.Serializable{
	PApplet parent;
	private float x1=1020;
	private float y1=115;
	private float x2=1080;
	private float y2=150;
	private boolean brawl=false;
	
	public Brawl(PApplet p) {
		parent=p;
	}
	public void display() {
		parent.stroke(0);
		if (brawl==true) {
			parent.fill(61, 226, 99);
		}
		else {
			parent.fill(255);
		}
		parent.rect(x1, y1, x2-x1, y2-y1);
		parent.fill(0);
		parent.textSize(16);
		parent.text("Brawl",1050,140);
	}
	/**
	 * Check to see whether or not the home button has been clicked
	 * @return whether or not the home button has been clicked
	 */
	public void checkClick() {
		if (parent.mouseX>=x1 && parent.mouseX<=x2 && parent.mouseY>=y1 && parent.mouseY<=y2) {
			if (brawl==false) {
				brawl=true;
			}
			else {
				brawl=false;
			}
		}
	}
	public boolean getBrawl() {
		return brawl;
	}
}
