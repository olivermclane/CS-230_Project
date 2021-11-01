import java.awt.*;

public class ScrollingBackground {

    // Two copies of the background image to scroll
    private final Background backOne;
    private final Background backTwo;

    public ScrollingBackground() {
        backOne = new Background(0, +700);
        backTwo = new Background(0, +backOne.getImageHeight() + 700);
    }

    public void loadBackground(Graphics g) {

        backOne.draw(g);
        backTwo.draw(g);

    }

}
