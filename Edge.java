public class Edge {
    
    private String start;
    private String end;
    private int weight;

    public Edge(String inStart, String inEnd, int inWeight) {
        start = inStart;
        end = inEnd;
        weight = inWeight;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public int getWeight() {
        return weight;
    }

    public void setStart(String input) {
        start = input;
    }

    public void setEnd(String input) {
        end = input;
    }

    public void setWeight(int input) {
        weight = input;
    }
}
