package Perceptron;


import java.util.*;
public class Main {

    public static void main(String[] args) {
        Map<List<Double>,String> trainSet = Reader.read("/Users/mariatanchyn/Downloads/Progect_Nai/data/train_Perceptron.csv");
        Map <List<Double>,String> testSet = Reader.read("/Users/mariatanchyn/Downloads/Progect_Nai/data/test_Perceptron.csv");

        TreeSet<String> classes = readingClasses(trainSet);
        Perceptron perceptron = new Perceptron(0.03, classes);
        perceptron.train(trainSet);

        Set<List<Double>> XTest = testSet.keySet();
        List<Integer> predictions = perceptron.predictTest(XTest);
        System.out.println("Test set accuracy: " + perceptron.evaluate(testSet, predictions));
        input(perceptron);
    }

    private static void input(Perceptron perceptron) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (true) {
            System.out.print("Please, enter your own vector ('1,2,3,4,5'): ");
            input = scanner.nextLine();

            if (input.toLowerCase().equals("quit")) {
                break;
            }

            List<Double> processedInput = processRawInput(input);
            if (processedInput == null) {
                System.out.println("Incorrect input");
                continue;
            }
            System.out.println(perceptron.userPredict(processedInput));
        }
    }

    private static List<Double> processRawInput(String input) {
        List<Double> output = new ArrayList<>();
        String[] arr = input.split(",\\s*");
        for (String s: arr) {
            try {
                output.add(Double.parseDouble(s));
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return output;
    }

    public static TreeSet<String> readingClasses(Map<List<Double>,String> classes){
        TreeSet<String> someClasses = new TreeSet<>();

        for (Map.Entry<List<Double>, String> pair: classes.entrySet()) {
            someClasses.add(pair.getValue());
            if (someClasses.size() == 2) {
                return someClasses;
            }
        }
        return someClasses;
    }
}
