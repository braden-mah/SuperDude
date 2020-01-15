import processing.core.PApplet;

public class Levels implements java.io.Serializable{
	PApplet parent;
	String fileName="";
	/**
	 * Initialize the loading screen
	 * @param p the PApplet
	 */
	public Levels(PApplet p) {
		parent=p;
	}
	/**
	 * Display the loading screen
	 */
	public void display() {
		parent.noStroke();
		parent.fill(200);
		parent.rect(0,0,1100,600);
		parent.textSize(16);
		parent.fill(0);
		parent.text("ENTER FILE NAME:",550,200);
		parent.text(fileName,550,250);
		parent.text("Press [ENTER] to start the level!",550,300);
		
	}
	/**
	 * Add a character to the name of the file
	 * @param c the character to add to the file name
	 */
	public void appendName(char c) {
		fileName+=c;
	}
	/**
	 * Remove a character from the end of the file name 
	 */
	public void removeChar() {
		if (fileName.length()>0) {
			fileName=fileName.substring(0,fileName.length()-1);
		}
	}
	/**
	 * Get the name of the file
	 * @return the name of the file
	 */
	public String getFileName() {
		return fileName;
	}
}
