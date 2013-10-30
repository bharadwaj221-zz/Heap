import java.io.*;

class BinomialHeap extends Heap {

	private BinomialHeapNode Nodes;
	private int size;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}


	public BinomialHeap() {
		Nodes = null;
		setSize(0);
	}

	// 2. Find the minimum key
	public int findMin() {
		if(Nodes==null)
			return -1;
		BinomialHeapNode minNode=Nodes.findMinNode();
		if(minNode==null)
			return -1;
		return minNode.key;
	}

	// 3. Unite two binomial heaps
	// helper procedure
	private void merge(BinomialHeapNode binHeap) {
		BinomialHeapNode temp1 = Nodes, temp2 = binHeap;
		while ((temp1 != null) && (temp2 != null)) {
			if (temp1.degree == temp2.degree) {

				BinomialHeapNode tmp = temp2;
				temp2 = temp2.sibling;
				tmp.sibling = temp1.sibling;
				temp1.sibling = tmp;
				temp1 = tmp.sibling;
			} else {
				if (temp1.degree < temp2.degree) {
					if ((temp1.sibling == null)
							|| (temp1.sibling.degree > temp2.degree)) {

						BinomialHeapNode tmp = temp2;
						temp2 = temp2.sibling;
						tmp.sibling = temp1.sibling;
						temp1.sibling = tmp;
						temp1 = tmp.sibling;
					} else {

						temp1 = temp1.sibling;
					}
				} else {
					BinomialHeapNode tmp = temp1;
					temp1 = temp2;
					temp2 = temp2.sibling;
					temp1.sibling = tmp;
					if (tmp == Nodes) {

						Nodes = temp1;
					} else {

					}
				}
			}
		}

		if (temp1 == null) {
			temp1 = Nodes;
			while (temp1.sibling != null) {

				temp1 = temp1.sibling;
			}
			temp1.sibling = temp2;
		} else {

		}
	}

	// another helper procedure
	private void unionNodes(BinomialHeapNode binHeap) {
		merge(binHeap);

		BinomialHeapNode prevTemp = null, temp = Nodes, nextTemp = Nodes.sibling;

		while (nextTemp != null) {
			if ((temp.degree != nextTemp.degree)
					|| ((nextTemp.sibling != null) && (nextTemp.sibling.degree == temp.degree))) {

				prevTemp = temp;
				temp = nextTemp;
			} else {
				if (temp.key <= nextTemp.key) {

					temp.sibling = nextTemp.sibling;
					nextTemp.parent = temp;
					nextTemp.sibling = temp.child;
					temp.child = nextTemp;
					temp.degree++;
				} else {
					if (prevTemp == null) {

						Nodes = nextTemp;
					} else {

						prevTemp.sibling = nextTemp;
					}
					temp.parent = nextTemp;
					temp.sibling = nextTemp.child;
					nextTemp.child = temp;
					nextTemp.degree++;
					temp = nextTemp;
				}
			}


			nextTemp = temp.sibling;
		}
	}

	// 4. Insert a node with a specific value
	public void insert(int value) {
		if (value > 0) {
			BinomialHeapNode temp = new BinomialHeapNode(value);
			if (Nodes == null) {
				Nodes = temp;
				setSize(1);
			} else {
				unionNodes(temp);
				setSize(getSize() + 1);
			}
		}
	}

	// 5. Extract the node with the minimum key
	public int extractMin() {
		if (Nodes == null)
			return -1;

		BinomialHeapNode temp = Nodes, prevTemp = null;
		BinomialHeapNode minNode = Nodes.findMinNode();
		while (temp.key != minNode.key) {

			prevTemp = temp;
			temp = temp.sibling;
		}

		if (prevTemp == null) {

			Nodes = temp.sibling;
		} else {

			prevTemp.sibling = temp.sibling;
		}
		temp = temp.child;
		BinomialHeapNode fakeNode = temp;
		while (temp != null) {

			temp.parent = null;
			temp = temp.sibling;
		}

		if ((Nodes == null) && (fakeNode == null)) {

			setSize(0);
		} else {
			if ((Nodes == null) && (fakeNode != null)) {

				Nodes = fakeNode.reverse(null);
				setSize(Nodes.getSize());
			} else {
				if ((Nodes != null) && (fakeNode == null)) {

					setSize(Nodes.getSize());
				} else {

					unionNodes(fakeNode.reverse(null));
					setSize(Nodes.getSize());
				}
			}
		}

		return minNode.key;
	}

	// 6. Decrease a key value
	public void decrease(int old_value, int new_value) {

		BinomialHeapNode temp = Nodes.findANodeWithKey(old_value);
		if (temp == null)
		{	
			System.out.println("Old key not found..");
			return;
		}
		if(old_value < new_value)
		{	System.out.println("new value is greater than old value. Cannot decrease..");
		return;
		}


		temp.key = new_value;
		BinomialHeapNode tempParent = temp.parent;

		while ((tempParent != null) && (temp.key < tempParent.key)) {
			int z = temp.key;
			temp.key = tempParent.key;
			tempParent.key = z;

			temp = tempParent;
			tempParent = tempParent.parent;
		}
	}

	// 7. Increase a key value
	public void increase(int old_value, int new_value) {
		BinomialHeapNode temp = Nodes.findANodeWithKey(old_value);
		if (temp == null)
		{	
			System.out.println("Old key not found..");
			return;
		}
		if(old_value > new_value)
		{	System.out.println("new value is less than old value. Cannot increase..");
		return;
		}

		temp.key = new_value;
		BinomialHeapNode minChild;
		while (temp!=null) {
			minChild = temp.child;
			if(minChild==null) break;
			BinomialHeapNode sib = minChild.sibling;
			while (sib != null) {
				if (sib.key < minChild.key)
					minChild = sib;
				sib = sib.sibling;
			}
			int tempVal = temp.key;
			if (minChild.key < temp.key) {
				temp.key = minChild.key;
				minChild.key = tempVal;
				temp = minChild;
			}
			else break;
		}
	}

	// 8. Delete a node with a certain key
	public void delete(int value) {
		if ((Nodes != null) && (Nodes.findANodeWithKey(value) != null)) {
			decrease(value, findMin() - 1);
			extractMin();
		}
	}

	public void displayHeap(String filename) {

		StringBuilder sbNodes=new StringBuilder("graph b {\n// Nodes\n");
		StringBuilder sbEdges=new StringBuilder("// Edges\n");


		BinomialHeapNode start=Nodes;
		BinomialHeapNode node=start;

		//display the root list
		while(node!=null)
		{
			sbNodes.append(node.key+";\n");
			if(node.sibling!=null)
				sbEdges.append(node.key+" -- "+node.sibling.key+" [color=blue,style=dotted];\n");
			node=node.sibling;
		}
		node=start;

		//for each sibling scan its children and print them
		while(node!=null)
		{
			traverse(node,sbNodes,sbEdges);
			node=node.sibling;
		}
		sbEdges.append("}");
		try {
			BufferedWriter writer=new BufferedWriter(new FileWriter(filename));
			writer.write(sbNodes.toString());
			writer.write(sbEdges.toString());
			writer.close();

		} catch (IOException e) {
			System.out.println("Error writing to output file..");
		}

	}
	private void traverse(BinomialHeapNode node,StringBuilder sb1, StringBuilder sb2) {
		BinomialHeapNode temp=node.child;
		if(temp!=null)
		{
			sb1.append(temp.key+";\n");
			sb2.append(node.key+" -- "+temp.key+" [color=black,style=null];\n");
			traverse(temp, sb1, sb2);
			temp=temp.sibling;
			while(temp!=null)
			{
				sb1.append(temp.key+";\n");
				sb2.append(node.key+" -- "+temp.key+" [color=black,style=null];\n");
				traverse(temp,sb1,sb2);
				temp=temp.sibling;

			}
		}

	}

}
// end of class BinomialHeap
