package com.dugger.pricetracker.data.tcgp.models.rarity;

import com.dugger.pricetracker.data.tcgp.models.category.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(schema = "tcgp", name = "tcgp_rarity")
public class Rarity {
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
