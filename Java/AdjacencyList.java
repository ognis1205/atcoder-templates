import java.util.*;

public class AdjacencyList {
    private  Map<Integer, List<Integer>> map;

    public AdjacencyList() {
        this.map = new HashMap<Integer, List<Integer>>();
    }

    public List<Integer> setEdge(int src, int dst) {
        List<Integer> edges = this.getEdge(src);
        edges.add(dst);
        return this.map.put(src, edges);
    }

    public List<Integer> getEdge(int src) {
        return this.map.getOrDefault(src, new LinkedList<Integer>());
    }
}
