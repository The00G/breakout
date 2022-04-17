import java.util.LinkedList;

public class Level {
    
    public static LinkedList<Brick> buildLevel(int id, Vector fieldSize) {
        switch(id) {

            case 1:
            int[][] map1 = {{8,8,8,8,8,8,8,8,8,8},
                            {7,7,7,7,7,7,7,7,7,7},
                            {6,6,6,6,6,6,6,6,6,6},
                            {5,5,5,5,5,5,5,5,5,5},
                            {4,4,4,4,4,4,4,4,4,4},
                            {3,3,3,3,3,3,3,3,3,3},
                            {2,2,2,2,2,2,2,2,2,2},
                            {1,1,1,1,1,1,1,1,1,1}};
            return buildBricks(map1, fieldSize);

            case 2:
            int[][] map2 = {{8,0,8,0,8,0,8,0,8,0},
                            {0,7,0,7,0,7,0,7,0,7},
                            {6,0,6,0,6,0,6,0,6,0},
                            {0,5,0,5,0,5,0,5,0,5},
                            {4,0,4,0,4,0,4,0,4,0},
                            {0,3,0,3,0,3,0,3,0,3},
                            {2,0,2,0,2,0,2,0,2,0},
                            {0,1,0,1,0,1,0,1,0,1}};
            return buildBricks(map2, fieldSize);

            case 3:
            int[][] map3 = {{8,8,8,8,8,8,8,8},
                            {7,7,7,7,7,7,7},
                            {6,6,6,6,6,6},
                            {5,5,5,5,5},
                            {4,4,4,4},
                            {3,3,3},
                            {2,2},
                            {1}};
            return buildBricks(map3, fieldSize);

            case 4:
            int[][] map4 = {{0,7,0,7,0,7,0,7,0,7},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,5,0,5,0,5,0,5,0,5},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,3,0,3,0,3,0,3,0,3},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,1,0,1,0,1,0,1,0,1}};
            return buildBricks(map4, fieldSize);

            case 55:
            int[][] map55 = {   { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
                                { 5,-1,-1,-1,-1,-1, 5,-1,-1,-1,-1,-1, 5},
                                { 5,-1, 5, 5, 5, 5, 5,-1, 5, 5, 5, 5, 5},
                                { 5,-1,-1,-1,-1, 5, 5,-1,-1,-1,-1, 5, 5},
                                { 5, 5, 5, 5, 5,-1, 5, 5, 5, 5, 5,-1, 5},
                                { 5, 5, 5, 5, 5,-1, 5, 5, 5, 5, 5,-1, 5},
                                { 5,-1, 5, 5, 5,-1, 5,-1, 5, 5, 5,-1, 5},
                                { 5, 5,-1,-1,-1, 5, 5, 5,-1,-1,-1, 5, 5},
                                { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}};
            return buildBricks(map55, fieldSize);

            default:
            return new LinkedList<>();
        }
    }

    public static LinkedList<Brick> buildBricks(int[][] map, Vector fieldSize) {
        LinkedList<Brick> bricks = new LinkedList<Brick>();
        for(int i = 0; i < map.length; i++){
            Vector brickSize = new Vector(fieldSize.x/(2*map[i].length),fieldSize.y/(6*map.length));
            for(int j = 0; j < map[i].length; j++) {
                if (map[i][j] !=0 ) {
                    bricks.add(new Brick(new Vector((2*j+1)*brickSize.x,(2*i+3)*brickSize.y),brickSize,map[i][j]));
                }
            }
        }
        return bricks;
    }
}
