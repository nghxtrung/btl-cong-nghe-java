package view;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import static utils.PokemonUtils.WINDOW_HEIGHT;
import static utils.PokemonUtils.WINDOW_WIDTH;

public class PokemonBackground extends JPanel{
	protected Image backgroundImage;

    public PokemonBackground() {
        this.backgroundImage = null;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, WINDOW_WIDTH,WINDOW_HEIGHT,this);
    }

    public void setBackgroundImage(String pathImage){
        this.backgroundImage = new ImageIcon(getClass().getResource(pathImage)).getImage();
        this.repaint();
    }
}
