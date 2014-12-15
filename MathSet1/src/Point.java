/**
 * User: BKudrin
 * Date: 15.12.2014
 * Time: 22:01
 */
public class Point {
    double x;
    double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void transform() {
        //Сюда введите отображение Хенона
    }

    public boolean outOfField(){
        return (Math.abs(x) > 2) || (Math.abs(y) > 2);
    }
}
