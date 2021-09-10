package com.dugger.pricetracker.data.tcgp.models.condition;

import com.dugger.pricetracker.data.tcgp.models.category.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(schema = "tcgp", name = "tcgp_condition")
public class Condition {
  @Id
  private int id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private Category category;
  private String name;
  private String abbreviation;
  private Timestamp doe;
  private Timestamp dlu;
}
