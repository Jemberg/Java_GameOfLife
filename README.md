# Conway's Game of Life in Java
This is a version of John Conway's Game of Life written in Java. It was created by Martin Jemberg and Freish as a part of a finals project in Ventspils University of Applied Sciences.

## Game of Life description
The Game of Life is a cellular automaton devised by the British mathematician John Horton Conway in 1970. It is a zero-player game, meaning that its evolution is determined by its initial state, requiring no further input. One interacts with the Game of Life by creating an initial configuration and observing how it evolves. It is Turing complete and can simulate a universal constructor or any other Turing machine. 

The rules for the game are simple:
* Every alive cell with less than 2 alive neighbors dies.
* Every alive cell with 2 or 3 alive neighbors lives on to the next generation.
* Every alive cell with more than 3 alive neighbors dies.
* Every dead cell with 3 neighbor cells becomes alive.

## Features
Although no longer under active development, the current features include:
* Adjustable speed (Slow, Medium or Fast).
* Adjustable grid size (Small, Medium or Large), grid size goes up to 75x75 tiles.
* Ability to enter custom seed for randomly generating tiles in grid.
* Clear the grid entirely.
* Cells can be clicked in with left click in the grid.
* Grids can be imported and exported with the help of JSON.

## Technologies
The following technologies were used in the development of this project:
* Java
* JavaFX
* Maven
* JSON

## Installation
Simply download the latest release in the release section and enjoy! 
