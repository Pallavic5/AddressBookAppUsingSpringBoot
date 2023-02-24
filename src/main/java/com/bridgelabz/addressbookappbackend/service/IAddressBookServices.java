package com.bridgelabz.addressbookappbackend.service;

import com.bridgelabz.addressbookappbackend.dto.AddressBookDTO;
import com.bridgelabz.addressbookappbackend.model.Contacts;

import java.util.List;
import java.util.Optional;

public interface IAddressBookServices {
    Contacts insertDetails(AddressBookDTO entity);
    Contacts updateDetails(long id, AddressBookDTO entity);
    Optional<Contacts> search(long id);
    List<Contacts> displayAll();
    String deleteById(long id);

    //CustomQuery attributes
    List<Contacts> findUserByAddress(String address);

    List<Contacts> findUserByCity(String city);

    List<Contacts> findUserByState(String state);

    List<Contacts> findUserByZip(String zipCode);

    String uploadByToken(AddressBookDTO addressBookDTO);

    Optional<Contacts> findByToken(String token);

    String deleteByUsingtoken(String token);
}
