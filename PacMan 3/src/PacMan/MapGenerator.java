package PacMan;

import java.util.Random;

public class MapGenerator {
    public static int[][]generateMap(int column, int row) {
        //0 - puste, 1 - sciana, 2 - punkt, 3 - pacman, 4-duszek, 5 - power up
        int[][] map = new int[row][column];
        //zewnętrzne ściany
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < column; j++) {
                if(i==0 || i==row-1 || j==0 || j==column-1) {
                    map[i][j] = 1;
                }
            }
        }
        for(int i = 2; i < row-2; i+=2) {
            for(int j = 2; j < column-2; j+=2) {
                map[i][j] = 1;
                int rand = (int)(Math.random()*4);
                int currentRow = i;
                int currentCol = j;
                switch(rand) {
                    case 0:
                        currentRow = i-1;
                        break;
                    case 1:
                        currentRow = i+1;
                        break;
                    case 2:
                        currentCol = j-1;
                        break;
                    case 3:
                        currentCol = j+1;
                        break;
                }
                if(currentRow>0 && currentRow < row - 1 && currentCol>0 && currentCol < column - 1) {
                    map[currentRow][currentCol] = 1;
                }
            }
        }
        //kratki przy zewnętrznych ścianach puste
        for(int i = 1; i < column-1; i++) {
            map[1][i] = 0;
            map[row-2][i] = 0;
        }
        for(int i = 1; i < row-1; i++) {
            map[i][1] = 0;
            map[i][column - 2] = 0;
        }
        for(int i = 1; i < row-1; i++) {
            for(int j = 1; j < column-1; j++) {
                if(map[i][j] == 0 ) {
                    if (map[i-1][j] == 1 && map[i+1][j] == 1 && map[i][j+1] == 1 && map[i][j-1] == 1) {
                        map[i][j] = 1;
                    }
                }
            }
        }
        for(int i = 1; i < row-1; i++) {
            for(int j = 1; j < column-1; j++) {
                if(Math.random()<0.1){
                    map[i][j] = 0;
                }
            }
        }
        map[row-5][column-5] = 0;//duszek1
        map[row/2][column/2] = 0;//duszek2
        map[row/2][column/2+5] = 0;//duszek3
        map[row/2+5][column-5] = 0;//duszek4
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < column; j++) {
                if(map[i][j] == 0) {
                    map[i][j] = 2;
                }
            }
        }
        return map;
    }
}
