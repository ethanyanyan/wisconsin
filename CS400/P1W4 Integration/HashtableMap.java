// --== CS400 Project One File Header ==--
// Name: Ethan Yikai Yan
// CSL Username: eyy
// Email: eyyan@wisc.edu
// Lecture #: LEC 002 TR 13:00 - 14:15
// Notes to Grader: <any optional extra notes to your grader>

import java.util.NoSuchElementException;

/**
 * This is a class that implements a MapADT in the form of a HashtableMap holding Key-Value pairs 
 * in the form of HashNodes. This HashtableMap has a loadFactor of >= 0.7 and would undergo
 * rehashing if that threshold is exceeded.
 * 
 * @author Ethan Yikai Yan
 */
public class HashtableMap<KeyType,ValueType> implements MapADT<KeyType, ValueType>{

    private int capacity; // Max capacity of the HashtableMap
    private int size; // Number of elements currently int the HashtableMap

    protected HashNode<KeyType,ValueType>[] map; // array of type HashNode to store key-value pairs
    static final double loadFactor = 0.7; // loadFactor to determine when rehashing is needed

    /**
     * Constructor for a HashtableMap with empty map
     * @param capacity maximum number of HashNodes map can store
     */
    public HashtableMap(int capacity) {
        this.capacity = capacity;

        @SuppressWarnings("unchecked")
        HashNode<KeyType,ValueType>[] array = 
                (HashNode<KeyType,ValueType>[]) new HashNode[this.capacity];
        this.map = array;
        for (int i = 0; i < capacity; i ++) {
            map[i] = null; // Initialize map to be all null
        }
    }
    
    /**
     * Constructor for a HashtableMap with empty map with a default capacity of 8
     */
    public HashtableMap() { 
        this.capacity = 8;

        @SuppressWarnings("unchecked")
        HashNode<KeyType,ValueType>[] array = 
                (HashNode<KeyType,ValueType>[]) new HashNode[this.capacity];
        this.map = array;
        for (int i = 0; i < capacity; i ++) {
            map[i] = null; // Initialize map to be all null
        }
    }

    // add a new key-value pair/mapping to this collection
    // throws exception when key is null or duplicate of one already stored
    public void put(KeyType key, ValueType value) throws IllegalArgumentException {
        // Key cannot be null
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        // No duplicate keys
        if (containsKey(key)) {
            throw new IllegalArgumentException("Duplicate key found");
        }

        // Initialize hashIndex of the key-value pair to insert
        int hashIndex = key.hashCode() % this.capacity;
        if (hashIndex < 0) {
            hashIndex = hashIndex + capacity;
        }
        // Use as a temp value to store initial insert index
        int initialInsertIndex = -1;
        int deletedEntryIndex = -1;

        // Loop through array to find empty spot that does not have node and never had a node
        while (hashIndex != initialInsertIndex) {
            if (initialInsertIndex == -1) {
                initialInsertIndex = hashIndex;
            }
            if (map[hashIndex] != null) {
                // Check if occupied space is a deleted HashNode
                if (map[hashIndex].getKey().equals(-1) && map[hashIndex].getValue().equals(-1)) {
                    deletedEntryIndex = hashIndex;
                    break;
                }
                // Else, move on and find empty spot
                hashIndex = (hashIndex + 1) % capacity;
            }
            else {
                break;
            }
        }
        if (deletedEntryIndex != -1) {
            map[deletedEntryIndex] = new HashNode<>(key, value);
        } else {
            map[hashIndex] = new HashNode<>(key, value);
        }
        this.size++;

        // rehash if current load exceeds loadfactor
        double currLoad = (1.0*size)/capacity;
        if (currLoad >= loadFactor) {
            rehash();
        }
    }

    /**
     * Private helper method to implement rehashing when loadFactor is exceeded
     */
    private void rehash() {
  
        // The present bucket list is made temp
        HashNode<KeyType,ValueType>[] oldMap = this.map;
 
        // New bucketList of double the old size is created
        this.map = (HashNode<KeyType,ValueType>[]) new HashNode[2 * this.capacity];
 
        for (int i = 0; i < 2 * this.size ; i++) {
            // Initialised to null
            this.map[i] = null;
        }
        // Now size is made zero and we loop through all
        // the nodes in the original bucket list(temp) and insert it into the new list
        this.size = 0;
        this.capacity = this.capacity * 2;
 
        for (int i = 0; i < oldMap.length; i++) {
            if (oldMap[i] != null) {
                if (oldMap[i].getKey() != null) {
                    this.put(oldMap[i].getKey(), oldMap[i].getValue());
                }
            }
        }
 
    }

    // check whether a key maps to a value within this collection
    public boolean containsKey(KeyType key) {
        boolean returnBool = false;
        int hashIndex = key.hashCode() % this.capacity;
        if (hashIndex < 0) {
            hashIndex = hashIndex + capacity;
        }
        while (this.map[hashIndex] != null) {
            if (this.map[hashIndex].getKey().equals(key)) {
                return true;
            }
            hashIndex = (hashIndex + 1) % capacity;
        }
        return returnBool;
    }

    // retrieve the specific value that a key maps to
    // throws exception when key is not stored in this collection
    public ValueType get(KeyType key) throws NoSuchElementException {
        ValueType returnValue = null;
        if (!containsKey(key)) {
            throw new NoSuchElementException("Key not in current map");
        }

        int hashIndex = key.hashCode() % this.capacity;
        if (hashIndex < 0) {
            hashIndex = hashIndex + capacity;
        }

        int initialIndex = -1;
        
        while (hashIndex != initialIndex) {
            if (initialIndex == -1) {
                initialIndex = hashIndex;
            }
            if (map[hashIndex] != null) {
                if (map[hashIndex].getKey().equals(key)) {
                    returnValue = map[hashIndex].getValue();
                    break;
                } else {
                    hashIndex = (hashIndex + 1) % capacity;
                }
            }
        }


        return returnValue;
    }

    // remove the mapping for a given key from this collection
    // throws exception when key is not stored in this collection
    public ValueType remove(KeyType key) throws NoSuchElementException {
        ValueType returnValue = null;
        if (!containsKey(key)) {
            throw new NoSuchElementException("Key not in current map");
        }

        int hashIndex = key.hashCode() % this.capacity;
        if (hashIndex < 0) {
            hashIndex = hashIndex + capacity;
        }

        int initialIndex = -1;
        while (hashIndex != initialIndex) {
            if (initialIndex == -1) {
                initialIndex = hashIndex;
            }
            if (map[hashIndex] != null) {
                if (map[hashIndex].getKey().equals(key)) {
                    returnValue = map[hashIndex].getValue();
                    break;
                } else {
                    hashIndex = (hashIndex + 1) % capacity;
                }
            }
        }

        map[hashIndex] = DeletedNode.getUniqueDeletedNode();
        this.size--;

        return returnValue;
    }

    // remove all key-value pairs from this collection
    public void clear() {
        for (int i = 0; i < this.capacity; i ++) {
            map[i] = null; // Set all to null
        }
        this.size = 0;
    }
    
    // retrieve the number of keys stored within this collection
    public int getSize() {
        return this.size;
    }

    // retrieve this collection's capacity (size of its underlying array)
    public int getCapacity() {
        return this.capacity;
    }
}
