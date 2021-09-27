package org.wcscda.worms.board;

import java.awt.geom.Point2D;

public interface IMovableVisitor {
  void visit(AbstractMovable ab, Point2D prevPosition);

  void visit(ARBEWithGravity arbewg, Point2D prevPosition);
}
