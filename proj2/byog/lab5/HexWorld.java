package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t){
        int nextLine = s;
        Position nextP = new Position(p.getX(), p.getY());
        //判断是前半部分还是后半部分
        boolean isFront = true;
        for (int i = 0; i < s; i++) {
            //判断是否是前半部分的最后一次循环,是最后一次循环则p的x值不需要改变
            boolean flag = true;
            addLine(world, nextP, nextLine, t);
            if (i != s - 1) {
                nextLine += 2;
                flag = false;
            }
            nextP = updateP(nextP, flag, isFront);
        }
        isFront = false;
        for (int i = 0; i < s; i++) {
            boolean flag = false;
            addLine(world, nextP, nextLine, t);
            if (i != s - 1) {
                nextLine -= 2;
                nextP = updateP(nextP, flag, isFront);
            }
        }
    }

    private static Position updateP(Position nextP, boolean flag, boolean isFront) {
        if (isFront) {
            if (flag) {
                return new Position(nextP.getX(), nextP.getY() + 1);
            } else {
                return new Position(nextP.getX() - 1, nextP.getY() + 1);
            }
        } else {
            return new Position(nextP.getX() + 1, nextP.getY() + 1);
        }
    }

    private static void addLine(TETile[][] world, Position nextP, int nextLine, TETile t) {
        int x = nextP.getX();
        int y = nextP.getY();
        for (int i = x; i < x + nextLine; i++) {
            world[i][y] = t;
        }
    }


    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];

        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        int s = 3;
        drawWorld(world, s, Tileset.WALL);

        ter.renderFrame(world);
    }

    private static void drawWorld(TETile[][] world, int s, TETile t) {
        for (int i = 0; i < 5; i++) {
            drawColumn(world, s, t, i);
        }
    }

    private static void drawColumn(TETile[][] world, int s, TETile t, int column) {
        Position columnBottomPosition = computeColumnBottomPosition(column, s);
        int n;
        switch (column) {
            case 0: n = 3; break;
            case 1: n = 4; break;
            case 2: n = 5; break;
            case 3: n = 4; break;
            case 4: n = 3; break;
            default: n = 0; break;
        }
        for (int i = 0; i < n; i++) {
            addHexagon(world, columnBottomPosition, s, t);
            columnBottomPosition.setY(columnBottomPosition.getY() + 2 * s);
        }
    }

    private static Position computeColumnBottomPosition(int column, int s) {
        int x = (column + 1) * (s - 1) + column * s;
        int y;
        switch (column) {
            case 0: y = 2 * s; break;
            case 1: y = s; break;
            case 2: y = 0; break;
            case 3: y = s; break;
            case 4: y = 2 * s; break;
            default: y = new Random(123321).nextInt(); break;
        }
        Position p = new Position(x, y);
        return p;
    }
}
