package mathset;

public class Model {
	int[][][] matrix = new int[1000][1000][3];
	double a, b;
	double x, y, newx, newy;
	public Model(double a,double b){
		this.a=a;
		this.b=b;
		for(int i=0; i<1000; i++){
			for(int j=0; j<1000; j++){
				matrix[i][j][0]=0;
				x = (i-500)/250;
				y = (j-500)/250;
				newx = 1 + y - a*x*x;
				newy = b*x;
				if((Math.abs(newx)>1.99)||(Math.abs(newy)>1.99)){
					matrix[i][j][1]=10000;
					matrix[i][j][2]=10000;
				}else{
					matrix[i][j][1]=(int) (250*newx+500);
					matrix[i][j][2]=(int) (250*newy+500);
				}
			}			
		}
	}
	public void iterate(){
		int[][] matrix2 = new int[1000][1000];
		for(int i=0; i<1000; i++){
			for(int j=0; j<1000; j++){
				if(matrix[i][j][0]==0){
					if(matrix[i][j][1]==10000){
						matrix2[i][j]=1;
					}else{
						matrix2[i][j]=matrix[matrix[i][j][1]][matrix[i][j][2]][0];
					}					
				}else{
					matrix2[i][j]=matrix[i][j][0]+1;
				}
			}			
		}
		for(int i=0; i<1000; i++){
			for(int j=0; j<1000; j++){
				matrix[i][j][0]=matrix2[i][j];
			}
		}
	}
}
