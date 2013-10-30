

// class for modeling the node of a Binomial heap derived from Node class
	public class BinomialHeapNode extends Node{



		public BinomialHeapNode parent; // pointer to the parent of the current node
		public BinomialHeapNode sibling; // pointer to the next binomial tree in the list
		public BinomialHeapNode child; // pointer to the first child of the current node

		public BinomialHeapNode(int k) {
			//	public BinomialHeapNode(Integer k) {
			key = k;
			degree = 0;
			parent = null;
			sibling = null;
			child = null;
		}

		
		public int getSize() {
			return (1 + ((child == null) ? 0 : child.getSize()) + ((sibling == null) ? 0
					: sibling.getSize()));
		}

		public BinomialHeapNode reverse(BinomialHeapNode sibl) {
			BinomialHeapNode ret;
			if (sibling != null)
				ret = sibling.reverse(this);
			else
				ret = this;
			sibling = sibl;
			return ret;
		}

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

		// Find a node with the given key
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