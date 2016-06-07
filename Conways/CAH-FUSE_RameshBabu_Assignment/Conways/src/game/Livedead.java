/**
 * @author Ramesh Babu Kuttuboina
 * Date: 5/20/2016 
 * Description: this file is implemented to calcuate the next generation of Conway's game 
 * of life given any initial state. it takes matrix as an input , process it and gives the
 * output matrix
 */
package game;
import java.util.Scanner;

public class Livedead {

	// static final int ROW=6,COL=8; static matrix testing
	static int ROW, COL;

	/**
	 * This method is meant to update the number of live neighbor cells for a
	 * particular cell it uses a temporary array to update the values 
	 * @param int[][] , int ,int
	 * @return: void
	 */
	public static void addlivecount(int[][] tmp, int row, int col) {
		// these arrays are helps us to access 8 neighbours
		int rowNbr[] = new int[] { -1, -1, -1, 0, 0, 1, 1, 1 };
		int colNbr[] = new int[] { -1, 0, 1, -1, 1, -1, 0, 1 };

		for (int i = 0; i < 8; i++) {
			if ((row + rowNbr[i] >= 0) && (row + rowNbr[i] < ROW) && (col + colNbr[i] < COL) && (col + colNbr[i] >= 0))
				tmp[row + rowNbr[i]][col + colNbr[i]]++;
		}
	}

	/**
	 * Description: This method is to update the output matrix based on the
	 * total number of live neighbors at that particular cell. For example: if
	 * count ==3 then it converts dead cell to live cell. in other case, if
	 * count<2 or count >3 it convert the live cell to dead cell.
	 * 
	 * @param int[][],int[][]
	 * @return void
	 */
	public static void replacecell(int[][] temp, int[][] output) {

		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {

				if (temp[i][j] < 2 || temp[i][j] > 3)
					output[i][j] = 0;
				else if (temp[i][j] == 3)
					output[i][j] = 1;
			}
		}
	}

	/**
	 * Method name: Process cell
	 * Description: this method is just to traverse all the live
	 *             cells and ignore dead cells
	 * @param int[][]
	 * @return void
	 */
	public void process_cells(int[][] cells) {

		if(cells.length==0){
		System.out.println("Array is Empty");
		return;
		}
			
		int tmp[][] = new int[ROW][COL];

		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				if (cells[i][j] == 1) {
					addlivecount(tmp, i, j);
				}
			}
		}
		replacecell(tmp, cells);
	}

	/**
	 * Method Name:printout
	 * Description: This method is just to print the array items
	 * @param int[][]
	 * @return void
	 */
	public void printout(int[][] out) {
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				System.out.print(out[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String args[]) {

		// Static test code 8x6 matrix - Start
		/*
		  int Cells[][]= new int[][] { 
		   {0, 0, 0, 0, 0, 0, 1, 0}, 
		   {1, 1, 1, 0,0, 0, 1, 0}, 
		   {0, 0, 0, 0, 0, 0, 1, 0}, 
		   {0, 0, 0, 0, 0, 0, 0, 0}, 
		   {0,0, 0, 1, 1, 0, 0, 0}, 
		   {0, 0, 0, 1, 1, 0, 0, 0} };
		 */
		// Static test code 8x6 matrix - Ends

		// dynamic way entering matrix - Start
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter the Number of rows");
		ROW = in.nextInt();
		System.out.println("Please enter the Number of Columns");
		COL = in.nextInt();
		System.out.println("Please enter either '1' or '0' [1-> live, 0 -> Dead]");

		int[][] Cells = new int[ROW][COL];
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				Cells[i][j] = in.nextInt();
			}
			System.out.println();
		}
		// dynamic way entering matrix - Ends

		Livedead find = new Livedead(); // Creating an instance
		
		try{
		System.out.println("**** INPUT **** ");
		System.out.println();
		find.printout(Cells);
		find.process_cells(Cells);
		System.out.println("**** OUTPUT **** ");
		System.out.println();
		find.printout(Cells);
		}
		catch(Exception e){
			System.out.println("Error occured in processing the INPUT Matrix");
		}
	}
}
