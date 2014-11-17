import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Паша on 15.11.2014.
 */
class View extends JFrame {
    Model model=new Model();
    int width=model.getWidth();
    int height=model.getHeight();
    int cellSize=model.getCellSize();

    ActionListener timerListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.doIterate();
            repaint();
        }
    };

    View(String s){
        super(s);
        setSize(width*cellSize+100,height*cellSize+100);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Timer timer= new Timer(1000,timerListener);
        timer.start();
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
