import java.util.Scanner;
//length = 120 width = 120 tile size = 12 cost of tiles = 13.35/box		total cost for materials: 106.80

public class CalcPriceOfFlooringApp {
		public static double costOfTiles = 0.0;
		public static double numOfTiles = 0;
		
	public static void main(String[] args) {
		double width = 0.0;
		double length = 0.0;
		int tileSize = 0;
		double sizeOfRoom = 0.0;
		int numOfTilesInBox = 12;
		Scanner kb = new Scanner(System.in);
		CalcSizeOfRoom size = new CalcSizeOfRoom();
		CalcNumOfTiles tiles = new CalcNumOfTiles();
		CalculatePrice price = new CalculatePrice();
		
		System.out.println("Enter the length of the room (in inches): ");
		width = kb.nextDouble();
		System.out.println("Enter the width of the room (in inches): ");
		length = kb.nextDouble();
		System.out.println("Enter the size of your tiles (10, 12, or 16 inches): ");
		tileSize = kb.nextInt();
		System.out.println("Enter the cost of the tiles (per box): ");
		costOfTiles = kb.nextDouble();
		costOfTiles = costOfTiles / numOfTilesInBox;
		kb.close();
		
		sizeOfRoom = size.calculateSize(length, width);
		numOfTiles = tiles.calculateTileNumber(sizeOfRoom, tileSize);
		price.calculatePrice(costOfTiles, numOfTiles);
		

	}

}
