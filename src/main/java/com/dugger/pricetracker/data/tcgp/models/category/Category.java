package com.dugger.pricetracker.data.tcgp.models.category;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(schema = "tcgp")
public class Category {
  @Id
  private Integer id;
  private String name;
  private Timestamp modified_on;
  private String display_name;
  private String sealed_name;
  private String non_sealed_name;
  @CreationTimestamp
  private Timestamp doe;
  @UpdateTimestamp
  private Timestamp dlu;
}
