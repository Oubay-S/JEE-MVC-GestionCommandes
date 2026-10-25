package ma.fstt.service;

import java.sql.SQLException;
import java.util.List;

public interface BaseRepository<T> {
    void save(T obj) throws SQLException;
    void update(T obj) throws SQLException;
    void delete(int id) throws SQLException;
    T getById(int id) throws SQLException;
    List<T> getAll() throws SQLException;
}