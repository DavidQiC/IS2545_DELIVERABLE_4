import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import static org.junit.Assert.*;
//import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestConvert2Int {
	MainPanel panel;
	private Cell[][] cells;

	@Before
	public void startUp() {
		panel = new MainPanel(15);
	}

	/*
	 * TEST1: if all cells are dead, 
	 * we can know the number of neighbors, say (5,5), is 0
	 */
	@Test
	public void testGetNumNeighbors1() throws Exception {
		Method getNumNeighborsMethod = MainPanel.class.getDeclaredMethod("getNumNeighbors", int.class, int.class);
		getNumNeighborsMethod.setAccessible(true);
		cells = new Cell[15][15];
		for(int i = 0; i < cells.length; i++){
			for(int j = 0; j < cells.length; j++){
				cells[i][j] = new Cell(false);
			}
		}
		int returnNum = (int) getNumNeighborsMethod.invoke(panel, 5, 5);
		assertEquals(0, returnNum);
	}
	
	/*
	 * TEST2: if all cells are dead, except the cells around (3,3) are live,
	 * we can know the number of neighbors, say (3,3), is 4
	 * 
	 *  ----------------------
	 * 	|	   | live |	     |
	 *  -----------------------
	 * 	| live | (3,3) |	live |
	 *  -----------------------
	 * 	|	   | live  |      |
	 *  -----------------------
	 */
	@Test
	public void testGetNumNeighbors2() throws Exception {
		int x = 3;
		int y = 3;
		cells = new Cell[15][15];
		for(int i = 0; i < cells.length; i++){
			for(int j = 0; j < cells.length; j++){
				cells[i][j] = new Cell(false);
			}
		}
		int[] dx = {0,0,1,-1};
		int[] dy = {1,-1,0,0};
		for(int k = 0; k < 4; k++){
			int nx = x + dx[k];
			int ny = y + dy[k];
			cells[nx][ny] = new Cell(true);
		}
		
		panel.setCells(cells);
		
		Method getNumNeighborsMethod = MainPanel.class.getDeclaredMethod("getNumNeighbors", int.class, int.class);
		getNumNeighborsMethod.setAccessible(true);
		
		int returnNum = (int)getNumNeighborsMethod.invoke(panel, x, y);
		assertEquals(4, returnNum);
	}
	
	/*
	 * TEST2: if all cells are dead, except the cells around (3,3) are live,
	 * we can know the number of neighbors, say (3,3), is 8
	 * 
	 *  ----------------------
	 * 	| live | live |	live |
	 *  -----------------------
	 * 	| live | (3,3) |	live |
	 *  -----------------------
	 * 	| live | live  |	live |
	 *  -----------------------
	 */
	@Test
	public void testGetNumNeighbors3() throws Exception {
		int x = 3;
		int y = 3;
		cells = new Cell[15][15];
		for(int i = 0; i < cells.length; i++){
			for(int j = 0; j < cells.length; j++){
				cells[i][j] = new Cell(false);
			}
		}
		int[] dx = {0,0,1,-1};
		int[] dy = {1,-1,0,0};
		for(int k = 0; k < 4; k++){
			int nx = x + dx[k];
			int ny = y + dy[k];
			cells[nx][ny] = new Cell(true);
		}
		cells[x - 1][y - 1].setAlive(true);
		cells[x + 1][y - 1].setAlive(true);
		cells[x + 1][y + 1].setAlive(true);
		cells[x - 1][y + 1].setAlive(true);
		
		panel.setCells(cells);
		
		Method getNumNeighborsMethod = MainPanel.class.getDeclaredMethod("getNumNeighbors", int.class, int.class);
		getNumNeighborsMethod.setAccessible(true);
		
		int returnNum = (int)getNumNeighborsMethod.invoke(panel, x, y);
		assertEquals(8, returnNum);
	}
	
}
