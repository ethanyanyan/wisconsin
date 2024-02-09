// DO NOT SUBMIT THIS CLASS TO GRADESCOPE
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

import processing.core.PApplet;
import processing.core.PImage;
import processing.sound.SoundFile;

/**
 * This class implements a graphic GUI application for the cs300 Fall 2022 p09 Chugidex program
 * 
 * @author Kendall and Ashley
 *
 */
public class ChugidexGUI extends PApplet {

  private final int CHUGIMON_IMG_WIDTH = 240;
  private final int CHUGIMON_IMG_HEIGHT = 240;
  private final int SCREEN_WIDTH = CHUGIMON_IMG_WIDTH;
  private final int SCREEN_HEIGHT = CHUGIMON_IMG_HEIGHT;
  private final int SCREEN_CORNER_RADIUS = 10;
  private final int BEZEL_WIDTH = 300;
  private final int BEZEL_HEIGHT = 300;
  private final int BEZEL_CORNER_RADIUS = 10;
  private final int DPAD_MAJOR_AXIS = 90;
  private final int DPAD_MINOR_AXIS = 30;
  private final int DPAD_CORNER_RADIUS = 5;
  private final int DPAD_OFFSET = 120;
  private final int ORB_OFFSET = 50;
  private final int ORB_DIAMETER = 60;
  private final int ORB_BORDER_WEIGHT = 8;
  private final int MINISCREEN_CORNER_X = 90;
  private final int MINISCREEN_CORNER_Y = 480;
  private final int MINISCREEN_WIDTH = 150;
  private final int MINISCREEN_HEIGHT = 90;
  private final int MINISCREEN_CORNER_RADIUS = 5;
  private final int MINISCREEN_TEXT_OFFSET = 5;
  
  private SoundFile buttonSound;

  private final int RED = color(207, 48, 86);
  private final int WHITE = color(238, 252, 234);
  private final int GRAY = color(140, 148, 164);
  private final int BLUE_WHITE = color(210, 254, 254);
  private final int GREEN = color(37, 183, 0);
  private final int YELLOW = color(245, 192, 13);
  private final int BLACK = color(0, 13, 43);
  private final int BLUE = color(48, 226, 251);

  private final boolean SOUND_ON = false;

  private PImage textureMap;
  private Chugidex chugidex;
  private HashMap<Integer, PImage> imageMap;

  /**
   * Sets the size of the display window of this graphic application
   */
  @Override
  public void settings() {
    size(400, 600);
  }


  /**
   * Defines the initial environment properties of this graphic application, loads media such as
   * images and fonts as the program starts.
   */
  @Override
  public void setup() {
    this.getSurface().setTitle("Chugidex"); // sets the title of the window
    this.imageMode(PApplet.CORNER); // Images are drawn using the x,y-coordinate
    // as the top-left corner
    // this.rectMode(PApplet.CORNERS); // When drawing rectangles interprets args
    // as top-left corner and bottom-right corner respectively
    this.focused = true; // window will be active upon running program
    this.textSize(20); // sets the font size for the text

    try {
      this.chugidex = new Chugidex();
    } catch (Exception e) {
      e.printStackTrace();
      this.exit();
    }
    this.imageMap = new HashMap<Integer, PImage>();

    if (SOUND_ON) {
      SoundFile opening = new SoundFile(this, "sounds" + File.separator + "opening.mp3");
      opening.loop();
      buttonSound = new SoundFile(this, "sounds" + File.separator + "button.mp3");
    }
  }

  /**
   * Executes each time a key is released after being pressed
   */
  @Override
  public void keyReleased() {
    if (key == CODED && keyCode == RIGHT) {
      if (SOUND_ON) {
        buttonSound.play();
      }
      this.chugidex.nextChugimon();
    } else if (key == CODED && keyCode == LEFT) {
      if (SOUND_ON) {
        buttonSound.play();
      }
      this.chugidex.previousChugimon();

    }
  }

  /**
   * Continuously updates the contents of the display window
   */
  public void draw() {
    drawBackground();
    drawChugimon(this.chugidex.currentChugimon());
    drawChugimonDescription(this.chugidex.currentChugimon());
  }

  private int getChugimonKey(Chugimon chugi) {
    // https://en.wikipedia.org/wiki/Pairing_function#Cantor_pairing_function
    return ((chugi.getFirstID() + chugi.getSecondID())
        * (chugi.getFirstID() + chugi.getSecondID() + 1) / 2) + chugi.getSecondID();
  }

  private PImage getChugimonImage(Chugimon chugi) {
    int chugiKey = getChugimonKey(chugi);
    if (this.imageMap.containsKey(chugiKey)) {
      return this.imageMap.get(chugiKey);
    }
    PImage chugiImg =
        this.loadImage("./images/" + chugi.getFirstID() + "." + chugi.getSecondID() + ".png");
    this.imageMap.put(chugiKey, chugiImg);
    return chugiImg;
  }

  private void drawChugimon(Chugimon chugi) {
    PImage chugiImage = getChugimonImage(chugi);
    this.image(chugiImage, width / 2 - SCREEN_WIDTH / 2, height / 2 - SCREEN_HEIGHT / 2,
        SCREEN_WIDTH, SCREEN_HEIGHT);
  }

  private void drawChugimonDescription(Chugimon chugi) {
    this.textAlign(LEFT, TOP);
    this.textSize(24);
    this.textLeading(26);
    fill(BLACK);
    String nameText = chugi.getName();
    String idText = "#" + chugi.getFirstID() + "." + chugi.getSecondID() + "";
    if (chugi.getFirstID() == chugi.getSecondID()) {
      nameText = "MissingNo.";
      idText = "#0";
    }
    String heightWeightText = String.format("%.1f", chugi.getHeight()) + "m "
        + String.format("%.1f", chugi.getWeight()) + " kg";
    String textToPrint = nameText + "\n" + idText + "\n" + heightWeightText;
    text(textToPrint, MINISCREEN_CORNER_X + MINISCREEN_TEXT_OFFSET,
        MINISCREEN_CORNER_Y + MINISCREEN_TEXT_OFFSET);
  }

  private void drawBackground() {
    this.background(RED);
    drawOrb();
    drawScreen();
    drawDPad();
    drawMinisreen();
  }

  private void drawOrb() {
    stroke(WHITE);
    strokeWeight(ORB_BORDER_WEIGHT);
    this.fill(BLUE);
    this.circle(ORB_OFFSET, ORB_OFFSET, ORB_DIAMETER);
  }

  private void drawScreen() {
    this.noStroke();
    this.fill(WHITE);
    this.rect(width / 2 - BEZEL_WIDTH / 2, height / 2 - BEZEL_HEIGHT / 2, BEZEL_WIDTH, BEZEL_HEIGHT,
        SCREEN_CORNER_RADIUS);

    this.fill(BLACK);
    this.rect(width / 2 - SCREEN_WIDTH / 2, height / 2 - SCREEN_HEIGHT / 2, SCREEN_WIDTH,
        SCREEN_HEIGHT, BEZEL_CORNER_RADIUS);
  }

  private void drawDPad() {
    this.noStroke();
    this.fill(BLACK);
    this.rect(width - DPAD_OFFSET + DPAD_MINOR_AXIS, height - DPAD_OFFSET, DPAD_MINOR_AXIS,
        DPAD_MAJOR_AXIS, DPAD_CORNER_RADIUS);
    this.rect(width - DPAD_OFFSET, height - DPAD_OFFSET + DPAD_MINOR_AXIS, DPAD_MAJOR_AXIS,
        DPAD_MINOR_AXIS, DPAD_CORNER_RADIUS);
  }

  private void drawMinisreen() {
    this.noStroke();
    this.fill(GREEN);
    this.rect(MINISCREEN_CORNER_X, MINISCREEN_CORNER_Y, MINISCREEN_WIDTH, MINISCREEN_HEIGHT,
        MINISCREEN_CORNER_RADIUS);
  }

  /**
   * Launches the application
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    PApplet.main("ChugidexGUI");
  }

}
