package Project3Data2112;

import java.util.*;

public class Graph {

    public Map<String, Integer> cityToIndex; // Hash Table (City -> Index)
    public List<List<Edge>> adjacencyList;  // Adjacency List
    public int vertexCount;

    public Graph() {
        this.cityToIndex = new HashMap<>();
        this.adjacencyList = new ArrayList<>();
        this.vertexCount = 0;
    }

    public void addCityIfAbsent(String cityName) {
        if (!cityToIndex.containsKey(cityName)) {
            cityToIndex.put(cityName, vertexCount++);
            adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(String fromCity, String toCity, int weight) {
        adjacencyList.get(cityToIndex.get(fromCity)).add(new Edge(toCity, weight));
    }

    public boolean IsThereAPath(String v1, String v2) {
        Set<String> visited = new HashSet<>();
        return dfsCheckPath(v1, v2, visited);
    }

    private boolean dfsCheckPath(String current, String target, Set<String> visited) {

        if (current.equals(target)) {
            return true;
        }
        visited.add(current);
        for (Edge edge : adjacencyList.get(cityToIndex.get(current))) {
            if (!visited.contains(edge.toCity) && dfsCheckPath(edge.toCity, target, visited)) {
                return true;
            }
        }
        return false;
    }

    public void BFSfromTo(String v1, String v2) {
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> parent = new HashMap<>();

        queue.add(v1);
        visited.add(v1);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(v2)) {
                break;
            }

            List<Edge> edges = new ArrayList<>(adjacencyList.get(cityToIndex.get(current)));

            for (int i = 0; i < edges.size() - 1; i++) {
                int minIndex = i;
                for (int j = i + 1; j < edges.size(); j++) {
                    if (edges.get(j).weight < edges.get(minIndex).weight) {
                        minIndex = j;
                    }
                }
                Edge temp = edges.get(i);
                edges.set(i, edges.get(minIndex));
                edges.set(minIndex, temp);
            }

            for (Edge edge : edges) {
                if (!visited.contains(edge.toCity)) {
                    queue.add(edge.toCity);
                    visited.add(edge.toCity);
                    parent.put(edge.toCity, current);
                }
            }
        }
        printPath(v1, v2, parent);
    }

    private void printPath(String start, String end, Map<String, String> parent) {
        List<String> path = new ArrayList<>();
        for (String at = end; at != null; at = parent.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        System.out.println("BFS Path: " + String.join(" -> ", path));
    }

    public void DFSfromTo(String v1, String v2) {
        Set<String> visited = new HashSet<>();
        List<String> path = new ArrayList<>();

        if (dfsFindPath(v1, v2, visited, path)) {
            String result = "";
            for (int i = 0; i < path.size(); i++) {
                result += path.get(i);
                if (i < path.size() - 1) {
                    result += " -> ";
                }
            }

            System.out.println("DFS Path: " + result);// ilk kısım ayıraç olarak kullanır listeler sıralar listeyi
        } else {
            System.out.println("No path found between " + v1 + " and " + v2);
        }
    }

    private boolean dfsFindPath(String current, String target, Set<String> visited, List<String> path) {
        path.add(current); 
        if (current.equals(target)) {
            return true; // Hedefe ulaşıldı
        }
        visited.add(current); 

        for (Edge edge : adjacencyList.get(cityToIndex.get(current))) {
            if (!visited.contains(edge.toCity)) {
                if (dfsFindPath(edge.toCity, target, visited, path)) {
                    return true; 
                }
            }
        }
        path.remove(path.size() - 1); 
        return false; 
    }

    public int WhatIsShortestPathLength(String v1, String v2) {
        Queue<String> queue = new LinkedList<>();
        Map<String, Integer> distances = new HashMap<>();
        Set<String> visited = new HashSet<>();

        queue.add(v1);
        visited.add(v1);
        distances.put(v1, 0);
 
        while (!queue.isEmpty()) {
            String current = queue.remove();
            int currentDistance = distances.get(current);

            if (current.equals(v2)) {
                return currentDistance;
            }

            for (Edge edge : adjacencyList.get(cityToIndex.get(current))) {
                if (!visited.contains(edge.toCity)) {
                    queue.add(edge.toCity);
                    visited.add(edge.toCity);
                    distances.put(edge.toCity, currentDistance + edge.weight);
                }
            }
        }

        System.out.println(v1 + " --x-- " + v2);
        return -1; 
    }

   
    public int NumberOfSimplePaths(String v1, String v2) {
        Set<String> visited = new HashSet<>();
        return countPaths(v1, v2, visited);
    }

    private int countPaths(String current, String target, Set<String> visited) {
        if (current.equals(target)) {
            return 1; 
        }

        visited.add(current);
        int pathCount = 0;

        for (Edge edge : adjacencyList.get(cityToIndex.get(current))) {
            if (!visited.contains(edge.toCity) && IsThereAPath(edge.toCity, target)) {
                pathCount += countPaths(edge.toCity, target, visited);
            }
        }

        visited.remove(current); 
        return pathCount;
    }

    public List<String> Neighbors(String v1) {
        List<String> neighbors = new ArrayList<>();
        for (Edge edge : adjacencyList.get(cityToIndex.get(v1))) {
            neighbors.add(edge.toCity);
        }
        return neighbors;
    }

    public List<String> HighestDegree() {
        int maxDegree = 0;
        List<String> result = new ArrayList<>();

        for (String city : cityToIndex.keySet()) {
            int degree = adjacencyList.get(cityToIndex.get(city)).size();
            if (degree > maxDegree) {
                maxDegree = degree;
                result.clear();
                result.add(city);
            } else if (degree == maxDegree) {
                result.add(city);
            }
        }
        return result;
    }

    public boolean IsDirected() {
        for (int i = 0; i < adjacencyList.size(); i++) {
            for (Edge edge : adjacencyList.get(i)) {
                String fromCity = getCityNameByIndex(i);
                boolean reverseEdgeExists = false;
                for (Edge reverseEdge : adjacencyList.get(cityToIndex.get(edge.toCity))) {
                    if (reverseEdge.toCity.equals(fromCity)) {
                        reverseEdgeExists = true;
                        break;
                    }
                }
                if (!reverseEdgeExists) {
                    return true; 
                }
            }
        }
        return false;
    }
    

    private String getCityNameByIndex(int index) {
        for (String key : cityToIndex.keySet()) {
            if (cityToIndex.get(key) == index) {
                return key;
            }
        }
        return null;
    } 

    public boolean AreTheyAdjacent(String v1, String v2) {
        for (Edge edge : adjacencyList.get(cityToIndex.get(v1))) {
            if (edge.toCity.equals(v2)) {
                return true;
            }
        }
        return false;
    }

    public boolean IsThereACycle(String v1) {
        Set<String> visited = new HashSet<>();
        return dfsCheckCycle(v1, v1, visited, null);
    }

    private boolean dfsCheckCycle(String start, String current, Set<String> visited, String parent) {
        visited.add(current);

        for (Edge edge : adjacencyList.get(cityToIndex.get(current))) {
            if (!edge.toCity.equals(parent)) { 
                if (edge.toCity.equals(start)) {
                    return true; 
                }
                if (!visited.contains(edge.toCity) && dfsCheckCycle(start, edge.toCity, visited, current)) {
                    return true;
                }
            }
        }

        return false;
    }

    public int NumberOfVerticesInComponent(String v1) {
        Set<String> visited = new HashSet<>();
        dfsCountComponent(v1, visited);
        return visited.size();
    }

    private void dfsCountComponent(String current, Set<String> visited) {
        visited.add(current);
        for (Edge edge : adjacencyList.get(cityToIndex.get(current))) {
            if (!visited.contains(edge.toCity)) {
                dfsCountComponent(edge.toCity, visited);
            }
        }
    }
}
