// --== CS400 Project One File Header ==--
// Name: Ethan Yikai Yan
// CSL Username: eyy
// Email: eyyan@wisc.edu
// Lecture #: LEC 002 TR 13:00 - 14:15
// Notes to Grader: <any optional extra notes to your grader>

/**
 * This class is a Helper class to group key-value pairs in a single object
 * 
 * @author Ethan Yikai Yan
 */
public class HashNode<K,V> {
        
    private V value; // value associated to a key
    private K key; // key for a HashNode
    
    /**
     * 
     * @param key key to assign to a HashNode
     * @param value value associated to a key to be assigned to a HashNode
     */
    public HashNode(K key, V value) {
        this.value = value;
        this.key = key;
    }

    /**
     * Getter method for the HashNode's value
     * @return the HashNode's associated value
     */
    public V getValue() {
        return this.value;
    }

    /**
     * Getter method for the HashNode's key
     * @return the key representing the HashNode
     */
    public K getKey() {
        return this.key;
    }
}