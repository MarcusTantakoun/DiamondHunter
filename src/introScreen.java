
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class introScreen extends BasicGameState {

    private Image mainImg;

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        mainImg = new Image("images/startImg.png");
    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input in = gc.getInput();
        if (in.isMousePressed(in.MOUSE_LEFT_BUTTON)) {
            sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
        }
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        mainImg.draw(0, 0);
    }

    public int getID() {
        return 0;
    }

}
