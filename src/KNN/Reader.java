package KNN;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    public static List<Iris> read(String path) {
        String row;
        String[] rowData;
        List<Iris> data = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while ((row = reader.readLine()) != null) {
                rowData = row.split("\t");

                data.add(new Iris(new double[]{
                        Double.parseDouble(rowData[0]),
                        Double.parseDouble(rowData[1]),
                        Double.parseDouble(rowData[2]),
                        Double.parseDouble(rowData[3])
                }, rowData[4]));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return data;
    }



}
