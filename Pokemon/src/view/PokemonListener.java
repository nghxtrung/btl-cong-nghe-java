package view;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.PokemonPointLine;
import static utils.PokemonUtils.EXIT_ACTION;
import static utils.PokemonUtils.PAUSE_ACTION;
import static utils.PokemonUtils.PLAY_ACTION;
import static utils.PokemonUtils.REPLAY_ACTION;
import static utils.PokemonUtils.RESUME_ACTION;

public class PokemonListener implements ActionListener{
	private PokemonFrame mainFrame;
    private PokemonButtonIcon selectedBtnP1;
    private PokemonButtonIcon selectedBtnP2;
    private Point p1 = null;
    private Point p2 = null;
    private PokemonPointLine line;
    private int score;

    public PokemonListener(PokemonFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof PokemonButtonIcon) {
            String btnIndex = e.getActionCommand();
            int indexDot = btnIndex.lastIndexOf(",");
            int x = Integer.parseInt(btnIndex.substring(0, indexDot));
            int y = Integer.parseInt(btnIndex.substring(indexDot + 1, btnIndex.length()));
            if (p1 == null) {
                p1 = new Point(x, y);
                selectedBtnP1 = (PokemonButtonIcon) e.getSource();
                selectedBtnP1.drawBorder();
            } else {
                p2 = new Point(x, y);
                selectedBtnP2 = (PokemonButtonIcon) e.getSource();
                line = mainFrame.getGame().checkTwoPoint(p1, p2);
                if (line != null) {
                    mainFrame.getGame().getNode(p1.x, p1.y).setValue(0);
                    mainFrame.getGame().getNode(p2.x, p2.y).setValue(0);
                    selectedBtnP1.setDisable();
                    selectedBtnP2.setDisable();
                    line = null;
                    score += 10;
                    mainFrame.getGame().setCountNode(mainFrame.getGame().getCountNode() - 1);
                    mainFrame.playPanel.updateScore(score + "");
                }
                selectedBtnP1.setBorder(null);
                p1 = null;
                p2 = null;
                if (mainFrame.getGame().getCountNode() == 0) {
                    mainFrame.playPanel.setPause(true);
                    mainFrame.playPanel.showDialogGame(mainFrame, "You are winer!\nDo you want play again?", "Win");
                }
            }
        }
        if (e.getActionCommand() == PLAY_ACTION) {
            mainFrame.timer.start();
            mainFrame.enablePlayPanel();
        }
        if (e.getActionCommand() == PAUSE_ACTION) {
            mainFrame.playPanel.setPause(true);
            mainFrame.enablePausePanel();
        }
        if (e.getActionCommand() == RESUME_ACTION) {
            mainFrame.playPanel.setPause(false);
            mainFrame.enablePlayPanel();
        }
        if (e.getActionCommand() == REPLAY_ACTION) {
            mainFrame.playPanel.setPause(false);
            mainFrame.newGame();
            mainFrame.enablePlayPanel();
        }
        if (e.getActionCommand() == EXIT_ACTION) {
            System.exit(0);
        }
    }
}
