import java.util.Random;

public class Dragon extends Character implements Moveable{
    private Random randGen; //random num generator used for moving
    private static final String DRAGON_WARNING = "You hear a fire breathing nearby!\n";
    // Message when dragon is nearby
    private static final String DRAGON_ENCOUNTER = "Oh no! You ran into the fire" 
            + "breathing dragon!\n"; // Message when player encounters dragon in same room
    
    /**
     * Constructor for a Dragon object. Initializes all instance fields. 
     * The label should be "DRAGON" by default.
     * 
     * @param currentRoom               the room that the Dragon starts in
     * @throws IllegalArgumentException with a descriptive message if currentRoom is 
     *                                  not a TreasureRoom
     */
    public Dragon(Room currentRoom) {
        super(currentRoom, "DRAGON");
        if (!(currentRoom instanceof TreasureRoom)) {
            throw new IllegalArgumentException("Dragon starting room must be a Treasure Room!");
        }
    }

    /**
     * Moves the Dragon to the destination room.
     * 
     * @param destination the Room to change it to
     * @return            true if the change was successful, false otherwise
     */
    public boolean changeRoom(Room destination) {
        if (this.canMoveTo(destination)) {
            this.setCurrentRoom(destination);
        }

        if (this.getCurrentRoom().equals(destination)) {
            return true;
        }

        return false;
    }

    /**
     * Checks if the dragon can move to the given destination. 
     * A valid move is the destination not a PortalRoom.
     * 
     * @param destination  the room to check if the dragon can move towards
     * @return             true if they can, false otherwise
     */
    public boolean canMoveTo(Room destination) {
        if(this.getCurrentRoom().isAdjacent(destination)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Picks randomly ONCE an adjacent room to move into.
     * 
     * @return the room that this Dragon should try to move into
     */
    public Room pickRoom() {
        randGen = new Random();
        int random = randGen.nextInt(this.getAdjacentRooms().size());
        return this.getAdjacentRooms().get(random);
    }

    /**
     * Getter for DRAGON_WARNING.
     * 
     * @return the string for warning about a dragon being nearby.
     */
    public static String getDragonWarning() {
        return DRAGON_WARNING;
    }

    /**
     * Getter for DRAGON_ENCOUNTER.
     * 
     * @return the string for letting the player know they ran into the dragon.
     */
    public static String getDragonEncounter() {
        return DRAGON_ENCOUNTER;
    }
}
