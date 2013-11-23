
public abstract class Heap {
	int key; // element in current node
	int degree; // depth of the binomial tree having the current node as its root
	
	public Heap makeHeap()
	{
		return null;
	}
	public abstract void insert(int key);
	public abstract void delete(int key);
	public abstract int extractMin();
	public abstract int findMin();
	public abstract void updateKey(int old,int key);
	
	public abstract void displayHeap(String filename);
}
