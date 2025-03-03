package com.pichincha.infra.adapter.http;

import com.pichincha.domain.entities.Client;
import com.pichincha.domain.entities.Movement;
import com.pichincha.domain.port.db.MovementsPortRepository;
import com.pichincha.domain.port.http.ClientsPortRepository;
import com.pichincha.infra.adapter.db.entites.MovementsDto;
import com.pichincha.infra.adapter.db.jpa.MovementsJpaRepository;
import com.pichincha.infra.adapter.db.mapper.MapperMovementEntity;
import com.pichincha.infra.adapter.http.exception.ClientFeingException;
import com.pichincha.infra.adapter.http.feign.ClientFeign;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientsRepository implements ClientsPortRepository {

    @Autowired
    private ClientFeign clientFeign;

    public Client getClientById(Long clientId) throws ClientFeingException {
        return MapperMovementEntity.toClient(clientFeign.getClientById(clientId));
    }
}
