
import org.newdawn.slick.*;

public class examples extends BasicGame {

    SpriteSheet sprite;
    Animation ani[] = new Animation[4];
    Image walk[][] = new Image[4][4];
    Image stopImage[] = new Image[4];

    int guyx = 100, guyy = 100;
    int dir = 3; //0=D, 1=L, 2=U, 3=R

    boolean stop = true;

    public examples(String title) {
        super(title);
    }

    public void init(GameContainer gc) throws SlickException {
        sprite = new SpriteSheet("images/george.png", 48, 48);
        sprite.startUse();
        for (int i = 0; i < 4; i++) {
            stopImage[i] = sprite.getSubImage(i, 0);
            for (int j = 0; j < 4; j++) {
                walk[i][j] = sprite.getSubImage(i, j);
            }
            ani[i] = new Animation(walk[i], 100); //100 speed
        }
    }

    public void update(GameContainer gc, int i) throws SlickException {
        Input in = gc.getInput();
        stop = false;
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
            stop = true;
        }
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        if (stop) {
            ani[dir].stop();
            stopImage[dir].draw(guyx, guyy);
        }
        else{
            ani[dir].start();
            ani[dir].draw(guyx, guyy);
        }
        
    }

    public static void main(String args[]) throws SlickException {
        examples game = new examples("Testing Game");
        AppGameContainer app = new AppGameContainer(game);
        app.setDisplayMode(800, 600, false);
        app.setShowFPS(false);
        app.setTargetFrameRate(100);
        app.start();
    }

}
