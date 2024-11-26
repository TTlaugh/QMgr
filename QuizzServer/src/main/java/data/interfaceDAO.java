package data;

import java.util.ArrayList;

public interface interfaceDAO<T> {
    public ArrayList<T> getAll();

    public boolean create(T t);

    public T getByID(String t);
    public T getByID(int t);

    public boolean update(T t);

    public boolean delete(String t);
    public boolean delete(int t);
}
