

// class for modeling the node of a Binomial heap derived from Node class
	public class BinomialHeapNode extends Node{



		public BinomialHeapNode parent; 
		public BinomialHeapNode sibling;
		public BinomialHeapNode child; // first child of the current node
		int degree; //number of children

		public BinomialHeapNode(int k) {
			//	public BinomialHeapNode(Integer k) {
			key = k;
			degree = 0;
			parent = null;
			sibling = null;
			child = null;
		}

		//size of the heap given size of the child + size of all siblings
		public int getSize() {
			return (1 + ((child == null) ? 0 : child.getSize()) + ((sibling == null) ? 0
					: sibling.getSize()));
		}

		//reverse the linked list of siblings of a particular node
		//used in
		public BinomialHeapNode reverse(BinomialHeapNode sibl) {
			BinomialHeapNode ret;
			if (sibling != null)
				ret = sibling.reverse(this);
			else
				ret = this;
			sibling = sibl;
			return ret;
		}

		//find the minimum node by scanning the root list
		public BinomialHeapNode findMinNode() {
			BinomialHeapNode x = this, y = this;

			int min = x.key;

			while (x != null) {
				if (x.key < min) {
					y = x;
					min = x.key;
				}
				x = x.sibling;
			}

			return y;
		}

		//find a node in the heap with the given key
		public BinomialHeapNode findANodeWithKey(int value) {
			BinomialHeapNode temp = this, node = null;
			while (temp != null) {
				if (temp.key == value) {
					node = temp;
					break;
				}
				if (temp.child == null)
					temp = temp.sibling;
				else {
					node = temp.child.findANodeWithKey(value);
					if (node == null)
						temp = temp.sibling;
					else
						break;
				}
			}

			return node;
		}

	}