package wcscda.dojo;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/** Hello world! */
public class HanoiTowerApp extends JFrame {
  public HanoiTowerApp(int nbDisks) {
    HanoiTowerDrawing htw = new HanoiTowerDrawing(nbDisks);
    add(htw);
    htw.setHanoiTower(new HanoiTower(nbDisks, htw));

    setResizable(false);
    pack();
    setTitle("WCS - CDA - Hanoi Tower");
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public static int max(int[] a) {
    return myMax(a, 0);
  }

  public static int myMax(int[] a, int position) {
    if(position < a.length - 1) {
      return Math.max(
              a[position],
              myMax(a, position + 1)
      );
    }
    else {
      return a[position];
    }
  }

  public static void main(String[] args) {
    System.out.println(max(new int[] {8, 9,5, 6, 7}));

    int nbDisks = 4;
    EventQueue.invokeLater(
        () -> {
          new HanoiTowerApp(nbDisks).setVisible(true);
        });
  }
}
