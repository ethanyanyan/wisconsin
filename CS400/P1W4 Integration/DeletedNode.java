// --== CS400 Project One File Header ==--
// Name: Ethan Yikai Yan
// CSL Username: eyy
// Email: eyyan@wisc.edu
// Lecture #: LEC 002 TR 13:00 - 14:15
// Notes to Grader: <any optional extra notes to your grader>

/**
 * This is a class that extends HashNode and represents a deleted node.
 * 
 * @author Ethan Yikai Yan
 */
public class DeletedNode extends HashNode {
    private static DeletedNode returnNode = null; // to be instantiated and returned as a 
                                                  // new UniqueDeletedNode

    /**
     * Constructor for a DeletedNode
     */
    private DeletedNode() {
        super(-1, -1);
    }

    /**
     * Helper method to help obtain a new UniqueDeletedNode
     * @return a new UniqueDeletedNode
     */
    public static DeletedNode getUniqueDeletedNode() {
        if (returnNode == null) {
            returnNode = new DeletedNode();
        }            
        return returnNode;
    }
}