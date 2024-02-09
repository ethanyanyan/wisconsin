import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;

/**
 * This class allows multiple values to be associated with the same key by
 * grouping such duplicate values together in a list.
 * 
 * This class builds on top of the P1W2 HashtableMap implementation.
 * 
 * @author AlgorithmEngineer, courtesy of the CS400 course staff.
 */
public class HashtableWithDuplicateKeysAE<KeyType, ValueType> extends HashtableMap<KeyType, List<ValueType>>
		implements HashtableWithDuplicateKeysInterface<KeyType, ValueType> {

	int numberOfValues;

	// pass through constructors:

	public HashtableWithDuplicateKeysAE(int capacity) {
		super(capacity);
		numberOfValues = 0;
	}

	public HashtableWithDuplicateKeysAE() {
		super();
		numberOfValues = 0;
	}

	/**
	 * This putOne method adds a single value to the list of values associated with
	 * the specified key. This is different from the inherited put method, which
	 * assigns an entire List<ValueType> to the specified key.
	 * 
	 * @param key   is used to store and look up this mapping
	 * @param value is one more value that should be associated with given key
	 */
	public void putOne(KeyType key, ValueType value) {
		// if the key is already a part of this collection
		if (containsKey(key)) {
			List<ValueType> values = get(key);
			// and it doesn't already map to the specified value
			if (!values.contains(value))
				// then add this value to the list of values for the given key
				values.add(value);
		} else {
			// otherwise map this key to a new list containing this value
			List<ValueType> values = new ArrayList<>();
			values.add(value);
			put(key, values);
		}
		numberOfValues++; // and track the number of values added via this method
	}

	/**
	 * This removeOne method removes a single value from the list of values
	 * associated with the specified key. This is different from the inherited
	 * remove method, which removes the entire list of values mapped to from this
	 * key.
	 * 
	 * @param key   is the key that this value is being removed from
	 * @param value is the specific value that should be removed
	 */
	public void removeOne(KeyType key, ValueType value) {
		List<ValueType> values = get(key);
		values.remove(value);
		if (values.size() == 0)
			remove(key);
		numberOfValues--; // and track the number of values removed via this method
	}

	/**
	 * @return int number of values stored in this collection
	 */
	public int getNumberOfValues() {
		return numberOfValues;
	}

	// the following overrides are meant to help keep numberOfValues in sync

	@Override
	public void clear() {
		numberOfValues = 0;
		super.clear();
	}

	@Override
	public List<ValueType> remove(KeyType key) {
		numberOfValues -= this.get(key).size();
		return super.remove(key);
	}

	@Override
	public void put(KeyType key, List<ValueType> values) {
		try {
			numberOfValues -= this.get(key).size();
		} catch (NoSuchElementException e) {
		} // nothing to remove
		numberOfValues += values.size();
		super.put(key, values);
	}

}
