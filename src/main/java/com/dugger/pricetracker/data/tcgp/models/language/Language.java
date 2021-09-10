package com.dugger.pricetracker.data.tcgp.models.language;

import com.dugger.pricetracker.data.tcgp.models.category.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(schema = "tcgp", name = "tcgp_language")
public class Language {
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
