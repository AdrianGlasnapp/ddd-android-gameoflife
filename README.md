# Game of Life
This is an example implementation of Conway's Game of Life in **Kotlin**.
The primary focus of the implementation is to demonstrate Domain-Driven Design patterns on **Android**
 with **Kotlin**.

## Rules of Conway's Game of Life
 > The universe of the Game of Life is an infinite, two-dimensional orthogonal grid of square
 > cells, each of which is in one of two possible states, live or dead, (or populated and unpopulated,
 > respectively). Every cell interacts with its eight neighbours, which are the cells that are
 > horizontally, vertically, or diagonally adjacent. At each step in time, the following transitions occur:

 >  1. Any live cell with fewer than two live neighbours dies, as if by underpopulation.
 >  2. Any live cell with two or three live neighbours lives on to the next generation.
 >  3. Any live cell with more than three live neighbours dies, as if by overpopulation.
 >  4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

 >  These rules, which compare the behavior of the automaton to real life, can be condensed into the following:
 >
 >  1. Any live cell with two or three live neighbours survives.
 >  2. Any dead cell with three live neighbours becomes a live cell.
 >  3. All other live cells die in the next generation. Similarly, all other dead cells stay dead.
 >
 >  The initial pattern constitutes the seed of the system. The first generation is created by
 >  applying the above rules simultaneously to every cell in the seed; births and deaths occur
 >  simultaneously, and the discrete moment at which this happens is sometimes called a tick. Each
 >  generation is a pure function of the preceding one. The rules continue to be applied repeatedly
 >  to create further generations.

 — [Conway's Game of Life - Wikipedia](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life)