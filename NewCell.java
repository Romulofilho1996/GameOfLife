import java.awt.*;
import javax.swing.*;

public class NewCell extends JPanel{
	
	public void paintComponent(Graphics g)
    {
        Color c1 = new Color(215,50,50);

        super.paintComponent(g);
        g.setColor(c1);
        g.drawOval(10,0,20,20);
        g.fillOval(10,0,20,20);
    }

    public Dimension getPreferredSize() {
        return new Dimension(40,40);
    }

}