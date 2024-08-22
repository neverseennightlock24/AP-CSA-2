package Personal;

import java.util.*;

public class Graph {
    private HashSet<String> nodes;
    private HashMap<String, ArrayList<Road>> edges;

    public Graph() {
        nodes = new HashSet<String>();
        edges = new HashMap<String, ArrayList<Road>>();
    }

    public void addEdge(String fromNode, String toNode, double length) {
        nodes.add(fromNode);
        nodes.add(toNode);
        Road road = new Road(toNode, length);
        edges.computeIfAbsent(fromNode, k -> new ArrayList<Road>()).add(road);
    }

    public ArrayList<Road> getRoad(String currNode) {
        return edges.get(currNode);
    }

    public Set<String> getNodes() {
        return nodes;
    }

    public ArrayList<String> dijkstra(String startNode, String endNode) {
        HashMap<String, Double> distance = new HashMap<String, Double>();
        HashMap<String, String> prev = new HashMap<String, String>();
        PriorityQueue<String> pq = new PriorityQueue<String>(Comparator.comparingDouble(distance::get));

        for (String node : nodes) {
            distance.put(node, Double.MAX_VALUE);
            prev.put(node, null);
        }

        distance.put(startNode, 0.0);
        pq.add(startNode);

        while (!pq.isEmpty()) {
            String current = pq.poll();
            ArrayList<Road> neighbours = edges.getOrDefault(current, new ArrayList<Road>());
            for (Road neighbour : neighbours) {
                double newDist = distance.get(current) + neighbour.getLength();
                if (newDist < distance.get(neighbour.getToNode())) {
                    distance.put(neighbour.getToNode(), newDist);
                    prev.put(neighbour.getToNode(), current);
                    pq.add(neighbour.getToNode());
                }
            }
        }

        ArrayList<String> path = new ArrayList<String>();
        for (String current = endNode; current != null; current = prev.get(current)) {
            path.add(current);
        }
        Collections.reverse(path);
        return path;
    }
}
