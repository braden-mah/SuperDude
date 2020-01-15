/*
 * SUPERDUDE - a game by Braden Mah :)
 * 
 * This is a physics-based running game where users are allowed to design
 * their own levels and then play them with other people.  
 * 
 * The goal of the game is fairly simple: run from the green flag to the orange flag
 * as quickly as possible to set a new high score.
 * 
 * What makes this game unique is that players are allowed to make their own levels.
 * This gives the game a creative aspect and makes it more dynamic.
 *   
 * I used outside sources to figure out how to use translate and rotate in order to get a swift running animation down.
 * 	- https://www.youtube.com/watch?v=o9sgjuh-CBM
 * 	- https://processing.org/reference/rotate_.html
 */



import processing.core.PApplet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;


public class Main extends PApplet{
	
	/*THE OBJECTS
	 * hero - this is your stickman avatar
	 * buildTab - when in "create" mode, this is the tab at the left that allows you to pull out walls and floors
	 * theCredits - this shows the credits for the game
	 * home - this is the home button at the top right corner
	 * retry - retry button at top right corner
	 * walls - when playing a level, all of the walls come from this arrayList
	 * platforms - when playing a level, all of the platforms come from this arrayList
	 * saveData - used to store objects for serialization
	 * loadData - used to load objects from serialized files
	 * instructionsArray - stores the instructions for the game which are displayed when you click on help
	 * record - placeholder object which is serialized when you finish creating a level
	 * gameRecord - object which holds the records for the different levels
	 * createWall - wall which can be manipulated and dragged around in creative mode
	 * greenFlag - starting flag which can be manipulated and dragged around in creative mode
	 * orangeFlag -end flag which can be manipulated and dragged around in creative mode
	 * startFlag - starting flag which is displayed when playing a level
	 * endFlag - ending flag which is displayed when playing a level
	 * iStartFlag - start flag for the help mode
	 * iEndFlag - end flag for the help mode
	 * save - save button for the game
	 * level - loading screen for the game where you are asked to enter the name of the level you wish to play
	 * createButton - when saving a new level, this button can be used to go back to "build mode"
	 * clock - timer for speed running
	 * creativeWalls - when creating a level, all of the walls which are placed down are stored here
	 * creativePlatforms - when creating a level, all of the platforms which are placed down are stored here
	 * iWalls - walls which are shown when you select help
	 * iPlatforms - platforms that are shown when you select help
	 */
	int counter=0;
	int counter2=3;
	int winner;
	Hero hero;
	Hero hero2;
	Mainscreen mainscreen;
	BuildTab buildTab = new BuildTab(this);
	Credits theCredits = new Credits(this);
	Home home = new Home(this);
	Retry retry = new Retry(this);
	ArrayList<Wall> walls = new ArrayList<Wall>();
	ArrayList<Platform> platforms = new ArrayList<Platform>();
	ArrayList<Object> saveData = new ArrayList<Object>();
	ArrayList<Object> loadData = new ArrayList<Object>();
	ArrayList<Instructions> instructionsArray = new ArrayList<Instructions>();
	Records record = new Records(this);
	Records gameRecord = new Records(this);
	Wall createWall = new Wall(0,0,0, this);
	GreenFlag greenFlag = new GreenFlag(this);
	OrangeFlag orangeFlag = new OrangeFlag(this);
	GreenFlag startFlag = new GreenFlag(this);
	OrangeFlag endFlag = new OrangeFlag(this);
	GreenFlag iStartFlag = new GreenFlag(this);
	OrangeFlag iEndFlag = new OrangeFlag(this);
	Save save = new Save(this);
	Levels levelMenu = new Levels(this);
	Create createButton = new Create(this);
	Clock clock = new Clock(this);
	Brawl brawl = new Brawl(this);
	ArrayList<Wall> creativeWalls = new ArrayList<Wall>();
	Platform createPlatform = new Platform (0,0,0,this);
	ArrayList<Platform> creativePlatforms = new ArrayList<Platform>();
	ArrayList<Wall> iWalls = new ArrayList<Wall>();
	ArrayList<Platform> iPlatforms = new ArrayList<Platform>();
	Formatter x;
	Scanner sc;
	
	/* THE VARIABLES
	 * startJump - determine whether or not your avatar has jumped
	 * frameRate - the frameRate of the game (60fps)
	 * fileName - the name of the file you want to save/load
	 * main - determine whether or not you are on the home screen
	 * recordScreen - determine whether or not you are on the record screen
	 * recordScreen2 - determine whether or not you are looking at the high scores
	 * game - determine whether or not you are playing a level
	 * loadLevels - determine whether or not you are still on the level loading screen
	 * create - determine whether or not you are creating a level
	 * saveStage - determine whether or not you are on the saving screen for a level you created
	 * instructions - determine whether or not you are playing the help level
	 * credits - determine whether or not you are looking at the credits
	 * overwritten - determines whether or not the new high score has been saved yet
	 */
	boolean stopWriting=false;
	boolean startJump;
	int frameRate=60;
	int level=-1;
	int scoreNum;
	int roundNum=1;
	float brawlTime;
	String fileName;
	String gameMode="Solo";
	boolean main=true;
	boolean recordScreen=false;
	boolean recordScreen2=false;
	boolean game=false;
	boolean loadLevels=false;
	boolean create=false;
	boolean saveStage=false;
	boolean instructions=false;
	boolean credits=false;
	boolean pause=false;
	int platformCounter=0;
	int wallCounter=0;
	boolean overwritten=false;
	String[] wallFile = new String[3];
	String[] platFile = new String[3];
	String[] startFlagFile = new String[2];
	String[] endFlagFile = new String[2];
	String[] stringRecordsFile = new String[5];
	String[] nameRecordsFile = new String[5];
	float[] floatRecordsFile = new float[5];
	ArrayList<String> oldFile = new ArrayList<String>();
	String line;
	
	public static void main (String[] args) {
		PApplet.main("Main");
	}

	public void settings() {
		size(1100,600);
	}
	
	/* SETUP
	 * Initializing arrayLists and objects
	 */

	public void setup() {
		smooth();
		hero=new Hero(this);
		hero2=new Hero(this);
		mainscreen=new Mainscreen(this);
		//walls.add(new Wall(300,100,400,this));
		//walls.add(new Wall(200,0,200,this));
		iWalls.add(new Wall(200f,300f,500f,this));
		iWalls.add(new Wall(130f,200f, 400f, this));
		iWalls.add(new Wall(400,150,450,this));
		iWalls.add(new Wall(470,70,380,this));
		iWalls.add(new Wall(550,310,450,this));
		iPlatforms.add(new Platform(500,50,200,this));
		iPlatforms.add(new Platform (300,200,400,this));
		iPlatforms.add(new Platform (225,300,400,this));
		iPlatforms.add(new Platform (150,300,400,this));
		iPlatforms.add(new Platform (450,400,550,this));
		iPlatforms.add(new Platform (380,470,550,this));
		iPlatforms.add(new Platform (310,470,650,this));
		iPlatforms.add(new Platform (500,800,1000,this));
		instructionsArray.add(new Instructions(226,323,219,325,375,500,"\nUse the wasd keys to move around.\n\nPress 'w' to jump up, onto and off of walls.", this));
		instructionsArray.add(new Instructions(488,72,485,50,640,289,"Press 's' while on a wall to fall down quicker.\n\nPress 'a' or 'd' to release yourself from a wall without jumping.",this));
		instructionsArray.add(new Instructions(709,529,706,518,1070,585,"Get to the orange flag as quickly as possible to complete the level and set a new High Score!",this));
		instructionsArray.add(new Instructions(387,475,400,470,550,585,"The bottom of platforms are transparent, meaning you can jump onto the wall above you.",this));
		//walls.add(new Wall(700,100,258,this));
		//platforms.add(new Platform(258,0,300,this)); 
		//platforms.add(new Platform(258,400,700,this));
		//platforms.add(new Platform(500,0,1200,this));
		//platforms.add(new Platform(100,300,600,this));
		//platforms.add(new Platform(400,300,600,this));
		iStartFlag.setFlag(80, 465);
		iEndFlag.setFlag(950, 465);
		frameRate(frameRate);
		textAlign(CENTER);
	}
	public void draw() {
		
		/* MAINSCREEN
		 * this shows the home screen for the game
		 */
		
		if (main==true) {
			background(0);
			mainscreen.display();
			
		}
		/* LOADING SCREEN
		 * this is the loading screen for the game, where you enter the name of the level you want to play
		 */
		
		else if (loadLevels==true) {
			levelMenu.display();
			home.display();
		}
		
		/* GAME
		 * This is the actual level that you play, with all of the functionality to allow your avatar to move around the map
		 */
		else if (game==true){
			if (gameMode.equals("Solo")) {
				System.out.println("Mouse X: "+mouseX+", Mouse Y: "+mouseY);
				clock.countTime();
				if (recordScreen==false) {
					gameRecord.setNameEntered(false);
				}
				background(127);
				startFlag.display();
				endFlag.display();
				home.display();
				retry.display();
				fill(255);
				
				//The following lines of code display the walls and platforms, and check to see if you have collided with any of them
				
				for (Wall w: walls) {
					w.displayWall();
				}
				for (Platform p: platforms) {
					p.displayPlatform();
				}
				clock.display();
				for (Platform p: platforms) {
					hero.checkPlatform(p);
				}
				if (hero.getStopPlatformRotation()==false) {
					hero.setOnPlatform(false);
				}
				for (Wall w: walls){
					hero.checkWalls(w);
				}
				if (hero.getStopWallRotation()==false) {
					hero.setWallCollision('n');
				}
				
				/* IN GAME ACTIONS
				 * hero.run(): get avatar to run when on platform
				 * hero.gravity(): avatar pulled downwards when in midair
				 * hero.wallClimb(): get avatar to cling to a wall
				 * hero.wallSlide(): get avatar to slide down a wall
				 * hero.move(): changes the position of the avatar based on current velocities
				 * endFlag.check(): check to see whether you are touching the end flag
				 */
				
				if (hero.getWallCollision()=='n' && hero.getOnPlatform()==true) {
					hero.run();
				}
				else if (hero.getOnPlatform()==false && hero.getWallCollision()=='n') {
					hero.gravity();
				}
				else if (hero.getWallCollision()!='n'){
					hero.wallClimb();
					hero.wallSlide(key);
				}
				hero.move();			
				hero.setStopWallRotation(false);
				hero.setStopPlatformRotation(false);
				if (recordScreen==false) {
					endFlag.check(hero.getMapBodyX(),hero.getMapBodyBotY(),hero.getMapBodyTopY());
				}
				
				
				/* RECORD SCREEN
				 * gameRecord.checkScore(): check to see whether you made the top five
				 * if (recordScreen==false): didn't make it, back to home screen
				 * if (recordScreen==true): display the record screen
				 * if (recordScreen2==true): display the current highscores
				 */
				
				
				if (endFlag.getComplete()==true ) {
					endFlag.setComplete(false);
					hero.resetVel();
					try {
						Scanner sc = new Scanner(new File(fileName));
						while (sc.hasNext()) {
							line=sc.next();
							if (line.equals("Records")){
								while (!line.equals("Names")) {
									line=sc.next();
									if (!line.equals("Names")) {
										stringRecordsFile=line.split(",");
										for (int i=0; i<stringRecordsFile.length; i++) {
											floatRecordsFile[i]=Float.parseFloat(stringRecordsFile[i]);
										}
									}
								}
							}
							if (line.equals("Names")) {
								line=sc.next();
								nameRecordsFile=line.split(",");
								for (int i=0; i<nameRecordsFile.length; i++) {
									System.out.println(nameRecordsFile[i]);
								}
							}
							
						}
						sc.close();
					}
					catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					for (int i=0; i<floatRecordsFile.length; i++) {
						if (recordScreen==false) {
							if (clock.getSeconds()+clock.getMilliseconds()/1000<floatRecordsFile[i]) {
								scoreNum=i;
								for (int j=floatRecordsFile.length-1; j>scoreNum; j--) {
									floatRecordsFile[j]=floatRecordsFile[j-1];
								}
								floatRecordsFile[scoreNum]=clock.getSeconds()+clock.getMilliseconds()/1000;
								recordScreen=true;
								for (int j=nameRecordsFile.length-1; j>scoreNum; j--) {
									nameRecordsFile[j]=nameRecordsFile[j-1];
								}
								nameRecordsFile[scoreNum]="    ";
							}
							else {
								recordScreen=false;
							}
						}
					}
					
					if (recordScreen==false) {
						game=false;
						main=true;
					}
				}
				if (recordScreen==true) {
					gameRecord.recordScreen(scoreNum);
					home.display();
					gameRecord.displayButton();
				}
				if (recordScreen2==true) {
					gameRecord.recordScreen2(floatRecordsFile,nameRecordsFile);
					gameRecord.displayHide();
				}
			}
			else if (gameMode.equals("Brawl")) {
				if (pause!=true) {
					background(127);
					clock.countTime();
					clock.display();
					startFlag.display();
					endFlag.display();
					home.display();
					retry.display();
					fill(255);
					
					//The following lines of code display the walls and platforms, and check to see if you have collided with any of them
					
					for (Wall w: walls) {
						w.displayWall();
					}
					for (Platform p: platforms) {
						p.displayPlatform();
					}
					for (Platform p: platforms) {
						hero.checkPlatform(p);
					}
					if (hero.getStopPlatformRotation()==false) {
						hero.setOnPlatform(false);
					}
					for (Wall w: walls){
						hero.checkWalls(w);
					}
					if (hero.getStopWallRotation()==false) {
						hero.setWallCollision('n');
					}
					
					/* IN GAME ACTIONS
					 * hero.run(): get avatar to run when on platform
					 * hero.gravity(): avatar pulled downwards when in midair
					 * hero.wallClimb(): get avatar to cling to a wall
					 * hero.wallSlide(): get avatar to slide down a wall
					 * hero.move(): changes the position of the avatar based on current velocities
					 * endFlag.check(): check to see whether you are touching the end flag
					 */
					
					if (hero.getWallCollision()=='n' && hero.getOnPlatform()==true) {
						hero.run();
					}
					else if (hero.getOnPlatform()==false && hero.getWallCollision()=='n') {
						hero.gravity();
					}
					else if (hero.getWallCollision()!='n'){
						hero.wallClimb();
						hero.wallSlide(key);
					}
					hero.move();			
					hero.setStopWallRotation(false);
					hero.setStopPlatformRotation(false);
					if (roundNum%2==0) {
						textAlign(LEFT);
						textSize(12);
						fill(255);
						text("Time to beat: "+brawlTime,50,70);
						textAlign(CENTER);
						hero.displayHitBox(1);
						hero2.displayHitBox(2);
					}
					else {
						hero.displayHitBox(2);
						hero2.displayHitBox(1);
					}
					for (Platform p: platforms) {
						hero2.checkPlatform(p);
					}
					if (hero2.getStopPlatformRotation()==false) {
						hero2.setOnPlatform(false);
					}
					for (Wall w: walls){
						hero2.checkWalls(w);
					}
					if (hero2.getStopWallRotation()==false) {
						hero2.setWallCollision('n');
					}
					
					/* IN GAME ACTIONS
					 * hero2.run(): get avatar to run when on platform
					 * hero2.gravity(): avatar pulled downwards when in midair
					 * hero2.wallClimb(): get avatar to cling to a wall
					 * hero2.wallSlide(): get avatar to slide down a wall
					 * hero2.move(): changes the position of the avatar based on current velocities
					 * endFlag.check(): check to see whether you are touching the end flag
					 */
					
					if (hero2.getWallCollision()=='n' && hero2.getOnPlatform()==true) {
						hero2.run();
					}
					else if (hero2.getOnPlatform()==false && hero2.getWallCollision()=='n') {
						hero2.gravity();
					}
					else if (hero2.getWallCollision()!='n'){
						hero2.wallClimb();
						hero2.wallSlide(key);
					}
					hero2.move();			
					hero2.setStopWallRotation(false);
					hero2.setStopPlatformRotation(false);
					if (roundNum%2==0) {
						hero2.displayHitBox(2);
					}
					else {
						hero2.displayHitBox(1);
					}
					checkHitBoxes();
				}
				else if (pause==true) {
					if (roundNum%2==0) {
						if (counter<=180) {
							noStroke();
							counter+=1;
							textSize(20);
							if (counter%60==0) {
								counter2-=1;
							}
							fill(204, 181, 252);
							stroke(0);
							rect(432,26,230,30);
							fill(0);
							text("Round 2 starting in "+counter2,550,50);
							textAlign(LEFT);
							textSize(12);
							fill(255);
							text("Time to beat: "+brawlTime,50,70);
							textAlign(CENTER);
						}
						else if (counter>180) {
							hero.startPosition(startFlag.getFlagPoleX(),startFlag.getFlagPoleY2());
							hero.resetVel();
							hero2.startPosition(endFlag.getFlagPoleX(), endFlag.getFlagPoleY2());
							hero2.resetVel();
							pause=false;
							counter=0;
						}
					}
					else {
						if (counter<=360) {
							counter+=1;
							textSize(20);
							fill(204, 181, 252);
							stroke(0);
							rect(432,26,230,30);
							if (counter<=180) {
								fill(0);
								text("Player "+winner+" wins!",550, 50);
							}
							else if (counter>180) {
								if (counter%60==0) {
									counter2-=1;
								}
								fill(0);
								text("New match in "+counter2,550,50);
							}
						}
						else {
							hero.startPosition(startFlag.getFlagPoleX(),startFlag.getFlagPoleY2());
							hero.resetVel();
							hero2.startPosition(endFlag.getFlagPoleX(), endFlag.getFlagPoleY2());
							hero2.resetVel();
							pause=false;
							counter=0;
						}
					}
				}
			}
			
		}
		
		/* CREATIVE MODE
		 * This is the mode that allows you to create your own level
		 */
		
		else if (create==true && saveStage==false) {
			
			background(127);
			home.display();
			save.displayButton();
			brawl.display();
			
			//The following lines of code displays the walls and platforms that you have planted
			
			for (Wall w:creativeWalls) {
				w.displayWall();
				w.displayJoints();
				w.shift();
			}
			for (Platform p: creativePlatforms) {
				p.displayPlatform();
				p.displayJoints();
				p.shift();
			}
			
			buildTab.moveBuildTab();
			buildTab.display();
			buildTab.checkBuildTabStatus();
			
			/* DRAG AND DROP
			 * buildTab.getChosenItem().equals(): drag wall/platform/flag out of buildTab and around map					  
			 */
			if (buildTab.getChosenItem().equals("wall")) {
				createWall.followMouse();
				createWall.displayWall();
			}
			else if (buildTab.getChosenItem().equals("platform")) {
				createPlatform.followMouse();
				createPlatform.displayPlatform();
			}
			else if (buildTab.getChosenItem().equals("startFlag")) {
				greenFlag.followMouse();
			}
			else if (buildTab.getChosenItem().equals("endFlag")) {
				orangeFlag.followMouse();
			}
			greenFlag.display();
			orangeFlag.display();
		}
		
		/* SAVE SCREEN
		 * This is the save screen of creative mode, that allows you to save your level
		 */
		
		else if (create==true && saveStage==true) {
			save.display();
			createButton.display();
			if (greenFlag.getOnField()==false || orangeFlag.getOnField()==false) {
				save.displaySaveReqs();
			}
			else if (save.getFileSaved()==true) {
				save.displaySaved();
			}
		}
		
		/* INSTRUCTIONS SCREEN
		 * This is the level that is displayed when you click on help
		 */
		
		else if (instructions==true) {
			
			clock.countTime();
			background(127);
			iStartFlag.display();
			iEndFlag.display();
			home.display();
			retry.display();
			fill(255);
			
			//This code is very similar to the actual game code, as the help mode is just another level with tips displayed throughout it. 
			
			for (Instructions i: instructionsArray) {
				i.display();
			}
			for (Wall w: iWalls) {
				w.displayWall();
			}
			for (Platform p: iPlatforms) {
				p.displayPlatform();
			}
			clock.display();
			for (Platform p: iPlatforms) {
				hero.checkPlatform(p);
			}
			if (hero.getStopPlatformRotation()==false) {
				hero.setOnPlatform(false);
			}
			for (Wall w: iWalls){
				hero.checkWalls(w);
			}
			if (hero.getStopWallRotation()==false) {
				hero.setWallCollision('n');
			}
			if (hero.getWallCollision()=='n' && hero.getOnPlatform()==true) {
				hero.run();
			}
			else if (hero.getOnPlatform()==false && hero.getWallCollision()=='n') {
				hero.gravity();
			}
			else if (hero.getWallCollision()!='n'){
				hero.wallClimb();
				hero.wallSlide(key);
			}
			hero.move();
			hero.setStopWallRotation(false);
			hero.setStopPlatformRotation(false);
			platformCounter=0;
			wallCounter=0;
			iEndFlag.check(hero.getMapBodyX(),hero.getMapBodyBotY(),hero.getMapBodyTopY());
			if (iEndFlag.getComplete()==true ) {
				iEndFlag.setComplete(false);
				hero.resetVel();
				main=true;
				instructions=false;
			}
		}
		
		/* CREDITS 
		 * Display the game credits
		 */
		
		else if (credits==true) {
			background(127);
			home.display();
			theCredits.display();
		}
	}
	@SuppressWarnings("unchecked")
	public void keyPressed() {
		
		//Run left and right, and jump up in the air
		
		if (hero.getWallCollision()=='n') {
			hero.setRun(key,true);
			if ((key=='w'||key==' ') && hero.getJump()==false && hero.getWallCollision()=='n') {
				hero.startJump(-10);
				startJump=true;
			}
		}
		//Jump off a wall
		
		else if (hero.getWallCollision()!='n' && key=='w') {
			hero.wallJump();
		}
		
		//Release yourself from a wall
		
		else if (key=='a' && hero.getWallCollision()=='l') {
			hero.wallRelease(key);
		}
		else if (key=='d' && hero.getWallCollision()=='r') {
			hero.wallRelease(key);
		}
		else if (hero.getWallCollision()!='n' && key=='s') {
			hero.setSlideDown(true);
		}
		if (gameMode=="Brawl") {
			if (hero2.getWallCollision()=='n') {
				hero2.setRun(keyCode,true);
				if ((keyCode==UP) && hero2.getJump()==false && hero2.getWallCollision()=='n') {
					hero2.startJump(-10);
					startJump=true;
				}
			}
			//Jump off a wall
			
			else if (hero2.getWallCollision()!='n' && keyCode==UP) {
				hero2.wallJump();
			}
			
			//Release yourself from a wall
			
			else if (keyCode==LEFT && hero2.getWallCollision()=='l') {
				hero2.wallRelease(keyCode);
			}
			else if (keyCode==RIGHT && hero2.getWallCollision()=='r') {
				hero2.wallRelease(keyCode);
			}
			//Slide down a wall
		
			else if (hero2.getWallCollision()!='n' && keyCode==DOWN) {
				hero2.setSlideDown(true);
			}
		}
		if (key=='r') {
			if (game==true) {
				if (gameMode=="Solo") {
					if (game==true && recordScreen==false) {
						hero.startPosition(startFlag.getFlagPoleX(),startFlag.getFlagPoleY2());
					}
					if (instructions==true) {
						hero.startPosition(iStartFlag.getFlagPoleX(), iStartFlag.getFlagPoleY2());
					}
					hero.resetVel();
					clock.resetSeconds();
					clock.resetMilliseconds();
				}
				else if (gameMode=="Brawl") {
					hero.startPosition(startFlag.getFlagPoleX(),startFlag.getFlagPoleY2());
					hero.resetVel();
					hero2.startPosition(endFlag.getFlagPoleX(),endFlag.getFlagPoleY2());
					hero2.resetVel();
					clock.resetSeconds();
					clock.resetMilliseconds();
					pause=false;
					counter=0;
					counter2=3;
					roundNum=1;
				}
			}
		}
		
		if (create==true) {
			if (key==BACKSPACE) {
				for (int i=0; i<creativeWalls.size(); i++) {
					if (creativeWalls.get(i).getChosen()==true) {
						creativeWalls.remove(i);
					}
				}
				for (int i=0; i<creativePlatforms.size(); i++) {
					if (creativePlatforms.get(i).getChosen()==true) {
						creativePlatforms.remove(i);
					}
				}
			}
		}
		//Write down a file name and serialize your level in creative mode
		
		if (create==true && saveStage==true) {
			if (key!=BACKSPACE && key!=' ' && keyCode!=ENTER && key!=CODED) {
				save.appendName(key);
			}
			else if (key==BACKSPACE) {
				save.removeChar();
			}
			else if (key==' ') {
				save.appendName('_');
			}
			else if (keyCode==ENTER) {
				if (greenFlag.getOnField()==true && orangeFlag.getOnField()==true) {
					if (brawl.getBrawl()==false) {
						try {
							fileName=save.getFileName()+".txt";
							x=new Formatter(fileName);
							x.format("%s", "Mode\n");
							x.format("%s", "Solo\n");
							x.format("%s","Wall\n");
							for (Wall w: creativeWalls) {
								x.format("%s", w.getX()+","+w.getY1()+","+w.getY2()+"\n");
							}
							x.format("%s","Platform\n");
							for (Platform p: creativePlatforms) {
								x.format("%s", p.getY()+","+p.getX1()+","+p.getX2()+"\n");
							}
							x.format("%s","StartFlag\n");
							x.format("%s", greenFlag.getFlagPoleX()+","+(greenFlag.getFlagPoleY2()-35)+"\n");
							x.format("%s", "EndFlag\n");
							x.format("%s", orangeFlag.getFlagPoleX()+","+(orangeFlag.getFlagPoleY2()-35)+"\n");
							x.format("%s","EOF\n");
							x.format("%s", "Records\n");
							x.format("%s", "999,999,999,999,999\n");
							x.format("%s", "Names\n");
							x.format("%s", "X,X,X,X,X");
							x.close();
							save.setFileSaved(true);
						}
						catch(IOException ex) {
							ex.printStackTrace();
						}
					}
					else if (brawl.getBrawl()==true) {
						try {
							System.out.println("Hello there!");
							fileName=save.getFileName()+".txt";
							x=new Formatter(fileName);
							x.format("%s", "Mode\n");
							x.format("%s", "Brawl\n");
							x.format("%s","Wall\n");
							for (Wall w: creativeWalls) {
								x.format("%s", w.getX()+","+w.getY1()+","+w.getY2()+"\n");
							}
							x.format("%s","Platform\n");
							for (Platform p: creativePlatforms) {
								x.format("%s", p.getY()+","+p.getX1()+","+p.getX2()+"\n");
							}
							x.format("%s","StartFlag\n");
							x.format("%s", greenFlag.getFlagPoleX()+","+(greenFlag.getFlagPoleY2()-35)+"\n");
							x.format("%s", "EndFlag\n");
							x.format("%s", orangeFlag.getFlagPoleX()+","+(orangeFlag.getFlagPoleY2()-35)+"\n");
							x.format("%s","EOF\n");
							x.close();
							save.setFileSaved(true);
						}
						catch(IOException ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		}
		
		//Add your name and score to the high scores list
		
		if (recordScreen==true) {
			if (key!=BACKSPACE && key!=' ' && keyCode!=ENTER && key!=CODED) {
				gameRecord.appendName(key);
			}
			else if (key==BACKSPACE) {
				gameRecord.removeChar();
			}
			else if (key==' ') {
				gameRecord.appendName('_');
			}
			else if (keyCode==ENTER && overwritten==false) {
				try {
					nameRecordsFile[scoreNum]=gameRecord.getName();
					sc = new Scanner(new File(fileName));
					while (sc.hasNext()) {
						line=sc.next();
						oldFile.add(line);
					}
					sc.close();
					x = new Formatter(fileName);
					for (int i=0; i<oldFile.size(); i++) {
						if (stopWriting==false) {
							if (!oldFile.get(i).equals("Names") && !oldFile.get(i).equals("Records")) {
								x.format("%s", oldFile.get(i)+"\n");
							}
							else if (oldFile.get(i).equals("Records")) {
								x.format("%s", "Records\n");
								x.format("%s", floatRecordsFile[0]+","+floatRecordsFile[1]+","+floatRecordsFile[2]+","+floatRecordsFile[3]+","+floatRecordsFile[4]+"\n");
								i+=1;
							}
							else if (oldFile.get(i).equals("Names")) {
								x.format("%s","Names\n");
								x.format("%s", nameRecordsFile[0]+","+nameRecordsFile[1]+","+nameRecordsFile[2]+","+nameRecordsFile[3]+","+nameRecordsFile[4]);
								i+=1;
								stopWriting=true;
							}
						}
					}
					x.close();
					overwritten=true;
					stopWriting=false;
				}
				catch(IOException ex) {
					ex.printStackTrace();
				}
				
			}
			
		}
		
		//Write down the name of the file you wish to load, and load the serialized file
		
		if (loadLevels==true) {
			if (key!=BACKSPACE && key!=' ' && keyCode!=ENTER && key!=CODED) {
				levelMenu.appendName(key);
			}
			else if (key==BACKSPACE) {
				levelMenu.removeChar();
			}
			else if (key==' ') {
				levelMenu.appendName('_');
			}
			else if (keyCode==ENTER) {
				fileName=levelMenu.getFileName()+".txt";
				generateLevel();
				loadLevels=false;
				game=true;
			}
		}
	}
	
	public void keyReleased() {
		hero.setRun(key, false);
		if (key=='s') {
			hero.setSlideDown(false);
		}
		if (gameMode=="Brawl") {
			hero2.setRun(keyCode, false);
			if (keyCode==DOWN) {
				hero2.setSlideDown(false);
			}
		}
	}
	
	public void mousePressed() {
		if (main==true) {
			
			//start playing the game
			
			if (mainscreen.getPlay()==true && main==true) {
				main=false;
				loadLevels=true;
			}
			
			//start up creative mode
			
			else if (mainscreen.getCreate()==true && main==true) {
				main=false;
				create=true;
			}
			
			//start up help mode
			
			else if (mainscreen.getHelp()==true && main==true) {
				main=false;
				instructions=true;
				hero.startPosition(iStartFlag.getFlagPoleX(),iStartFlag.getFlagPoleY2());
				clock.resetMilliseconds();
				clock.resetSeconds();
				hero.resetVel();
			}
			
			//start up credits
			
			else if (mainscreen.getCredits()==true && main==true) {
				main=false;
				credits=true;
			}
		}
		
		//go back home from loading screen
		
		if (loadLevels==true) {
			if (home.checkClick()==true) {
				main=true;
				loadLevels=false;
			}
		}
		
		//go back home while in a game
		
		if (game==true) {
			if (home.checkClick()==true) {
				if (recordScreen==true) {
					gameRecord.setNameEntered(false);
					gameRecord.setScoreNum(0);
					recordScreen=false;
					recordScreen2=false;
					clock.resetSeconds();
					clock.resetMilliseconds();
					overwritten=false;
				}
				if (gameMode.equals("Brawl")) {
					pause=false;
					counter=0;
					counter2=3;
					roundNum=1;
				}
				game=false;
				main=true;
			}
			if (retry.checkClick()==true) {
				hero.startPosition(startFlag.getFlagPoleX(),startFlag.getFlagPoleY2());
				hero.resetVel();
				clock.resetSeconds();
				clock.resetMilliseconds();
				if (gameMode.equals("Brawl")) {
					pause=false;
					counter=0;
					counter2=3;
					roundNum=1;
				}
			}
		}
		
		//display the highscore screen
		
		if (game==true && recordScreen==true) {
			if (gameRecord.checkClick()==true) {
				recordScreen2=true;
			}
		}
		
		//hide the highscore screen
		
		if (recordScreen2==true) {
			if (gameRecord.checkClick2()==true) {
				recordScreen2=false;
			}
		}
		
		//When in creative mode, allows you to pick up walls, platforms, flags, etc. to build your own map
		
		if (create==true && saveStage!=true) {
			buildTab.checkClick();
			for (Wall w: creativeWalls) {
				w.checkClick();
			}
			for (Platform p: creativePlatforms) {
				p.checkClick();
			}
			if (home.checkClick()==true) {
				if (create==true) {
					creativeWalls.clear();
					creativePlatforms.clear();
				}
				create=false;
				main=true;
				greenFlag.setOnField(false);
				greenFlag=new GreenFlag(this);
				orangeFlag.setOnField(false);
				orangeFlag=new OrangeFlag(this);
			}
			if (save.checkClick()==true) {
				saveStage=true;
			}
			brawl.checkClick();
		}
		
		//Return to building mode in creative mode
		
		if (create==true && saveStage==true) {
			if (createButton.checkClick()==true) {
				saveStage=false;
				save.setFileSaved(false);
			}
		}
		
		//Return to home screen from instructions
		
		if (instructions==true) {
			if (home.checkClick()==true) {
				main=true;
				instructions=false;
			}
			if (retry.checkClick()==true) {
				if (gameMode=="Solo") {
					if (game==true && recordScreen==false) {
						hero.startPosition(startFlag.getFlagPoleX(),startFlag.getFlagPoleY2());
					}
					if (instructions==true) {
						hero.startPosition(iStartFlag.getFlagPoleX(), iStartFlag.getFlagPoleY2());
					}
					hero.resetVel();
					clock.resetSeconds();
					clock.resetMilliseconds();
				}
				else if (gameMode=="Brawl") {
					hero.startPosition(startFlag.getFlagPoleX(),startFlag.getFlagPoleY2());
					hero.resetVel();
					hero2.startPosition(endFlag.getFlagPoleX(),endFlag.getFlagPoleY2());
					hero2.resetVel();
				}
			}
			for (Instructions i: instructionsArray) {
				if (i.getHover()==true) {
					i.setDisplay();
				}
			}
		}
		
		//Return to home screen from credits
		
		if (credits==true) {
			if (home.checkClick()==true) {
				main=true;
				credits=false;
				theCredits.resetStarWarsEffect();
			}
		}
	}
	public void mouseReleased() {
		
		//drop the walls/platforms/flags that you are dragging around to create your map
		
		if (buildTab.getChosenItem().equals("wall")) {
			creativeWalls.add(new Wall(createWall.getX(),createWall.getY1(),createWall.getY2(),this));
			buildTab.setChosenItem("nothing");
			creativeWalls.get(creativeWalls.size()-1).setChosen(false);
		}
		else if (buildTab.getChosenItem().equals("platform")) {
			creativePlatforms.add(new Platform(createPlatform.getY(), createPlatform.getX1(), createPlatform.getX2(), this));
			buildTab.setChosenItem("nothing");
			creativePlatforms.get(creativePlatforms.size()-1).setChosen(false);
		}
		else if (buildTab.getChosenItem().equals("startFlag")) {
			buildTab.setChosenItem("nothing");
			greenFlag.setOnField(true);
		}
		else if (buildTab.getChosenItem().equals("endFlag")) {
			buildTab.setChosenItem("nothing");
			orangeFlag.setOnField(true);
		}
		for (Wall w: creativeWalls) {
			if (w.getDragWall()!='n') {
				w.setDragWall('n');
			}
		}
		for (Platform p: creativePlatforms) {
			if (p.getDragPlatform()!='n') {
				p.setDragPlatform('n');
			}
		}
	}
	public void generateLevel(){
		walls.clear();
		platforms.clear();
		try {
			Scanner sc = new Scanner(new File(fileName));
			while (sc.hasNext()) {
				line=sc.next();
				if (line.equals("Mode")) {
					while (!line.equals("Wall")) {
						line=sc.next();
						if (!line.equals("Wall")) {
							if (line.equals("Solo")) {
								gameMode="Solo";
							}
							else if (line.contentEquals("Brawl")) {
								gameMode="Brawl";
							}
						}
					}
				}
				if (line.equals("Wall")) {
					while (!line.equals("Platform")){
						line=sc.next();
						if (!line.equals("Platform")) {
							wallFile=line.split(",");
							walls.add(new Wall(Float.parseFloat(wallFile[0]),Float.parseFloat(wallFile[1]),Float.parseFloat(wallFile[2]),this));
						}
					}
				}
				if (line.equals("Platform")) {
					while (!line.equals("StartFlag")){
						line=sc.next();
						if (!line.equals("StartFlag")){
							platFile=line.split(",");
							platforms.add(new Platform(Float.parseFloat(platFile[0]),Float.parseFloat(platFile[1]),Float.parseFloat(platFile[2]),this));
						}
					}
				}
				if (line.equals("StartFlag")) {
					while (!line.equals("EndFlag")){
						line=sc.next();
						if (!line.equals("EndFlag")) {
							startFlagFile=line.split(",");
							startFlag.setFlag(Float.parseFloat(startFlagFile[0]),Float.parseFloat(startFlagFile[1]));
						}
					}
				}
				if (line.equals("EndFlag")) {
					while (!line.equals("EOF")) {
						line=sc.next();
						if (!line.equals("EOF")) {
							endFlagFile=line.split(",");
							endFlag.setFlag(Float.parseFloat(endFlagFile[0]), Float.parseFloat(endFlagFile[1]));
						}
					}
				}
				if (gameMode.equals("Solo")) {
					hero.startPosition(startFlag.getFlagPoleX(),startFlag.getFlagPoleY2());
					hero.resetVel();
					clock.resetMilliseconds();
					clock.resetSeconds();
				}
				else if (gameMode.equals("Brawl")) {
					hero.startPosition(startFlag.getFlagPoleX(),startFlag.getFlagPoleY2());
					hero.resetVel();
					hero2.startPosition(endFlag.getFlagPoleX(), endFlag.getFlagPoleY2());
					hero2.resetVel();
					roundNum=1;
				}
				clock.resetMilliseconds();
				clock.resetSeconds();
				game=true;
			}
			sc.close();
		}
		catch(Exception c) {
			c.printStackTrace();
		}
	}
	public void checkHitBoxes() {
		if (hero.getHitX2()>hero2.getHitX1() && hero.getHitX1()<hero2.getHitX2() && hero.getHitY2()>hero2.getHitY1() && hero.getHitY1()<hero2.getHitY2()) {
			roundNum+=1;
			if (roundNum%2==0) {
				brawlTime=clock.getSeconds()+clock.getMilliseconds()/1000;
			}
			else {
				if (clock.getSeconds()+clock.getMilliseconds()/1000<brawlTime) {
					winner=2;
				}
				else {
					winner=1;
				}
			}
			clock.resetMilliseconds();
			clock.resetSeconds();
			pause=true;
			counter2=3;
		}
	}
}
