import processing.core.PApplet;
public class Mainscreen implements java.io.Serializable{
	PApplet parent;
	private boolean inPlay=false;
	private boolean inCreate=false;
	private boolean inMain=true;
	private boolean inHelp=false;
	private boolean inCredits=false;
	/**
	 * Initialize the main screen
	 * @param p the PApplet
	 */
	public Mainscreen(PApplet p) {
		parent=p;
	}
	/**
	 * Check to see if the mouse is hovering over the play button and adjust text colors accordingly
	 */
	public void checkPlay() {
		if (parent.mouseX>500 && parent.mouseX<600 && parent.mouseY>170 && parent.mouseY<205){
			parent.fill(226, 242, 2);
			inPlay=true;
		}
		else {
			inPlay=false;
		}
	}
	/**
	 * Check to see if the mouse is hovering over the create button and adjust text colors accordingly
	 */
	public void checkCreate() {
		if (parent.mouseX>480 && parent.mouseX<615 && parent.mouseY>270 && parent.mouseY<310){
			parent.fill(1, 241, 62);
			inCreate=true;
		}
		else {
			inCreate=false;
		}
	}
	/**
	 * Check to see if the mouse is hovering over the help button and adjust text colors accordingly
	 */
	public void checkHelp() {
		if (parent.mouseX>500 & parent.mouseX<595 && parent.mouseY>370 && parent.mouseY<410) {
			parent.fill(149, 44, 165);
			inHelp=true;
		}
		else {
			inHelp=false;
		}
	}
	/**
	 * Check to see if the mouse is hovering over the credits button and adjust text colors accordingly
	 */
	public void checkCredits() {
		if (parent.mouseX>470 && parent.mouseX<625 && parent.mouseY>465 && parent.mouseY<510) {
			inCredits=true;
			parent.fill(232, 53, 53);
		}
		else {
			inCredits=false;
		}
	}
	/**
	 * Display the main screen
	 */
	public void display() {
		parent.background(0);
		parent.fill(255);
		parent.textSize(64);
		parent.text("SUPERDUDE",1100/2,100);
		parent.textSize(32);
		checkPlay();
		parent.text("PLAY",1100/2,200);
		parent.fill(255);
		checkCreate();
		parent.text("CREATE",1100/2,300);
		parent.fill(255);
		checkHelp();
		parent.text("HELP", 1100/2, 400);
		parent.fill(255);
		checkCredits();
		parent.text("CREDITS", 1100/2, 500);
	}
	/**
	 * Get whether or not the mouse is hovering over the help button
	 * @return whether or not the mouse is hovering over the help button
	 */
	public boolean getHelp() {
		return inHelp;
	}
	/**
	 * Get whether or not the mouse is hovering over the play button
	 * @return whether or not the mouse is hovering over the play button
	 */
	public boolean getPlay() {
		return inPlay;
	}
	/**
	 * Get whether or not the mouse is hovering over the create button
	 * @return whether or not the mouse is hovering over the create button
	 */
	public boolean getCreate() {
		return inCreate;
	}
	/**
	 * Get whether or not the mouse is hovering over the credits button
	 * @return whether or not the mouse is hovering over the credits button
	 */
	public boolean getCredits() {
		return inCredits;
	}
	/**
	 * Set whether or not the user is on the mainscreen
	 * @param bool whether or not the user is on the mainscreen
	 */
	public void setMain(boolean bool) {
		inMain=bool;
	}
	/**
	 * Get whether or not the user is on the mainscreen
	 * @return whether or not the user is on the mainscreen
	 */
	public boolean getMain() {
		return inMain;
	}
	/**
	 * Set whether or not the user is hovering over the play button
	 * @param bool whether or not the user is hovering over the play button
	 */
	public void setPlay(boolean bool) {
		inPlay=bool;
	}
	/**
	 * Set whether or not the user is hovering over the create button
	 * @param bool whether or not the user is hovering over the create button
	 */
	public void setCreate(boolean bool) {
		inCreate=bool;
	}
	/**
	 * Set whether or not the user is hovering over the credits button
	 * @param bool whether or not the user is hovering over the credits button
	 */
	public void setCredits(boolean bool) {
		inCredits=bool;
	}
}
