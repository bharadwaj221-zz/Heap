import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] argv) {

		Scanner scan = new Scanner(System.in);

		// Make corresponding Heap based on the command

		Heap h = null;
		try {
			if (argv[0].equals("-binomial"))
				h = new BinomialHeap();
			else if (argv[0].equals("-binary"))
				h = new BinaryHeap(100);
			else if (argv[0].equals("-fibonacci"))
				h = new FibonacciHeap();
			else
				System.out.println("Invalid command option..\n(Format: Main -heap_type)");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Specify the option\n(Format: Main -heap_type)");
			System.exit(0);
		}
		char ch;
		int oldKey, newKey;

		// Perform Heap operations in a menu driven manner

		do

		{

			System.out.println("\nHeap Operations");
			System.out.println("1. Insert ");
			System.out.println("2. Delete ");
			System.out.println("3. Update key");
			System.out.println("4. Find minimum element");
			System.out.println("5. Extract minimum element");
			System.out.println("6. Display heap");
			System.out.println("Enter your choice: ");
			int choice = scan.nextInt();

			switch (choice)

			{

			case 1:
				System.out.println("Enter integer element to insert");
				h.insert(scan.nextInt());

				break;

			case 2:

				System.out.println("Enter element to delete ");
				h.delete(scan.nextInt());

				break;

			case 3:

				System.out.println("Enter old key");
				oldKey = scan.nextInt();
				System.out.println("Enter new key");
				newKey = scan.nextInt();
				h.updateKey(oldKey, newKey);

				break;

			case 4:
				int min = h.findMin();
				if (min == -1)
					System.out.println("Heap empty..");
				else
					System.out.println("Minimum element: " + min);

				break;

			case 5:
				min = h.extractMin();
				if (min == -1)
					System.out.println("Heap empty..");
				else
					System.out.println("Minimum element extracted: " + min);

				break;

			case 6:

				h.displayHeap("output.gv");
				File file = new File("output.gv");
				try {
					Desktop.getDesktop().open(file);
				} catch (IOException e) {

				}
				break;

			default:

				System.out.println("Wrong Entry \n ");

				break;

			}

			System.out.println("\nPress Y to continue: ");
			ch = scan.next().charAt(0);

		} while (ch == 'Y' || ch == 'y');
		scan.close();

	}

}
