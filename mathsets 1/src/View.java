/**
 * Created by Паша on 10.11.2014.
 */
import java.awt.*;
import javax.swing.*;

class View extends JFrame{
    Model model = new Model();
    View(String s){
        super(s);
        setSize(model.width*model.cellSize+100,model.height*model.cellSize+100);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void paint(Graphics g){

        for(int i=0;i<model.height; i++){
            for(int j=0;j<model.width; j++){
                g.setColor((model.picture[i][j])? Color.green : Color.white);
                g.fillRect(i*model.cellSize+50,j*model.cellSize+50,model.cellSize,model.cellSize);
            }
        }
    }
}