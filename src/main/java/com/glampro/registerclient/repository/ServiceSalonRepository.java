package com.glampro.registerclient.repository;

import com.glampro.registerclient.model.ServiceSalon;
import com.glampro.registerclient.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ServiceSalonRepository extends JpaRepository<ServiceSalon, UUID> {

    List<ServiceSalon> findByProfessional(@Param("professional") User professional);

    List<ServiceSalon> findByNameService(@Param("nameService") String nameService);

    List<ServiceSalon> findByNameServiceContainingIgnoreCase(@Param("nameService")String nameService);

}
