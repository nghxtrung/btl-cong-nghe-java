package view;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class PokemonButton extends JButton{
	public PokemonButton(String path, int width, int height, String action, PokemonListener listener) {
        Image image = new ImageIcon(getClass().getResource(path)).getImage();
        Icon icon = new ImageIcon(image.getScaledInstance(width, height, image.SCALE_SMOOTH));
        
        this.setIcon(icon);
        this.setBorder(null);
        this.setContentAreaFilled(false);
        this.setActionCommand(action);
        this.addActionListener(listener);
    }
}
