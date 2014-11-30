
public class LifeGame {
	public void dosomething(){
	
	}
	public LifeField fieldWithGlider(int size,int x,int y){
		int[][] glider={
				{0,1,0},
		
				{0,0,1},
				{1,1,1}
		};
		LifeField result=new LifeField(size);
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				result.field[(x+i)%size][(y+j)%size]=glider[i][j];
		return result;
	}

}
