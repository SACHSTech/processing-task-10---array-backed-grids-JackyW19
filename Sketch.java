import processing.core.PApplet;

public class Sketch extends PApplet {

  // declare variables
  
  int mouseXOnGrid;
  int mouseYOnGrid;
  int selectedCount;
  int selectedRowCount;
  int selectedColumnCount;
  int grid;
  
  int intCellWidth = 35;
  int intCellHeight = 35;
  int intCellMargin = 5;
  int intRowCount = 10;
  int intColumnCount = 10;

  boolean blnGridPressed = false;
  boolean blnPrintGrid = false;
  boolean blnGridReset;
  
  int width = (intCellWidth * intRowCount) + (intCellMargin * (intRowCount + 1));
  int height = (intCellHeight * intColumnCount) + (intCellMargin * (intColumnCount + 1));
  int[][] intGrid = new int[intRowCount][intColumnCount];
  
 
	
  
  public void settings() {
  
    size(width, height);
  }


  public void setup() {
    background(0);
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {

    // methods
    drawGrid();
    gridData();
  }

   public void drawGrid(){
    for (int COLUMNS = 0; COLUMNS < intColumnCount; COLUMNS++){
      for (int ROWS = 0; ROWS < intRowCount; ROWS++){

        if (blnGridPressed && mouseX == COLUMNS && mouseY == ROWS){

          if (intGrid[ROWS][COLUMNS] == 0){

            intGrid[ROWS][COLUMNS] = 1;
            selectedCount ++;
          }

          else if (intGrid[ROWS][COLUMNS] == 1){

            intGrid[ROWS][COLUMNS] = 0;
            selectedCount --;
          }

          if (COLUMNS > 0 && intGrid[ROWS][COLUMNS-1] == 0){

            intGrid[ROWS][COLUMNS-1] = 1;
            selectedCount ++;
          }

          else if (COLUMNS > 0 && intGrid[ROWS][COLUMNS-1] == 1){
          
            intGrid[ROWS][COLUMNS-1] = 0;
            selectedCount --;
          }

          if (COLUMNS < intColumnCount - 1 && intGrid[ROWS][COLUMNS+1] == 0){

            intGrid[ROWS][COLUMNS+1] = 1;
            selectedCount ++;
          }

          else if (COLUMNS < intColumnCount - 1 && intGrid[ROWS][COLUMNS+1] == 1){

            intGrid[ROWS][COLUMNS+1] = 0;
            selectedCount --;
          }

          if (ROWS > 0 && intGrid[ROWS-1][COLUMNS] == 0){

            intGrid[ROWS-1][COLUMNS] = 1;
            selectedCount ++;
          }
          
          else if (ROWS > 0 && intGrid[ROWS-1][COLUMNS] == 1){

            intGrid[ROWS-1][COLUMNS] = 0;
            selectedCount --;
          }

          if (ROWS < intRowCount - 1 && intGrid[ROWS+1][COLUMNS] == 0){

            intGrid[ROWS+1][COLUMNS] = 1;
            selectedCount ++;
          }

          else if (ROWS < intRowCount - 1 && intGrid[ROWS+1][COLUMNS] == 1){

            intGrid[ROWS+1][COLUMNS] = 0;
            selectedCount --;
            
          }

          // display text
          println( "Total of " + selectedCount + " cells are selected.");
          
          blnGridPressed = false;
        }

        // colour change to green
        if (intGrid[ROWS][COLUMNS] == 1){

          fill(0, 255, 0);
          
        }

        else {

          fill(255, 255, 255);
        }

        rect(intCellMargin + (COLUMNS * (intCellWidth + intCellMargin)), intCellMargin + (ROWS * (intCellHeight + intCellMargin)), intCellWidth, intCellHeight);

        if (blnGridReset){
          for (int i = 0; i < intColumnCount; i++){
            for (int j = 0; j < intRowCount; j++){
    
              intGrid[j][i] = 0;
            }
          }

          selectedCount = 0;
          grid = 0;
          selectedRowCount = 0;
          selectedColumnCount = 0;
        }
      }
    }
  }
  
  public void gridData(){

    if (blnPrintGrid){

      for (int i = 0; i < intRowCount; i++){
        for (int j = 0; j < intColumnCount; j++){
  
          if (intGrid[i][j] == 1){

            selectedRowCount ++;
          }

          if (j < intColumnCount - 1){

            if (intGrid[i][j] == 1 && intGrid[i][j+1] == 1){

              grid ++;
            }
          }
          
          if (j > 0 && j < intColumnCount){

            if (intGrid[i][j-1] == 1 && intGrid[i][j] == 1 && j == intColumnCount - 1){

              grid ++;
            } 

            else if (intGrid[i][j-1] == 1 && intGrid[i][j] == 1 && intGrid[i][j+1] == 0 && j < intColumnCount - 1){

              grid ++;
            }
          }
        }

        if (selectedRowCount > 2 && grid > 0){

          println("There are " + grid + " continuous blocks selected on row " + i + ".");
        }

      println("Row " + i + " has " + selectedRowCount + " cells selected.");

       selectedRowCount = 0;
        grid = 0;
      }

      for (int i = 0; i < intColumnCount; i++){
        for (int j = 0; j < intRowCount; j ++){

          if (intGrid[j][i] == 1){

            selectedColumnCount ++;
          }
        }

        println("Column " + i + " has " + selectedColumnCount + " cells selected.");
        selectedColumnCount = 0;
      }

      blnPrintGrid = false;
    }
  }

  public void mousePressed(){
  
    mouseX = mouseX / (intCellWidth + intCellMargin);
    mouseY = mouseY / (intCellHeight + intCellMargin);
    blnGridPressed = true;
    blnPrintGrid = true;
  }
  
  // grid resets if r key is pressed
  public void keyPressed(){
    if (key == 'r'){

      blnGridReset = true;
    }
  }

    
  public void keyReleased(){
    if (key == 'r'){

      blnGridReset = false;
    }
  }
}