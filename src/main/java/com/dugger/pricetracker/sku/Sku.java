package com.dugger.pricetracker.sku;

import com.dugger.pricetracker.data.tcgp.models.category.Category;
import com.dugger.pricetracker.data.tcgp.models.condition.Condition;
import com.dugger.pricetracker.data.tcgp.models.group.Group;
import com.dugger.pricetracker.data.tcgp.models.language.Language;
import com.dugger.pricetracker.data.tcgp.models.printing.Printing;
import com.dugger.pricetracker.data.tcgp.models.product.Product;
import com.dugger.pricetracker.data.tcgp.models.rarity.Rarity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(schema = "tcgp", name = "tcgp_product")
public class Sku {
  @Id
  private int id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id")
  private Product product;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "language_id")
  private Language language;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "printing_id")
  private Printing printing;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "condition_id")
  private Condition condition;
  private Timestamp doe;
  private Timestamp dlu;
}
