import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

public class Life extends JFrame {

    private Painter painter;

    public Life() {
        setSize(800, 700); // устанавливаем размеры окна
        setVisible(true); // на всякий случай делаем окно видимым
        setTitle("Life");  // устанавливаем имя окошка
        setDefaultCloseOperation(EXIT_ON_CLOSE);// устанавливаем дефолтное действие при закрытии приложения
        painter = new Painter(new Field(50));
        setContentPane(painter);
        //add(new CommandBar());

        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent ke) {
                if (painter.timer.isRunning()) {
                    System.out.println("not running");
                    painter.timer.stop();
                } else {
                    System.out.println("running");
                    painter.timer.start();
                }
            }
        });
    }

    /**
     * Точка входа программы на выполнение.
     */
    public static void main(String[] args) {
        new Life();
    }
}
