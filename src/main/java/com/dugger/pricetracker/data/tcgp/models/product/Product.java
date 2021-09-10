package com.dugger.pricetracker.data.tcgp.models.product;

import com.dugger.pricetracker.data.tcgp.models.category.Category;
import com.dugger.pricetracker.data.tcgp.models.group.Group;
import com.dugger.pricetracker.data.tcgp.models.rarity.Rarity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(schema = "tcgp", name = "tcgp_product")
public class Product {
  @Id
  private int id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "group_id")
  private Group group;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private Category category;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "rarity_id")
  private Rarity rarity;
  private String name;
  private String clean_name;
  private String image_url;
  private Timestamp modified_on;
  private Timestamp doe;
  private Timestamp dlu;
}
