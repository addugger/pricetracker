package com.dugger.pricetracker.data.tcgp.models.rarity;

import com.dugger.pricetracker.data.tcgp.models.category.Category;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(schema = "tcgp", name = "tcgp_rarity")
public class Rarity {
  public final static int DEFAULT_ID = -1;
  public final static String DEFAULT_NAME = "UNKNOWN RARITY";
  public final static String DEFAULT_ABBREVIATION = "N/A";

  @Id
  private int id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private Category category;
  private String name;
  private String abbreviation;
  @CreationTimestamp
  @Column(updatable = false)
  private Timestamp doe;
  @UpdateTimestamp
  private Timestamp dlu;
}
