package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import model.PokemonGame;
import static utils.PokemonUtils.MAX_TIME;
import static utils.PokemonUtils.WINDOW_HEIGHT;
import static utils.PokemonUtils.WINDOW_WIDTH;

import java.awt.Image;

public class PokemonFrame extends JFrame{
    private PokemonMenuPanel menuPanel;
    private PokemonPausePanel pausePanel;
	PokemonPlayPanel playPanel;
    PokemonTime timer;
    private PokemonGame game;
    private PokemonListener listener;

    public PokemonFrame(PokemonGame game) {
        Image icon = (new ImageIcon(getClass().getResource("/resources/icon.png"))).getImage();
        this.setIconImage(icon);
        this.setTitle("Pokemon Game");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setLocationRelativeTo(null);
        
        this.game = game;
        this.listener = new PokemonListener(this);
        this.init();
        this.timer = new PokemonTime(this, MAX_TIME);
    }
    
    private void init() {
        menuPanel = new PokemonMenuPanel(listener);
        menuPanel.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        playPanel = new PokemonPlayPanel(listener);
        playPanel.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        playPanel.renderMap(game, listener);
        pausePanel = new PokemonPausePanel(listener);
        pausePanel.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.add(menuPanel);
        this.add(pausePanel);
        this.add(playPanel);
        menuPanel.setVisible(true);
    }

    public PokemonGame getGame() {
        return game;
    }
    
    protected void enablePausePanel() {
        menuPanel.setVisible(false);
        playPanel.setVisible(false);
        pausePanel.setVisible(true);
    }
    
    protected void enablePlayPanel() {
        menuPanel.setVisible(false);
        pausePanel.setVisible(false);
        playPanel.setVisible(true);
    }
    
    protected void newGame() {
        PokemonGame game = new PokemonGame();
        PokemonListener listener = new PokemonListener(this);
        this.game = game;
        this.listener = listener;
        this.timer.setTime(MAX_TIME);
        playPanel.refreshGame(game, listener);
    }
}
