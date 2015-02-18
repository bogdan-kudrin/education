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

    public static void parseFile() {
        Date startDate = new Date();
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\magneticFieldFiles\\vtkField.vtk"));
            String line = br.readLine();
            int lineCounter = 1;

            while (lineCounter < 5) {
                lineCounter++;
                line = br.readLine();
            }

            String[] lineStrParams = line.split(" ");

            BaseCounter.areaSizeX = Integer.parseInt(lineStrParams[1]);
            BaseCounter.areaSizeY = Integer.parseInt(lineStrParams[2]);
            BaseCounter.areaSizeZ = Integer.parseInt(lineStrParams[3]);

            while (lineCounter < 7) {
                lineCounter++;
                line = br.readLine();
            }

            lineStrParams = line.split(" ");

            BaseCounter.stepX = Integer.parseInt(lineStrParams[1]);
            BaseCounter.stepY = Integer.parseInt(lineStrParams[2]);
            BaseCounter.stepZ = Integer.parseInt(lineStrParams[3]);

            BaseCounter.scalefactorX = 1000.0 / BaseCounter.stepX;
            BaseCounter.scalefactorY = 1000.0 / BaseCounter.stepY;
            BaseCounter.scalefactorZ = 1000.0 / BaseCounter.stepZ;

            while (lineCounter < 9) {
                lineCounter++;
                line = br.readLine();
            }

            Scanner s = new Scanner(br);
            BaseCounter.initField();

            for (int k = 0; k < BaseCounter.areaSizeZ; k += 1) {
                for (int j = 0; j < BaseCounter.areaSizeY; j += 1) {
                    for (int i = 0; i < BaseCounter.areaSizeX; i += 1) {
                        BaseCounter.fieldDistr[i][j][k] = new Vector3d(Double.parseDouble(s.next()), Double.parseDouble(s.next()), Double.parseDouble(s.next()));
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeParsedFieldDistribution() {
        try {
            File file = new File("C:\\magneticFieldFiles\\vtkFieldParseTest.vtk");
            file.getParentFile().mkdirs();
            BufferedWriter vtkFieldWriter = new BufferedWriter(new FileWriter(file));

            vtkFieldWriter.write("# vtk DataFile Version 2.0\n" +
                    "Cube example\n" +
                    "ASCII\n" +
                    "DATASET STRUCTURED_POINTS\n" +
                    "DIMENSIONS " + BaseCounter.areaSizeX + " " + BaseCounter.areaSizeY + " " + BaseCounter.areaSizeZ + "\n" +
                    "ORIGIN 0 0 0\n" +
                    "SPACING " + BaseCounter.stepX + " " + BaseCounter.stepY + " " + BaseCounter.stepZ + "\n" +
                    "POINT_DATA " + BaseCounter.areaSizeX * BaseCounter.areaSizeY * BaseCounter.areaSizeZ + "\n" +
                    "VECTORS vectors double\n");
            int f = -1;
            for (int k = 0; k < BaseCounter.areaSizeZ; k += 1) {
                for (int j = 0; j < BaseCounter.areaSizeY; j += 1) {
                    for (int i = 0; i < BaseCounter.areaSizeX; i += 1) {
                        Vector3d value = BaseCounter.fieldDistr[i][j][k];
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
