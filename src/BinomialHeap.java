import java.util.LinkedList;
import java.util.*;

public class BinomialHeap extends Heap {

	private Node head;
	private LinkedList<Node> rootList;

	
	public  BinomialHeap() {
		
		this.head=null;
		
	}


	public void insert(int key) {

		BinomialHeap bh=new BinomialHeap();
		Node x=new Node(key);
		x.parent=null;
		x.child=null;
		x.siblings=null;
		x.degree=0;
		bh.head=x;
		Union(bh);
		
		
	}

	public BinomialHeap Union(BinomialHeap bh2) {
		BinomialHeap bh=new BinomialHeap();
		bh.head=binomialHeapMerge(this,bh2);
		
		if(bh.head==null)
			return bh;
		return null;
		
		
	}


	private Node binomialHeapMerge(BinomialHeap binomialHeap, BinomialHeap bh2) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void delete(int key) {
		decrease(key,-9999999);
		extractMin();
	}

	@Override
	public Node extractMin() {
		
		//Remove the minimum element from rootlist of the heap
		Node x=findMin();
		rootList.remove(x);
		
		//create a new heap 
		BinomialHeap bh=new BinomialHeap();
		
		//reverse the order of the childeren of x
		x.child.siblings.add(0, x.child);
		LinkedList<Node> childList=new LinkedList<Node>(x.child.siblings);
		Collections.reverse(childList);
		
		//set the head of the new heap as first element of the new heap
		bh.head=childList.getFirst();
		BinomialHeap unionHeap=Union(bh);
		this.head=unionHeap.head;
		this.rootList=unionHeap.rootList;
		return x;
		
	}

	@Override
	public Node findMin() {
		// TODO Auto-generated method stub
		return null;
		
		
	}

	@Override
	public void decrease(int key, int newKey) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void increase(int key, int newKey) {
		// TODO Auto-generated method stub
		
	}

	

}
