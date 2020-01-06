import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class gameLauncher extends StateBasedGame {

    public gameLauncher(String title) {
        super(title);
    }
    
    public void initStatesList(GameContainer gc) throws SlickException {
       this.addState(new introScreen());
       this.addState(new mainGame());
       this.addState(new gameOverScreen());
    }

    public static void main(String args[]) throws SlickException {
        gameLauncher game = new gameLauncher("Diamond Hunter");
        AppGameContainer app = new AppGameContainer(game);
        app.setDisplayMode(800, 600, false);
        app.setShowFPS(false);
        app.setTargetFrameRate(100);
        app.start();
    }

}
