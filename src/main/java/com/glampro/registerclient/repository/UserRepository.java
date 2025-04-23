package com.glampro.registerclient.repository;

import com.glampro.registerclient.dto.UserResponseDTO;
import com.glampro.registerclient.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(@Param("email") String email);

    Optional<User> findByCpf(@Param("cpf") String cpf);

    @Query(value = """
        SELECT new com.glampro.registerclient.dto.UserResponseDTO(
            u.id,
            u.name,
            u.email,
            u.phone,
            u.cpf,
            u.birthDate,
            u.isClient,
            u.isProfessional,
            a.street,
            a.number,
            a.complement,
            a.city,
            a.state,
            a.zipCode
            )
        FROM User u
        LEFT JOIN Address a ON u.id = a.user.id
        WHERE u.isActive = true and (:userId IS NULL OR u.id = :userId)
        """)
    List<UserResponseDTO> findAllUsersWithAddresses(@Param("userId") UUID userId);

    @Query(value = """
        SELECT new com.glampro.registerclient.dto.UserResponseDTO(
            u.id,
            u.name,
            u.email,
            u.phone,
            u.cpf,
            u.birthDate,
            u.isClient,
            u.isProfessional,
            a.street,
            a.number,
            a.complement,
            a.city,
            a.state,
            a.zipCode
            )
        FROM User u
        LEFT JOIN Address a ON u.id = a.user.id and u.isActive = true
        WHERE u.email = :email
        """)
    UserResponseDTO findUserByEmail(@Param("email") String email);

}
