import java.awt.event.*;

public class Assignment2 extends GameEngine {
	
	/* Main function to set up the game */
	public static void main(String[] args) {
		// Create assignment 2
		createGame( new Assignment2() );
	}
	
	//====================================================================
	// GAME VARIABLES
	//====================================================================
	
	// Variable to keep track of Game State
	int gameState;
	
	// Variables to keep track of window width and height
	int width;
	int height;
	String windowDimensions;
	
	// Variables to store profile number
	int profile;
	String selectedProfile;
	
	// Save Variables
	boolean saveFiles;
	int save;
	
	// Audio Variables
	boolean audio;
	
	//====================================================================
	// MENU
	//====================================================================
	
	// Varibale to keep track of Menu Option
	int menuOption;
	
	// Variable to keep track of Menu State
	int menuState;
	
	// Function to initialse Menu Variables
	public void initMenu() {
		menuOption = 0;
		menuState = 0;
	}
	
	// Function to draw Main Menu
	public void drawMainMenu() {
		// Title screen
		if(menuState == 0) {
			// Draw game title
			changeColor(white);
			drawBoldText((width/10)*2, (height/10), "Assignment 2");
			drawSolidRectangle((width/10)*2, (height/10)+2, 255, 3);
			
			// Draw Prompt
			drawText((width/10), (height/4)*3,"Press Any Key To Start");
		}
		
		// Profile select screen
		if(menuState == 1) {
			changeColor(white);
			// Draw heading
			drawBoldText((width/10)*2, (height/10),"Select a Profile:");
			drawSolidRectangle((width/10)*2, (height/10)+2, 295, 3);
			
			// Draw options
			if(menuOption == 0) {
				changeColor(green);
				drawText((width/10)*2, (height-height/3), "-Profile 1");
			}else{
				changeColor(white);
				drawText((width/10)*2, (height-height/3), "-Profile 1");
			}
			if(menuOption == 1) {
				changeColor(green);
				drawText((width/10)*2, (height-height/3)+(height/9), "-Profile 2");
			}else{
				changeColor(white);
				drawText((width/10)*2, (height-height/3)+(height/9), "-Profile 2");
			}
			if(menuOption == 2) {
				changeColor(green);
				drawText((width/10)*2, (height-height/3)+(height/9)*2, "-Profile 3");
			}else{
				changeColor(white);
				drawText((width/10)*2, (height-height/3)+(height/9)*2, "-Profile 3");
			}
		}
		
		// Main menu screen
		if(menuState == 2) {
			changeColor(white);
			// Draw Heading
			drawBoldText((width/10)*2, (height/10),selectedProfile);
			drawSolidRectangle((width/10)*2, (height/10)+2, 170, 3);
			
			// Draw Options
			if(menuOption == 0) {
				changeColor(green);
				drawText((width/10)*2, (height/2),"-New Game");
			}else{
				changeColor(white);
				drawText((width/10)*2, (height/2),"-New Game");
			}
			if(menuOption == 1) {
				changeColor(green);
				drawText((width/10)*2, (height/2)+(height/10),"-Profile Select");
			}else{
				changeColor(white);
				drawText((width/10)*2, (height/2)+(height/10),"-Profile Select");
			}
			if(menuOption == 2) {
				changeColor(green);
				drawText((width/10)*2, (height/2)+(height/10)*2,"-Load Game");
			}else if(!saveFiles) {
				changeColor(125, 125, 125);
				drawText((width/10)*2, (height/2)+(height/10)*2,"-Load Game");
			}else{
				changeColor(white);
				drawText((width/10)*2, (height/2)+(height/10)*2,"-Load Game");
			}
			if(menuOption == 3) {
				changeColor(green);
				drawText((width/10)*2, (height/2)+(height/10)*3,"-Settings");
			}else{
				changeColor(white);
				drawText((width/10)*2, (height/2)+(height/10)*3,"-Settings");
			}
			if(menuOption == 4) {
				changeColor(green);
				drawText((width/10)*2, (height/2)+(height/10)*4,"-Exit");
			}else{
				changeColor(white);
				drawText((width/10)*2, (height/2)+(height/10)*4,"-Exit");
			}
		}
		
		// Load screen
		if(menuState == 3) {
			drawLoad();
		}
		
		// Settings Screen
		if(menuState == 4) {
			drawSettings();
		}
	}
	
	// Function to draw Settings Screen
	public void drawSettings() {
		changeColor(white);
		// Draw Heading
		drawBoldText((width/10)*2, (height/10), "Settings:");
		drawSolidRectangle((width/10)*2, (height/10)+2, 165, 3);
		
		// Draw Options
		if(menuOption == 0) {
			changeColor(green);
			if(audio) {
				drawText((width/10)*2, (height/2), "-Audio: ON");
			}else{
				drawText((width/10)*2, (height/2), "-Audio: OFF");
			}
		}else{
			changeColor(white);
			if(audio) {
				drawText((width/10)*2, (height/2), "-Audio: ON");
			}else{
				drawText((width/10)*2, (height/2), "-Audio: OFF");
			}
		}
		windowDimensions = String.format("-Video: %dx%d", width, height);
		if(menuOption == 1) {
			changeColor(green);
			drawText((width/10)*2, (height/2)+(height/6), windowDimensions);
		}else{
			changeColor(white);
			drawText((width/10)*2, (height/2)+(height/6), windowDimensions);
		}
		if(menuOption == 2) {
			changeColor(green);
			drawText((width/10)*2, (height/2)+(height/6)*2, "-Back");
		}else{
			changeColor(white);
			drawText((width/10)*2, (height/2)+(height/6)*2, "-Back");
		}
	}
	
	// Function to draw Load Screen
	public void drawLoad() {
		changeColor(white);
		// Draw Heading
		drawBoldText((width/10)*2, (height/10), "Load:");
		drawSolidRectangle((width/10)*2, (height/10)+2, 105, 3);
		
		// Draw Options
		if(menuOption == 0) {
			changeColor(green);
			drawText((width/10)*2, (height/2), "-Save1");
		}else{
			changeColor(white);
			drawText((width/10)*2, (height/2), "-Save1");
		}
		if(menuOption == 1) {
			changeColor(green);
			drawText((width/10)*2, (height/2)+(height/8), "-Save2");
		}else{
			changeColor(white);
			drawText((width/10)*2, (height/2)+(height/8), "-Save2");
		}
		if(menuOption == 2) {
			changeColor(green);
			drawText((width/10)*2, (height/2)+(height/8)*2, "-Save3");
		}else{
			changeColor(white);
			drawText((width/10)*2, (height/2)+(height/8)*2, "-Save3");
		}
		if(menuOption == 3) {
			changeColor(green);
			drawText((width/10)*2, (height/2)+(height/8)*3, "-Back");
		}else{
			changeColor(white);
			drawText((width/10)*2, (height/2)+(height/8)*3, "-Back");
		}
	}
	
	// Function to draw Pause Menu
	public void drawPauseMenu() {
		// Pause Screen
		if(menuState == 0) {
			changeColor(white);
			// Draw Heading
			drawBoldText((width/10)*2, (height/10), "Paused:");
			drawSolidRectangle((width/10)*2, (height/10)+2, 150, 3);
			// Draw Options
			if(menuOption == 0) {
				changeColor(green);
				drawText((width/10)*2, (height/2), "-Resume");
			}else{
				changeColor(white);
				drawText((width/10)*2, (height/2), "-Resume");
			}
			if(menuOption == 1) {
				changeColor(green);
				drawText((width/10)*2, (height/2)+(height/10), "-Settings");
			}else{
				changeColor(white);
				drawText((width/10)*2, (height/2)+(height/10), "-Settings");
			}
			if(menuOption == 2) {
				changeColor(green);
				drawText((width/10)*2, (height/2)+(height/10)*2, "-Save");
			}else{
				changeColor(white);
				drawText((width/10)*2, (height/2)+(height/10)*2, "-Save");
			}
			if(menuOption == 3) {
				changeColor(green);
				drawText((width/10)*2, (height/2)+(height/10)*3, "-Load");
			}else if(!saveFiles) {
				changeColor(125, 125, 125);
				drawText((width/10)*2, (height/2)+(height/10)*3, "-Load");
			}else{
				changeColor(white);
				drawText((width/10)*2, (height/2)+(height/10)*3, "-Load");
			}
			if(menuOption == 4) {
				changeColor(green);
				drawText((width/10)*2, (height/2)+(height/10)*4, "-Main Menu");
			}else{
				changeColor(white);
				drawText((width/10)*2, (height/2)+(height/10)*4, "-Main Menu");
			}
		}
		
		// Settings Screen
		if(menuState == 1) {
			drawSettings();
		}
		
		// Save Screen
		if(menuState == 2) {
			changeColor(white);
			// Draw Heading
			drawBoldText((width/10)*2, (height/10), "Save:");
			drawSolidRectangle((width/10)*2, (height/10)+2, 105, 3);
			
			// Draw Options
			if(menuOption == 0) {
				changeColor(green);
				drawText((width/10)*2, (height/2), "-Save1");
			}else{
				changeColor(white);
				drawText((width/10)*2, (height/2), "-Save1");
			}
			if(menuOption == 1) {
				changeColor(green);
				drawText((width/10)*2, (height/2)+(height/8), "-Save2");
			}else{
				changeColor(white);
				drawText((width/10)*2, (height/2)+(height/8), "-Save2");
			}
			if(menuOption == 2) {
				changeColor(green);
				drawText((width/10)*2, (height/2)+(height/8)*2, "-Save3");
			}else{
				changeColor(white);
				drawText((width/10)*2, (height/2)+(height/8)*2, "-Save3");
			}
			if(menuOption == 3) {
				changeColor(green);
				drawText((width/10)*2, (height/2)+(height/8)*3, "-Back");
			}else{
				changeColor(white);
				drawText((width/10)*2, (height/2)+(height/8)*3, "-Back");
			}
		}
		
		// Load Screen
		if(menuState == 3) {
			drawLoad();
		}
	}
	
	// Function to handle navigation in main menu
	public void navigateMainMenu(KeyEvent event) {
		// Title Screen
		if(menuState == 0) {
			menuState ++;
			return;
		}
		
		// Profile Select Screen
		if(menuState == 1) {
			if(event.getKeyCode() == KeyEvent.VK_DOWN) {
				if(menuOption == 2) {
					menuOption -= 2;
				}else{
					menuOption ++;
				}
			}
			if (event.getKeyCode() == KeyEvent.VK_UP) {
				if(menuOption == 0) {
					menuOption += 2;
				}else{
					menuOption --;
				}
			}
			if(event.getKeyCode() == KeyEvent.VK_ENTER) {
				profile = menuOption + 1;
				selectedProfile = String.format("Profile %d:", profile);
				menuOption = 0;
				menuState ++;
				return;
			}
		}
		
		// Main Menu screen
		if(menuState == 2) {
			if(event.getKeyCode() == KeyEvent.VK_DOWN) {
				if(menuOption == 4) {
					menuOption -= 4;
				}else if((!saveFiles) && (menuOption == 1)) {
					menuOption += 2;
				}else{
					menuOption ++;
				}
			}
			if(event.getKeyCode() == KeyEvent.VK_UP) {
				if(menuOption == 0) {
					menuOption += 4;
				}else if((!saveFiles) && (menuOption == 3)) {
					menuOption -= 2;
				}else{
					menuOption --;
				}
			}
			if(event.getKeyCode() == KeyEvent.VK_ENTER) {
				// New Game
				if(menuOption == 0) {
					gameState ++;
				}
				// Profile Select
				if(menuOption == 1) {
					menuOption = 0;
					menuState --;
					return;
				}
				// Load Game
				if(menuOption == 2) {
					menuState ++;
					menuOption = 0;
					return;
				}
				// Settings
				if(menuOption == 3) {
					menuState += 2;
					menuOption = 0;
					return;
				}
				// Exit
				if(menuOption == 4) {
					System.exit(0);
				}
			}
		}
		
		// Load Screen
		if(menuState == 3) {
			if(event.getKeyCode() == KeyEvent.VK_DOWN) {
				if(menuOption == 3) {
					menuOption -= 3;
				}else{
					menuOption ++;
				}
			}
			if(event.getKeyCode() == KeyEvent.VK_UP) {
				if(menuOption == 0) {
					menuOption += 3;
				}else{
					menuOption --;
				}
			}
			if(event.getKeyCode() == KeyEvent.VK_ENTER) {
				// Save1, Save2, Save3
				if((menuOption >= 0) && (menuOption <= 2)) {
					save = menuOption + 1;
					gameState++;
				}
				// Back
				if(menuOption == 3) {
					menuState--;
					menuOption = 0;
					return;
				}
			}
		}
		
		// Settings Screen
		if(menuState == 4) {
			if(event.getKeyCode() == KeyEvent.VK_DOWN) {
				if(menuOption == 2) {
					menuOption -= 2;
				}else{
					menuOption++;
				}
			}
			if(event.getKeyCode() == KeyEvent.VK_UP) {
				if(menuOption == 0) {
					menuOption += 2;
				}else{
					menuOption--;
				}
			}
			if(event.getKeyCode() == KeyEvent.VK_ENTER) {
				// Audio
				if(menuOption == 0) {
					if(audio) {
						audio = false;
					}else{
						audio = true;
					}
				}
				// Back
				if(menuOption == 2) {
					menuState -= 2;
					menuOption = 0;
					return;
				}
			}
		}
	}
	
	// Function to handle navigation in Pause Menu
	public void navigatePauseMenu(KeyEvent event) {
		// Pause Menu screen
		if(menuState == 0) {
			if(event.getKeyCode() == KeyEvent.VK_DOWN) {
				if(menuOption == 4) {
					menuOption -= 4;
				}else if((!saveFiles) && (menuOption == 2)){
					menuOption += 2;
				}else{
					menuOption++;
				}
			}
			if(event.getKeyCode() == KeyEvent.VK_UP) {
				if(menuOption == 0) {
					menuOption += 4;
				}else if((!saveFiles) && (menuOption == 4)) {
					menuOption -= 2;
				}else{
					menuOption--;
				}
			}
			if(event.getKeyCode() == KeyEvent.VK_ENTER) {
				// Resume
				if(menuOption == 0) {
					gameState--;
					return;
				}
				// Settings
				if(menuOption == 1) {
					menuOption = 0;
					menuState++;
					return;
				}
				// Save
				if(menuOption == 2) {
					menuOption = 0;
					menuState += 2;
					return;
				}
				// Load
				if(menuOption == 3) {
					menuOption = 0;
					menuState += 3;
					return;
				}
				// Main Menu
				if(menuOption == 4) {
					menuOption = 0;
					menuState = 2;
					gameState -= 2;
					return;
				}
			}
		}
		
		// Settings screen
		if(menuState == 1) {
			if(event.getKeyCode() == KeyEvent.VK_DOWN) {
				if(menuOption == 2) {
					menuOption -= 2;
				}else{
					menuOption ++;
				}
			}
			if(event.getKeyCode() == KeyEvent.VK_UP) {
				if(menuOption == 0) {
					menuOption += 2;
				}else{
					menuOption--;
				}
			}
			if(event.getKeyCode() == KeyEvent.VK_ENTER) {
				// Audio
				if(menuOption == 0) {
					if(audio) {
						audio = false;
					}else{
						audio = true;
					}
				}
				// Back
				if(menuOption == 2) {
					menuState --;
					menuOption = 0;
					return;
				}
			}
		}
		
		// Save screen
		if(menuState == 2) {
			if(event.getKeyCode() == KeyEvent.VK_DOWN) {
				if(menuOption == 3) {
					menuOption -= 3;
				}else{
					menuOption ++;
				}
			}
			if(event.getKeyCode() == KeyEvent.VK_UP) {
				if(menuOption == 0) {
					menuOption += 3;
				}else{
					menuOption--;
				}
			}
			if(event.getKeyCode() == KeyEvent.VK_ENTER) {
				// Save1, Save2, Save3
				if((menuOption >= 0) && (menuOption <= 2)) {
					save = menuOption + 1;
					menuOption = 0;
					menuState -= 2;
					return;
				}
				//Back
				if(menuOption == 3) {
					menuOption = 0;
					menuState -= 2;
					return;
				}
			}
		}
		
		// Load screen
		if(menuState == 3) {
			if(event.getKeyCode() == KeyEvent.VK_DOWN) {
				if(menuOption == 3) {
					menuOption -= 3;
				}else{
					menuOption ++;
				}
			}
			if(event.getKeyCode() == KeyEvent.VK_UP) {
				if(menuOption == 0) {
					menuOption += 3;
				}else{
					menuOption --;
				}
			}
			if(event.getKeyCode() == KeyEvent.VK_ENTER) {
				// Save1, Save2, Save3
				if((menuOption >= 0) && (menuOption <= 2)) {
					save = menuOption + 1;
					gameState--;
					return;
				}
				// Back
				if(menuOption == 3) {
					menuState -= 3;
					menuOption = 0;
					return;
				}
			}
		}
	}
	
	//====================================================================
	// GAME
	//====================================================================
	
	// Draw Game
	public void drawGame() {
		changeColor(orange);
		drawBoldText((width/10)*2, (height/2), "Insert Game Here");
	}
	
	// Initialise the game
	public void init() {
		// Initialise window
		width = 500;
		height = 500;
		setWindowSize(width, height);
		
		// Initialise game variables
		gameState = 0;
		profile = 0;
		
		// Initialise save file variables
		saveFiles = false;
		save = 0;
		
		// Initialise audio variables
		audio = true;
		
		// Initialise menu variables
		initMenu();
		
	}
	
	public void update(double dt) {
		// Stops updating the game while in a menu
		if((gameState == 0) || (gameState == 2)) {
			return;
		}
	}
	
	public void paintComponent() {
		// Clear the background to black
		changeBackgroundColor(black);
		clearBackground(width, height);
		
		// Main Menu
		if(gameState == 0) {
			drawMainMenu();
		}
		
		// Pause Menu
		if(gameState == 2) {
			drawPauseMenu();
		}
		
		// Game Running
		if(gameState == 1) {
			drawGame();
		}
	}
	
	//=====================================================================
	// KEY EVENT HANDLERS
	//=====================================================================
	
	// KeyPressed Event Handler
	public void keyPressed(KeyEvent event) {
		// Main Menu
		if(gameState == 0) {
			navigateMainMenu(event);
		}
		
		// Game
		if(gameState == 1) {
			if(event.getKeyCode() == KeyEvent.VK_ESCAPE) {
				gameState++;
				initMenu();
				return;
			}
		}
		
		// Pause Menu
		if(gameState == 2) {
			navigatePauseMenu(event);
		}
	}
	
	// KeyReleased Event Handler
	public void keyReleased(KeyEvent event) {
		
	}
	
}