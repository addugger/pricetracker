package com.dugger.pricetracker.data.models.demotable;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DemoTableRepository extends CrudRepository<DemoTable, Integer> {
    List<DemoTable> findByLastName(String lastName);
}
