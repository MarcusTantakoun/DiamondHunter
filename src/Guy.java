
import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

public class Guy {

    private Animation ani[] = new Animation[4];
    private Image walk[][] = new Image[4][4];
    private Image stopImage[] = new Image[4];
    private SpriteSheet guy;
    private boolean stopped;
    private Rectangle hitbox;
    private int dir;

    public Guy(int x, int y) throws SlickException {
        guy = new SpriteSheet("images/george.png", 48, 48);
        guy.startUse();
        for (int i = 0; i < 4; i++) {
            stopImage[i] = guy.getSubImage(i, 0);
            for (int j = 0; j < 4; j++) {
                walk[i][j] = guy.getSubImage(i, j);
            }
            ani[i] = new Animation(walk[i], 100);
        }
        guy.endUse();
        hitbox = new Rectangle(x, y, 26, 30);
        stopped = true;
        dir = 3;
    }

    public void move(Input in, ArrayList<Rectangle> walls) {
        stopped = false;
        int guyx = (int) hitbox.getX();
        int guyy = (int) hitbox.getY();
        int origx = guyx, origy = guyy;

        if (in.isKeyDown(Input.KEY_RIGHT)) {
            guyx++;
            dir = 3;
        } else if (in.isKeyDown(Input.KEY_LEFT)) {
            guyx--;
            dir = 1;
        } else if (in.isKeyDown(Input.KEY_DOWN)) {
            guyy++;
            dir = 0;
        } else if (in.isKeyDown(Input.KEY_UP)) {
            guyy--;
            dir = 2;
        } else {
            stopped = true;
        }
        hitbox.setX(guyx);
        hitbox.setY(guyy);

        if (isHitting(walls)) {
            hitbox.setX(origx);
            hitbox.setY(origy);
        }
    }
    
    public boolean isHitting(ArrayList<Rectangle> rect){
        for(Rectangle r: rect){
            if(hitbox.intersects(r)){
                return true;
            }
        }
        return false;
    }
    
    public boolean isHitting(GameObject go){
        return hitbox.intersects(go.getHitbox());
    }
    
    public void draw(){
        if(stopped){
            ani[dir].stop();
            stopImage[dir].draw(hitbox.getX() - 12, hitbox.getY() - 12);
        }
        else{
            ani[dir].start();
            ani[dir].draw(hitbox.getX() - 12, hitbox.getY() - 12);
        }
    }
}
