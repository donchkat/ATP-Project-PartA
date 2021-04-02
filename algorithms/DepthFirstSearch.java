package algorithms;

import java.util.Iterator;

public class DepthFirstSearch {
    void DFSVisit(int v, boolean visited[],Graph graph)
    {
        // Mark the current node as visited and print it
        visited[v] = true;
        System.out.print(v + " ");

        // Recur for all the vertices adjacent to this
        // vertex
        Iterator<Integer> i = graph.adj[v].listIterator();
        while (i.hasNext())
        {
            int n = i.next();
            if (!visited[n])
                DFSVisit(n, visited,graph);
        }
    }

    // The function to do DFS traversal.
    // It uses recursive
    // DFSVisit()
    void DFS(int v, Graph graph)
    {
        // Mark all the vertices as
        // not visited(set as
        // false by default in java)
        boolean visited[] = new boolean[graph.V];

        DFSVisit(v, visited,graph);
    }


}
