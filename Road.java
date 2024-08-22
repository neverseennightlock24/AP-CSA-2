package Personal;

public class Road {
    private String toNode;
    private String roadName;
    double length;
    double traffic;
    double speedLimit;

    public Road(String toNode, String roadName, double length, double traffic, double speedLimit) {
        this.toNode = toNode;
        this.roadName = roadName;
        this.length = length;
        this.traffic = traffic;
        this.speedLimit = speedLimit;
    }

    public Road(String toNode, String roadName, double length, double speedLimit) {
        this.toNode = toNode;
        this.roadName = roadName;
        this.length = length;
        this.speedLimit = speedLimit;
        this.traffic = speedLimit;
    }

    public String getToNode() {
        return toNode;
    }

    public String getRoadName() {
        return roadName;
    }

    public double getLength() {
        return length;
    }

    public double getTraffic() {
        return traffic;
    }

    public double getSpeedLimit() {
        return speedLimit;
    }

    public void setTraffic(double traffic) {
        this.traffic = traffic;
    }

    public void setSpeedLimit(double speedLimit) {
        this.speedLimit = speedLimit;
    }
}
