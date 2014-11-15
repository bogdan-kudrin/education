import javax.swing.*;
import java.awt.*;

/**
 * Created by Паша on 15.11.2014.
 */
class View extends JFrame {
    Controller controller = new Controller(this);
    Model model=controller.getModel();
    int width=model.getWidth();
    int height=model.getHeight();
    int cellSize=model.getCellSize();
    View(String s){
        super(s);
        setSize(model.width*model.cellSize+100,model.height*model.cellSize+100);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void paint(Graphics g){

        for(int i=0;i<height; i++){
            for(int j=0;j<width; j++){
                g.setColor((model.getCell(i,j))? Color.green : Color.white);
                g.fillRect(i*cellSize+50,j*cellSize+50,cellSize,cellSize);
            }
        }
    }
}
