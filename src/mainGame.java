
import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class mainGame extends BasicGameState {

    int timer = 0;
    Guy guy;
    ArrayList<GameObject> diamonds = new ArrayList();
    terrain terrain;
    GameObject house;
    GameObject ghost, ghost2;

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        guy = new Guy(0, 170);
        ghost = new Ghost(100, 170, "images/ghost.png");
        ghost2 = new Ghost(700, 170, "images/ghost.png");
        house = new collectItem(280, 400, "images/house.png");
        terrain = new terrain();

        for (int i = 0; i < 10; i++) {
            GameObject d = new collectItem(0, 0, "images/diamond.png");
            ((collectItem) d).place(terrain.getBarriers());
            diamonds.add(d);
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input in = gc.getInput();
        guy.move(in, terrain.getBarriers());
        timer++;

        for (int j = 0; j < diamonds.size(); j++) {
            if (guy.isHitting(diamonds.get(j))) {
                diamonds.remove(j);
                if (diamonds.isEmpty() && terrain.hasExitRoad() == false) {
                    terrain.addExitRoad();
                }
            }
        }

        if (guy.isHitting(ghost) || guy.isHitting(ghost2)) {
            gameOverScreen.setMessage("You were captured by the ghost - you LOSE!", 200);
            sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
        } else if (guy.isHitting(house)) {
            gameOverScreen.setMessage("You collected all the diamonds and made it home - you WIN!", 100);
            sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
        }

        if (timer == 5) {
            for (GameObject bd : diamonds) {
                bd.move(terrain.getBarriers());
            }
            timer = 0;
        }
        if (timer % 2 == 0) {
            ghost.move(terrain.getBarriers());
            ghost2.move(terrain.getBarriers());
        }

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        terrain.draw();
        if (terrain.hasExitRoad()) {
            house.draw();
            guy.draw();
            for (GameObject dm : diamonds) {
                dm.draw();
            }
            ghost.draw();
            ghost2.draw();
        }
    }

    public int getID() {
        return 1;
    }

}
