package Personal;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numPlaces = 0;
        while (true) {
            System.out.println("Enter the number of intersections you want to add: ");
            try {
                numPlaces = Integer.parseInt(sc.nextLine());
                if (numPlaces >= 2) {
                    break;
                } else {
                    System.out.println("There must be a minimum of 2 intersections!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        HashMap<Integer, String> namePlaces = MapRoadBuilder.getIntersections(numPlaces, sc);
        Graph graph = MapRoadBuilder.getRoadGraph(numPlaces, namePlaces, sc);

        MapRoadBuilder.resolveTravelTime(graph, sc);
    }
}
