
public class CalcNumOfTiles {
	protected double calculateTileNumber(double sizeOfRoom, int tileSize){
		double numOfTiles = 0.0;
		int tileSizeSq = tileSize * tileSize;
		
		numOfTiles = sizeOfRoom / tileSizeSq;
		
		return numOfTiles;
	}
}
