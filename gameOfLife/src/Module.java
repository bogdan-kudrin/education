/**
 * Created by Света on 07.12.2014.
 */
public class Module {

        boolean [][]A=new boolean[500][500];
        Module () {
            for (int s = 0; s < 500; s++) {
                for (int z = 0; z < 500; z++) {
                    Point R = new Point(s, z);
                    A[s][z] = R.Run();
                }
            }
        }

}
