
public abstract class Heap {
	
	public Heap makeHeap()
	{
		return null;
	}
	public abstract void insert(int key);
	public abstract void delete(Node x);
	public abstract Node extractMin();
	public abstract Node findMin();
	public abstract void decrease(Node x,int key);
	public abstract void increase(Node x,int key);
	public void displayHeap(String filename)
	{
		
	}
}
