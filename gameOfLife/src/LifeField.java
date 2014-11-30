
public class LifeField {
	int[][] field;
	int size;
	
	public LifeField(int sizeuwant){
		size=sizeuwant;
		field=new int[size][size];
		
	}
	public void next(){
		int[][] newField=new int[size][size];
		for(int i=0;i<size;i++)
			for(int j=0;j<size;j++){
				int neighborsAlive=0;
				for(int di=-1;di<2;di++)
					for(int dj=-1;dj<2;dj++)
						if((di!=0)||(dj!=0)) 
							neighborsAlive+=field[(i+di+size)%size][(j+dj+size)%size];
				if((neighborsAlive==3)||((neighborsAlive==2)&&(field[i][j]>0)))
					newField[i][j]=1;
				else
					newField[i][j]=0;
			}
		field=newField;
	}
}
