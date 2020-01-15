import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import processing.core.PApplet;
import processing.core.PConstants;

public class Records implements java.io.Serializable{
	transient private PApplet parent;
	private int scoreNum=0;
	private float[] scores = {999,999,999,999,999};
	private String[] recordNames= {"","","","",""};
	private String name ="";
	private float tempNum;
	private float seconds;
	private float milliseconds;
	private float rectButtonX1=500;
	private float rectButtonX2=600;
	private float rectButtonY1=350;
	private float rectButtonY2=385;
	private float hideButtonX1=820;
	private float hideButtonY1=75;
	private float hideButtonX2=880;
	private float hideButtonY2=105;
	private float scoresRectX1=200;
	private float scoresRectX2=900;
	private float scoresRectY1=50;
	private float scoresRectY2=500;
	private boolean nameEntered=false;
	/**
	 * Initialize the high score object
	 * @param p
	 */
	public Records(PApplet p) {
		parent=p;
	}
	/**
	 * Print the high scores
	 */
	public void show() {
		for (int i=0; i<scores.length; i++) {
			System.out.println(scores[i]);
		}
	}
	/**
	 * Check to see whether or not the score is in the top 5
	 * @param seconds seconds it takes to beat the level
	 * @param milliseconds milliseconds it takes to beat the level
	 * @return whether or not the score is in the top 5
	 */
	public boolean checkScore(float seconds, float milliseconds) {
		for (int i=0; i<=scores.length-1; i++) {
			if (seconds+milliseconds/1000<=scores[i]) {
				scoreNum=i;
				this.seconds=seconds;
				this.milliseconds=milliseconds;
				return true;
			}
		}
		return false;
	}
	/**
	 * Add the score to the high score list
	 */
	public void addScore() {
		for (int j=scores.length-1; j>scoreNum; j--) {
			scores[j]=scores[j-1];
		}
		scores[scoreNum]=seconds+milliseconds/1000;
	}
	/**
	 * Set the PApplet for the object
	 * @param p the PApplet
	 */
	public void setPApplet(PApplet p) {
		parent=p;
	}
	/**
	 * Display the record screen where you input your name
	 */
	public void recordScreen(int scoreNum) {
		parent.textAlign(PConstants.CENTER);
		parent.background(127);
		parent.fill(0);
		parent.textSize(32);
		parent.text("New High Score!",550,100);
		parent.textSize(24);
		parent.text("You are #"+(scoreNum+1)+" on the leaderboard!", 550, 150);
		parent.text("Please enter your name:",550,200);
		parent.text(name,550,250);
		parent.textSize(16);
		parent.text("Press [ENTER] to save your highscore!",550,300);
	}
	/**
	 * Display the high scores list
	 */
	public void recordScreen2(float[] floatRecordsFile, String[] nameRecordsFile) {
		parent.fill(255);
		parent.stroke(0);
		parent.rect(scoresRectX1,scoresRectY1,scoresRectX2-scoresRectX1,scoresRectY2-scoresRectY1);
		parent.textAlign(PConstants.LEFT);
		parent.fill(0);
		parent.textSize(32);
		parent.text("High Scores",250, 100);
		parent.textSize(24);
		for (int i=0; i<nameRecordsFile.length; i++) {
			parent.text("#"+(i+1)+". "+nameRecordsFile[i]+" --> "+floatRecordsFile[i]+"s",250,200+(i*50));
		}
		parent.textAlign(PConstants.CENTER);
	}
	/**
	 * Add a character to the high score name
	 * @param c the character to add to the high score name
	 */
	public void appendName(char c) {
		name+=c;
	}
	/**
	 * Remove the last character for the high score name
	 */
	public void removeChar() {
		if (name.length()>0) {
			name=name.substring(0,name.length()-1);
		}
	}
	/**
	 * Add the name to the high scores list
	 */
	public void addName() {
		if (nameEntered==false) {
			for (int j=recordNames.length-1; j>scoreNum; j--) {
				recordNames[j]=recordNames[j-1];
			}
			recordNames[scoreNum]=name;
			nameEntered=true;
		}
	}
	/**
	 * Check to see whether or not the score is in the top 5
	 * @param seconds seconds it takes to beat the level
	 * @param milliseconds milliseconds it takes to beat the level
	 * @return whether or not the score is in the top 5
	 */
	public boolean checkRecords(float seconds, float milliseconds) {
		for (int i=0; i<=scores.length-1; i++) {
			if (seconds+milliseconds/1000<=scores[i]) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Display the button to display the high scores
	 */
	public void displayButton() {
		parent.stroke(0);
		if (parent.mouseX>=rectButtonX1 && parent.mouseX<=rectButtonX2 && parent.mouseY>=rectButtonY1 && parent.mouseY<=rectButtonY2) {
			parent.fill(174, 110, 239);
		}
		else {
			parent.fill(255);
		}
		parent.rect(rectButtonX1, rectButtonY1, rectButtonX2-rectButtonX1, rectButtonY2-rectButtonY1);
		parent.fill(0);
		parent.textSize(16);
		parent.text("Records",550,375);
	}
	public boolean checkClick() {
		if (parent.mouseX>=rectButtonX1 && parent.mouseX<=rectButtonX2 && parent.mouseY>=rectButtonY1 && parent.mouseY<=rectButtonY2) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * Display the button to hide the high scores
	 */
	public void displayHide() {
		if (parent.mouseX>=hideButtonX1 && parent.mouseX<=hideButtonX2 && parent.mouseY>=hideButtonY1 && parent.mouseY<=hideButtonY2) {
			parent.fill(174, 110, 239);
		}
		else {
			parent.fill(255);
		}
		parent.rect(hideButtonX1,hideButtonY1,hideButtonX2-hideButtonX1,hideButtonY2-hideButtonY1);
		parent.textSize(16);
		parent.fill(0);
		parent.text("Hide",850,95);
		
	}
	/**
	 * Check to see if the mouse is hovering over the hide icon
	 * @return
	 */
	public boolean checkClick2() {
		if (parent.mouseX>=hideButtonX1 && parent.mouseX<=hideButtonX2 && parent.mouseY>=hideButtonY1 && parent.mouseY<=hideButtonY2) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * Get the high score name entered
	 * @return the high score name entere 
	 */
	public boolean getNameEntered() {
		return nameEntered;
	}
	/**
	 * Set the high scoore name entered
	 * @param nameEntered the high score name entered
	 */
	public void setNameEntered(boolean nameEntered) {
		this.nameEntered = nameEntered;
	}
	/**
	 * Set where you place on the leaderboard
	 * @param i where you place on the leaderboard
	 */
	public void setScoreNum(int i) {
		scoreNum=i;
	}
	public String getName() {
		return name;
	}
}
