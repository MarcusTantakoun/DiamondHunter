
import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

public class collectItem extends GameObject {

    int xMove, yMove;

    public collectItem(int x, int y, String s) throws SlickException {
        super(x, y, s);
        xMove = (int) (Math.random() * 2 + 1) % 2 == 0 ? -1 : 1;
        yMove = (int) (Math.random() * 2 + 1) % 2 == 0 ? -1 : 1;
    }

    public void place(ArrayList<Rectangle> barriers) {
        boolean hitting;

        do {
            hitting = false;
            hitbox.setX((int) (Math.random() * 760));
            hitbox.setY((int) (Math.random() * 600));
            for (Rectangle barrier : barriers) {
                if (hitbox.intersects(barrier)) {
                    hitting = true;
                }
            }
        } while (hitting);
    }

    @Override
    public void move(ArrayList<Rectangle> barriers) {
        int xloc = (int) hitbox.getX();
        int yloc = (int) hitbox.getY();

        xloc += xMove;
        yloc += yMove;

        hitbox.setX(xloc);
        boolean hitting = false;

        for (Rectangle barrier : barriers) {
            if (hitbox.intersects(barrier) || xloc < 10 || xloc > 780) {
                hitting = true;
            }
        }

        if (hitting) {
            xMove = -xMove;
            xloc += xMove;
            hitbox.setX(xloc);
        }

        hitting = false;
        hitbox.setY(yloc);
        for (Rectangle barrier : barriers) {
            if (hitbox.intersects(barrier) || yloc < 10 || yloc > 620) {
                hitting = true;
            }
        }
        if (hitting) {
            yMove = -yMove;
            yloc += yMove;
            hitbox.setY(yloc);
        }

    }

}
