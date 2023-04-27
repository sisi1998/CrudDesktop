
package Services;

import java.util.ArrayList;

/**
 *
 * @author Ousse
 */
public interface IServiceU<T> {
    void insert(T o);
    void update(T o);
    void delete(int id);
    T readById(int id);
    ArrayList<T> readAll();
    
    
}
