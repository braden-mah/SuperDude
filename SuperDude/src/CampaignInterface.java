import processing.core.PApplet;

public class CampaignInterface {
	private PApplet parent;
	int[][] levels = new int[1][3];
	private int counter=1;
	private int x=150;
	private int y=100;
	private boolean[] hoverList = new boolean[40];
	public CampaignInterface (PApplet p) {
		parent=p;
		for (int i=0; i<levels.length; i++) {
			for (int j=0; j<levels[i].length; j++) {
				levels[i][j]=counter;
				counter+=1;
			}
		}
	}
	public void display() {
		counter=1;
		for (int i=0; i<levels.length; i++) {
			for (int j=0; j<levels[i].length; j++) {
				parent.fill(255);
				parent.stroke(0);
				checkHover();
				parent.rect(x,y,50,50);
				parent.textSize(20);
				parent.fill(0);
				parent.text(levels[i][j],x+25,y+30);
				x+=100;
				counter+=1;
			}
			y+=100;
			x=150;
		}
		y=100;
		x=150;
	}
	public void checkHover() {
		if (parent.mouseX<=x+50 && parent.mouseX>=x && parent.mouseY>=y && parent.mouseY<=y+50) {
			parent.fill(27, 209, 39);
			hoverList[counter-1]=true;
		}
		else {
			hoverList[counter-1]=false;
		}
	}
	public int getLevel() {
		for (int i=0; i<hoverList.length; i++) {
			if (hoverList[i]==true) {
				return i+1;
			}
		}
		return -1;
	}
	public void resetHoverList() {
		for (int i=0; i<hoverList.length; i++) {
			hoverList[i]=false;
		}
	}
}
