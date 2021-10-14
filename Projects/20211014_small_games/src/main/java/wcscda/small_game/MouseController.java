package wcscda.small_game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;

public class MouseController extends MouseAdapter {
    private final Board board;

    public MouseController(Board board) {
        this.board = board;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        board.redraw(new Drawable(){
            @Override
            public void draw(Graphics2D g, ImageObserver io) {
                int x = mouseEvent.getX();
                int y = mouseEvent.getY();

                g.setColor(Color.WHITE);
                g.drawLine(x - 30, y - 30, x + 30, y +30);
                g.drawLine(x + 30, y - 30, x - 30, y +30);
            }
        } );
    }

    /*@Override
    public void mousePressed(MouseEvent mouseEvent) {

    }*/
}
