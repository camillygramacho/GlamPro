package com.glampro.registerclient.repository;

import com.glampro.registerclient.dto.SchedulingClientResponseDTO;
import com.glampro.registerclient.dto.SchedulingResponseDTO;
import com.glampro.registerclient.model.Scheduling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SchedulingRepository extends JpaRepository<Scheduling, UUID> {

    @Query("""
    SELECT new com.glampro.registerclient.dto.SchedulingResponseDTO(
        ag.id,
        ser.nameService,
        us.email,
        us.name,
        ad.city,
        FUNCTION('TO_CHAR', ag.dateTimeAvailable, 'DD/MM/YYYY'),
        FUNCTION('TO_CHAR', ag.dateTimeAvailable, 'HH24:MI')
    )
    FROM Scheduling ag
    LEFT JOIN ServiceSalon ser ON ag.serviceSalon.id = ser.id and ag.available = true
    LEFT JOIN User us ON ser.professional.id = us.id
    LEFT JOIN Address ad ON us.id = ad.user.id
    WHERE (:email IS NULL OR us.email = :email)
      AND (:nameService IS NULL OR ser.nameService = :nameService)
""")
    List<SchedulingResponseDTO> listSchedulingFilter(
            @Param("email") String email,
            @Param("nameService") String nameService
    );

    @Query("""
    SELECT new com.glampro.registerclient.dto.SchedulingClientResponseDTO(
        ag.id,
        ser.nameService,
        us.email,
        us.name,
        ad.city,
        FUNCTION('TO_CHAR', ag.dateTimeAvailable, 'DD/MM/YYYY'),
        FUNCTION('TO_CHAR', ag.dateTimeAvailable, 'HH24:MI'),
        FUNCTION('TO_CHAR', ag.dateScheduling, 'DD/MM/YYYY')
    )
    FROM Scheduling ag
    LEFT JOIN ServiceSalon ser ON ag.serviceSalon.id = ser.id and ag.available = false
    LEFT JOIN User us ON ser.professional.id = us.id
    LEFT JOIN Address ad ON us.id = ad.user.id
    WHERE (:idClient IS NULL OR ag.client.id = :idClient)
      AND (:email IS NULL OR us.email = :email)
      AND (:nameService IS NULL OR ser.nameService = :nameService)
""")
    List<SchedulingClientResponseDTO> listSchedulingClientFilter(
            @Param("email") String email,
            @Param("nameService") String nameService,
            @Param("idClient") UUID idClient
    );
}
