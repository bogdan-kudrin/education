package gamelife;

import java.util.Random;

public class field {
	Random rand = new Random();
	int size;
	boolean[][] matrix;
		public	field(int size) {
		this.size=size;
		matrix = new boolean[size][size];
		for( int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				if(rand.nextInt(7)<3){
					matrix[i][j]=true;
				}
				else{
					matrix[i][j]=false;
				}
			}		
		}
	}
		public void iterate(){
			boolean[][] matrix2 = new boolean[size][size];
			int k;
			int p1;
			int q1;
			for(int i=0; i<size; i++){
				for(int j=0; j<size; j++){
					k=0;
					for(int p=-1; p<2; p++){
						for(int q=-1; q<2; q++){
							p1=p;
							q1=q;
							if(i+p1>size-1){
								p1=p1-size;
							}
							if(j+q1>size-1){
								q1=q1-size;
							}
							if(i+p1<0){
								p1=p1+size;
							}
							if(j+q1<0){
								q1=q1+size;
							}
							if((matrix[i+p1][j+q1])&&((p1!=0)||(q1!=0))){
								k+=1;
							}													
						}
						}
					if(matrix[i][j]){
						if((k==2)||(k==3)){
							matrix2[i][j]=true;
						}
						else{
							matrix2[i][j]=false;
						}
					}
					if(!matrix[i][j]){
						if(k==3){
							matrix2[i][j]=true;
						}
					}
				}
			}
			matrix=matrix2;
		}
	public void setalive(int x, int y, boolean state){
		matrix[x-1][y-1] = state;
	}

}
