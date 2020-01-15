import processing.core.PApplet;

public class Save implements java.io.Serializable{
	PApplet parent;
	private float x1=1020;
	private float y1=70;
	private float x2=1080;
	private float y2=105;
	public String fileName="";
	private boolean fileSaved=false;
	/**
	 * Initialize the save screen
	 * @param p the PApplet
	 */
	public Save (PApplet p) {
		parent=p;
	}
	/**
	 * Display the save screen for creative mode
	 */
	public void display() {
		parent.noStroke();
		parent.fill(200);
		parent.rect(0,0,1100,600);
		parent.textSize(16);
		parent.fill(0);
		parent.text("ENTER FILE NAME:",550,200);
		parent.text(fileName,550,250);
		parent.textSize(16);
		parent.text("Press [ENTER] to save the level!",550,300);
		
	}
	/**
	 * Add a character to the file name
	 * @param c the character to be appended to the file name
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
	 * Get the file name
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * display the save button
	 */
	public void displayButton() {
		if (parent.mouseX>=x1 && parent.mouseX<=x2 && parent.mouseY>=y1 && parent.mouseY<=y2) {
			parent.fill(174, 110, 239);
		}
		else {
			parent.fill(255);
		}
		parent.rect(x1, y1, x2-x1, y2-y1);
		parent.fill(0);
		parent.textSize(16);
		parent.text("Save",1050,95);
	}
	/**
	 * Check to see whether the save button has been clicked
	 * @return
	 */
	public boolean checkClick() {
		if (parent.mouseX>=x1 && parent.mouseX<=x2 && parent.mouseY>=y1 && parent.mouseY<=y2) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * Display the requirements to save a level
	 */
	public void displaySaveReqs() {
		parent.textSize(16);
		parent.fill(0);
		parent.text("You need to plant flags first!",550,500);
	}
	/**
	 * Display that the file has been saved successfully
	 */
	public void displaySaved() {
		parent.textSize(16);
		parent.fill(0);
		parent.text("Your file has been saved as \""+fileName+"\"!",550,500);
	}
	/**
	 * Set whether or not the file has been saved
	 * @param bool whether or not the file has been saved
	 */
	public void setFileSaved(boolean bool) {
		fileSaved=bool;
	}
	/**
	 * Get whether or not the file has been saved
	 * @return whether or not the file has been saved
	 */
	public boolean getFileSaved() {
		return fileSaved;
	}
	
}
