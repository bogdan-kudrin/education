
public class Model {
	public int[][] table = new int[100][100];
	public int width;
	public int hieght;
    Model (int a,int b){   	
    		for (int i = 0; i < a; i++)
    			for (int j = 0; j < b; j++)
    				table[i][j] = 0;
    		width = a;
    		hieght = b;
    	}
    public void kill(int a, int b){
    		table[a][b] = 0;
    	}
    public void revitalize(int a, int b, int c){
    		table[a][b] = c;
    	}
    	
    	



}
