package KNN;


import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String TRAIN_SET_PATH = "./data/train.csv";
    private static final String TEST_SET_PATH = "./data/test.csv";


    public static void main(String[] args) {
        String trainSetInput = TRAIN_SET_PATH;
        String testSetInput = TEST_SET_PATH;

        Scanner sc1 = new Scanner(System.in);
        Scanner scStr = new Scanner(System.in);

        System.out.print("Enter k: ");
        int k = sc1.nextInt();

        System.out.print("Enter train set file name: ");

        String input = scStr.nextLine();
        if (!input.equals("")) {
            trainSetInput = input;
        }

        System.out.print("Enter test set file name: ");

        input = scStr.nextLine();
        if (!input.equals("")) {
            testSetInput = input;
        }

        sc1.close();
        scStr.close();

        List<Iris> trainSet = Reader.read(trainSetInput);
        List<Iris> testSet = Reader.read(testSetInput);
        new KNN(k).predict(trainSet,testSet);
    }


}
