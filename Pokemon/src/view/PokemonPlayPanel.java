package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import model.PokemonGame;
import static utils.PokemonUtils.COL_MATRIX;
import static utils.PokemonUtils.COL_MATRIX_ICON;
import static utils.PokemonUtils.HEIGHT_ICON;
import static utils.PokemonUtils.MAX_TIME;
import static utils.PokemonUtils.PAUSE_ACTION;
import static utils.PokemonUtils.ROW_MATRIX;
import static utils.PokemonUtils.ROW_MATRIX_ICON;
import static utils.PokemonUtils.WIDTH_ICON;

public class PokemonPlayPanel extends PokemonBackground{
	private JPanel matrixIconPanel;
    private JPanel graphicsPanel;
    private JPanel controlPanel;
    private JLabel lbTime;
    private JLabel lbScore;
    private JProgressBar progressTime;
    private PokemonButton btnPauseGame;
    private PokemonButtonIcon[][] pkBtn = new PokemonButtonIcon[ROW_MATRIX][COL_MATRIX];
    private boolean isPause;

    public PokemonPlayPanel(PokemonListener listener) {
        this.isPause = false;
        this.setVisible(false);
        this.setLayout(new BorderLayout());
        this.setBackgroundImage("/resources/play.jpg");
        
        this.createGraphicsPanel();
        
        lbTime = new JLabel(MAX_TIME + "");
        lbScore = new JLabel("0");
        progressTime = new JProgressBar(0, 100);
        progressTime.setValue(100);

        JPanel panelScore = new JPanel(new GridLayout());
        panelScore.add(new JLabel("Score: "));
        panelScore.add(lbScore);
        panelScore.setOpaque(false);

        JPanel panelTime = new JPanel(new GridLayout());
        panelTime.add(new JLabel("Time: "));
        panelTime.add(lbTime);
        panelTime.setOpaque(false);

        JPanel panelScoreAndTime = new JPanel(new BorderLayout());
        panelScoreAndTime.add(panelScore, BorderLayout.WEST);
        panelScoreAndTime.add(panelTime, BorderLayout.EAST);
        panelScoreAndTime.add(progressTime, BorderLayout.SOUTH);
        panelScoreAndTime.setOpaque(false);

        controlPanel = new JPanel(new BorderLayout(5, 0));
        controlPanel.setBorder(new EmptyBorder(10, 3, 0, 3));
        controlPanel.add(panelScoreAndTime, BorderLayout.CENTER);
        btnPauseGame = new PokemonButton("/resources/pause.png", 40, 40, PAUSE_ACTION, listener);
        controlPanel.add(btnPauseGame, BorderLayout.WEST);
        controlPanel.setOpaque(false);
        
        this.add(graphicsPanel, BorderLayout.CENTER);
        this.add(controlPanel, BorderLayout.NORTH);
    }
    
    private JPanel createGraphicsPanel() {
        matrixIconPanel = new JPanel(new GridLayout(ROW_MATRIX_ICON, COL_MATRIX_ICON));
        matrixIconPanel.setPreferredSize(new Dimension(WIDTH_ICON * COL_MATRIX_ICON, HEIGHT_ICON * ROW_MATRIX_ICON));
        matrixIconPanel.setOpaque(false);
        graphicsPanel = new JPanel(new GridBagLayout());
        graphicsPanel.setOpaque(false);
        graphicsPanel.add(matrixIconPanel);
        return graphicsPanel;
    }
    
    protected void renderMap(PokemonGame game, PokemonListener listener) {
        for (int i = 1; i < ROW_MATRIX  - 1; i++) {
            for (int j = 1; j < COL_MATRIX - 1; j++) {
                pkBtn[i][j] = new PokemonButtonIcon(game.getNode(i, j));
                pkBtn[i][j].addActionListener(listener);
                matrixIconPanel.add(pkBtn[i][j]);
            }
        }
    }
    
    protected void refreshGame(PokemonGame game, PokemonListener listener) {
        matrixIconPanel.removeAll();
        graphicsPanel.removeAll();
        this.add(createGraphicsPanel(), BorderLayout.CENTER);
        this.renderMap(game, listener);
        this.validate();
        this.updateScore("0");
        this.updateTime(MAX_TIME);
        this.updateProgressTime(100);
    }
    
    protected void showDialogGame(PokemonFrame mainFrame, String message, String title) {
		int select = JOptionPane.showOptionDialog(mainFrame, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (select == 0) {
	            this.setPause(false);
	            mainFrame.newGame();
		} else {
            System.exit(0);
		}
    }
    
    protected void updateScore(String score) {
        this.lbScore.setText(score);
    }

    protected void updateTime(int time) {
        this.lbTime.setText(time + "");
    }

    protected void updateProgressTime(int progress) {
        this.progressTime.setValue(progress);
    }

    public boolean isPause() {
        return isPause;
    }

    public void setPause(boolean isPause) {
        this.isPause = isPause;
    }
}
