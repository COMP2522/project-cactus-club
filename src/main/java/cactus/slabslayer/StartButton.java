package cactus.slabslayer;

public class StartButton extends Button {

    /**
     * Constructs a new button.
     *
     * @param window as a Window
     */
    public StartButton(Window window) {
        super(window);
    }

    /**
     * Executes the button's given function if clicked.
     */
    @Override
    public void execute() {
        Game.getGameInstance().currState = Game.State.PLAYING;
    }

}
