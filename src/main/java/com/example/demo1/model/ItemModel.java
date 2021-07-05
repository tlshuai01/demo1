package com.example.demo1.model;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemModel {
    @Id
    private int id;
    @NonNull
    @Column
    private String name;
    @NonNull
    @Column
    private Date date;
    @NonNull
    @Column
    private BigDecimal price;
}
