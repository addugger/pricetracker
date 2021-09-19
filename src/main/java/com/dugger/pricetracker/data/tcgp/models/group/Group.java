package com.dugger.pricetracker.data.tcgp.models.group;

import com.dugger.pricetracker.data.tcgp.models.category.Category;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
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
  @Column(updatable = false)
  private Timestamp doe;
  @UpdateTimestamp
  private Timestamp dlu;
}
