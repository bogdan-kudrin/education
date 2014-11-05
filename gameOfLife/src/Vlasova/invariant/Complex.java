/**
 * Created by Admin on 08.10.2014.
 */
public class Complex {
    private double Re;
    private double Im;

    public Complex(double _Re, double _Im){
        Re = _Re;
        Im = _Im;
    }

    Complex Sum (Complex Val){
        Complex Res = new Complex(Re + Val.Re, Im + Val.Im);
        return Res;
    }

    Double Mod(){
        return ( Math.sqrt(Re * Re + Im * Im));
    }

    Boolean Proverka(){
        if ((Math.abs(Re)<=2) || (Math.abs(Im)<=2)) return true;
        else return false;
    }

    Complex Sqr(){
        return( new Complex(Re*Re - Im*Im, 2 * Re * Im) );
    }

}
