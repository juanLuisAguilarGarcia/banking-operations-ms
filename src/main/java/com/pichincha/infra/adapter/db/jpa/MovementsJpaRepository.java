package com.pichincha.infra.adapter.db.jpa;

import com.pichincha.infra.adapter.db.entites.MovementsDto;
import org.springframework.data.repository.CrudRepository;

public interface MovementsJpaRepository extends CrudRepository<MovementsDto, Long> {

     MovementsDto findByMovementId(Long movementId);
}
