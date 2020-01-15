import processing.core.PApplet;
import processing.core.PConstants;

public class Clock {
	private float seconds;
	private float frameCounter;
	private float milliseconds;
	private float clockX=50;
	private float clockY=50;
	private PApplet parent;
	/**
	 * Initialize the clock object
	 * @param p the PApplet
	 */
	public Clock(PApplet p) {
		parent=p;
	}
	/**
	 * Reset the clock's millisecond counter
	 */
	public void resetMilliseconds() {
		milliseconds=0;
		frameCounter=0;
	}
	/**
	 * Reset the clock's second counter
	 */
	public void resetSeconds() {
		seconds=0;
		frameCounter=0;
	}
	/**
	 * Count the amount of time that has passed
	 */
	public void countTime() {
		milliseconds+=1000/60;
		frameCounter+=1;
		if (frameCounter>=60) {
			seconds+=1;
			frameCounter=0;
			resetMilliseconds();
		}
	}
	/**
	 * Display the clock
	 */
	public void display() {
		parent.fill(255);
		parent.textSize(20);
		parent.textAlign(PConstants.LEFT);
		parent.text(Math.round(seconds)+"."+Math.round(milliseconds),clockX,clockY);
		parent.textAlign(PConstants.CENTER);
	}
	/**
	 * Get the amount of seconds on the clock
	 * @return the amount of seconds on the clock
	 */
	public float getSeconds() {
		return seconds;
	}
	/**
	 * Get the amount of milliseconds on the clock
	 * @return the amount of milliseconds on the clock
	 */
	public float getMilliseconds() {
		return milliseconds;
	}
}
