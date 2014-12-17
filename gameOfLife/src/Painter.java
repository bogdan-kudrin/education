import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Painter extends JPanel {

    public static final int DELAY = 100; // задержка перед следующим шагом
    private Field field;
    private int w, h, n;

    public final Timer timer = new Timer(DELAY, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            update();
        }
    });

    public Painter(final Field field) {
        n = field.n;

        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent me) {
                w = getWidth() / n;
                h = getHeight() / n;
                field.setAlive(me.getX() / w, me.getY() / h, true);
                paint();
            }

        });
        this.field = field;
    }

    public void update() {
        if (field == null) {
            return;
        }
        w = getWidth() / n;
        h = getHeight() / n;
        field.verifyCells();
        paint();
    }

    public void paint(){
        Graphics g = getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        double i = 0;
        for (Cell cell : field.cells) {
            if (cell.isAlive()) {
                g.setColor(Color.BLACK);
                g.fillOval((int) (i % n) * w, (int) (i / n) * h, w, h);
            }
            i++;
        }
    }

    @Override
    public void paintComponents(Graphics grphcs) {
        super.paintComponents(grphcs);
        update();
    }
}
