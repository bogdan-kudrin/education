/**
 * Created by Admin on 22.09.2014.
 */
public class Table
{
    private int size;
    private boolean table[][];


    public Table(){
        size = 1;
        table = new boolean[1][1];
        table[0][0] = false;
    }

    public Table(int _size)
    {
        size = _size;
        table = new boolean[size][size];
        for (int i = 0; i < _size; i++)
           for (int j = 0; j < _size; j++)
            table[i][j]=false;
    }

    public Table(int _size, boolean ar[][])
    {
        size = _size;
        table = new boolean[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                table[i][j] = ar[i][j];
    }

    public void setZnach(int k, int l, boolean f)
    {
        if ((k >= 0) && ( l >= 0) && (k < size) && (l < size))
        table[k][l] = f;
    }

    public int getSize()
    {
        return size;
    }

    public boolean[][] getTable()
    {
        boolean ar[][] = new boolean[size][size];
        for (int i = 0; i < size; ++i)
            for (int j = 0; j < size; j++)
                ar[i][j]= table[i][j];
        return ar;
    }

    public boolean getOne(int k, int l)
    {
            if ((k >= 0) && ( l >= 0) && (k < size) && (l < size))
            return table[k][l];
        else
                throw new RuntimeException("Выход за границы массива");
    }

    public boolean getNeighbour(int k, int l, int i)
    {
        switch (i)
        {
            case 1:  return table[(size + k - 1) % size][(size + l - 1) % size];
            case 2:  return table[(size + k) % size][(size + l - 1) % size];
            case 3:  return table[(size + k + 1) % size][(size + l - 1) % size];
            case 4:  return table[(size + k -1) % size][(size + l) % size];
            case 5:  return table[(size + k+  1) % size][(size + l) % size];
            case 6:  return table[(size + k - 1) % size][(size + l + 1) % size];
            case 7:  return table[(size + k) % size][(size + l + 1) % size];
            case 8:  return table[(size + k + 1) % size][(size + l + 1) % size];
            default: return false;
        }

    }
}
