/**
 * Created by dan on 12/8/14.
 */
public class Complex {

    private double re;
    private double im;

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double abs() {
        return Math.hypot(re, im);
    }

    public Complex multiply(Complex a) {
        return new Complex(this.re * a.re - this.im * a.im, this.re * a.im + this.im * a.re);
    }

    public Complex add(Complex a) {
        return new Complex(this.re + a.re, this.im + a.im);
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }
}

