package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;
import static utils.PokemonUtils.EXIT_ACTION;
import static utils.PokemonUtils.HEIGHT_BUTTON;
import static utils.PokemonUtils.PLAY_ACTION;
import static utils.PokemonUtils.WIDTH_BUTTON;

public class PokemonMenuPanel extends PokemonBackground{
	private JPanel buttonPanel;
    private JPanel graphicsPanel;
    private PokemonButton btnPlayGame;
    private PokemonButton btnExitGame;

    public PokemonMenuPanel(PokemonListener listener) {
        this.setVisible(false);
        this.setLayout(new BorderLayout());
        this.setBackgroundImage("/resources/background.png");
        
        btnPlayGame = new PokemonButton("/resources/play.png", WIDTH_BUTTON, HEIGHT_BUTTON, PLAY_ACTION, listener);
        btnExitGame = new PokemonButton("/resources/exit.png", WIDTH_BUTTON, HEIGHT_BUTTON, EXIT_ACTION, listener);
        
        int countButton = 2;
        int distanceBetweenButton = 20;
        buttonPanel = new JPanel(new GridLayout(countButton, 1, 0, distanceBetweenButton));
        buttonPanel.setOpaque(false);
        buttonPanel.setPreferredSize(new Dimension(WIDTH_BUTTON, (HEIGHT_BUTTON * countButton) + (distanceBetweenButton * (countButton - 1))));
        buttonPanel.add(btnPlayGame);
        buttonPanel.add(btnExitGame);
        
        graphicsPanel = new JPanel(new GridBagLayout());
        graphicsPanel.setOpaque(false);
        graphicsPanel.add(buttonPanel);
        
        this.add(graphicsPanel, BorderLayout.CENTER);
    }
}
