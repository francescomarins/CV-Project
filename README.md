# CV-Project

## ** First (easy) - Dilation algorithm using Java

PRE-REQUISITES:
 - Java installed on your own machine

### HOW TO RUN

 - step 1: compile and run the code using Java speicifing as parameters the source image and the number of dilation loops (example: `Java Dilate board.png 1`)



## ** Second (advance) - Maze Solver using C++ and OpenCV

The idea is to use Mathematical Morphology to implement a maze solver.

PRE-REQUISITES:

  * Library:
    - OpenCV installed in own computer

  * Image:
   - The maze must be composed by ONLY 2 walls
   - The maze must have 1 entrance and 1 exit only
   - The maze can be rotated as preferred, the program is orientation-indipendent


## HOW TO RUN

 - step 1: Clone the repository with "git clone" or download the folder from this page
 - step 2: Compile the program with a c++ compiler (Ubuntu: `g++ mazeSolver.cpp -o mazeSolver -std=c++11 $(pkg-config --cflags --libs opencv)`)
 - step 3: Run the program specifing the image source and the modality. The modality indicates how large must be the element size depending on the original image. So the suggestion is to test the program using both 1 and 2 modality and choose the best result. ./mazeSolver ImgSRC modality(1 or 2). (Ubuntu: `./mazeSolver test6.png 1`)
 
