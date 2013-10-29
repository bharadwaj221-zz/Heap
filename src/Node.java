

public class Node {

	int key;
	public Node parent,child;
	public Node sibling;
	int degree;
	public Node(int key) {
		this.key=key;
	}
	
	Node()
	{
		//siblings.add(this);
	}

}
