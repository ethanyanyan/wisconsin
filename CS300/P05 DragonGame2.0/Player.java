public class Player extends Character implements Moveable {
    private boolean hasKey = false; // Determines if the Player is holding the key or not

    /**
     * Constructor for player object. The label should be "PLAYER" and not have a key by default.
     * 
     * @param currentRoom the room that the Character is located in
     * @throws IllegalArgumentException if the currentRoom is not a StartRoom
     */
    public Player(Room currentRoom) {
        super(currentRoom, "PLAYER");
        if (!(currentRoom instanceof StartRoom)) {
            throw new IllegalArgumentException("first room must be startRoom!");
        }
    }

    /**
     * Determines if the player has the key.
     * 
     * @return true if the player has the key, false otherwise
     */
    public boolean hasKey() {
        if (hasKey == true) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gives player the key.
     */
    public void obtainKey() {
        if (hasKey == false) {
            hasKey = true;
        }
    }

    /**
     * Moves the Player to the destination room.
     * 
     * @param destination the Room to change it to
     * @return true if the change was successful, false otherwise
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
     * Determines whether or not the player can move to the given destination room
     * 
     * @param destination  room the player wants to move to
     * @return  true if it is a valid movement, false otherwise
     */
    public boolean canMoveTo(Room destination) {
        if(this.getCurrentRoom().isAdjacent(destination)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the player needs to teleport and move them if needed.
     * 
     * @return  true if a teleport occurred, false otherwise
     */
    public boolean teleport() {
        if (this.getCurrentRoom() instanceof PortalRoom) {
            this.changeRoom(((PortalRoom) (this.getCurrentRoom())).getTeleportLocation());
            return true;
        }
        return false;
    }

    /**
     * Determines whether or not the given dragon is nearby. 
     * A dragon is considered nearby if it is in one of the adjacent rooms.
     * 
     * @param d the dragon to check if nearby
     * @return  true if the dragon is nearby, false otherwise
     */
    public boolean isDragonNearby(Dragon d) {
        Room dragonRoom = d.getCurrentRoom();
        if (this.getAdjacentRooms().contains(dragonRoom)) {
            return true;
        }
        return false;
    }

    /**
     * Determines whether or not a portal room is nearby. 
     * A portal room is considered nearby if it is one of the adjacent rooms.
     * 
     * @return  true if a portal room is nearby, false otherwise
     */
    public boolean isPortalNearby() {
        for(int i = 0; i < this.getCurrentRoom().getAdjacentRooms().size(); i++) {
            if (this.getCurrentRoom().getAdjacentRooms().get(i) instanceof PortalRoom) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines whether or not a treasure room is nearby. 
     * A treasure room is considered nearby if it is one of the adjacent rooms.
     * 
     * @return  true if a treasure room is nearby, false otherwise
     */
    public boolean isTreasureNearby() {
        for(int i = 0; i < this.getCurrentRoom().getAdjacentRooms().size(); i++) {
            if (this.getCurrentRoom().getAdjacentRooms().get(i) instanceof TreasureRoom) {
                return true;
            }
        }
        return false;
    }
}
