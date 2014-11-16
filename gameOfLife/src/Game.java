/**
 * Created by Admin on 22.09.2014.
 **/
import java.util.Scanner;
public class Game {
    public static void main(String[] args) throws Throwable
    {
        try {

            new GameJFrame().setVisible(true);
        }
        catch(RuntimeException e)
        {
            System.out.println("Возникла ошибка" + e);
        }


    }

}