package Personal;

import java.util.*;

public class MapRoadBuilder {
    public static HashMap<Integer, String> getIntersections(int numPlaces, Scanner sc) {
        HashMap<Integer, String> namePlaces = new HashMap<Integer, String>();
        Set<String> namesSet = new HashSet<>();

        for (int i = 0; i < numPlaces; i++) {
            String tempName = "";
            while (true) {
                if (i == 0) {
                    System.out.println("Enter the name of the 1st intersection: ");
                } else if (i == 1) {
                    System.out.println("Enter the name of the 2nd intersection: ");
                } else if (i == 2) {
                    System.out.println("Enter the name of the 3rd intersection: ");
                } else {
                    System.out.println("Enter the name of the " + (i + 1) + "th intersection: ");
                }
                tempName = sc.nextLine();
                if (namesSet.contains(tempName)) {
                    System.out.println("Name already exists. Please enter a distinct name.");
                } else {
                    namesSet.add(tempName);
                    namePlaces.put(i, tempName);
                    break;
                }
            }
        }
        return namePlaces;
    }

    public static Graph getRoadGraph(int numPlaces, HashMap<Integer, String> namePlaces, Scanner sc) {
        Graph graph = new Graph();
        int[][] map = new int[numPlaces][numPlaces];

        for (int i = 0; i < numPlaces; i++) {
            for (int k = i + 1; k < numPlaces; k++) {
                if (i != k) {
                    String isRoad = "";
                    while (true) {
                        System.out.println("Is there a road between " + namePlaces.get(i) + " and " + namePlaces.get(k) + "? (Y/N)");
                        isRoad = sc.nextLine();
                        if (isRoad.equalsIgnoreCase("Y") || isRoad.equalsIgnoreCase("N")) {
                            break;
                        } else {
                            System.out.println("Invalid input. Please enter Y or N.");
                        }
                    }
                    if (isRoad.equalsIgnoreCase("Y")) {
                        map[i][k] = 1;
                    }
                }
            }
        }

        for (int i = 0; i < numPlaces; i++) {
            for (int k = 0; k < numPlaces; k++) {
                if (map[i][k] == 1) {
                    System.out.println("Enter the length of the road in km between " + namePlaces.get(i) + " and " + namePlaces.get(k) + ": ");
                    double length = 0;
                    while (true) {
                        try {
                            length = Double.parseDouble(sc.nextLine());
                            if (length < 0) {
                                System.out.println("Invalid input. Length cannot be negative, resubmit a value.");
                            } else {
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid number.");
                        }
                    }
                    graph.addEdge(namePlaces.get(i), namePlaces.get(k), length);
                }
            }
        }
        return graph;
    }

    public static void resolveTravelDistance(Graph graph, Scanner sc) {
        Set<String> namesSet = new HashSet<>(graph.getNodes());
        String start = "";
        while (true) {
            System.out.println("Enter the name of the starting point of your desired route: ");
            start = sc.nextLine();
            if (!namesSet.contains(start)) {
                System.out.println("Intersection does not exist. Please enter a valid intersection name.");
            } else {
                break;
            }
        }

        String end = "";
        while (true) {
            System.out.println("Enter the name of the ending point of your desired route: ");
            end = sc.nextLine();
            if (!namesSet.contains(end)) {
                System.out.println("Intersection does not exist. Please enter a valid intersection name.");
            } else if (end.equals(start)) {
                System.out.println("Ending point cannot be the same as the starting point. Please enter a different intersection name.");
            } else {
                break;
            }
        }

        double totalDistance = 0;
        ArrayList<String> path = new ArrayList<String>();
        path = graph.dijkstra(start, end);
        System.out.println("Take the following roads to your destination: ");
        for (int i = 0; i < path.size() - 1; i++) {
            String currNode = path.get(i);
            String nextNode = path.get(i + 1);
            ArrayList<Road> roads = graph.getRoad(currNode);
            for (Road road : roads) {
                if (road.getToNode().equals(nextNode)) {
                    totalDistance += road.getLength();
                    break;
                }
            }
        }
        if (totalDistance == 0) {
            System.out.println("There is no valid route!");
        } else {
            System.out.println("The shortest route from " + start + " to " + end + " will cover a distance of " + totalDistance + " km.");
        }
    }
}
