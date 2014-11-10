/**
 * Created by Паша on 10.11.2014.
 */
class Model{
    int width=200, height=200, cellSize=4;

    boolean[][] picture = new boolean[width][height];

    boolean[][] copy = new boolean[width][height];

    Model(){
        for(int i=0;i<height; i++){
            for(int j=0;j<width; j++){

               picture[i][j]=true;
            }
        }
    }
    public  void doIterate(){
        for(int i=0;i<height; i++){
            for(int j=0;j<width; j++){

                if(picture[i][j]) {

                    Point point = new Point(i, j, width, height);

                    point.doIterate();


                        int i1 = (int) ((point.x - point.ax) * width / (point.bx - point.ax));
                        int j1 = (int) ((point.y - point.ay) * height / (point.by - point.ay));

                    if(i1>=0 & i1<height & j1>=0 & j1<width) {
                        copy[i][j] = picture[i1][j1];
                    }

                    else{
                            copy[i][j] = false;
                    }

                }
            }
        }

            picture=copy;
    }
}
