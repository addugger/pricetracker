package com.dugger.pricetracker.data.tcgp.models.group;

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
@Table(schema = "tcgp", name = "tcgp_group")
public class Group {
  @Id
  private int id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private Category category;
  private String name;
  private String abbreviation;
  private boolean is_supplemental;
  private Timestamp published_on;
  private Timestamp modified_on;
  @CreationTimestamp
  private Timestamp doe;
  @UpdateTimestamp
  private Timestamp dlu;
}
