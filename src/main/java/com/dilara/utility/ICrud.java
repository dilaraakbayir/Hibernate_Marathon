package com.dilara.utility;

import com.dilara.entity.Urun;

import java.util.List;
import java.util.Optional;

public interface ICrud<T, ID> {
    T save(T entity);
    Iterable<T> saveAll(Iterable<T> entities); //List, Set vs.
    Optional<T> findById(ID id); //id Long id'yse Long olur, String ise String olur vb.
    boolean existsById(ID id);
    List<T> findAll();
    /**
     * Bir postun yorum listesini bulmak istiyorum,
     * select * from tblcomment where post id = ?
     */
    List<T> findAllByFromColumnAndValue(String columnName, Object value);
    void deleteById(ID id);
    List<T> findAllByEntity(T entity);


}
