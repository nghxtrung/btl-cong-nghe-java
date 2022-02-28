package view;

import static utils.PokemonUtils.MAX_TIME;

public class PokemonTime extends Thread{
	private PokemonFrame mainFrame;
    private int time;

    public PokemonTime(PokemonFrame mainFrame, int time) {
        this.mainFrame = mainFrame;
        this.time = time;
    }

    protected void setTime(int time) {
        this.time = time;
    }
    
    protected int getTime() {
		return time;
	}

	@Override
    public void run() {
        while (true) {
            if (!mainFrame.playPanel.isPause() && this.time > 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.time--;
            }
            mainFrame.playPanel.updateTime(this.time);
            mainFrame.playPanel.updateProgressTime((int) ((double) this.time / MAX_TIME * 100));
            if (mainFrame.getGame().getCountNode() > 0 && this.time == 0) {
            	mainFrame.playPanel.showDialogGame(mainFrame, "Full time!\nDo you want play again?", "Lose");
          	}
        }
    }
}
