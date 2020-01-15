import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PConstants;
public class Hero extends PApplet implements java.io.Serializable{
	private int wallCollisionCounter=0;
	private float rotateCounter=0;
	private float velX=0;
	private float velY=0;
	private boolean stopPlatformRotation;
	private boolean stopWallRotation;
	private boolean dir=false;
	private boolean bobDown=true;
	private boolean jump=false;
	private boolean runRight=false;
	private boolean runLeft=false;
	private boolean wallCollisionCooldown=false;
	private char wallCollision='n';
	private boolean onPlatform = false;
	private char jumpDirection;
	private char slide='n';
	private boolean slideDown=false;
	private Wall collisionWall;
	private float hitX1,hitX2,hitY1,hitY2;
	Head head = new Head(0,-6,12);
	Body body = new Body(0,0,0,20);
	Joint leftArm = new Joint(0,0,-7,+15);
	Joint rightArm = new Joint(0,0,7,15);
	Joint leftLeg = new Joint(0,0,-7.5f,15f);
	Joint rightLeg = new Joint(0,0,7.5f,15f);
	PShape headShape;
	PShape bodyShape;
	PShape leftArmShape;
	PShape rightArmShape;
	PShape leftLegShape;
	PShape rightLegShape;
	PApplet parent;
	private char legForward='r';
	/**
	 * Initialize the hero object
	 * @param p the PApplet
	 */
	public Hero(PApplet p) {
		parent=p;
	}
	/**
	 * Display the avatar while stationary
	 */
	public void displayStationary() {
		parent.strokeWeight(1);
		parent.stroke(0);
		parent.pushMatrix();
		parent.translate(body.getTransX(),body.getTransY());
		parent.line(body.getTopX(),body.getTopY(),body.getBotX(),body.getBotY());
		parent.popMatrix();
		parent.pushMatrix();
		parent.translate(head.getTransX(),head.getTransY());
		parent.ellipse(head.getX(),head.getY(),head.getRadius(),head.getRadius());
		parent.popMatrix();
		parent.pushMatrix();
		parent.translate(leftArm.getTransX(),leftArm.getTransY());
		parent.line(leftArm.getBodyX(),leftArm.getBodyY(),leftArm.getEndX(),leftArm.getEndY());
		parent.line(rightArm.getBodyX(),rightArm.getBodyY(),rightArm.getEndX(), rightArm.getEndY());
		parent.popMatrix();
		parent.pushMatrix();
		parent.translate(leftLeg.getTransX(), leftLeg.getTransY());
		parent.line(leftLeg.getBodyX(),leftLeg.getBodyY(),leftLeg.getEndX(),leftLeg.getEndY());
		parent.line(rightLeg.getBodyX(),rightLeg.getBodyY(),rightLeg.getEndX(),rightLeg.getEndY());
		parent.popMatrix();
	}
	/**
	 * Set the starting position of the avatar
	 * @param x the starting x-coord.
	 * @param y the starting y-coord.
	 */
	public void startPosition(float x, float y) {
		parent.strokeWeight(1);
		parent.pushMatrix();
		parent.translate(x,y);
		body.setTransX(x);
		body.setTransY(y);
		parent.line(body.getTopX(),body.getTopY(),body.getBotX(),body.getBotY());
		parent.popMatrix();
		parent.pushMatrix();
		parent.translate(x,y);
		head.setTransX(x);
		head.setTransY(y);
		parent.ellipse(head.getX(),head.getY(),head.getRadius(),head.getRadius());
		parent.popMatrix();
		parent.pushMatrix();
		parent.translate(x, y+3);
		parent.line(leftArm.getBodyX(),leftArm.getBodyY(),leftArm.getEndX(),leftArm.getEndY());
		leftArm.setTransX(x);
		leftArm.setTransY(y+3);
		parent.line(rightArm.getBodyX(),rightArm.getBodyY(),rightArm.getEndX(), rightArm.getEndY());
		rightArm.setTransX(x);
		rightArm.setTransY(y+3);
		parent.popMatrix();
		parent.pushMatrix();
		parent.translate(x, y+20);
		parent.line(leftLeg.getBodyX(),leftLeg.getBodyY(),leftLeg.getEndX(),leftLeg.getEndY());
		leftLeg.setTransX(x);
		leftLeg.setTransY(y+20);
		parent.line(rightLeg.getBodyX(),rightLeg.getBodyY(),rightLeg.getEndX(),rightLeg.getEndY());
		rightLeg.setTransX(x);
		rightLeg.setTransY(y+20);
		parent.popMatrix();
	}
	/**
	 * Get the lowest y position of the avatar's body (including translations)
	 * @return the lowest y position of the avatar's body (including translations)
	 */
	public float getMapBodyBotY() {
		return body.getMapBotY();
	}
	/**
	 * Get the x position of the avatar's body (including translations)
	 * @return the x position of the avatar's body (including translations)
	 */
	public float getMapBodyX() {
		return body.getMapBotX();
	}
	/**
	 * Reset the velocities of the avatar
	 */
	public void resetVel() {
		velX=0;
		velY=0;
	}
	/**
	 * Display the avatar running to the right
	 */
	public void displayRunRight(){
		parent.fill(255);
		parent.strokeWeight(1);
		parent.pushMatrix();
		parent.translate(body.getTransX(),body.getTransY());
		parent.line(body.getTopX(),body.getTopY(),body.getBotX(),body.getBotY());
		parent.popMatrix();
		parent.pushMatrix();
		parent.translate(head.getTransX(),head.getTransY());
		parent.ellipse(head.getX(),head.getY(),head.getRadius(),head.getRadius());
		parent.popMatrix();
		parent.pushMatrix();
		parent.translate(leftArm.getTransX(),leftArm.getTransY());
		parent.line(leftArm.getBodyX(),leftArm.getBodyY(),leftArm.getBodyX()-8,leftArm.getBodyY());
		parent.line(leftArm.getBodyX(),leftArm.getBodyY()+4,leftArm.getBodyX()-8, leftArm.getBodyY()+4);
		parent.popMatrix();
		if (dir==true) {
			rotateCounter+=3;
			if (rotateCounter>=0) {
				dir=false;
			}
		}
		else if (dir==false) {
			rotateCounter-=3;
			if (rotateCounter<=-60) {
				dir=true;
			}
		}
		parent.pushMatrix();
		parent.translate(leftLeg.getTransX(), leftLeg.getTransY());
		parent.rotate(radians(rotateCounter));
		parent.line(leftLeg.getBodyX(),leftLeg.getBodyY(),leftLeg.getEndX(),leftLeg.getEndY());
		parent.popMatrix();
		parent.pushMatrix();
		parent.translate(rightLeg.getTransX(), rightLeg.getTransY());
		parent.rotate(radians(-rotateCounter));
		parent.line(rightLeg.getBodyX(),rightLeg.getBodyY(),rightLeg.getEndX(),rightLeg.getEndY());
		parent.popMatrix();
	}
	/**
	 * Display the avatar running to the left
	 */
	public void displayRunLeft() {
		parent.fill(255);
		parent.strokeWeight(1);
		parent.pushMatrix();
		parent.translate(body.getTransX(),body.getTransY());
		parent.line(body.getTopX(),body.getTopY(),body.getBotX(),body.getBotY());
		parent.popMatrix();
		parent.pushMatrix();
		parent.translate(head.getTransX(),head.getTransY());
		parent.ellipse(head.getX(),head.getY(),head.getRadius(),head.getRadius());
		parent.popMatrix();
		parent.pushMatrix();
		parent.translate(leftArm.getTransX(),leftArm.getTransY());
		parent.line(leftArm.getBodyX(),leftArm.getBodyY(),leftArm.getBodyX()+8,leftArm.getBodyY());
		parent.line(leftArm.getBodyX(),leftArm.getBodyY()+4,leftArm.getBodyX()+8, leftArm.getBodyY()+4);
		parent.popMatrix();
		if (dir==true) {
			rotateCounter+=3;
			if (rotateCounter>=0) {
				dir=false;
			}
		}
		else if (dir==false) {
			rotateCounter-=3;
			if (rotateCounter<=-60) {
				dir=true;
			}
		}
		parent.pushMatrix();
		parent.translate(leftLeg.getTransX(), leftLeg.getTransY());
		parent.rotate(radians(rotateCounter));
		parent.line(leftLeg.getBodyX(),leftLeg.getBodyY(),leftLeg.getEndX(),leftLeg.getEndY());
		parent.popMatrix();
		parent.pushMatrix();
		parent.translate(rightLeg.getTransX(), rightLeg.getTransY());
		parent.rotate(radians(-rotateCounter));
		parent.line(rightLeg.getBodyX(),rightLeg.getBodyY(),rightLeg.getEndX(),rightLeg.getEndY());
		parent.popMatrix();
	}
	/**
	 * Adjust x-velocitity as avatar slides to the right
	 */
	public void slideRight() {
		velX-=0.2f;
		if (velX<0) {
			velX=0;
			slide='n';
		}
	}
	/**
	 * Adjust x-velocity as avatar slides to the left
	 */
	public void slideLeft() {
		velX+=0.2f;
		if (velX>0) {
			velX=0;
			slide='n';
		}
	}
	/**
	 * Display the avatar sliding
	 */
	public void displaySlide() {
		parent.fill(255);
		parent.strokeWeight(1);
		parent.pushMatrix();
		parent.translate(body.getTransX(),body.getTransY());
		parent.line(body.getTopX(),body.getTopY(),body.getBotX(),body.getBotY());
		parent.popMatrix();
		parent.pushMatrix();
		parent.translate(head.getTransX(),head.getTransY());
		parent.ellipse(head.getX(),head.getY(),head.getRadius(),head.getRadius());
		parent.popMatrix();
		parent.pushMatrix();
		parent.translate(leftArm.getTransX(),leftArm.getTransY());
		parent.line(leftArm.getBodyX(),leftArm.getBodyY(),leftArm.getEndX(),leftArm.getEndY());
		parent.line(rightArm.getBodyX(),rightArm.getBodyY(),rightArm.getEndX(), rightArm.getEndY());
		parent.popMatrix();
		parent.pushMatrix();
		parent.translate(leftLeg.getTransX(), leftLeg.getTransY());
		parent.line(leftLeg.getBodyX(),leftLeg.getBodyY(),leftLeg.getEndX(),leftLeg.getEndY());
		parent.popMatrix();
		parent.pushMatrix();
		parent.translate(rightLeg.getTransX(), rightLeg.getTransY());
		parent.line(rightLeg.getBodyX(),rightLeg.getBodyY(),rightLeg.getEndX(),rightLeg.getEndY());
		parent.popMatrix();
	}
	/**
	 * Display the avatar jumping to the right
	 */
	public void displayRightJump() {
		parent.fill(255);
		parent.strokeWeight(1);
		parent.pushMatrix();
		parent.translate(body.getTransX(),body.getTransY());
		parent.line(body.getTopX(),body.getTopY(),body.getBotX(),body.getBotY());
		parent.popMatrix();
		parent.pushMatrix();
		parent.translate(head.getTransX(),head.getTransY());
		parent.ellipse(head.getX(),head.getY(),head.getRadius(),head.getRadius());
		parent.popMatrix();
		parent.pushMatrix();
		parent.translate(leftArm.getTransX(),leftArm.getTransY());
		parent.line(leftArm.getBodyX(),leftArm.getBodyY()+4,leftArm.getEndX(),leftArm.getEndY()+4);
		parent.line(leftArm.getBodyX(),leftArm.getBodyY(),leftArm.getEndX(), leftArm.getEndY());
		parent.popMatrix();
		parent.pushMatrix();
		parent.translate(leftLeg.getTransX(), leftLeg.getTransY());
		parent.line(leftLeg.getBodyX(),leftLeg.getBodyY(),leftLeg.getEndX(),leftLeg.getEndY());
		parent.popMatrix();
		parent.pushMatrix();
		parent.translate(rightLeg.getTransX(), rightLeg.getTransY());
		parent.line(rightLeg.getBodyX(),rightLeg.getBodyY(),rightLeg.getBodyX()+7.5f,rightLeg.getBodyY()-3);
		parent.line(rightLeg.getBodyX()+7.5f,rightLeg.getBodyY()-3,rightLeg.getBodyX()+7.5f,rightLeg.getBodyY()+5);
		parent.popMatrix();
	}
	/**
	 * Display the avatar jumping to the left
	 */
	public void displayLeftJump() {
		parent.fill(255);
		parent.strokeWeight(1);
		parent.pushMatrix();
		parent.translate(body.getTransX(),body.getTransY());
		parent.line(body.getTopX(),body.getTopY(),body.getBotX(),body.getBotY());
		parent.popMatrix();
		parent.pushMatrix();
		parent.translate(head.getTransX(),head.getTransY());
		parent.ellipse(head.getX(),head.getY(),head.getRadius(),head.getRadius());
		parent.popMatrix();
		parent.pushMatrix();
		parent.translate(leftArm.getTransX(),leftArm.getTransY());
		parent.line(rightArm.getBodyX(),rightArm.getBodyY()+4,rightArm.getEndX(),rightArm.getEndY()+4);
		parent.line(rightArm.getBodyX(),rightArm.getBodyY(),rightArm.getEndX(), rightArm.getEndY());
		parent.popMatrix();
		parent.pushMatrix();
		parent.translate(rightLeg.getTransX(), rightLeg.getTransY());
		parent.line(rightLeg.getBodyX(),rightLeg.getBodyY(),rightLeg.getEndX(),rightLeg.getEndY());
		parent.popMatrix();
		parent.pushMatrix();
		parent.translate(leftLeg.getTransX(), leftLeg.getTransY());
		parent.line(leftLeg.getBodyX(),leftLeg.getBodyY(),leftLeg.getBodyX()-7.5f,leftLeg.getBodyY()-3);
		parent.line(leftLeg.getBodyX()-7.5f,leftLeg.getBodyY()-3,leftLeg.getBodyX()-7.5f,leftLeg.getBodyY()+5);
		parent.popMatrix();
	}
	/**
	 * Display the avatar jumping neutrally
	 */
	public void displayNeutralJump() {
		parent.fill(255);
		parent.strokeWeight(1);
		parent.pushMatrix();
		parent.translate(body.getTransX(),body.getTransY());
		parent.line(body.getTopX(),body.getTopY(),body.getBotX(),body.getBotY());
		parent.popMatrix();
		parent.pushMatrix();
		parent.translate(head.getTransX(),head.getTransY());
		parent.ellipse(head.getX(),head.getY(),head.getRadius(),head.getRadius());
		parent.popMatrix();
		parent.pushMatrix();
		parent.translate(leftArm.getTransX(),leftArm.getTransY());
		parent.line(leftArm.getBodyX(),leftArm.getBodyY(),leftArm.getEndX(),leftArm.getEndY());
		parent.line(rightArm.getBodyX(),rightArm.getBodyY(),rightArm.getEndX(), rightArm.getEndY());
		parent.popMatrix();
		parent.pushMatrix();
		parent.translate(rightLeg.getTransX(), rightLeg.getTransY());
		parent.line(rightLeg.getBodyX(),rightLeg.getBodyY(),rightLeg.getBodyX()-7.5f,rightLeg.getBodyY()-3);
		parent.line(rightLeg.getBodyX()-7.5f,rightLeg.getBodyY()-3,rightLeg.getBodyX()-7.5f,rightLeg.getBodyY()+5);
		parent.popMatrix();
		parent.pushMatrix();
		parent.translate(leftLeg.getTransX(), leftLeg.getTransY());
		parent.line(leftLeg.getBodyX(),leftLeg.getBodyY(),leftLeg.getBodyX()+7.5f,leftLeg.getBodyY()-3);
		parent.line(leftLeg.getBodyX()+7.5f,leftLeg.getBodyY()-3,leftLeg.getBodyX()+7.5f,leftLeg.getBodyY()+5);
		parent.popMatrix();
	}
	/**
	 * Get the x velocity of the avatar
	 * @return the x velocity of the avatar
	 */
	public float getVelX() {
		return velX;
	}
	/**
	 * Get whether or not the avatar is jumping
	 * @return whether or not the avatar is jumping
	 */
	public boolean getJump() {
		return jump;
	}
	/**
	 * Figure out what keys are being pressed to get the avatar to run
	 * @param key the current key being pressed/released
	 * @param bool whether the key is being pressed or released
	 */
	public void setRun(int key,boolean bool) {
		if (key=='d') {
			runRight=bool;
		}
		else if (key=='a') {
			runLeft=bool;
		}
		if (key==LEFT) {
			runLeft=bool;
		}
		if (key==RIGHT) {
			runRight=bool;
		}
	}
	/**
	 * Start the jump of the avatar
	 * @param velY the starting jump velocity of the avatar
	 */
	public void startJump(float velY) {
		jump=true;
		this.velY=velY;
		if (velX>0) {
			jumpDirection='r';
		}
		else if (velX<0) {
			jumpDirection='l';
		}
		else if (velX==0) {
			jumpDirection='n';
		}
	}
	/**
	 * Set whether or not the avatar is jumping
	 * @param bool whether or not the avatar is jumping 
	 */
	public void setJump(boolean bool) {
		jump=bool;
	}
	/**
	 * Set wich direction the avatar is sliding
	 * @param x which direction the avatar is sliding
	 */
	public void setSlide(char x) {
		slide=x;
	}
	/**
	 * Get which direction the avatar is sliding
	 * @return which direction the avatar is sliding
	 */
	public char getSlide() {
		return slide;
	}
	/**
	 * Move the avatar's position based on his current velocities
	 */
	public void move() {
		head.transX(velX);
		body.transX(velX);
		leftArm.transX(velX);
		rightArm.transX(velX);
		leftLeg.transX(velX);
		rightLeg.transX(velX);
		head.transY(velY);
		body.transY(velY);
		leftArm.transY(velY);
		rightArm.transY(velY);
		leftLeg.transY(velY);
		rightLeg.transY(velY);
	}
	/**
	 * Determine what type of running animation to show based on the avatar's current action
	 */
	public void run() {
		if ((runRight==true && runLeft==true)||(runRight==false && runLeft==false)) {
			displaySlide();
			if (velX>0) {
				slideRight();
			}
			else if (velX<0) {
				slideLeft();
			}
			else if (velX==0) {
				displayStationary();
			}
		}
		else if (runRight==true) {
			displayRunRight();
			if (velX<=0) {
				velX+=0.4f;
			}
			else {
				velX+=0.1f;
			}
		}
		else if (runLeft==true) {
			displayRunLeft();
			if (velX>0) {
				velX-=0.4f;
			}
			else {
				velX-=0.1f;
			}
		}
	}
	/**
	 * Display jump animation based on avatar's direction and decrease y-velocity to bring the avatar back down to a platform
	 */
	public void gravity() {
		velY+=0.5;
		if (runRight==true) {
			displayRightJump();
		}
		else if (runLeft==true) {
			displayLeftJump();
		}
		else if (runLeft==false && runRight==false) {
			displayNeutralJump();
		}
		if (runLeft==true) {
			velX-=0.1f;
		}
		else if (runRight==true) {
			velX+=0.1f;
		}
	}
	/**
	 * Check to see whether the avatar is interacting with a platform
	 * @param platform one of the level's platforms
	 */
	public void checkPlatform(Platform platform) {
		if (velY>=0 && rightLeg.getMapEndY()>=platform.getY() && body.getMapTopY()<=platform.getY() && rightLeg.getMapEndX()>=platform.getX1() && leftLeg.getMapEndX()<=platform.getX2() && stopPlatformRotation==false) {
			velY=0;
			rightLeg.setTransY(platform.getY()-rightLeg.getEndY());
			head.setTransY(rightLeg.getTransY()-20);
			body.setTransY(rightLeg.getTransY()-20);
			rightArm.setTransY(rightLeg.getTransY()-20);
			leftArm.setTransY(rightLeg.getTransY()-20);
			leftLeg.setTransY(rightLeg.getTransY());
			stopPlatformRotation=true;
			onPlatform=true;
			jump=false;
		}
	}
	/**
	 * Set whether or not the avatar is on a platform
	 * @param bool whether or not the avatar is on a platform
	 */
	public void setOnPlatform(boolean bool) {
		onPlatform=bool;
	}
	/**
	 * Check to see whether or not the avatar is touching a wall
	 * @param wall one of the level's walls
	 */
	public void checkWalls(Wall wall) {
		if (stopWallRotation==false && wall.getX()<=rightLeg.getMapEndX() && wall.getX()>=leftLeg.getMapEndX() && ((wall.getY1()<=body.getMapBotY() && wall.getY1()>=head.getMapY()) || (wall.getY2()>=body.getMapTopY() && wall.getY1()<=head.getMapY()))) {
			if (velX>0) {
				wallCollision='l';
			}
			else if (velX<0) {
				wallCollision='r';
			}
			velX=0;
			stopWallRotation=true;
			collisionWall=wall;
		}
	}
	/**
	 * Get whether or not to keep cycling through walls to see if they are interacting with the avatar
	 * @return whether or not to keep cycling through walls to see if they are interacting with the avatar
	 */
	public boolean getStopWallRotation() {
		return stopWallRotation;
	}
	
	/**
	 * Set whether or not to keep cycling through walls to see if they are interacting with the avatar
	 * @param bool whether or not to keep cycling through walls to see if they are interacting with the avatar
	 */
	public void setStopWallRotation(boolean bool) {
		stopWallRotation=bool;
	}
	/**
	 * Set the direction the avatar has collided with a wall
	 */
	public void setWallCollision(char ch) {
		wallCollision=ch;
	}
	/**
	 * Display the wall climb animation based on the direction of the avatar's collision with a wall
	 */
	public void wallClimb() {
		parent.strokeWeight(1);
		if (wallCollision=='l') {
			displayLeftWallClimb(collisionWall);
		}
		else if (wallCollision=='r') {
			displayRightWallClimb(collisionWall);
		}
		
	}
	/**
	 * Display the wall climb animation upon colliding with the right side of a wall
	 * @param wall the wall the avatar has collided with
	 */
	public void displayRightWallClimb (Wall wall) {
		parent.fill(255);
		leftArm.setTransX(wall.getX()-leftArm.getEndX()+0.5f);
		head.setTransX(leftArm.getTransX());
		leftLeg.setTransX(leftArm.getTransX());
		rightLeg.setTransX(leftArm.getTransX());
		rightArm.setTransX(leftArm.getTransX());
		body.setTransX(leftArm.getTransX());
		leftArm.setTransX(leftArm.getTransX());
		parent.pushMatrix();
		parent.translate(body.getTransX(),body.getTransY());
		parent.line(body.getTopX(),body.getTopY(),body.getBotX(),body.getBotY());
		parent.popMatrix();
		parent.pushMatrix();
		parent.translate(head.getTransX(),head.getTransY());
		parent.ellipse(head.getX(),head.getY(),head.getRadius(),head.getRadius());
		parent.popMatrix();
		parent.pushMatrix();
		parent.translate(leftArm.getTransX(),leftArm.getTransY());
		parent.line(rightArm.getBodyX(),rightArm.getBodyY(),rightArm.getEndX(), rightArm.getEndY());
		parent.line(leftArm.getBodyX(), leftArm.getBodyY(), leftArm.getEndX(),leftArm.getEndY());
		parent.popMatrix();
		parent.pushMatrix();
		parent.translate(leftLeg.getTransX(), leftLeg.getTransY());
		parent.line(leftLeg.getBodyX(), leftLeg.getBodyY(),leftLeg.getEndX(), leftLeg.getEndY()-10);
		parent.line(leftLeg.getBodyX(),leftLeg.getBodyY(),leftLeg.getEndX(),leftLeg.getEndY());
		parent.popMatrix();
	}
	/**
	 * Display the wall climb animation upon colliding with the left side of a wall
	 * @param wall the wall the avatar has collided with
	 */
	public void displayLeftWallClimb(Wall wall) {
		parent.fill(255);
		rightArm.setTransX(wall.getX()-rightArm.getEndX()-0.5f);
		head.setTransX(rightArm.getTransX());
		leftLeg.setTransX(rightArm.getTransX());
		rightLeg.setTransX(rightArm.getTransX());
		body.setTransX(rightArm.getTransX());
		leftArm.setTransX(rightArm.getTransX());
		parent.pushMatrix();
		parent.translate(body.getTransX(),body.getTransY());
		parent.line(body.getTopX(),body.getTopY(),body.getBotX(),body.getBotY());
		parent.popMatrix();
		parent.pushMatrix();
		parent.translate(head.getTransX(),head.getTransY());
		parent.ellipse(head.getX(),head.getY(),head.getRadius(),head.getRadius());
		parent.popMatrix();
		parent.pushMatrix();
		parent.translate(leftArm.getTransX(),leftArm.getTransY());
		parent.line(rightArm.getBodyX(),rightArm.getBodyY(),rightArm.getEndX(), rightArm.getEndY());
		parent.line(leftArm.getBodyX(), leftArm.getBodyY(), leftArm.getEndX(),leftArm.getEndY());
		parent.popMatrix();
		parent.pushMatrix();
		parent.translate(rightLeg.getTransX(), rightLeg.getTransY());
		parent.line(rightLeg.getBodyX(), rightLeg.getBodyY(),rightLeg.getEndX(), rightLeg.getEndY()-10);
		parent.line(rightLeg.getBodyX(),rightLeg.getBodyY(),rightLeg.getEndX(),rightLeg.getEndY());
		parent.popMatrix();
		
	}
	/**
	 * Get what side of the wall the avatar has collided with
	 * @return the side of the wall the avatar has collided with
	 */
	public char getWallCollision() {
		return wallCollision;
	}
	/**
	 * Adjust velocities when jumping off a wall
	 */
	public void wallJump() {
		if (wallCollision=='r') {
			wallCollision='n';
			leftArm.setTransX(collisionWall.getX()+1+7);
			velX=5f;
			velY=-10;
			jump=true;
			move();
		}
		else if (wallCollision=='l') {
			wallCollision='n';
			rightArm.setTransX(collisionWall.getX()-1-7);
			velX=-5f;
			velY=-10;
			jump=true;
			move();
		}
	}
	/**
	 * Get whether or not the avatar is on a platform
	 * @return whether or not the avatar is on a platform
	 */
	public boolean getOnPlatform() {
		return onPlatform;
	}
	/**
	 * Set whether or not to keep cycling through platforms to determine if a collision has taken place
	 * @param bool whether or not to keep cycling through platforms to determine if a collision has taken place
	 */
	public void setStopPlatformRotation(boolean bool){
		stopPlatformRotation=bool;
	}
	/**
	 * Get whether or not to keep cycling through platforms to determine if a collision has taken place
	 * @return whether or not to keep cycling through platforms to determine if a collision has taken place
	 */
	public boolean getStopPlatformRotation() {
		return stopPlatformRotation;
	}
	/**
	 * Get the y-velocity of the avatar
	 * @return y velocity
	 */
	public float getVelY() {
		// TODO Auto-generated method stub
		return velY;
	}
	/**
	 * Get the lowest y-coordinate of the avatar's right leg (including translations)
	 * @return the lowest y-coordinate of the avatar's right leg (including translations)
	 */
	public float getRightLegMapEndY() {
		return rightLeg.getMapEndY();
	}
	/**
	 * Get the rightermost x-coordinate of the avatar's right leg (including translations)
	 * @return Get the rightermost x-coordinate of the avatar's right leg (including translations
	 */
	public float getRightLegMapEndX() {
		return rightLeg.getMapEndX();
	}
	/**
	 * Release yourself from a wall and adjust x-velocities accordingly
	 * @param c the key pressed to release yourself from the wall
	 */
	public void wallRelease(int c) {
		if (c=='d') {
			velX+=2f;
			move();
		}
		else if (c=='a') {
			velX-=2f;
			move();
		}
		if (c==RIGHT) {
			velX+=2f;
			move();
		}
		else if (c==LEFT) {
			velX-=2f;
			move();
		}
		
	}
	/**
	 * Slide down a wall and adjust y-velocities accordingly
	 * @param c the key pressed to slide down the wall
	 */
	public void wallSlide(int c) {
		if (slideDown==true){
			velY=2f;
		}
		else {
			velY=1f;
		}
	}
	/**
	 * Set whether or not the avatar is sliding down a wall
	 * @param bool whether or not the avatar is sliding down a wall
	 */
	public void setSlideDown(boolean bool) {
		slideDown=bool;
	}
	/**
	 * Get the body's top y-coordinate (including translations)
	 * @return the body's top y-coordinate (including translations)
	 */
	public float getMapBodyTopY(){
		return body.getMapTopY();
	}
	/**
	 * get the right leg's top y-coordinate (including translations)
	 * @return the right leg's top y-coordinate (including translations)
	 */
	public float getMapRightLegBodyY() {
		return rightLeg.getMapBodyY();
	}
	public void displayHitBox(int num) {
		hitX1=body.getMapBotX()-10;
		hitX2=body.getMapBotX()+10;
		hitY1=head.getMapY()-6.5f;
		hitY2=rightLeg.getMapEndY()+0.5f;
		if (num==1) {
			parent.stroke(239, 35, 35);
		}
		else if (num==2) {
			parent.stroke(35, 175, 51);
		}
		parent.noFill();
		parent.rect(hitX1,hitY1,hitX2-hitX1,hitY2-hitY1);
		parent.stroke(0);
	}
	public float getHitX1() {
		return hitX1;
	}
	public void setHitX1(float hitX1) {
		this.hitX1 = hitX1;
	}
	public float getHitX2() {
		return hitX2;
	}
	public void setHitX2(float hitX2) {
		this.hitX2 = hitX2;
	}
	public float getHitY1() {
		return hitY1;
	}
	public void setHitY1(float hitY1) {
		this.hitY1 = hitY1;
	}
	public float getHitY2() {
		return hitY2;
	}
	public void setHitY2(float hitY2) {
		this.hitY2 = hitY2;
	}
}
