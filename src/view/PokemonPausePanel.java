package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import static utils.PokemonUtils.EXIT_ACTION;
import static utils.PokemonUtils.HEIGHT_BUTTON;
import static utils.PokemonUtils.REPLAY_ACTION;
import static utils.PokemonUtils.RESUME_ACTION;
import static utils.PokemonUtils.WIDTH_BUTTON;

public class PokemonPausePanel extends PokemonBackground{
	private JPanel buttonPanel;
    private JPanel graphicsPanel;
    private PokemonButton btnResumeGame;
    private PokemonButton btnReplayGame;
    private PokemonButton btnExitGame;

    public PokemonPausePanel(PokemonListener listener) {
        this.setVisible(false);
        this.setLayout(new BorderLayout());
        this.setBackgroundImage("/resources/background.png");
        
        btnResumeGame = new PokemonButton("/resources/resume.png", WIDTH_BUTTON, HEIGHT_BUTTON, RESUME_ACTION, listener);
        btnReplayGame = new PokemonButton("/resources/replay.png", WIDTH_BUTTON, HEIGHT_BUTTON, REPLAY_ACTION, listener);
        btnExitGame = new PokemonButton("/resources/exit.png", WIDTH_BUTTON, HEIGHT_BUTTON, EXIT_ACTION, listener);
        
        int countButton = 3;
        int distanceBetweenButton = 20;
        buttonPanel = new JPanel(new GridLayout(countButton, 1, 0, distanceBetweenButton));
        buttonPanel.setOpaque(false);
        buttonPanel.setPreferredSize(new Dimension(WIDTH_BUTTON, (HEIGHT_BUTTON * countButton) + (distanceBetweenButton * (countButton - 1))));
        buttonPanel.add(btnResumeGame);
        buttonPanel.add(btnReplayGame);
        buttonPanel.add(btnExitGame);
        
        graphicsPanel = new JPanel(new GridBagLayout());
        graphicsPanel.setOpaque(false);
        graphicsPanel.add(buttonPanel);
        
        this.add(graphicsPanel, BorderLayout.CENTER);
    }
}
