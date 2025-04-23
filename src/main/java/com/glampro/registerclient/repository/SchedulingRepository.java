package com.glampro.registerclient.repository;

import com.glampro.registerclient.dto.UserResponseDTO;
import com.glampro.registerclient.model.Scheduling;
import com.glampro.registerclient.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SchedulingRepository extends JpaRepository<Scheduling, UUID> {


}
