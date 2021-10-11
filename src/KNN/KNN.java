package KNN;


import java.util.*;


public class KNN {
    private int k;

    public KNN(int k) {
        this.k = k;
    }

    public List<String> predict(List<Iris> trainSet,List<Iris> testSet) {
        List<String> predicted = new ArrayList<>();
        for (Iris iris: testSet) {
            predicted.add(predict(iris, trainSet));
        }
        System.out.println(accuracy(testSet, predicted));
        return predicted;
    }

    private String predict(Iris iris, List<Iris> trainSet) {
        Map<Iris, Double> distances = distances(iris,trainSet);
        distances = pickKFirst(distances);
        return pickClass(distances);
    }

    private double distance(Iris first, Iris second) {
        double dist = 0;
        for (int i = 0; i < first.getParametres().length ; i++) {
            dist += Math.pow((first.getParametres()[i] - second.getParametres()[i]),2);
        }
        return Math.sqrt(dist);
    }

    private Map<Iris, Double> distances(Iris iris, List<Iris> trainSet) {
        Map<Iris, Double> distances = new HashMap<>();

        for (int i = 0; i < trainSet.size() ; i++) {
            distances.put(trainSet.get(i), distance(iris,trainSet.get(i)));
        }

        distances = valueSort(distances);
        return distances;
    }

    private Map<Iris, Double> pickKFirst(Map<Iris, Double> distances) {
        Map<Iris, Double> map = new HashMap<>();
        int count = 0;
        for (Map.Entry<Iris, Double> pair: distances.entrySet()) {
            if (count < k){
                map.put(pair.getKey(), pair.getValue());
                count++;
            }
        }
        return map;
    }

    private String pickClass(Map<Iris, Double> distances) {
        String nameOfClass = "";

        int countofsetosa = 0;
        int countOfVersi = 0;
        int countOfVirgi = 0;
        for (Map.Entry<Iris, Double> pair: distances.entrySet()) {
            if (pair.getKey().getType().equals("Iris-setosa")) {
                countofsetosa++;
            }
            if (pair.getKey().getType().equals("Iris-versicolor")) {
                countOfVersi++;
            }
            if (pair.getKey().getType().equals("Iris-virginica")) {
                countOfVirgi++;
            }
        }
        if (countofsetosa > countOfVersi && countofsetosa > countOfVirgi) nameOfClass = "Iris-setosa";
        else if (countOfVersi > countofsetosa && countOfVersi > countOfVirgi) nameOfClass = "Iris-versicolor";
        else nameOfClass = "Iris-virginica";
        System.out.println(nameOfClass);
        return nameOfClass;
    }

    private double accuracy(List<Iris> testSet, List<String> predicted) {
        if (testSet.size() != predicted.size()){
            return -1;
        }
        double accuracy = 0.0;

        for (int i = 0; i < testSet.size() ; i++) {
            if (testSet.get(i).getType().equals(predicted.get(i))){
                accuracy++;
            }
        }

        return (accuracy/predicted.size()) * 100;
    }

    private static Map<Iris, Double> valueSort(final Map<Iris, Double> distances) {
        Map<Iris, Double> sorted = new TreeMap<>(Comparator.comparing(distances::get));
        sorted.putAll(distances);
        return sorted;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }
}
