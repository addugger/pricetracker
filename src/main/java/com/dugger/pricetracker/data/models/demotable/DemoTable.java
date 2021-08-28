package com.dugger.pricetracker.data.models.demotable;

import com.sun.istack.NotNull;
import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Table(schema = "demo_db")
public class DemoTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    private Character middleInitial;
    private String job;
    @Column(nullable = false)
    private Short age;
    @Column(nullable = false)
    private Timestamp doe;

    @Override
    public String toString() {
        return String.format("Row[%s, %s]", firstName, lastName);
    }
}
