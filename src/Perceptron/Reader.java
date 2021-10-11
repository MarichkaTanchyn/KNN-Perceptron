package Perceptron;

import KNN.Iris;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Reader {
    public static Map <List<Double>,String> read(String path) {
        String row;
        String[] rowData;
        Map<List<Double>,String> data = new HashMap<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while ((row = reader.readLine()) != null) {
                rowData = row.split("\t");


                ArrayList<Double> inputs = new ArrayList<>(Arrays.asList(
                        Double.parseDouble(rowData[0]),
                        Double.parseDouble(rowData[1]),
                        Double.parseDouble(rowData[2]),
                        Double.parseDouble(rowData[3])));

                data.put(inputs,rowData[4]);

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return data;
    }


}
