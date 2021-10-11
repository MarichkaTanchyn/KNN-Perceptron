package KNN;
import java.util.Arrays;
import java.util.Objects;

public class Iris {

    private double[] parametres;
    private String type;

    public Iris(double[] parametres, String type) {
        this.parametres = parametres;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Iris iris = (Iris) o;
        return Arrays.equals(parametres, iris.parametres) &&
                Objects.equals(type, iris.type);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(type);
        result = 31 * result + Arrays.hashCode(parametres);
        return result;
    }

    @Override
    public String toString() {
        return "Iris{" +
                "parametres=" + Arrays.toString(parametres) +
                ", type='" + type + '\'' +
                '}';
    }

    public double[] getParametres() {
        return parametres;
    }

    public void setParametres(double[] parametres) {
        this.parametres = parametres;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}