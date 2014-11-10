/**
 * Created by Admin on 22.09.2014.
**/
import java.util.Scanner;
public class game {
    public static void main(String[] args) throws Throwable
    {
        try {

            int size;
            //узнаем размер игры
            System.out.println("Введите размер");
            Scanner in = new Scanner(System.in);
            size = in.nextInt();
            //создаем окно по размеру игры и делаем его видимым
            new GameJFrame(size).setVisible(true);
        }
         catch(RuntimeException e)
        {
            System.out.println("Возникла ошибка" + e);
        }


    }

}
