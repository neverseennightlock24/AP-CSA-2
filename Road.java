package Personal;

public class Road {
    private String toNode;
    double length;

    public Road(String toNode, double length) {
        this.toNode = toNode;
        this.length = length;
    }

    public String getToNode() {
        return toNode;
    }

    public double getLength() {
        return length;
    }
}
