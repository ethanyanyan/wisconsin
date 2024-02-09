/**
 * This class contains the implementation of the Min Heap data structure
 */
public class MinHeap implements MinHeapInterface {

	public int heapSize = 0; //Size of the heap

	public HeapNode[] heapArray; // Array which stores the actual nodes

	/**
	 * Constructor for the MinHeap data structure
	 */
	public MinHeap() {
		heapArray = new HeapNode[1];
	}

	/**
	 * Method to remove all nodes in the MinHeap and reset it
	 */
	public void removeAll() {
		heapSize = 0;
		heapArray = new HeapNode[1];
	}

	/**
	 * Method to check if heap is empty
	 */
	public boolean isEmpty() {

		if (heapSize == 0)
			return true;
		else
			return false;
	}

	/**
	 * Method to insert a node into the minHeap
	 */
	public void insert(Item item) {
		HeapNode heapNode = new HeapNode(item);
		insertNode(heapNode);
	}

	/**
	 * Internal insert helper method containing the insertion logic. 
	 * Called by the two external insert functions
	 * @param heapNode Node to be inserted
	 */
	private void insertNode(HeapNode heapNode) {
		if (heapSize > 0 && heapSize == heapArray.length) {
			expandHeap();
		}
		int i = heapSize;
		heapSize++;
		heapArray[i] = heapNode;

		while (i != 0 && getParentNode(i).key >= heapArray[i].key) {

			if (getParentNode(i).key == heapArray[i].key) {
                // break tie-breakers by name
				if (getParentNode(i).rbNode.getName().compareTo(heapArray[i].rbNode.getName()) > 0) {
										
					int parentIndex = (i - 1) / 2;
					swap(i, parentIndex);
					i = parentIndex;
				}
				else
				{
					break;
				}

			} else {
				int parentIndex = (i - 1) / 2;
				swap(i, parentIndex);
				i = parentIndex;
			}
		}

	}

	/**
	 * Helper method which returns the parent of a specified node
	 * @param index index of the parent node in the heapArray
	 * @return HeapNode of the parent
	 */
	private HeapNode getParentNode(int index) {
		int parentIndex = (index - 1) / 2;
		return heapArray[parentIndex];
	}

	/**
	 * Helper method which swaps two nodes in the min heap
	 * @param index1 first node's index 
	 * @param index2 second node's index
	 */
	private void swap(int index1, int index2) {
		HeapNode temp = heapArray[index1];
		heapArray[index1] = heapArray[index2];
		heapArray[index2] = temp;
	}

	/**
	 * Helper method which doubles the size of the heap when it becomes full
	 */
	private void expandHeap() {
		HeapNode[] tempHeap = new HeapNode[heapArray.length * 2];
		System.arraycopy(heapArray, 0, tempHeap, 0, heapArray.length);
		heapArray = tempHeap;
	}

	/**
	 * Heapify helper method to ensure that the min heap properties are maintained after 
	 * modifications are made to the heap
	 * @param index Node's index to be heapified from
	 */
	private void heapify(int index) {
		int left = index * 2 + 1;
		int right = index * 2 + 2;

		int smallest = index;

		if (left < heapSize && heapArray[left].key <= heapArray[index].key) {
			
			if(heapArray[left].key == heapArray[index].key)
			{
				if(heapArray[left].rbNode.getName().compareTo(heapArray[index].rbNode.getName()) < 0)
					smallest = left;
			}
			else 
				smallest = left;
				
		}
		if (right < heapSize && heapArray[right].key <= heapArray[smallest].key) {
			
			
			if(heapArray[right].key == heapArray[smallest].key)
			{
				if(heapArray[left].rbNode.getName().compareTo(heapArray[index].rbNode.getName()) > 0)
					smallest = right;
			}
			else 
				smallest = right;
		}
		if (smallest != index) {
			swap(index, smallest);
			heapify(smallest);
		}
	}

	/**
	 * Method to extract the minimum key value node of the MinHeap
	 */
	public Item getMin() {

		if (heapSize == 1) {
			HeapNode min = heapArray[0];
			heapSize--;
			heapArray[0] = null;
			return min.getItem();
		} else {
			HeapNode min = heapArray[0];
			heapArray[0] = heapArray[heapSize - 1];
			heapArray[heapSize - 1] = null;
			heapSize--;
			heapify(0);

			return min.getItem();
		}
	}

	/**
	 * toString method to print out the toLevel string of the Heap
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < heapSize; i++) {
			sb.append(heapArray[i].getItem().getName());
			if (i != heapSize - 1) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}

}