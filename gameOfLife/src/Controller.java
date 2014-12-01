
public class Controller {

		public void iterate(Model workModel){
	    
		int width1, hieght1, summ;
		Model new1 = new Model(workModel.width, workModel.hieght);
		for (int i = 0; i < workModel.width; i++)
			for (int j = 0; j < workModel.hieght; j++)
				new1.table[i][j] = 0;
		for (int i = 0; i < workModel.width; i++)
			for (int j = 0; j < workModel.hieght; j++)
			{
			summ = 0;
			for (int o = -1; o < 2; o++)
				for (int g = -1; g < 2; g++){
				if (o + i == -1) width1 = workModel.width - 1;
				else
					if (o + i == workModel.width) width1 = 0; else width1 = o + i;
				if (j + g == -1) hieght1 = workModel.hieght - 1;
				else
					if (j + g == workModel.hieght) hieght1 = 0; else hieght1 = j + g;
				summ = summ + workModel.table[width1][hieght1];
				}
	        summ=summ-workModel.table[i][j];
	        //cout << endl << "Summa " << summ<< endl;
			if (summ == 3) new1.table[i][j] = 1;
			if ((summ == 2) && (workModel.table[i][j] == 1)) new1.table[i][j] = 1;
			//new1.table[i][j] = ((summ+workModel.table[i][j])/3)*((6-(summ+workModel.table[i][j]))/3);
			}
		for (int i = 0; i < workModel.width; i++)
			for (int j = 0; j < workModel.hieght; j++)
				workModel.table[i][j] = new1.table[i][j];
	    //cout << endl;
	   // for (LL i = 0; i < k; i++){
		//	cout << endl;
		//for (LL j = 0; j < l; j++){
		//	cout << new1.table[i][j] << " ";
		//}
	    //}


	}

}
