package src;
import java.util.LinkedList;

import helpers.mathHelper;

public class Level {
    
    /**
     * Create the bricks of a given level
     * 
     * @param id        Level id
     * @param fieldSize game's fieldsize
     * @return          level's bricks
     */
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

            case 5:
            int[][] map5m = {{7,7,7,7,7,7,7,7,7,7},
                             {6,6,6,6,6,6,6,6,6,6},
                             {5,5,5,5,5,5,5,5,5,5},
                             {4,4,4,4,4,4,4,4,4,4},
                             {3,3,3,3,3,3,3,3,3,3},
                             {2,2,2,2,2,2,2,2,2,2},
                             {1,1,1,1,1,1,1,1,1,1},
                             {0,0,0,0,0,0,0,0,0,0}};
            int[][] map5M = {{8,8,8,8,8,8,8,8,8,8},
                             {8,8,8,8,8,8,8,8,8,8},
                             {7,7,7,7,7,7,7,7,7,7},
                             {6,6,6,6,6,6,6,6,6,6},
                             {5,5,5,5,5,5,5,5,5,5},
                             {4,4,4,4,4,4,4,4,4,4},
                             {3,3,3,3,3,3,3,3,3,3},
                             {2,2,2,2,2,2,2,2,2,2}};
            return buildBricks(map5m, map5M, fieldSize);

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
            
            case -1:
            int [][] map404 = {{1,0},{0,0}};
            return buildBricks(map404, fieldSize);

            default:
            return new LinkedList<>();
        }
    }

    /**
     * Creates bricks according to a given map
     * 
     * @param map       map containing the total life of the bricks
     * @param fieldSize game's fieldsize
     * @return          list of bricks placed according to the map
     */
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

    /**
     * Creates bricks according to the maps.
     * The life of the brick is chosen randomly between the mapMin and the mapMax
     * 
     * @param mapMin    map containing the minimum total life of the bricks
     * @param mapMax    map containing the maximum total life of the bricks
     * @param fieldSize game's fieldsize
     * @return          list of bricks placed according to the map
     */
    public static LinkedList<Brick> buildBricks(int[][] mapMin, int[][] mapMax, Vector fieldSize) {
        LinkedList<Brick> bricks = new LinkedList<Brick>();
        for(int i = 0; i < mapMin.length; i++){
            Vector brickSize = new Vector(fieldSize.x/(2*mapMin[i].length),fieldSize.y/(6*mapMin.length));
            for(int j = 0; j < mapMin[i].length; j++) {
                int life = mathHelper.RandomIntBetween(mapMin[i][j],mapMax[i][j]);
                if (life !=0 ) {
                    bricks.add(new Brick(new Vector((2*j+1)*brickSize.x,(2*i+3)*brickSize.y),brickSize,life));
                }
            }
        }
        return bricks;
    }
}
