package Perceptron;

import java.util.*;

public class Perceptron {
    private final double a;
    private final ArrayList<Double> weights;
    private final TreeSet<String> classes;

    public Perceptron(double a, TreeSet<String> classes) {
        this.a = a;
        weights = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            weights.add(-5 + Math.random() * 10);
        }

        this.classes = classes;
    }

    private int predict(List<Double> input) {
        if (input.size() < weights.size()) {
            input.add(-1.0);
        }

        double net = 0;
        if (weights.size() != input.size()) {
            return -1;
        }

        for (int i = 0; i < weights.size(); i++) {
            net += weights.get(i) * input.get(i);
        }
        int y = 0;
        if (net >= 0) y = 1;
        return y;
    }

    public String userPredict(List<Double> input) {
        return mapIntToClass(predict(input));
    }


    private void train(List<Double> input,int d, int y) {
        for (int i = 0; i < weights.size(); i++) {
             weights.set(i, weights.get(i) + input.get(i) * (d-y) * a);
        }
    }

    public void train(Map<List<Double>,String> trainSet) {
        double accuracy = 0;
        List<Integer> predictions = new ArrayList<>();
        List<Integer> realClasses = new ArrayList<>();
        int prediction;
        while (accuracy <= 0.95) {
            for (Map.Entry<List<Double>, String> pair: trainSet.entrySet()) {
                prediction = predict(pair.getKey());
                train(pair.getKey(), mapClassToInt(pair.getValue()),prediction);
                predictions.add(prediction);
                realClasses.add(mapClassToInt(pair.getValue()));
            }
            accuracy = accuracy(predictions, realClasses);
            predictions.clear();
            realClasses.clear();
        }
    }

    public List<Integer> predictTest(Set<List<Double>> X) {
        List<Integer> predictions = new ArrayList<>();
        for (List<Double> input: X) {
            predictions.add(predict(input));
        }
        return predictions;
    }

    public double evaluate(Map<List<Double>, String> testSet, List<Integer> predictions) {
        Map<String, ArrayList<Integer>> classPredictionAccuracy = new HashMap<>();
        double accuracy = 0;
        int i = 0;
        for (Map.Entry<List<Double>, String> pair: testSet.entrySet()) {
            if (mapClassToInt(pair.getValue()) == predictions.get(i++)) {
                accuracy += 1;
                if (!classPredictionAccuracy.containsKey(pair.getValue())) {
                    classPredictionAccuracy.put(pair.getValue(), new ArrayList<>(List.of(1, 0)));
                } else {
                    ArrayList<Integer> tmp = classPredictionAccuracy.get(pair.getValue());
                    tmp.set(0, tmp.get(0) + 1);
                    classPredictionAccuracy.put(pair.getValue(), tmp);
                }
            } else {
                if (!classPredictionAccuracy.containsKey(pair.getValue())) {
                    classPredictionAccuracy.put(pair.getValue(), new ArrayList<>(List.of(0, 1)));
                } else {
                    ArrayList<Integer> tmp = classPredictionAccuracy.get(pair.getValue());
                    tmp.set(1, tmp.get(1) + 1);
                    classPredictionAccuracy.put(pair.getValue(), tmp);
                }
            }
        }
        for (Map.Entry<String, ArrayList<Integer>> pair: classPredictionAccuracy.entrySet()) {
            System.out.println(pair.getKey() + " " + pair.getValue());
        }
        return accuracy/predictions.size();
    }

    private int mapClassToInt(String className){
        if (classes.size() > 2){
            return -1;
        }
        if (classes.first().equals(className)) return 1;
        else return 0;
    }

    private String mapIntToClass(int className) {
        if (classes.size() > 2) {
            return "";
        }
        if (className == 1) {
            return classes.first();
        } else {
            return classes.last();
        }
    }


    private double accuracy(List<Integer> predictions, List<Integer> realClasses) {
        double accuracy = 0;
        if (predictions.size() != realClasses.size()) {
            return 0;
        }
        for (int i = 0; i < predictions.size(); i++) {
            if (predictions.get(i).equals(realClasses.get(i))) {
                accuracy += 1;
            }
        }
        return accuracy/predictions.size();
    }
}
