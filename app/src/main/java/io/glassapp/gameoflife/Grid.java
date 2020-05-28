package io.glassapp.gameoflife;

public class Grid {

    private int[][] grid;
    private int rows;
    private int columns;

    /**
     * Constructor that creates a random state
     */
    public Grid(int rows, int columns) {
        grid = new int[rows][columns];
        for (int i = 0; i< rows; i++) {
            for (int j = 0; j< columns; j++) {
                grid[i][j] = (int) Math.round(Math.random());
            }
        }

        this.rows = rows;
        this.columns = columns;
    }
    /**
     * Returns the current generation
     *
     * @return Returns two dimensional array representing current state
     */
    public int[][] currentGeneration() {
        return this.grid;
    }

    /**
     * Returns the number of rows in the grid
     *
     * @return Number of rows
     */
    public int rows() {
        return this.rows;
    }

    /**
     * Returns the number of columns in the grid
     *
     * @return Number of columns
     */
    public int columns() {
        return this.columns;
    }

    /**
     * Returns the next generation
     *
     * @return Returns two dimensional array representing next state
     */
    public int[][] nextGeneration() {

        int[][] futureGeneration = new int[rows][columns];

        // iterate through two dimensional array
        for (int i=0; i<rows; i++) {
            for(int j=0; j<columns; j++) {
                futureGeneration[i][j] = this.getNextStateForCell(i,j);
            }
        }
        return futureGeneration;
    }

    /**
     * Gets next state for a particular cell
     *
     * @param i Row index of the grid
     * @param j Column index of the grid
     * @return 1 is cell alive, 0 if cell is dead
     */
    private int getNextStateForCell(int i, int j) {

        // get living neighbors
        int aliveNeighbors = this.calculateLivingNeighbors(i,j);

        /**
         * Cell is lonely with less than two live neighbors and dies
         */
        if ((grid[i][j] == 1) && (aliveNeighbors < 2)) {
            return  0;
        }

        /**
         * Cell is overcrowded and dies
         */
        else if ((grid[i][j] == 1) && aliveNeighbors > 3) {
            return  0;
        }

        /**
         * Cell is dead but 3 lives neighbors causes it to be born
         */
        else if (grid[i][j] == 0 && aliveNeighbors == 3) {
            return  1;
        }

        /**
         * Nothing changes so copy that state
         */
        else {
            return grid[i][j];
        }
    }

    /**
     * Returns the count of living neighbors for a particular cell in the grid
     *
     * @param i Row index of the grid
     * @param j Column index of the grid
     * @return Count of living neighbors for particular cell
     */
    private int calculateLivingNeighbors(int i, int j) {
        int liveCount = 0;
        for (int x=-1; x<=1; x++) {
            for (int y = -1; y <= 1; y++) {
                // check for boundary conditions
                if (i + x < 0 || i + x > (this.rows - 1) || y + j < 0 || y + j > (this.columns - 1)) {
                    continue;
                }
                liveCount += grid[i + x][y + j];
            }
        }

        // remove since we may have counted ourselves
        liveCount -= grid[i][j];

        return liveCount;
    }

    /**
     * Determines if array is jagged.
     *
     * @param array Array to test if jagged
     * @return True if jagged, otherwise false
     */
    private boolean isJagged(int[][] array) {
        boolean isJagged = false;

        if (array != null) {
            Integer length = null;
            for (int i=0; i<array.length; i++) {
                if(length == null) {
                    length = array[i].length;
                }
                else if (length.equals(array[i].length)) {
                    continue;
                }
                else {
                    isJagged = true;
                    break;
                }
            }
        }

        return isJagged;
    }
}
