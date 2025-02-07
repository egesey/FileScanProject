package Project3Data2112;

public class Edge {

    String toCity;
    int weight;

    Edge(String toCity, int weight) {
        this.toCity = toCity;
        this.weight = weight;
    }

    public String getToCity() {
        return toCity;
    }

    public int getWeight() {
        return weight;
    }
 
    public void setToCity(String toCity) {
        this.toCity = toCity;
    }
  
    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge[toCity=" + toCity + ", weight=" + weight + "]";
    }
 
}
