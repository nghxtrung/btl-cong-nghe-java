package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import static utils.PokemonUtils.COL_MATRIX;
import static utils.PokemonUtils.COL_MATRIX_ICON;
import static utils.PokemonUtils.IMAGE_ICON_COUNT;
import static utils.PokemonUtils.ROW_MATRIX;
import static utils.PokemonUtils.ROW_MATRIX_ICON;

public class PokemonGame {
	private PokemonNode[][] game;
	private int countNode;
    
    public PokemonGame() {
        this.game = new PokemonNode[ROW_MATRIX][COL_MATRIX];
        this.countNode = ROW_MATRIX_ICON * COL_MATRIX_ICON / 2;
        for (int i = 0; i < COL_MATRIX; i++) {
            PokemonNode nodeBorderTop = new PokemonNode(0, i, 0);
            PokemonNode nodeBorderBottom = new PokemonNode(ROW_MATRIX - 1, i, 0);
            this.game[0][i] = nodeBorderTop;
            this.game[ROW_MATRIX - 1][i] = nodeBorderBottom;
        }
        for (int i = 0; i < ROW_MATRIX; i++) {
            PokemonNode nodeBorderLeft = new PokemonNode(i, 0, 0);
            PokemonNode nodeBorderRight = new PokemonNode(i, COL_MATRIX - 1, 0);
            this.game[i][0] = nodeBorderLeft;
            this.game[i][COL_MATRIX - 1] = nodeBorderRight;
        }
        ArrayList<Point> listPoint = new ArrayList<Point>();
        for (int i = 1; i < ROW_MATRIX - 1; i++) {
            for (int j = 1; j < COL_MATRIX - 1; j++) {
                listPoint.add(new Point(i, j));
            }
        }
        int max = ROW_MATRIX_ICON * COL_MATRIX_ICON / IMAGE_ICON_COUNT;
        if (max % 2 == 1) {
            max += 1;
        }
        Random rand = new Random();
        int arr[] = new int[IMAGE_ICON_COUNT + 1];
        int i = 0;
        do {
            int index = rand.nextInt(IMAGE_ICON_COUNT) + 1;
            if (arr[index] < max) {
                arr[index] += 2;
                for (int j = 0; j < 2; j++) {
                    int size = listPoint.size();
                    int pointIndex = rand.nextInt(size);
                    int x = listPoint.get(pointIndex).x;
                    int y = listPoint.get(pointIndex).y;
                    PokemonNode node = new PokemonNode(x, y, index);
                    this.game[x][y] = node;
                    listPoint.remove(pointIndex);
                }
                i++;
            }
        } while (i < ROW_MATRIX_ICON * COL_MATRIX_ICON / 2);
    }
    
    public void showGame() {
        for (int i = 0; i < ROW_MATRIX; i++) {
            for (int j = 0; j < COL_MATRIX; j++) {
                System.out.printf("%3d", this.game[i][j].getValue());
            }
            System.out.println();
        }
    }
    
    public PokemonNode getNode(int row, int col) {
    	return this.game[row][col];
    }

    
    public int getCountNode() {
		return countNode;
	}

	public void setCountNode(int countNode) {
		this.countNode = countNode;
	}

	private boolean checkLineX(int y1, int y2, int x) {
	    int min = Math.min(y1, y2);
	    int max = Math.max(y1, y2);
	    for (int y = min + 1; y < max; y++) {
	        if (this.game[x][y].getValue() != 0) {
	            return false;
	        }
	    }
	    return true;
    }
    
    private boolean checkLineY(int x1, int x2, int y) {
        int min = Math.min(x1, x2);
        int max = Math.max(x1, x2);
        for (int x = min + 1; x < max; x++) {
            if (this.game[x][y].getValue() != 0) {
                return false;
            }
        }
        return true;
    }
    
    private boolean checkRectX(Point p1, Point p2) {
        Point pMinY = p1, pMaxY = p2;
        if (p1.y > p2.y) {
            pMinY = p2;
            pMaxY = p1;
        }
        for (int y = pMinY.y; y <= pMaxY.y; y++) {
            if (y > pMinY.y && this.game[pMinY.x][y].getValue() != 0) {
                return false;
            }
            if ((this.game[pMaxY.x][y].getValue() == 0 || y == pMaxY.y) && checkLineY(pMinY.x, pMaxY.x, y) && checkLineX(y, pMaxY.y, pMaxY.x)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean checkRectY(Point p1, Point p2) {
        Point pMinX = p1, pMaxX = p2;
        if (p1.x > p2.x) {
            pMinX = p2;
            pMaxX = p1;
        }
        for (int x = pMinX.x; x <= pMaxX.x; x++) {
            if (x > pMinX.x && this.game[x][pMinX.y].getValue() != 0) {
                return false;
            }
            if ((this.game[x][pMaxX.y].getValue() == 0 || x == pMaxX.x) && checkLineX(pMinX.y, pMaxX.y, x) && checkLineY(x, pMaxX.x, pMaxX.y)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean checkMoreLineX(Point p1, Point p2, int type) {
        Point pMinY = p1, pMaxY = p2;
        if (p1.y > p2.y) {
            pMinY = p2;
            pMaxY = p1;
        }
        int y = pMaxY.y + type;
        int row = pMinY.x;
        int colFinish = pMaxY.y;
        if (type == -1) {
            colFinish = pMinY.y;
            y = pMinY.y + type;
            row = pMaxY.x;
        }
        if ((this.game[row][colFinish].getValue() == 0 || pMinY.y == pMaxY.y) && checkLineX(pMinY.y, pMaxY.y, row)) {
            while (this.game[pMinY.x][y].getValue() == 0 && this.game[pMaxY.x][y].getValue() == 0) {
                if (checkLineY(pMinY.x, pMaxY.x, y)) {
                    return true;
                }
                y += type;
            }
        }
        return false;
    }
    
    private boolean checkMoreLineY(Point p1, Point p2, int type) {
        Point pMinX = p1, pMaxX = p2;
        if (p1.x > p2.x) {
            pMinX = p2;
            pMaxX = p1;
        }
        int x = pMaxX.x + type;
        int col = pMinX.y;
        int rowFinish = pMaxX.x;
        if (type == -1) {
            rowFinish = pMinX.x;
            x = pMinX.x + type;
            col = pMaxX.y;
        }
        if ((this.game[rowFinish][col].getValue() == 0 || pMinX.x == pMaxX.x) && checkLineY(pMinX.x, pMaxX.x, col)) {
            while (this.game[x][pMinX.y].getValue() == 0 && this.game[x][pMaxX.y].getValue() == 0) {
                if (checkLineX(pMinX.y, pMaxX.y, x)) {
                    return true;
                }
                x += type;
            }
        }
        return false;
    }    
    
    public PokemonPointLine checkTwoPoint(Point p1, Point p2) {
        if (!p1.equals(p2) && this.game[p1.x][p1.y].getValue() == this.game[p2.x][p2.y].getValue()) {
            if (p1.x == p2.x) {
                if (checkLineX(p1.y, p2.y, p1.x)) {
                    return new PokemonPointLine(p1, p2);
                }
            }
            if (p1.y == p2.y) {
                if (checkLineY(p1.x, p2.x, p1.y)) {
                    return new PokemonPointLine(p1, p2);
                }
            }
            if (checkRectX(p1, p2)) {
                return new PokemonPointLine(p1, p2);
            }
            if (checkRectY(p1, p2)) {
                return new PokemonPointLine(p1, p2);
            }
            if (checkMoreLineX(p1, p2, 1)) {
                return new PokemonPointLine(p1, p2);
            }
            if (checkMoreLineX(p1, p2, -1)) {
                return new PokemonPointLine(p1, p2);
            }
            if (checkMoreLineY(p1, p2, 1)) {
                return new PokemonPointLine(p1, p2);
            }
            if (checkMoreLineY(p1, p2, -1)) {
                return new PokemonPointLine(p1, p2);
            }
        }
        return null;
    }
}
