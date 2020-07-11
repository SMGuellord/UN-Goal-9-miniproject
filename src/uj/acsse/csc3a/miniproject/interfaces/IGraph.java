/**
 * 
 */
package uj.acsse.csc3a.miniproject.interfaces;

/**
 * @author MG Sumba
 *
 */
public interface IGraph<T extends Comparable<T>> {

	 public void addVertex(T vertex);
	 public void removeVertex(T vertex);
	 public void addEdge(T vertex, T dVertex);
	 public void removeEdge(T vertex, T dVertex);
	 public boolean isAdjacent(T vertex, T dVertex);
	 public Iterable<T> getNeighbors(T vertex);
	 public Iterable<T> getAllVertices();
	
}
