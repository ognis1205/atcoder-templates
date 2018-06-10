import java.util.*;

public class UnionFind {
    private int[] parent;
    private int[] rank;

    public UnionFind(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; ++i) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int i) {
        if (i < 0 || i > parent.length) throw new NoSuchElementException();
        return root(i);
    }

    public int union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v) return u;
        if (rank[v] < rank[u]) {
            int t = v;
            v = u;
            u = t;
        }
        parent[u] = v;
        rank[v] += rank[u];
        rank[u] = -1;
        return v;
    }

    public boolean same(int u, int v) {
        return find(u) == find(v);
    }

    private int root(int u) {
        while (parent[u] != u) u = parent[u];
        return u;
    }
}
