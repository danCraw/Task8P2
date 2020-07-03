package graphBuilding;
import java.io.FileNotFoundException;
import java.util.*;

public class Graph {

    private static Map<Integer, Integer> firstGraphRoads = new HashMap<>();
    private static Map<Integer, Integer> secondGraphRoads = new HashMap<>();
    private static Set<Integer> firstGraphNodes = new HashSet<>();
    private static Set<Integer> secondGraphNodes = new HashSet<>();


    public static void readRoads(Integer[] firstGraph, Integer[] secondGraph) throws FileNotFoundException {
        firstGraphNodes.addAll(Arrays.asList(firstGraph));
        secondGraphNodes.addAll(Arrays.asList(secondGraph));
    }

    public static boolean checkIsomorphism(Integer[] firstGraph, Integer[] secondGraph) throws FileNotFoundException {
        readRoads(firstGraph, secondGraph);
        if (firstGraphNodes.size() != secondGraphNodes.size() || firstGraphRoads.size() != secondGraphRoads.size()) {
            return false;
        } else {
            for (int i = 0; i < Graph.getReachabilityMatrixFirstGraph().length - 1; i++) {
                for (int j = 1; j < Graph.getReachabilityMatrixFirstGraph().length; j++) {
                    for (int n = 0; n < Graph.getReachabilityMatrixFirstGraph().length - 1; n++) {
                        for (int k = 1; k < Graph.getReachabilityMatrixFirstGraph().length; k++) {
                            if (Arrays.deepEquals((changeColumns(n, k, changeLines(i, j, getReachabilityMatrixFirstGraph()))), getReachabilityMatrixSecondGraph())) {
                                return true;
                            }
                            System.out.println();
                        }
                    }
                }
            }
        }
        return false;
    }

    public static String addFirstGraphRoads(Integer[] firstGraph) throws FileNotFoundException {
        int flag = 1;
        int firstPoint = 0;
        int secondPoint = 0;
        for (int i = 0; i < firstGraph.length; i++) {
            if ((flag == 1) && (i < firstGraph.length)) {
                firstPoint = firstGraph[i];
                flag++;
                i++;
            }
            if ((flag == 2) && (i < firstGraph.length)) {
                secondPoint = firstGraph[i];
                flag = 1;
            }
            firstGraphRoads.put(firstPoint * 10 + secondPoint, 1);
            firstGraphRoads.put(secondPoint * 10 + firstPoint, 1);
        }
        return Arrays.toString(firstGraph);
    }

    public static String addSecondGraphRoads(Integer[] secondGraph) throws FileNotFoundException {
        int flag = 1;
        int firstPoint = 0;
        int secondPoint = 0;
        for (int i = 0; i < secondGraph.length; i++) {
            if ((flag == 1) && (i < secondGraph.length)) {
                firstPoint = secondGraph[i];
                flag++;
                i++;
            }
            if ((flag == 2) && (i < secondGraph.length)) {
                secondPoint = secondGraph[i];
                flag = 1;
            }
            secondGraphRoads.put(firstPoint * 10 + secondPoint, 1);
            secondGraphRoads.put(secondPoint * 10 + firstPoint, 1);
        }
        return Arrays.toString(secondGraph);
    }

    public static Integer[][] changeLines(int replaceableLine, int replacementLine, Integer[][] arr) {
        int tmp;
        for (int i = 0; i < arr.length; i++) {
            tmp = arr[replaceableLine][i];
            arr[replaceableLine][i] = arr[replacementLine][i];
            arr[replacementLine][i] = tmp;
        }
        return arr;
    }

    public static Integer[][] changeColumns(int replaceableColumn, int replacementColumn, Integer[][] arr) {
        int tmp;
        for (int i = 0; i < arr.length; i++) {
            tmp = arr[i][replaceableColumn];
            arr[i][replaceableColumn] = arr[i][replacementColumn];
            arr[i][replacementColumn] = tmp;
        }
        return arr;
    }

    public static Integer[][] getReachabilityMatrixFirstGraph() {
        Integer[][] arr = new Integer[firstGraphNodes.size()][firstGraphNodes.size()];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if ((firstGraphRoads.get(i * 10 + j) == null)) {
                    arr[i][j] = 0;
                } else {
                    arr[i][j] = 1;
                }
            }
        }
        return arr;
    }

    public static Integer[][] getReachabilityMatrixSecondGraph() {
        Integer[][] arr = new Integer[secondGraphNodes.size()][secondGraphNodes.size()];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if ((secondGraphRoads.get(i * 10 + j) == null)) {
                    arr[i][j] = 0;
                } else {
                    arr[i][j] = 1;
                }
            }
        }
        return arr;
    }
}





