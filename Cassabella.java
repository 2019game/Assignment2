import java.awt.*;
import java.awt.event.KeyEvent;

public class Cassabella extends GameEngine {
    // Main Function
    public static void main(String args[]) {
        // Create a new Platform
        createGame(new Cassabella());
    }

    // --------------------------------------------------------------------------------------------
    // Game variables
    // --------------------------------------------------------------------------------------------
    boolean left, right;
    int width = 900;  // Display width
    int height = 600; // Display height
    int i, j;
    int score;
    String playerScore;

    // --------------------------------------------------------------------------------------------
    // Image variables
    // --------------------------------------------------------------------------------------------
    Image itemSheet;
    Image backgroundImage;

    // --------------------------------------------------------------------------------------------
    // Tilemap variables
    // --------------------------------------------------------------------------------------------
    double tileWidth = 30.0;
    double tileHeight = 30.0;
    double mapX, mapY;  // Returns the pixel value of mapPos int
    double mapW = width / tileWidth;
    double mapH = height / tileHeight;
    int mapPos;
    int mapSize;  // (Screen width / tile width) * (Screen height / tile height)
    double map[]; // Array of tile Id integers

    // --------------------------------------------------------------------------------------------
    // Initialise tilemap - Creates an integer reference array for each block
    // --------------------------------------------------------------------------------------------
    public void initMap() {
        mapSize = floor(mapW) * floor(mapH);
        map = new double[mapSize];

        for (i = 0; i < mapSize; i++) {  // Map size = Screen width / tile width *
            map[i] = i;
        }
    }

    // --------------------------------------------------------------------------------------------
    // Return pixel coordinate of tile map integer
    // --------------------------------------------------------------------------------------------
    public double mapPositionX(int PositionX) {
        mapX = (map[PositionX] % mapW) * (tileWidth);
        return (mapX); // Return the x pixel value of mapPos
    }

    public double mapPositionY(int PositionY) {
        mapY = (map[PositionY] / mapW) * (tileHeight);
        return (mapY); // Return the y pixel value of mapPos
    }

    // --------------------------------------------------------------------------------------------
    // Platforms
    // --------------------------------------------------------------------------------------------
    double platformX[], platformY[];
    double doorPosX, doorPosY;
    int numPlatforms;
    int platformPos[];
    int doorPos;
    Image grassLeft;
    Image grassCentre;
    Image grassRight;
    Image door;

    // --------------------------------------------------------------------------------------------
    //Initialise Platforms
    // --------------------------------------------------------------------------------------------
    public void initPlatforms() {
        // Load Images
        grassLeft = subImage(itemSheet, 360, 120, 60, 60);
        grassCentre = subImage(itemSheet, 420, 120, 60, 60);
        grassRight = subImage(itemSheet, 480, 120, 60, 60);
        door = subImage(itemSheet, 240, 0, 120, 120);

        // Create 5 platforms
        numPlatforms = 5;

        // Allocate door position
        doorPos = 65;
        doorPosX = mapPositionX(doorPos);
        doorPosY = mapPositionY(doorPos);

        // Allocate arrays
        platformX = new double[numPlatforms];
        platformY = new double[numPlatforms];
        platformPos = new int[numPlatforms];

        platformPos[0] = 454; // Platform start tile Map position
        platformPos[1] = 372;
        platformPos[2] = 321;
        platformPos[3] = 223;
        platformPos[4] = 154;

        // Get pixel coordinates for each Tile position
        for (i = 0; i < numPlatforms; i++) {
            platformX[i] = mapPositionX(platformPos[i]);
            platformY[i] = mapPositionY(platformPos[i]);
        }
    }

    public void drawPlatforms() {

        for (i = 0; i < numPlatforms; i++) {
            drawImage(grassLeft, platformX[i], platformY[i], tileWidth, tileHeight);
            for (j = 1; j <= 3; j++) {
                drawImage(grassCentre, platformX[i] + tileWidth * j, platformY[i], tileWidth, tileHeight);
            }
            drawImage(grassRight, platformX[i] + tileWidth * 4, platformY[i], tileWidth, tileHeight);
        }
        drawImage(door, doorPosX, doorPosY, tileWidth * 2, tileHeight * 3);
    }

    // --------------------------------------------------------------------------------------------
    // Items
    // --------------------------------------------------------------------------------------------
    double coinX[], coinY[];
    double goldDX, goldDY, redDX, redDY, greenDX, greenDY, blueDX, blueDY;
    double goldKX, goldKY, redKX, redKY, greenKX, greenKY, blueKX, blueKY;
    double goldDX2, goldDY2, redDX2, redDY2, greenDX2, greenDY2, blueDX2, blueDY2;
    double goldKX2, goldKY2, redKX2, redKY2, greenKX2, greenKY2, blueKX2, blueKY2;
    double goldBlockX, goldBlockY, chestClosedX, chestClosedY, chestOpenX, chestOpenY;
    int itemSet, itemType, coinPos[], goldDPos, redDPos, greenDPos, blueDPos;
    int goldKPos, redKPos, greenKPos, blueKPos, goldBlockPos, chestCPos, chestOPos;
    int redDPos2, goldDPos2, greenDPos2, blueDPos2, redKPos2, goldKPos2, greenKPos2, blueKPos2;
    boolean[] dActive = new boolean[4];
    Image coinImage, goldDImage, redDImage, greenDImage, blueDImage;
    Image goldBlockImage, goldKImage, redKImage, greenKImage, blueKImage, chestClosedImage, chestOpenImage;

    // Populate diamonds and keys
    public void itemFill(int itemType, int itemSet, int starter) {
        if ((itemType == 1) && (itemSet == 1)) {  // If diamonds Set 1
            redDPos = starter;
            goldDPos = starter + 1;
            blueDPos = starter + 2;
            greenDPos = starter + 3;
            // Get pixel coordinates for diamonds
            redDX = mapPositionX(redDPos);
            redDY = mapPositionY(redDPos);
            goldDX = mapPositionX(goldDPos);
            goldDY = mapPositionY(goldDPos);
            blueDX = mapPositionX(blueDPos);
            blueDY = mapPositionY(blueDPos);
            greenDX = mapPositionX(greenDPos);
            greenDY = mapPositionY(greenDPos);
        } else if ((itemType == 1) && (itemSet == 2)) {  // Diamonds Set 2
            redDPos2 = starter;
            goldDPos2 = starter + 1;
            blueDPos2 = starter + 2;
            greenDPos2 = starter + 3;
            // Get pixel coordinates for diamond set 2
            redDX2 = mapPositionX(redDPos2);
            redDY2 = mapPositionY(redDPos2);
            goldDX2 = mapPositionX(goldDPos2);
            goldDY2 = mapPositionY(goldDPos2);
            blueDX2 = mapPositionX(blueDPos2);
            blueDY2 = mapPositionY(blueDPos2);
            greenDX2 = mapPositionX(greenDPos2);
            greenDY2 = mapPositionY(greenDPos2);
        }
        // Keys
        if ((itemType == 2) && (itemSet == 1)) {  // If Keys Set 1
            redKPos = starter;
            goldKPos = starter + 1;
            blueKPos = starter + 2;
            greenKPos = starter + 3;
            // Get pixel coordinates for diamonds
            redKX = mapPositionX(redKPos);
            redKY = mapPositionY(redKPos);
            goldKX = mapPositionX(goldKPos);
            goldKY = mapPositionY(goldKPos);
            blueKX = mapPositionX(blueKPos);
            blueKY = mapPositionY(blueKPos);
            greenKX = mapPositionX(greenKPos);
            greenKY = mapPositionY(greenKPos);
        } else if ((itemType == 2) && (itemSet == 2)) {  // Keys Set 2
            redKPos2 = starter;
            goldKPos2 = starter + 1;
            blueKPos2 = starter + 2;
            greenKPos2 = starter + 3;
            // Get pixel coordinates for keys set 2
            redKX2 = mapPositionX(redKPos2);
            redKY2 = mapPositionY(redKPos2);
            goldKX2 = mapPositionX(goldKPos2);
            goldKY2 = mapPositionY(goldKPos2);
            blueKX2 = mapPositionX(blueKPos2);
            blueKY2 = mapPositionY(blueKPos2);
            greenKX2 = mapPositionX(greenKPos2);
            greenKY2 = mapPositionY(greenKPos2);
        }
    }
    // --------------------------------------------------------------------------------------------
    //  Initialise Items
    // --------------------------------------------------------------------------------------------

    public void initItems() {
        coinImage = subImage(itemSheet, 0, 120, 60, 60);
        goldDImage = subImage(itemSheet, 0, 60, 60, 60);
        redDImage = subImage(itemSheet, 60, 60, 60, 60);
        greenDImage = subImage(itemSheet, 120, 60, 60, 60);
        blueDImage = subImage(itemSheet, 180, 60, 60, 60);
        goldBlockImage = subImage(itemSheet, 540, 0, 60, 60);
        goldKImage = subImage(itemSheet, 60, 0, 60, 60);
        redKImage = subImage(itemSheet, 0, 0, 60, 60);
        greenKImage = subImage(itemSheet, 180, 0, 60, 60);
        blueKImage = subImage(itemSheet, 120, 0, 60, 60);
        chestClosedImage = subImage(itemSheet, 360, 0, 60, 60);
        chestOpenImage = subImage(itemSheet, 420, 0, 60, 60);

        coinPos = new int[4];
        coinX = new double[4];
        coinY = new double[4];

        // Item arrays
        // Coins
        coinPos[0] = 339;
        coinPos[1] = 340;
        coinPos[2] = 258;
        coinPos[3] = 259;
        // Get pixel coordinates for each coin
        for (i = 0; i < 4; i++) {
            coinX[i] = mapPositionX(coinPos[i]);
            coinY[i] = mapPositionY(coinPos[i]);
        }
        // Diamonds
        // Allocate Diamond start positions
        itemType = 1;  // Diamonds
        itemSet = 1; // Which set of diamonds
        int diamondsStart = 232;
        itemFill(itemType, itemSet, diamondsStart);
        for (i = 0; i < 4; i++) {
            dActive[i] = true;
        }
        int diamonds2Start = 364;
        itemSet = 2;
        itemFill(itemType, itemSet, diamonds2Start);

        // Keys
        // Allocate Key start positions
        itemType = 2;  // Keys
        itemSet = 1;  // Which set of keys
        int keyStart = 133;
        itemFill(itemType, itemSet, keyStart);

        int keyStart2 = 282;
        itemSet = 2;
        itemFill(itemType, itemSet, keyStart2);

        // Chest
        chestCPos = 476;
        chestOPos = 476;
        // Get pixel coordinates for Chest
        chestClosedX = mapPositionX(chestCPos);
        chestClosedY = mapPositionY(chestCPos);
        chestOpenX = mapPositionX(chestOPos);
        chestOpenY = mapPositionY(chestOPos);
    }

    // --------------------------------------------------------------------------------------------
    // Update Items
    // --------------------------------------------------------------------------------------------
    public void updateItems() {
        for (i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    if (dActive[i] == false) {
                        break;
                    } else {
                        if (((charX >= redDX) && (charX <= redDX + 30)) && ((charY >= redDY) && (charY <= redDY + 30))) {
                            score++;
                            dActive[i] = false;
                        }
                        break;
                    }
                case 1:
                    if (dActive[i] == false) {
                        break;
                    } else {
                        if ((charX == goldDX) && (charY == goldDY)) {
                            score++;
                            dActive[i] = false;
                        }
                        break;
                    }
                case 2:
                    if (dActive[i] == false) {
                        break;
                    } else {
                        if ((charX == blueDX) && (charY == blueDY)) {
                            score++;
                            dActive[i] = false;
                        }
                        break;
                    }
                case 3:
                    if (dActive[i] == false) {
                        break;
                    } else {
                        if ((charX == greenDX) && (charY == greenDY)) {
                            score++;
                            dActive[i] = false;
                        }
                        break;
                    }
            }
        }
    }
    // --------------------------------------------------------------------------------------------
    // Draw Items
    // --------------------------------------------------------------------------------------------
    public void drawItems() {
        for (i = 0; i < 4; i++) {  // Draw coins
            drawImage(coinImage, coinX[i], coinY[i], tileWidth, tileHeight);
        }
        // Draw Diamonds
        for(i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    if (dActive[i] == false) {
                        break;
                    } else {
                        drawImage(redDImage, redDX, redDY, tileWidth, tileHeight);
                        break;
                    }
                case 1:
                    if (dActive[i] == false) {
                        break;
                    } else {
                        drawImage(goldDImage, goldDX, goldDY, tileWidth, tileHeight);
                        break;
                    }
                case 2:
                    if (dActive[i] == false) {
                        break;
                    } else {
                        drawImage(blueDImage, blueDX, blueDY, tileWidth, tileHeight);
                        break;
                    }
                case 3:
                    if (dActive[i] == false) {
                        break;
                    } else {
                        drawImage(greenDImage, greenDX, greenDY, tileWidth, tileHeight);
                        break;
                    }
            }
        }
        drawImage(redDImage, redDX2, redDY2, tileWidth, tileHeight);
        drawImage(goldDImage, goldDX2, goldDY2, tileWidth, tileHeight);
        drawImage(blueDImage, blueDX2, blueDY2, tileWidth, tileHeight);
        drawImage(greenDImage, greenDX2, greenDY2, tileWidth, tileHeight);
        // Draw Keys
        drawImage(redKImage, redKX, redKY, tileWidth, tileHeight);
        drawImage(goldKImage, goldKX, goldKY, tileWidth, tileHeight);
        drawImage(blueKImage, blueKX, blueKY, tileWidth, tileHeight);
        drawImage(greenKImage, greenKX, greenKY, tileWidth, tileHeight);
        drawImage(redKImage, redKX2, redKY2, tileWidth, tileHeight);
        drawImage(goldKImage, goldKX2, goldKY2, tileWidth, tileHeight);
        drawImage(blueKImage, blueKX2, blueKY2, tileWidth, tileHeight);
        drawImage(greenKImage, greenKX2, greenKY2, tileWidth, tileHeight);

        // Draw Chest
        drawImage(chestClosedImage, chestClosedX, chestClosedY, tileWidth * 1.5, tileHeight * 1.5);
    }

    // --------------------------------------------------------------------------------------------
    // Initialise Score
    // --------------------------------------------------------------------------------------------
    public void initScore() {
        score = 0;
    }

    // --------------------------------------------------------------------------------------------
    // Update Score
    // --------------------------------------------------------------------------------------------
    public void updateScore() {
        playerScore = playerScore.format("%d", score);
    }

    // --------------------------------------------------------------------------------------------
    // Draw Score
    // --------------------------------------------------------------------------------------------
    public void drawScore() {
        changeColor(black);
        drawText(width / 2, 50, playerScore);
    }

    // --------------------------------------------------------------------------------------------
    // Enemies
    // --------------------------------------------------------------------------------------------
    // Enemy variables
    double spikeX, spikeY, mushDownX, mushDownY, mushUpX, mushUpY, brownMushX, brownMushY;
    int spikePos, mushPos, brownMushPos;
    Image spikeImage, mushDImage, mushUpImage, mushBImage;

    // --------------------------------------------------------------------------------------------
    // Initialise Enemies
    // --------------------------------------------------------------------------------------------
    public void initEnemies() {
        // Spikes
        spikePos = 500;
        spikeX = mapPositionX(spikePos);
        spikeY = mapPositionY(spikePos);
        spikeImage = subImage(itemSheet, 120, 180, 60, 60);
        // Mushroom
        mushPos = 195;
        mushDownX = mapPositionX(mushPos);
        mushDownY = mapPositionY(mushPos);
        mushDImage = subImage(itemSheet, 180, 180, 60, 60);
    }

    // --------------------------------------------------------------------------------------------
    // Update Enemies
    // --------------------------------------------------------------------------------------------
    public void updateEnemies() {

    }

    // --------------------------------------------------------------------------------------------
    // Draw Enemies
    // --------------------------------------------------------------------------------------------
    public void drawEnemies() {
         drawImage(mushDImage, mushDownX, mushDownY, tileWidth, tileHeight);
    }

    // --------------------------------------------------------------------------------------------
    // Character
    // --------------------------------------------------------------------------------------------
    double charX, charY; // Character pixel coordinates
    double charW, charH; // Character width & height
    double charVX, charVY; // Character velocity & gravity
    int charFrame;
    boolean charFalling;

    // Character Sprite Images
    Image charSpriteSheet;
    Image charSpriteRun;
    Image charFallingImage;
    Image charStandingImage;
    Image charRunning[];

    // Initialise Character
    public void initChar() {
        // Set Character Display
        charW = 37.5;  // Width of character
        charH = 50;  // Height of character

        // Set starting position of character
        mapPos = 480;
        charX = mapPositionX(mapPos);
        charY = mapPositionY(mapPos);

        // Set Character starting velocity
        charVX = 0;
        charVY = 0;
        charFalling = false;

        // Load Sprite Sheets
        charSpriteRun = loadImage("CRunning114x140.png");
        charSpriteSheet = loadImage("CIdleClimb95x145.png");

        // Load Sprites
        charStandingImage = subImage(charSpriteSheet, 0, 0, 95, 145);
        charFallingImage = subImage(charSpriteSheet, 950, 0, 95, 145);
        charRunning = new Image[16];

        // Load Character Running Sprites
        for (int i = 0; i < 16; i++) {
            int x = i % 4;
            int y = i / 4;
            charRunning[i] = subImage(charSpriteRun, x * 114, y * 140, 114, 140);
        }
        charFrame = 0;
    }

    public int getFrame(double d, int num_frames) {
        return (int) Math.floor(((getTime() / 1000.0 % d) / d) * num_frames);
    }

    // --------------------------------------------------------------------------------------------
    // Update Character
    // --------------------------------------------------------------------------------------------
    public void updateChar(double dt) {
        // Select Character frame
        charFrame = getFrame(0.5, 16);

        charVX = 0;  // Set horizontal velocity

        if (left) {  // Moving left
            charVX -= 150;
        }
        if (right) {  // Moving right
            charVX += 150;
        }
        charVY += 400 * dt; // Add in Gravity

        // Move Character
        charX += charVX * dt;
        charY += charVY * dt;
        charFalling = true; // Set Character Falling

        // Check Bottom Screen Boundary
        if (charY >= ((height - 60) - (charH / 2))) {
            charVY = 0;     // Change velocity to zero
            charY = ((height - 60) - (charH / 2)); // Reset character to bottom of display
            charFalling = false;
        }
        // Check Top Screen Boundary
        if (charY <= (charH / 2)) {
            charVY = 0;     // Change velocity to zero
            charY = charH / 2; // Reset character to top of screen
            charFalling = true; // Character is falling
        }
        // Check Left Screen Boundary
        if (charX <= (charW / 2)) {
            charX = charW / 2;  // Reset character to left screen
        }
        // Check Right Screen Boundary
        if (charX >= width - (charW / 2)) {
            charX = width - (charW / 2); // Reset character to right screen
        }
        // Calculate the Edges of the Character
        double charLeft = charX - charW / 2;
        double charRight = charX + charW / 2;
        double charTop = charY - charH / 2;
        double charBottom = charY + charH / 2;

        // Calculate platform Collisions
        for (int i = 0; i < numPlatforms; i++) { // For All Platforms
            // Calculate edges of the platform
            double platformLeft = platformX[i];
            double platformRight = platformX[i] + tileWidth * 5;
            double platformTop = platformY[i];
            double platformBottom = platformY[i] - tileHeight;

            // Check for character and platform collision
            if ((charLeft < platformRight) && (charRight > platformLeft) &&
                    (charTop < platformBottom) && (charBottom > platformTop)) {
                // Process Collision
                double tx = 1000000;
                double ty = 1000000;

                // Check if character is moving Left
                if (charVX < 0) {
                    // Calculate the time since collision in X
                    tx = (charLeft - platformRight) / charVX;
                } else if (charVX > 0) {
                    // Calculate the time since collision in X
                    tx = (charRight - platformLeft) / charVX;
                }

                // Check if character is moving Up
                if (charVY < 0) {
                    // Calculate the time since collision in Y
                    ty = (charTop - platformBottom) / charVY;
                } else if (charVY > 0) {
                    // Calculate the time since collision in Y
                    ty = (charBottom - platformTop) / charVY;
                }

                // Work out which collision to apply
                if (tx < ty) {  // Horizontal collision first
                    if (charVX < 0) {
                        // Stop character movement
                        charVX = 0;
                        // Adjust character position
                        charX = platformRight + charW / 2;
                    } else {
                        // Stop the character from moving
                        charVX = 0;
                        // Adjust character position
                        charX = platformLeft - charW / 2;
                    }
                } else {
                    if (charVY < 0) {  // Vertical collision first
                        // Stop character movement
                        charVY = 0;
                        // Adjust character position
                        charY = platformBottom + charH / 2;
                    } else {
                        // Stop character movement
                        charVY = 0;
                        // Adjust character position
                        charY = platformTop - charH / 2;
                        charFalling = false;
                    }
                }
            }
        }
    }

    // --------------------------------------------------------------------------------------------
    // Space bar - Jump Action
    // --------------------------------------------------------------------------------------------
    public void jumpChar() {
        if (charFalling != true) { // If Character is not falling
            charVY = -300;  // Set character velocity
            charFalling = true;
        }
    }

    // --------------------------------------------------------------------------------------------
    // Draw Character
    // --------------------------------------------------------------------------------------------
    public void drawChar() {

        // Draw character standing
        drawImage(charStandingImage, charX - charW / 2, charY - charH / 2, charW, charH);

        // Draw animated character images
        if (charFalling) { // Draw falling images
            if (charVX > 0) {  // Character falling right
                drawImage(charFallingImage, charX - charW / 2, charY - charH / 2, charW, charH);
            } else if (charVX < 0) {  // Character falling left
                drawImage(charFallingImage, charX + charW / 2, charY - charH / 2, -charW, charH);
            } else {  // Character falling straight down
                drawImage(charFallingImage, charX - charW / 2, charY - charH / 2, charW, charH);
            }
        } else if (charVX != 0) {  // Draw running images
            if (charVX > 0) {  // Character moving right
                drawImage(charRunning[charFrame], charX - charW / 2, charY - charH / 2, charW, charH);
            } else if (charVX < 0) {  // Character moving left
                drawImage(charRunning[charFrame], charX + charW / 2, charY - charH / 2, -charW, charH);
            } else {  // Draw character Standing
                drawImage(charStandingImage, charX - charW / 2, charY - charH / 2, charW, charH);
            }
        }
        drawImage(spikeImage, spikeX, spikeY, tileWidth, tileHeight);
    }
    // --------------------------------------------------------------------------------------------
    // Initialise the game
    // --------------------------------------------------------------------------------------------
    public void init() {
        // Initialise Window
        setWindowSize(width, height);
        backgroundImage = loadImage("Sidescroll.png");

        // Load Sprite Sheets
        itemSheet = loadImage("ItemSpritesheet.png");

        // Initialise Tile Map array
        initMap();

        // Initialise Platforms
        initPlatforms();

        // Initialise Items
        initItems();

        // Initialise Enemies
        initEnemies();

        // Initialise Score
        initScore();

        // Initialise Character
        initChar();
    }
    // --------------------------------------------------------------------------------------------
    // Update the game
    // --------------------------------------------------------------------------------------------

    public void update(double dt) {

        // Update Character
        updateChar(dt);

        // Update Enemies
        updateEnemies();

        // Update Items
        updateItems();

        // Update Score
        updateScore();

        // Update Level
        // updateLevel(){
    }

    // --------------------------------------------------------------------------------------------
    // Paint the Screen
    // --------------------------------------------------------------------------------------------

    public void paintComponent() {
        // Clear the background to white * paint background image
        changeBackgroundColor(white);
        clearBackground(width, height);
        drawImage(backgroundImage, 0, 0, width, height);

        // Draw Platforms
        drawPlatforms();

        // Draw Items
        drawItems();

        // Draw Enemies
        drawEnemies();

        // Draw Character
        drawChar();

        // Draw Score
        drawScore();
        changeColor(yellow);
        drawCircle(charX,charY,4);
        changeColor(green);
        drawCircle(redDX+15,redDY+15,15);
    }

    // --------------------------------------------------------------------------------------------
    // Game Input
    // --------------------------------------------------------------------------------------------
    // Keyboard

    // Key pressed
    public void keyPressed(KeyEvent event) {
        // Left Arrow
        if (event.getKeyCode() == KeyEvent.VK_LEFT) {
            left = true;
        }
        // Right Arrow
        if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = true;
        }
        // Space Bar
        if (event.getKeyCode() == KeyEvent.VK_SPACE) {
            jumpChar();
        }
    }

    // KeyReleased
    public void keyReleased(KeyEvent event) {
        // Left Arrow
        if (event.getKeyCode() == KeyEvent.VK_LEFT) {
            left = false;
        }
        // Right Arrow
        if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = false;
        }
    }
}
		