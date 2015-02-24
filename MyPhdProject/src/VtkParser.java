import javax.vecmath.Vector3d;
import java.io.*;
import java.util.Date;
import java.util.Scanner;

/**
 * User: BKudrin
 * Date: 13.02.2015
 * Time: 18:42
 */
public class VtkParser {

    BaseCounter baseCounter = new BaseCounter();
    
    public void parseFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\magneticFieldFiles\\vtkField.vtk"));
            String line = br.readLine();
            int lineCounter = 1;

            while (lineCounter < 5) {
                lineCounter++;
                line = br.readLine();
            }

            String[] lineStrParams = line.split(" ");

            baseCounter.areaSizeX = Integer.parseInt(lineStrParams[1]);
            baseCounter.areaSizeY = Integer.parseInt(lineStrParams[2]);
            baseCounter.areaSizeZ = Integer.parseInt(lineStrParams[3]);

            while (lineCounter < 7) {
                lineCounter++;
                line = br.readLine();
            }

            lineStrParams = line.split(" ");

            baseCounter.stepX = Integer.parseInt(lineStrParams[1]);
            baseCounter.stepY = Integer.parseInt(lineStrParams[2]);
            baseCounter.stepZ = Integer.parseInt(lineStrParams[3]);

            baseCounter.scalefactorX = 1000.0 / baseCounter.stepX;
            baseCounter.scalefactorY = 1000.0 / baseCounter.stepY;
            baseCounter.scalefactorZ = 1000.0 / baseCounter.stepZ;

            while (lineCounter < 9) {
                lineCounter++;
                br.readLine();
            }

            Scanner s = new Scanner(br);
            baseCounter.initField();

            for (int k = 0; k < baseCounter.areaSizeZ; k += 1) {
                for (int j = 0; j < baseCounter.areaSizeY; j += 1) {
                    for (int i = 0; i < baseCounter.areaSizeX; i += 1) {
                        baseCounter.fieldDistr[i][j][k] = new Vector3d(Double.parseDouble(s.next()), Double.parseDouble(s.next()), Double.parseDouble(s.next()));
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeParsedFieldDistribution() {
        try {
            File file = new File("C:\\magneticFieldFiles\\vtkFieldParseTest.vtk");
            file.getParentFile().mkdirs();
            BufferedWriter vtkFieldWriter = new BufferedWriter(new FileWriter(file));

            vtkFieldWriter.write("# vtk DataFile Version 2.0\n" +
                    "Cube example\n" +
                    "ASCII\n" +
                    "DATASET STRUCTURED_POINTS\n" +
                    "DIMENSIONS " + baseCounter.areaSizeX + " " + baseCounter.areaSizeY + " " + baseCounter.areaSizeZ + "\n" +
                    "ORIGIN 0 0 0\n" +
                    "SPACING " + baseCounter.stepX + " " + baseCounter.stepY + " " + baseCounter.stepZ + "\n" +
                    "POINT_DATA " + baseCounter.areaSizeX * baseCounter.areaSizeY * baseCounter.areaSizeZ + "\n" +
                    "VECTORS vectors double\n");
            int f = -1;
            for (int k = 0; k < baseCounter.areaSizeZ; k += 1) {
                for (int j = 0; j < baseCounter.areaSizeY; j += 1) {
                    for (int i = 0; i < baseCounter.areaSizeX; i += 1) {
                        Vector3d value = baseCounter.fieldDistr[i][j][k];
                        f++;
                        if (f == 5) {
                            f = 0;
                            vtkFieldWriter.write("\n");
                        }
                        vtkFieldWriter.write(value.x + " " +
                                value.y + " " +
                                value.z + '\u0009');
                    }
                }
            }

            vtkFieldWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
