package com.community.client.services;

import com.community.client.models.Address;
import com.community.client.models.Community;
import com.community.client.repositories.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

//Address service layer
@Service
public class AddressService {
    public AddressRepository addressRepository;

    //constructor
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    // Implement the methods from repository,and it will be used in controller layer
    //implement the save address
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    //implement the get an address by id
    public Address getAddressById(Long id){
        Optional<Address> addressOptional = addressRepository.findAddressById(id);
        if (addressOptional.isPresent()){
            return addressOptional.get();
        }else {
            return null;
        }
    }
}
