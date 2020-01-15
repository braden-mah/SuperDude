import processing.core.PApplet;

public class Retry {
	private float x1=1020;
	private float y1=65;
	private float x2=1080;
	private float y2=100;
	private PApplet parent;
	public Retry(PApplet p){
		parent=p;
	}
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
		parent.text("Retry",1050,90);
	}
	public boolean checkClick() {
		if (parent.mouseX>=x1 && parent.mouseX<=x2 && parent.mouseY>=y1 && parent.mouseY<=y2) {
			return true;
		}
		else {
			return false;
		}
	}
}
