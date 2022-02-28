package view;

import java.awt.Color;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import model.PokemonNode;
import static utils.PokemonUtils.HEIGHT_ICON;
import static utils.PokemonUtils.WIDTH_ICON;

public class PokemonButtonIcon extends JButton{
	private PokemonNode node;

    public PokemonButtonIcon(PokemonNode node){
        this.node = node;
        
        int r = node.getRow();
        int c = node.getCol();
        int v = node.getValue();
        Icon icon = getIcon(v);
        
        this.setBorder(null);
        this.setOpaque(false);
        this.setIcon(icon);
        this.setActionCommand(r + "," + c);
    }
    
    protected void setDisable() {
        this.setIcon(null);
        this.setEnabled(false);
    }
    
    protected void drawBorder() {
        this.setBorder(new LineBorder(Color.RED));
    }
    
    private Icon getIcon(int index){
        //lấy ảnh ra
        Image image = new ImageIcon(getClass().getResource("/resources/" + index + ".png")).getImage();
        //tạo icon có độ dài và rộng đã cho + căn chỉnh
        Icon icon = new ImageIcon(image.getScaledInstance(WIDTH_ICON, HEIGHT_ICON, image.SCALE_SMOOTH));
        return icon;
    }
}
