package wcscda.dojo;

import java.util.Stack;

public class HanoiTower {
  private static HanoiTower instance;

  private HanoiTowerDrawing boardDrawing;

  public Stack[] getStacks() {
    return stacks;
  }

  private Stack<Integer>[] stacks;
  private Integer selectedColumn;

  public HanoiTower(int nbDisks, HanoiTowerDrawing boardDrawing) {
    instance = this;
    this.boardDrawing = boardDrawing;

    stacks = new Stack[3];
    for(int i = 0; i < 3; ++i) {
      stacks[i] = new Stack();
    }
    boardDrawing = boardDrawing;
    for (int i = nbDisks; i > 0; --i) {
      stacks[0].add(i);
    }
  }

  public static HanoiTower getInstance() {
    return instance;
  }

  public void processKey(int index) {
    if (selectedColumn == null) {
      processFirstKey(index);
    } else {
      processSecondKey(index);
    }
  }

  private void processFirstKey(Integer index) {
    if(stacks[index].isEmpty()) {
      boardDrawing.drawMessage("ERROR : cannot select empty columns");
    }
    else {
      selectedColumn = index;
      boardDrawing.drawHighlight(index);
    }
  }

  private void processSecondKey(Integer index) {
    if(index == selectedColumn) {
      boardDrawing.drawHanoiTower();
      selectedColumn = null;
    }
    else {
      if(stacks[index].isEmpty()
              || (stacks[index].peek() > stacks[selectedColumn].peek()) )
      {
        doLegalMove(index);
      }
      else {
        boardDrawing.drawMessage("ERROR : illegal move");
      }
    }
  }

  private void doLegalMove(Integer index) {
    move(selectedColumn, index);
    selectedColumn = null;

    if(isFinished()) {
      boardDrawing.drawMessage("You win");
    }
    else {
      boardDrawing.drawHanoiTower();
    }
  }

  private boolean isFinished() {
    return stacks[0].isEmpty() && stacks[1].isEmpty();
  }


  private void move(Integer colFrom, Integer colTo) {
    stacks[colTo].add(stacks[colFrom].pop());
  }
}
