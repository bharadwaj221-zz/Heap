import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BinaryHeap extends Heap {

	int[] keys;
	int size;

	public BinaryHeap(int n) {
		size = 0;
		keys = new int[n];

	}

	public void insert(int key) {

		size++;
		keys[size] = key;
		percUp(size);

	}

	// used in insertions and increase key operations
	private void percUp(int j) {

		int i, item;
		i = j;
		item = keys[j];
		while (i > 1 && item < keys[i / 2]) {
			keys[i] = keys[i / 2];
			i /= 2;
			
		}

		keys[i] = item;

	}

	// used in delete and decrease key operations
	private void percDown(int i) {
		int min, temp;

		if (i <= size) {
			int left = 2 * i;
			int right = 2 * i + 1;
			if (left <= size && keys[left] < keys[i])
				min = left;
			else
				min = i;

			if (right <= size && keys[right] < keys[min])
				min = right;

			if (min != i) {
				temp = keys[i];
				keys[i] = keys[min];
				keys[min] = temp;
				percDown(min);
			}

		}

	}

	public void delete(int key) {
		int i;
		for (i = 1; i <= size; i++)
			if (key == keys[i])
				break;
		if (i > size)
			System.out.println("Key not found..");
		else if (i == size) {
			keys[i] = -1;
			size--;
		} else {
			keys[i] = keys[size];
			keys[size] = -1;
			size--;
			percDown(i);
		}

	}

	@Override
	public int extractMin() {
		if (size == 0)
			return -1;
		int min = keys[1];
		delete(min);
		return min;

	}

	@Override
	public int findMin() {
		if (size == 0)
			return -1;
		return keys[1];
	}

	public void updateKey(int old, int key) {
		int i;
		for (i = 1; i <= size; i++)
			if (old == keys[i])
				break;
		if (i > size) {
			System.out.println("Key not found..");
			return;
		}
		if (old < key)
			increase(old, key, i);
		else if (old > key)
			decrease(old, key, i);

	}

	public void decrease(int old, int key, int i) {

		keys[i] = key;
		percUp(i);

	}

	public void increase(int old, int key, int i) {

		keys[i] = key;
		percDown(i);

	}

	@Override
	public void displayHeap(String filename) {
		StringBuilder sbNodes = new StringBuilder("graph b {\n// Nodes\n");
		StringBuilder sbEdges = new StringBuilder("// Edges\n");
		int i, j;
		for (i = 1; i <= size; i++) {
			sbNodes.append(keys[i] + ";\n");
			j = 2 * i;
			if (j <= size)
				sbEdges.append(keys[i] + " -- " + keys[j]
						+ " [color=black,style=null];\n");
			j++;
			if (j <= size)
				sbEdges.append(keys[i] + " -- " + keys[j]
						+ " [color=black,style=null];\n");
		}
		sbEdges.append("}");
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
			writer.write(sbNodes.toString());
			writer.write(sbEdges.toString());
			writer.close();

		} catch (IOException e) {
			System.out.println("Error writing to output file..");
		}

	}

}
