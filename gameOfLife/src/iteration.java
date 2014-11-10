/**
 * Created by Admin on 22.09.2014.
 */
public class iteration
{
    private Table model;

    public iteration () {
        model = new Table();
    }

    public iteration (int size)
    {
        model = new Table(size);
    }

    public iteration (int size, boolean [][]ar)
    {
        model = new Table(size, ar) ;
    }

    public void print()
    {
        for  (int i = 0; i < model.getSize(); i++)
        {
            for (int j = 0; j < model.getSize(); j++)
                if ( model.getOne(i,j)) System.out.print( "1 "); else System.out.print( "0 ");
            System.out.println("");
        }
    }

    public void ChangeZnach(int i, int k){
        model.setZnach(i,k,!model.getOne(i,k));
    }


    public void Done()
   {
        Table tmp = new Table(model.getSize(),model.getTable());
        int n = tmp.getSize();
        for (int i = 0; i <= n-1; i++)
          for (int j = 0; j <= n-1; j++)
          {
              int k = 0;
              for (int l = 1; l <= 8; l++)
              if (tmp.getNeighbour(i,j,l)) k++;
              if ( (k < 2) || (k > 3) ) model.setZnach(i,j,false);
              else if (k == 3) model.setZnach(i,j,true);
          }

    }

    public int size(){
        return model.getSize();
    }

    public boolean getZnach (int i, int j){
        return model.getOne(i, j);
    }

}
