import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Паша on 14.11.2014.
 */
public class Controller {

    Timer timer;
    View view;
    Model model=new Model(10,10);

    int panelWidth;
    int panelHeight;
    int cellSize;


    ActionListener timerListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.doIteration();
            view.repaint();
        }

    };
    ActionListener stopListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           pause();
        }
    };
    ActionListener startListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            timer.restart();
            view.littlePanel.start.setEnabled(false);
            view.bigPanel.pause = false;
        }
    };

    ActionListener newFieldListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int width=0,height=0;

            try {
                width = Integer.valueOf(
                        view.northPanel.width.getText()
                );
            }catch (NumberFormatException e1) {return;}

            try {
                height = Integer.valueOf(
                        view.northPanel.height.getText()
                );
            }catch (NumberFormatException e1) {return;}

            pause();

            model=new Model(width,height);
            setModel();
            view.bigPanel.setModel(model);

            view.bigPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));
            view.pack();
            view.repaint();
        }
    };


    Controller(View view){
        this.view=view;
         setModel();
    }

    public void prepareForWork(){
        timer= new Timer(1000, timerListener);
        view.littlePanel.stop.addActionListener(stopListener);
        view.littlePanel.start.addActionListener(startListener);
        view.northPanel.newField.addActionListener(newFieldListener);

        timer.start();
        timer.stop();
    }
    public Model getModel()
    {return model;}

    public void setModel() {
       setPanelHeight(model.getHeight() * model.getCellSize() + 100);
       setPanelWidth(model.getWidth() * model.getCellSize() + 100);
       setCellSize(model.getCellSize());
    }

    public void setCellSize(int cellSize)
    { this.cellSize = cellSize;}

    public void setPanelHeight(int panelHeight)
    {this.panelHeight = panelHeight;}

    public void setPanelWidth(int panelWidth)
    {this.panelWidth = panelWidth;}


    public int getPanelWidth()
    {return panelWidth;}

    public int getPanelHeight()
    {return panelHeight;}


    public void findAndChangeCell(int x,int y){
        if (x > 50 & x < panelWidth - 50 & y > 50 & y < panelHeight - 50) {
            int j = (x - 50) / cellSize;
            int i = (y - 50) /cellSize;
            model.changeCell(i,j);
            view.repaint();
        }
    }


    public void setSpeed(int speed)
    { timer.setDelay(10000/speed);}

    public  void pause(){
        timer.stop();
        view.littlePanel.start.setEnabled(true);
        view.bigPanel.pause = true;
    }

}
