import javax.swing.*;
import java.awt.*;

public class Cell extends JPanel {
    private boolean alive;

    public Cell() {
	alive = false;
    }

    public void set(boolean celula) {
    alive = celula; 
    }

    public boolean isAlive() {
	return alive;
    }

    public void kill() {
	this.alive = false;
    }
	
    public void revive() {
	this.alive = true;
    }
}
