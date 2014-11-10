package com.company;
/**
 * Created by Паша on 10.11.2014.
 */
class Model{
    int w=10, h=10, r=50;
    boolean model[][] = new boolean[h][w];
    Model(){
        for(int i=0;i<h;i++){
            for(int j=0;j<w;j++)
                model[i][j]=false;
        }

    }
    public void do_iteration(){
        boolean copy[][] = new boolean[h][w];
        int i;
        int j;
        int a; //i-1
        int b; //i+1
        int c; //j-1
        int d; //j+1
        int n;
        for(i=0;i<h;i++)
        {
            for(j=0;j<w;j++)
            {
                a=(i==0)? h-1 : i-1;
                b=(i==h-1)? 0 : i+1;
                c=(j==0)? w-1 : j-1;
                d=(j==w-1)? 0 : j+1;

                n=0;
                if(model[a][c]) n++;
                if(model[a][j]) n++;
                if(model[a][d]) n++;
                if(model[i][c]) n++;
                if(model[i][d]) n++;
                if(model[b][c]) n++;
                if(model[b][j]) n++;
                if(model[b][d]) n++;

                if(!model[i][j])
                {

                    copy[i][j]=(n==3);
                }
                else
                {
                    copy[i][j]=(n==3 || n==2);
                }
            }
        }
        model = copy;
    }
}

