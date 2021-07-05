package com.example.demo1.dao;

import com.example.demo1.model.ItemModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface ItemDao extends JpaRepository<ItemModel, Integer>{

    @Modifying
    @Query(name = "insertItem",nativeQuery = true,
            value = "insert into item(name,date,price) " +
                    "values(:name,:date,:price)")
     void insertItem(@Param("name") String name,
                    @Param("date") Date date, @Param("price") BigDecimal price);

    @Query(name = "selectItemByName",nativeQuery = true,
            value = "select id,name,date,price from item  " +
                    "where NAME =:name")
    List<ItemModel> selectItemByName(@Param("name") String name);


    @Modifying
    @Query(name = "deleteItemByName",nativeQuery = true,
            value = "delete from item  where NAME =:name")
    void deleteItemByName(@Param("name") String name);

    @Modifying
    @Query(name = "updateItem",nativeQuery = true,
            value = "update item set DATE =:date,price =:price where NAME =:name")
    void updateItem(@Param("name") String name, @Param("date")
            Date date, @Param("price")BigDecimal price);
}
