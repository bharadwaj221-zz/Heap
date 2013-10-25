
public abstract class Heap {
	
	public Heap makeHeap()
	{
		return null;
	}
	public abstract void insert(int key);
	public abstract void delete(int key);
	public abstract Node extractMin();
	public abstract Node findMin();
	public abstract void decrease(int key, int newKey);
	public abstract void increase(int key, int newKey);
	public void displayHeap(String filename)
	{
		
	}
}
