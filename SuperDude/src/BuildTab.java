import processing.core.PApplet;

public class BuildTab implements java.io.Serializable{
	private float pullX1=0;
	private float pullX2=40;
	private float pullY1=200;
	private float pullY2=400;
	private float pullTriangleX1=10;
	private float pullTriangleY1=275;
	private float pullTriangleX2=30;
	private float pullTriangleY2=300;
	private float pullTriangleX3=10;
	private float pullTriangleY3=325;
	private float iconX1=-100;
	private float wallIconY1=0;
	private float iconX2=0;
	private float wallIconY2=100;
	private float wallIconWallX=-50;
	private float wallIconWallY1=20;
	private float wallIconWallY2=80;
	private float platIconY1=100;
	private float platIconY2=200;
	private float platIconPlatY=150;
	private float platIconPlatX1=-80;
	private float platIconPlatX2=-20;
	private float startFlagIconY1=200;
	private float startFlagIconY2=300;
	private float startFlagIconPoleX=-50;
	private float startFlagIconPoleY1=220;
	private float startFlagIconPoleY2=280;
	private float startFlagIconFlagX1=-50;
	private float startFlagIconFlagY1=220;
	private float startFlagIconFlagX2=-30;
	private float startFlagIconFlagY2=230;
	private float startFlagIconFlagX3=-50;
	private float startFlagIconFlagY3=240;
	private float endFlagIconY1=300;
	private float endFlagIconY2=400;
	private float endFlagIconPoleX=-50;
	private float endFlagIconPoleY1=320;
	private float endFlagIconPoleY2=380;
	private float endFlagIconFlagX1=-50;
	private float endFlagIconFlagY1=320;
	private float endFlagIconFlagX2=-30;
	private float endFlagIconFlagY2=330;
	private float endFlagIconFlagX3=-50;
	private float endFlagIconFlagY3=340;
	private String chosenItem="nothing";
	private PApplet parent;
	private String pullTabStatus = "collapsed";
	/**
	 * Initialize the Build Tab object
	 * @param p the PApplet
	 */
	public BuildTab(PApplet p) {
		parent=p;
	}
	/**
	 * Get the x-coordinate of the pull tab
	 * @return the x-coordinate of the pull tab
	 */
	public float getPullX1() {
		return pullX1;
	}
	/**
	 * Display the build Tab
	 */
	public void display() {
		parent.fill(173, 162, 147);
		parent.stroke(0);
		parent.strokeWeight(1);
		parent.rect(pullX1,pullY1,pullX2-pullX1,pullY2-pullY1);
		parent.fill(35, 132, 13);
		parent.triangle(pullTriangleX1,pullTriangleY1,pullTriangleX2,pullTriangleY2,pullTriangleX3,pullTriangleY3);
		checkHover(iconX1,wallIconY1,iconX2, wallIconY2);
		parent.rect(iconX1, wallIconY1, iconX2-iconX1, wallIconY2-wallIconY1);
		parent.line(wallIconWallX, wallIconWallY1, wallIconWallX, wallIconWallY2);
		checkHover(iconX1,platIconY1,iconX2,platIconY2);
		parent.rect(iconX1, platIconY1, iconX2-iconX1, platIconY2-platIconY1);
		parent.line(platIconPlatX1, platIconPlatY, platIconPlatX2, platIconPlatY);
		checkHover(iconX1,startFlagIconY1,iconX2,startFlagIconY2);
		parent.rect(iconX1, startFlagIconY1, iconX2-iconX1, startFlagIconY2-startFlagIconY1);
		parent.line(startFlagIconPoleX, startFlagIconPoleY1, startFlagIconPoleX, startFlagIconPoleY2);
		parent.fill(11, 186, 25);
		parent.triangle(startFlagIconFlagX1, startFlagIconFlagY1, startFlagIconFlagX2, startFlagIconFlagY2, startFlagIconFlagX3, startFlagIconFlagY3);
		checkHover(iconX1,endFlagIconY1,iconX2,endFlagIconY2);
		parent.rect(iconX1, endFlagIconY1, iconX2-iconX1, endFlagIconY2-endFlagIconY1);
		parent.line(endFlagIconPoleX, endFlagIconPoleY1, endFlagIconPoleX, endFlagIconPoleY2);
		parent.fill(226, 135, 6);
		parent.triangle(endFlagIconFlagX1, endFlagIconFlagY1, endFlagIconFlagX2, endFlagIconFlagY2, endFlagIconFlagX3, endFlagIconFlagY3);
	}
	/**
	 * Check to see if the mouse is hovering over one of the icons on the buildTab and adjust color accordingly
	 * @param x1 the first x-coord. of the icon
	 * @param y1 the first y-coord. of the icon
	 * @param x2 the second x-coord. of the icon
	 * @param y2 the second y-coord. of the icon
	 */
	public void checkHover(float x1, float y1, float x2, float y2) {
		if (parent.mouseX>=x1 && parent.mouseX<=x2 && parent.mouseY>=y1 && parent.mouseY<=y2) {
			parent.fill(237, 106, 99);
		}
		else {
			parent.fill(173, 162, 147);
		}
		
	}
	/**
	 * Check to see if the tab has been clicked, and what actions need to be done based on the click
	 */
	public void checkClick() {
		if (parent.mouseX>=pullX1 && parent.mouseX<=pullX2 && parent.mouseY>=pullY1 && parent.mouseY<=pullY2) {
			if (pullTabStatus.equals("collapsed")) {
				pullTabStatus="expanding";
			}
			else if (pullTabStatus.equals("expanded")) {
				pullTabStatus="collapsing";
			}
		}
		if (parent.mouseX>=iconX1 && parent.mouseX<=iconX2 && parent.mouseY>=wallIconY1 && parent.mouseY<=wallIconY2) {
			chosenItem="wall";
		}
		else if (parent.mouseX>=iconX1 && parent.mouseX<=iconX2 && parent.mouseY>=platIconY1 && parent.mouseY<=platIconY2) {
			chosenItem="platform";
		}
		else if (parent.mouseX>=iconX1 && parent.mouseX<=iconX2 && parent.mouseY>=startFlagIconY1 && parent.mouseY<=startFlagIconY2) {
			chosenItem="startFlag";
		}
		else if (parent.mouseX>=iconX1 && parent.mouseX<=iconX2 && parent.mouseY>=endFlagIconY1 && parent.mouseY<=endFlagIconY2) {
			chosenItem="endFlag";
		}
	}
	/**
	 * Get the name of the item that you want to add to your level
	 * @return the name of the item that you want to add to your level
	 */
	public String getChosenItem() {
		return chosenItem;
	}
	/**
	 * Pull the build tab out, or collapse it
	 */
	public void moveBuildTab() {
		if (pullTabStatus.equals("expanding")) {
			transXBuildTab(4);
		}
		else if (pullTabStatus.equals("collapsing")) {
			transXBuildTab(-4);
		}
	}
	/**
	 * Check to see whether the build tab is fully pulled out or collapsed
	 */
	public void checkBuildTabStatus(){
		if (pullX1>=100) {
			pullTabStatus="expanded";
		}
		else if (pullX1<=0) {
			pullTabStatus="collapsed";
		}
	}
	/**
	 * Set the item that you want to add to your level
	 * @param a the name of the item that you want to add to your level
	 */
	public void setChosenItem(String a) {
		chosenItem=a;
	}
	/**
	 * Translate the build tab based on whether it is being pulled in or out
	 * @param pull the amount to pull/collapse the tab by
	 */
	public void transXBuildTab(float pull) {
		this.pullTriangleX3 += pull;
		this.pullTriangleX2 += pull;
		this.pullTriangleX1 += pull;
		this.pullX2 += pull;
		this.pullX1 += pull;
		this.iconX1 += pull;
		this.iconX2 += pull;
		this.wallIconWallX +=pull;
		this.platIconPlatX1+=pull;
		this.platIconPlatX2+=pull;
		this.startFlagIconPoleX+=pull;
		this.startFlagIconFlagX1+=pull;
		this.startFlagIconFlagX2+=pull;
		this.startFlagIconFlagX3+=pull;
		this.endFlagIconPoleX+=pull;
		this.endFlagIconFlagX1+=pull;
		this.endFlagIconFlagX2+=pull;
		this.endFlagIconFlagX3+=pull;
	}
	
}
