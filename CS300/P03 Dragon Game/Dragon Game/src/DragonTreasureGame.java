import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * Class that sets up the Dragon Treasure Adventure Game and handles control flow and game logic
 * @author Michelle
 *
 */
public class DragonTreasureGame {
  
  private static ArrayList<Room> roomList; //list of rooms in the cave
  private static Dragon dragon; //instance of the dragon
  private static Player player; //instance of the player
  private static GameState currentGameState; //keeps track whether to keep playing or not
  
  /** 
   * Reads ands parses the information to generate and create the Rooms of the cave.
   * @param roomInfoFile The file that the room info should be read from
   */
  private static void loadRoomInfo(File roomInfoFile) {
    Scanner fileReader = null;
    try {
      
      //scanner to read from file
      fileReader= new Scanner(roomInfoFile);
      
      //read line by line until none left
      while(fileReader.hasNext()) {
        String nextLine = fileReader.nextLine();
        
        //parse info and create new room 
        String parts[] = nextLine.split(" \\| ");
        int id = Integer.parseInt(parts[0]); //get the room id
        String description = parts[1]; //get the room description
        Room newRoom = new Room(id, description); 
        
        if(parts.length==3) { //has a char at end, special type of room
        
          /* Switch statements are like if and else ifs but only work on a simple condition like someChar == 'A'*/
          switch(parts[2]){
            case "S":
              newRoom.setRoomType(RoomType.START);
              break;
            case "L":
              Room.assignTeleportLocation(id);
              break;
            case "P":
              newRoom.setRoomType(RoomType.PORTAL);
              break;
            case "T":
              newRoom.setRoomType(RoomType.TREASURE);
              break;
            default:
              break;
          }  
        }
        
        roomList.add(newRoom);
      }
    }catch(FileNotFoundException e) { //handle checked exception
      e.printStackTrace();
    }finally {
      if(fileReader != null)
        fileReader.close(); //close scanner regardless of what happened for security reasons :)
    }
   // System.out.println(roomList.toString()); //DEBUG line
  }
  
  /**
   * Searches the roomList arraylist to find the room that has the same ID.
   * You can assume that there will always be a match. 
   * @param id
   * @return The room from the arraylist that matches the given id
   */
  private static Room getRoomByID(int id){
    int indexToEdit = roomList.indexOf(new Room(id,"dummy"));
    Room toEdit = roomList.get(indexToEdit); 
    return toEdit;
  }
  
  /** 
   * Reads ands parses the information to set up the map by "connecting" rooms that are labeled 
   * to be adjacent to another.
   *
   * @param adjFile The file that the adjacent room info should be read from
   */
  private static void loadAdjacentRoomInfo(File adjFile) {
   Scanner fileReader = null;
    try {
          //scanner to read from file
          fileReader= new Scanner(adjFile);
          
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
        }catch(FileNotFoundException e) { //handle checked exception
          e.printStackTrace();
        }finally {    //close scanner regardless of what happened for security reasons :)
          if(fileReader != null)
            fileReader.close();
        }
    //System.out.println(roomList.toString()); //DEBUG line  
  }
  
  /**
   * Finds the room in the list that is the starting room. 
   * Create player object and places them there.
   */
  private static void placePlayerAtStart(){
	  
    //search for room that is START type
    for(int i=0; i<roomList.size(); i++) {
      Room r = roomList.get(i);
      
      if(r.getType() == RoomType.START) { //we found it, create the player and put them there
        player = new Player(r);
        return;
      }
    }
  }
  
  /**
   * Finds the room in the list that is the treasure room. 
   * Create dragon object and places them there.
   */
  private static void placeDragonAtTreasure(){
	  
    //search for room that is TREASURE type
    for(int i=0; i<roomList.size(); i++) {
      Room r = roomList.get(i);
      
      if(r.getType() == RoomType.TREASURE) { //we found it, create the dragon and put them there
        dragon = new Dragon(r);
        return;
      }
    }
  }

  /** Sets up the game by populating the rooms, player, dragon, and initial game state
   * 
   * @param roomInfoFile File The file that the room info should be read from
   * @param adjFile File The file that the adjacent room info should be read from
   */
  private static void setup(File roomInfoFile, File adjFile) {
    roomList = new ArrayList<Room>();
    
    loadRoomInfo(roomInfoFile);
    loadAdjacentRoomInfo(adjFile);
    
    placePlayerAtStart();
    placeDragonAtTreasure();
    
    currentGameState = GameState.CONTINUE;   
  } 
 
  /** Dragon takes its turn moving into a different room.
   */
  private static void dragonTurn() {dragon.changeRooms();}
  
  /**
   * Prints any warning based if dragon, portal, or treasure is nearby
   */
  private static void printWarnings(){
   
    if(player.isDragonNearby(dragon))
      System.out.println(Dragon.getDragonWarning());
    
    if(player.isPortalNearby())
      System.out.println(Room.getPortalWarning());
    
    if(player.isTreasureNearby())
      System.out.println(Room.getTreasureWarning());
  }
 
  /**
   * Handles player turn by prompting until getting a valid room to move into.
   * Then moves them.
   * Handles teleportation as well.
   * @param consoleInput Scanner object used to get input from the console
   * @author Michelle
   */
  private static void playerTurn(Scanner consoleInput){
	  
    //print out some info for the player
    System.out.println(player.getCurrentRoom());
    printWarnings();
    
    //prompt user for their movement
    System.out.print("Which room would you like to go to?");
    int response = consoleInput.nextInt();
    System.out.println(response);
    Room moveTo = getRoomByID(response);
    
    //keep prompting if not a valid movement
    while(!player.canMoveTo(moveTo)){
    	
      System.out.println("Not a valid room");
      System.out.print("Which room would you like to go to?");
      response = consoleInput.nextInt();
      System.out.println(response);
      System.out.println(getRoomByID(response));
      moveTo = getRoomByID(response);
    }
    
    //move the player
    player.changeRoom(moveTo);    
    
    //teleports player if they stepped into a portal room
    if(player.shouldTeleport()){
    	
      Room teleportLocation = getRoomByID(Room.getTeleportationRoom());
      player.changeRoom(teleportLocation);
      System.out.println("You stepped into a magical portal and were teleported to a new room!!!");
    }
  }
  
  /**
   * Updates the game state depending on the location of the player to either
   * continue, lose if same spot as dragon, win if in same spot as treasure room
   */
  private static void updateGameState(){
	  
    //in same room as dragon
    if(player.getCurrentRoom().equals(dragon.getCurrentRoom())) {
      currentGameState = GameState.LOSE;
      return;}
    
    //in treasure room
    if(player.getCurrentRoom().getType() == RoomType.TREASURE) {
      currentGameState = GameState.WIN;
      return;}
    
    //default game state
    currentGameState = GameState.CONTINUE;
    return;
    
  }
  
  /**
   * Continues the process of alternating between player and dragon turns
   * until the game is over.
   */
  private static void gamePlayLoop() {
    Scanner consoleInput = new Scanner(System.in);
    
    //while game is to continues 
    while(currentGameState == GameState.CONTINUE) {
      playerTurn(consoleInput);
      
      dragonTurn();
     // System.out.println("Dragon Location:" +dragon.getCurrentRoom()); //Can comment back in to see where they are
      
      updateGameState();
    }
    
    consoleInput.close(); //close it when done for security reasons! :)  
  }
  
  /**Depending on the end state of the game print out a different message on 
   * whether they won or lost.
   * @author Michelle
   */
  private static void printResults() {
    if(currentGameState == GameState.LOSE)
      System.out.println("[LOSE] You were charred to a crisp by the dragon! :(");
    
    if(currentGameState == GameState.WIN)
      System.out.println("[WIN] Hooray! You found the dragon's treasure! :)");
  }
  
  
  public static void main(String[] args) {
    setup(new File("roominfo.txt"), new File("map.txt"));
    gamePlayLoop();
    printResults();
  }
  
}
