package com.glampro.registerclient.repository;

import com.glampro.registerclient.model.Address;
import com.glampro.registerclient.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {

    Address findByUser(@Param("user") User user);

}
