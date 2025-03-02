package com.pichincha.infra.adapter.db;

import com.pichincha.domain.entities.Account;
import com.pichincha.domain.port.db.ClientsPortRepository;
import com.pichincha.infra.adapter.db.entites.ClientsDto;
import com.pichincha.infra.adapter.db.jpa.ClientsJpaRepository;
import com.pichincha.infra.adapter.db.mapper.MapperClientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientsRepository implements ClientsPortRepository {

    @Autowired
    private ClientsJpaRepository clientsJpaRepository;

    public Account save(ClientsDto clientToSave){
        return MapperClientEntity.toClient(clientsJpaRepository.save(clientToSave));
    }

    public Account getClientById(Long clientId){
        return MapperClientEntity.toClient(clientsJpaRepository.findByClientIdAndIsActive(clientId, true));
    }

    public void deleteClient(Long clientId){
        clientsJpaRepository.deleteById(clientId);
        return;
    }

    public Account updateClient(ClientsDto clientToUpdate){
        return MapperClientEntity.toClient(clientsJpaRepository.save(clientToUpdate));
    }

    public Account getClientByIdentificationTypeIdAndIdentificationNumber(Long typeId, Long number){
        return MapperClientEntity.toClient(clientsJpaRepository.findByIdentificationTypeIdAndNumber(typeId, number));
    }
}
