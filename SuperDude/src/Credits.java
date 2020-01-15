import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;

public class Credits {
	private PApplet parent;
	private float spacing=0;
	private float starWarsEffect=0;
	ArrayList<String> credits = new ArrayList<String>();
	/**
	 * Initialize the credits screen
	 * @param p the PApplet
	 */
	public Credits(PApplet p) {
		parent=p;
		credits.add("Developer:");
		credits.add("---------------");
		credits.add("");
		credits.add("Braden Mah");
		credits.add("");
		credits.add("");
		credits.add("Debuggers:");
		credits.add("---------------");
		credits.add("");
		credits.add("Adam");
		credits.add("");
		credits.add("Amanuel");
		credits.add("");
		credits.add("Asad");
		credits.add("");
		credits.add("Chris");
		credits.add("");
		credits.add("Jake");
		credits.add("");
		credits.add("Justin");
		credits.add("");
		credits.add("Kyle");
	}
	/**
	 * Display the credits screen
	 */
	public void display() {
		parent.textAlign(PConstants.CENTER);
		parent.textSize(24);
		parent.fill(255);
		for (String s: credits) {
			parent.text(s,1100/2,100+(25*spacing)-starWarsEffect);
			spacing+=1;
		}
		spacing=0;
		starWarsEffect+=1;
	}
	/**
	 * Reset the scrolling credits effect
	 */
	public void resetStarWarsEffect() {
		starWarsEffect=0;
	}
	
}
