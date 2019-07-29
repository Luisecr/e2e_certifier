package com.e2e.certifier.repositories;

import com.e2e.certifier.Entities.Dictamen;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DictamenRepository extends CrudRepository<Dictamen, Long> {
    List<Dictamen> findByFolio(String folio);
}
