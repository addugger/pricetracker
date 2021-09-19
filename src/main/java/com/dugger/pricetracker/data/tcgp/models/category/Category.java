package com.dugger.pricetracker.data.tcgp.models.category;

import com.dugger.pricetracker.data.tcgp.models.rarity.Rarity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(schema = "tcgp", name = "tcgp_category")
public class Category {
  @Id
  private Integer id;
  private String name;
  private Timestamp modified_on;
  private String display_name;
  private String sealed_label;
  private String non_sealed_label;
  @CreationTimestamp
  @Column(updatable = false)
  private Timestamp doe;
  @UpdateTimestamp
  private Timestamp dlu;

  @PostPersist
  public void addDefaultRarityId() {
    Rarity defaultRarity = new Rarity();
    defaultRarity.setId(Rarity.DEFAULT_ID);
    defaultRarity.setCategory(this);
    defaultRarity.setName(Rarity.DEFAULT_NAME);
    defaultRarity.setAbbreviation(Rarity.DEFAULT_ABBREVIATION);
  }
}
