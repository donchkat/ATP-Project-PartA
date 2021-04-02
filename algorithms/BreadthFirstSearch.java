package algorithms;

import search.ASearchingAlgorithm;
import search.ISearchable;
import search.MazeState;

import java.util.Iterator;
import java.util.LinkedList;

public class BreadthFirstSearch extends ASearchingAlgorithm {

    // prints BFS traversal from a given source s
    void BFS(int s,Graph graph) {
        // Mark all the vertices as not visited(By default
        // set as false)
        boolean visited[] = new boolean[graph.V];

        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<Integer>();

        // Mark the current node as visited and enqueue it
        visited[s] = true;
        queue.add(s);

        while (queue.size() != 0) {
            // Dequeue a vertex from queue and print it
            s = queue.poll();
            System.out.print(s + " ");

            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            Iterator<Integer> i = graph.adj[s].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }

    @Override
    public MazeState search (ISearchable iSearchable) {
        return null;
    }

    @Override
    public int getNumberVisitedNodes () {
        return 0;
    }
}
