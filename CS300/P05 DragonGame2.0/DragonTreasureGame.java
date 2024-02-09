import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import processing.core.PApplet;
import processing.core.PImage;
import java.io.File;

public class DragonTreasureGame extends PApplet {

    private ArrayList<Room> roomList; // ArrayList of rooms in teh game, to be initialized in setup
    private File roomInfo; // File containing all the information about each room
    private File mapInfo; // File containing map details
    private ArrayList<Character> characters; // ArrayList of characters in the game
    private boolean isDragonTurn = false; // Boolean to indicate if it is the dragon's turn, false 
                                          // meaning it is not the dragon's turn, vice versa
    private int gameState = 0; // gameState 0 indicates game continues, 1 indicates player won, 2 
                               // indicates player has lost
    
    /**
     * Overrides settings method in PApplet to set window of width 800 and height 600
     */
    @Override
    public void settings() {
        size(800, 600);
    }

    /**
     * Overrides setup method in PApplet to perform the basic setup of the game window. This 
     * includes setting the title, drawing of images, formatting of texts, initializing roomList, 
     * loading the roominfo and mapinfo files, setting the background for the treasure room, 
     * loading the portal image, roominfo, and mapinfo, initializing characters, and loading 
     * the characters of the game.
     */
    @Override
    public void setup() {
        this.getSurface().setTitle("Dragon Treasure Adventure"); // sets the title of the window
        this.imageMode(PApplet.CORNER); //Images are drawn using the x,y-coordinate
        //as the top-left corner
        this.rectMode(PApplet.CORNERS); //When drawing rectangles interprets args
        //as top-left corner and bottom-right corner respectively
        this.focused = true; // window will be active upon running program
        this.textAlign(CENTER); // sets the text alignment to center
        this.textSize(20); //sets the font size for the text
        
        roomList = new ArrayList<Room>();
        Room.setProcessing(this);
        File roomInfoFile = new File("roominfo.txt");
        File mapInfoFile = new File("map.txt");
        roomInfo = roomInfoFile;
        mapInfo = mapInfoFile;
        TreasureRoom.setTreasureBackground(loadImage("/Users/ethanyan01/Desktop/P05 DragonGame2.0/"
                +"images/treasure.jpg"));
        PortalRoom.setPortalImage(loadImage("/Users/ethanyan01/Desktop/P05 DragonGame2.0/"
                +"images/portal.png"));
        loadRoomInfo();
        loadMap();

        characters = new ArrayList<Character>();
        loadCharacters();
    }

    /**
     * Adds the characters involved in the game into the characters ArrayList
     */
    private void loadCharacters() {
        System.out.println("Adding characters...");
        characters.add(new Character(getRoomByID(5),"KEYHOLDER"));
        characters.add(new Player(getRoomByID(1)));
        characters.add(new Dragon(getRoomByID(9)));
    }

    /** Loads in room info using the file stored in roomInfo
     *  @author Michelle 
     */
    private void loadRoomInfo() {
    System.out.println("Loading rooms...");
    Scanner fileReader = null;
    try {
        
        //scanner to read from file
        fileReader= new Scanner(roomInfo);
        
        //read line by line until none left
        while(fileReader.hasNext()) {
        String nextLine = fileReader.nextLine();
        
        //parse info and create new room 
        String[] parts = nextLine.split(" \\| ");
        int ID = Integer.parseInt(parts[1].trim()); //get the room id
        String imageName = null;
        String description = null;
        PImage image = null;
        Room newRoom = null;
        
        if(parts.length >= 3) {
            imageName = parts[2].trim();
            image = this.loadImage("images" + File.separator + imageName);
            
        }
        
        if(parts.length == 4) {
            description = parts[3].trim(); //get the room description
        }

        switch(parts[0].trim()) {
            case "S":
            newRoom = new StartRoom(ID, image);
            break;
            case "R":
            newRoom = new Room(ID, description, image);
            break;
            case "P":
            newRoom = new PortalRoom(ID, description, image);
            break;
            case "T":
            newRoom = new TreasureRoom(ID);
            break;
            default:
            break;
        }  
        
        if(newRoom != null) {
            roomList.add(newRoom);
        }
        }
    }catch(IOException e) { //handle checked exception
        e.printStackTrace();
    }finally {
        if(fileReader != null)
        fileReader.close(); //close scanner regardless of what happened for security reasons :)
    }
    }

    /** Loads in room connections using the file stored in mapInfo
     *  @author Michelle 
     */
    private void loadMap() {
    System.out.println("Loading map...");
    Scanner fileReader = null;
    try {
            //scanner to read from file
            fileReader= new Scanner(mapInfo);
            
        //read line by line until none left
            while(fileReader.hasNext()) {
            
            //parse info
            String nextLine = fileReader.nextLine();
            String parts[] = nextLine.split(" ");
            int id = Integer.parseInt(parts[0]);
            
            Room toEdit = getRoomByID(id); //get the room we need to update info for adjacent rooms
            
            //add all the rooms to the adj room list of toEdit
            for(int i=1; i<parts.length; i++) {
                Room toAdjAdd = getRoomByID(Integer.parseInt(parts[i]));
                toEdit.addToAdjacentRooms(toAdjAdd);
            }
            }
        }catch(IOException e) { //handle checked exception
            e.printStackTrace();
        }finally {    //close scanner regardless of what happened for security reasons :)
            if(fileReader != null)
            fileReader.close();
        }
    }

    /**
     * Get the room objected associated with the given ID.
     * @param id the ID of the room to retrieve
     * @return the Room that corresponds to that id
     * @author Michelle
     */
    private Room getRoomByID(int id){
    int indexToEdit = roomList.indexOf(new Room(id,"dummy", null));
    Room toEdit = roomList.get(indexToEdit); 
    return toEdit;
    }

    /**
     * This method is for the overall game logic. It is a callback method which is utlized to be 
     * the gameplay loop.
     */
    public void draw() {
        int playerIndex = 0; // Index of the player character in the characters ArrayList
        int dragIndex = 0; // Index of the dragon character in the characters ArrayList
        int keyIndex = 0; // Index of the keyholder character in the characters ArrayList
        for (int i = 0; i < characters.size(); i ++) {
            if (characters.get(i) instanceof Player) {
                playerIndex = i; 
                characters.get(i).getCurrentRoom().draw(); // Draws the current room of the player
            }
            if (characters.get(i) instanceof Dragon) {
                dragIndex = i;
            }
            if (characters.get(i).getLabel().equals("KEYHOLDER")) {
                keyIndex = i;
            }
        }

        for (int i = 0; i < characters.get(playerIndex).getAdjacentRooms().size(); i ++) {
            // Checks if player near a portal room and prints portal warning if so
            if (characters.get(playerIndex).getAdjacentRooms().get(i) instanceof PortalRoom) {
                System.out.println(((PortalRoom) characters.get(playerIndex).getAdjacentRooms()
                        .get(i)).getPortalWarning());
            }
            // Checks if player is near a treasure room and prints treasure warning if so
            if (characters.get(playerIndex).getAdjacentRooms().get(i) instanceof TreasureRoom) {
                System.out.println(((TreasureRoom) characters.get(playerIndex).getAdjacentRooms()
                        .get(i)).getTreasureWarning());
            }
            // Checks if player is near dragon and prints dragon warning if so
            if (characters.get(playerIndex).getAdjacentRooms().get(i)
                    .equals(characters.get(dragIndex).getCurrentRoom())) {
                System.out.println(((Dragon) characters.get(dragIndex)).getDragonWarning());
            }
        }

        // Checks if player is in keyholder's room and obtains key if so
        if (characters.get(playerIndex).getCurrentRoom().equals(characters.get(keyIndex).getCurrentRoom())) {
            if (!((Player) characters.get(playerIndex)).hasKey()) {
                ((Player) characters.get(playerIndex)).obtainKey();
                System.out.println("KEY OBATINED");
            }
        }

        // Checks if player is in portal room and teleports player to a random adjacent room if so
        if (characters.get(playerIndex).getCurrentRoom() instanceof PortalRoom) {
            if(((Player) characters.get(playerIndex)).teleport()) {
                System.out.println(PortalRoom.getTeleportMessage());;
            }
        }

        /**
         *  Checks if it is the dragon's turn and if game is till running, changes dragon room to a 
         *  random room that is not a portal room
         */ 
        if (isDragonTurn == true && gameState == 0) {
            Room randomRoom = ((Dragon) characters.get(dragIndex)).pickRoom();
            ((Dragon) characters.get(dragIndex)).changeRoom(randomRoom);
            isDragonTurn = false;
        }

        // Checks if the player has won the game (ie has key and is in treasure room)
        if ((characters.get(playerIndex).getCurrentRoom() instanceof TreasureRoom) && ((Player) characters.get(playerIndex)).hasKey() == true ) {
            gameState = 1;
            System.out.println("You win!");
        }

        // Checks if player has lost the game (ie if player is in same room as the dragon)
        if (characters.get(playerIndex).getCurrentRoom().equals(characters.get(dragIndex).getCurrentRoom())) {
            gameState = 2;
            System.out.println("You lose!");
        }
    }

    /**
     * Overrides PApplet's keyPressed method to allow player to press key on keyboard to 
     * change room to the room with the ID of the key pressed
     */ 
    @Override
    public void keyPressed() {
        int playerIndex = 0;
        for (int i = 0; i < characters.size(); i ++) {
            if (characters.get(i) instanceof Player) {
                playerIndex = i;
                characters.get(i).getCurrentRoom().draw();
            }
        }

        int newID = Integer.parseInt(String.valueOf(key));
        if (gameState != 1 && gameState != 2 && gameState == 0) {
            if (((Player) characters.get(playerIndex)).changeRoom(getRoomByID(newID))) {
                isDragonTurn = true;
            } else {
                System.out.println("Pick a valid Room!");
            }
        }
    }

    // Main method to get the game running
    public static void main(String[] args) {
        PApplet.main("DragonTreasureGame");
    }
}