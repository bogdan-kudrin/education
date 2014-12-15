/**
 * Created by Пользователь on 15.12.2014.
 */
public class Model {
    int[][] model=new int[200][200];
    int iterationNumber=1;

    Model(){
        for(int i=0; i<200; i++){
            for(int j=0; j<200; j++){
                model[i][j]=0;
            }
        }
    }

    public void iterate(){

        for(int i=0; i<200; i++){
            for(int j=0; j<200; j++) {
                if (model[i][j] == 0) {
                    Point p = new Point(i, j);
                    p.iterate();
                    int i1 = p.getI();
                    int j1 = p.getJ();

                    if (i1 < 0 | i1 > 199 | j1 < 0 | j1 > 199 ) {
                         model[i][j]=iterationNumber;
                    }
                    else {
                        if(model[i1][j1]>0 & model[i1][j1]<iterationNumber){
                            model[i][j]=iterationNumber;
                        }
                    }
                }
            }
        }
      iterationNumber++;
    }

    public int getColor(int i,int j){
        if(model[i][j]>0) {
            return (206 - (model[i][j] % 4) * 50);
        }
        else
            return(0);
    }
}
