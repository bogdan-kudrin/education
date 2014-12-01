/**
 * Created by Паша on 15.11.2014.
 */
class Model{
    int width=200, height=200, cellSize=4;

    int[][] picture = new int[width][height];

   int[][] copy = new int[width][height];

    int numberOfIteration=0;
    Model(){
        for(int i=0;i<height; i++){
            for(int j=0;j<width; j++){

                picture[i][j]=0;
            }
        }
    }
    public  void doIterate(){
        for(int i=0;i<height; i++){
            for(int j=0;j<width; j++){

                if(picture[i][j]==0) {

                    Point point = new Point(i, j, width, height);

                    point.doIterate();


                    int i1 = (int) (point.getX() * width / point.getDistanceX());
                    int j1 = (int) (point.getY() * height /point.getDistanceY());

                    if(i1>=0 & i1<height & j1>=0 & j1<width ) {
                        if( picture[i1][j1]==0) {
                            copy[i][j] = 0;
                        }
                        else{
                            copy[i][j] = numberOfIteration+1;
                        }
                    }

                    else{
                        copy[i][j] = numberOfIteration+1;
                    }
                }
            }
        }
        picture=copy;
        numberOfIteration=(numberOfIteration+1)%4;
    }

    public int getCell(int i, int j)
    {return picture[i][j];}

    public int getWidth()
    {return width;}

    public int getHeight()
    {return height;}

    public int getCellSize()
    {return cellSize;}
}


