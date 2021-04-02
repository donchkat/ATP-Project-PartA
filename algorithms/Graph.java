package algorithms;
import java.util.LinkedList;
public class Graph {
        public int V; // No. of vertices

        // Array  of lists for
        // Adjacency List Representation
        public LinkedList<Integer> adj[];
/**
 * @param v:number of nodes in the graph
 * return: graph without edges
   **/
        Graph(int v)
        {
            V = v;
            adj = new LinkedList[v];
            for (int i = 0; i < v; ++i)
                adj[i] = new LinkedList();
        }
    /**
     * @param v:from edge index
     * @param w : to edge index
     * return: graph without edges
     **/
        void addEdge(int v, int w)
        {
            adj[v].add(w); // Add w to v's list.

        }

    }
