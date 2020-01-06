
import org.newdawn.slick.*;

public class terrainTest extends BasicGame {
    
    terrain map;
    public terrainTest(String title) {
        super(title);
    }

    public void init(GameContainer gc) throws SlickException {
        map = new terrain();
    }

    public void update(GameContainer gc, int i) throws SlickException {

    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        map.draw();
        map.drawGrid(g);
    }

    public static void main(String args[]) throws SlickException {
        terrainTest game = new terrainTest("Testing Game");
        AppGameContainer app = new AppGameContainer(game);
        app.setDisplayMode(800, 600, false);
        app.setShowFPS(false);
        app.setTargetFrameRate(100);
        app.start();
    }

}
