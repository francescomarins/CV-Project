# CV-Project

The idea is to use Mathematical Mortphology to implement a maze solver.

PRE-REQUISISTES:

  * Library:
    - OpenCV installed in own computer

  * Image:
   - The maze has to be composed by ONLY 2 walls
   - The maze has to have 1 entrance and 1 exit only
   - The maze can be rotates as preference, the program is orientation-indipendent


## HOW TO RUN

 - step 1: Clone the repositore with gitClone or download the folder from this page
 - step 2: Compile the program with a c++ compiler (Ubuntu: `g++ mazeSolver.cpp -o test -std=c++11 $(pkg-config --cflags --libs opencv)`)
 - step 3: Run the program specifing the image source and the modality. The modality indicates how large must be the element size depending on the original image. So the suggestion is to test the program using both 1 and 2 modality and choose the better result. ./test ImgSRC modality(1 or 2). (Ubuntu: `./test test6.png 1`)
 
