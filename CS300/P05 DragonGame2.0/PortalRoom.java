import java.util.Random;
import processing.core.PImage;

public class PortalRoom extends Room {
    private Random randGen; // random number generator for location picking
    private static final String PORTAL_WARNING = "You feel a distortion in space nearby.\n";
    // Portal Nearby mesage
    private static final String TELEPORT_MESSAGE = "The space distortion teleported"
            + " you to another room!\n"; // Teleport message when player has been teleported
    private static PImage portalImage; // image of a portal to be shown in all portal rooms

    /**
     * Constructor for a PortalRoom object. Initializes all instance data fields.
     * 
     * @param id          the ID that this PortalRoom should have
     * @param description the verbal description this PortalRoom should have
     * @param image       the image that should be used as a background when drawing this 
     *                    PortalRoom.
     */
    public PortalRoom(int ID, String description, processing.core.PImage image) {
        super(ID, description, image);
    }

    /**
     * Getter for PORTAL_WARNING.
     * 
     * @return the string for warning about a portal being nearby.
     */
    public static String getPortalWarning() {
        return PORTAL_WARNING;
    }

    /**
     * Getter for TELEPORT_MESSAGE.
     * 
     * @return the string for letting the player know they were teleported.
     */
    public static String getTeleportMessage() {
        return TELEPORT_MESSAGE;
    }

    /**
     * Picks an adjacent room at random for the player to teleport into.
     * 
     * @return The room that player should immediately be moved to
     */
    public Room getTeleportLocation() {
        randGen = new Random();
        int newRoom = randGen.nextInt(this.getAdjacentRooms().size());
        return this.getAdjacentRooms().get(newRoom);
    }

    /**
     * Draws this PortalRoom to the window by drawing the background image, a rectangle, 
     * some text, and the portal image.
     * 
     * @return The room that player should immediately be moved to
     */
    @Override
    public void draw() {
        super.draw();
        processing.image(portalImage, 325, 225);
    }   
    
    /**
     * Sets the portal image for the PortalRoom class.
     * 
     * @param portalImage the image to represent the portal
     */
    public static void setPortalImage(processing.core.PImage portalImage) {
        PortalRoom.portalImage = portalImage;
    }
}
