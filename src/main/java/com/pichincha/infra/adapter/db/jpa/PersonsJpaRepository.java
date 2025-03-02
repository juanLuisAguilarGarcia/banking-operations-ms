package com.pichincha.infra.adapter.db.jpa;

import com.pichincha.infra.adapter.db.entites.PersonsDto;
import org.springframework.data.repository.CrudRepository;

public interface PersonsJpaRepository extends CrudRepository<PersonsDto, Long> {
}
