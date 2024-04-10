package data.DAO;

import java.util.List;
import java.util.Optional;
 
public interface DAO<T> {
 
    boolean insert(T t);
 
    boolean update(T t);
 
    boolean delete(String id);
 
    List<T> getAll();
 
    Optional<T> get(String id);
}