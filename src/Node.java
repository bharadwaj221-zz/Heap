import java.util.LinkedList;


public class Node {

	int key;
	public Node parent,child;
	public LinkedList<Node> siblings;
	
	int degree;
	public Node(int key) {
		this.key=key;
	}
	
	Node()
	{
		//siblings.add(this);
	}

}
