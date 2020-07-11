/**
 * 
 */
package uj.acsse.csc3a.miniproject.interfaces;

/**
 * @author MG Sumba
 *
 */
public interface IList<T> {
	//Concrete behaviors of the LinkedList class.
		public void insert(T data);
		public void remove(T data);
		public void traverseList();
		public int size();
}
