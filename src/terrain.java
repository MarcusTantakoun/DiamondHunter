
import org.newdawn.slick.*;
import java.util.ArrayList;
import org.newdawn.slick.geom.Rectangle;

public class terrain {

    private SpriteSheet grass;
    private boolean roadOn;
    private ArrayList<Rectangle> barriers = new ArrayList<Rectangle>();

    public terrain() throws SlickException {
        grass = new SpriteSheet("images/grass.png", 32, 32);

        barriers.add(new Rectangle(0, 0, 320, 160));
        barriers.add(new Rectangle(384, 0, 32 * 13, 160));
        barriers.add(new Rectangle(0, 224, 32 * 3, 32 * 13));
        barriers.add(new Rectangle(576, 224, 32 * 7, 32 * 13));

        barriers.add(new Rectangle(160, 224, 32 * 11, 32 * 13));

    }

    public void draw() {
        grass.startUse();
        Image lawn = grass.getSprite(0, 0);
        Image path = grass.getSprite(1, 2);
        Image stone = grass.getSprite(6, 5);

        for (int i = 0; i < 800; i += 32) {
            for (int j = 0; j < 160; j += 32) {
                if (i == 320 || i == 352) {
                    path.draw(i, j);
                } else {
                    lawn.draw(i, j);
                }
            }
        }

        for (int i = 0; i < 800; i += 32) {
            for (int j = 160; j < 224; j += 32) {
                path.draw(i, j);
            }
        }

        for (int i = 0; i < 800; i += 32) {
            for (int j = 224; j < 640; j += 32) {
                if (i == 96 || i == 128 || i == 512 || i == 544) {
                    path.draw(i, j);
                } else {
                    lawn.draw(i, j);
                }
            }
        }

        if (roadOn) {
            for (int i = 160; i <= 288; i += 32) {
                stone.draw(i, 384);
            }
            stone.draw(256, 416);
            stone.draw(288, 416);
        }
        grass.endUse();

    }

    public void drawGrid(Graphics g) {
        g.setColor(new Color(200, 200, 200));
        TrueTypeFont ttf = new TrueTypeFont(new java.awt.Font("Arial", 0, 10), true);
        for (int i = 0; i < 32 * 25; i += 32) {
            for (int j = 0; j < 32 * 20; j += 32) {
                Rectangle box = new Rectangle(i, j, 32, 32);
                g.draw(box);
                ttf.drawString(i + 3, j + 3, "" + i, Color.white);
                ttf.drawString(i + 3, j + 12, "" + j, Color.white);
            }
        }
        g.setColor(Color.red);
        for (Rectangle barrier : barriers) {
            g.draw(barrier);
        }
    }

    public void addExitRoad() {
        roadOn = true;
        barriers.remove(4);
        barriers.add(new Rectangle(160, 224, 32 * 11, 32 * 5 - 3));
        barriers.add(new Rectangle(320, 384, 32 * 6, 32 * 8));
        barriers.add(new Rectangle(256, 448, 32 * 2, 32 * 6));
        barriers.add(new Rectangle(160, 416 + 3, 32 * 3, 32 * 7 - 3));
    }
    
    public boolean hasExitRoad(){
        return roadOn;
    }
    
    public ArrayList<Rectangle> getBarriers(){
        return barriers;
    }

}
