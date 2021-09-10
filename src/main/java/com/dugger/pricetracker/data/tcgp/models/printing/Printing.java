package com.dugger.pricetracker.data.tcgp.models.printing;

import com.dugger.pricetracker.data.tcgp.models.category.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(schema = "tcgp", name = "tcgp_printing")
public class Printing {
  @Id
  private int id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private Category category;
  private String name;
  private Timestamp modified_on;
  private Timestamp doe;
  private Timestamp dlu;
}
