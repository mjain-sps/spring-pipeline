package com.community.client.repositories;

import com.community.client.models.Address;
import com.community.client.models.Community;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends CrudRepository<Address,Long> {

    //save the address
    Address save(Address address);

    //find an address by ID
    Optional<Address> findAddressById(Long id);
}
