package com.bridgelabz.addressbookappbackend.repository;

import com.bridgelabz.addressbookappbackend.model.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressBookRepositories extends JpaRepository<Contacts,Long> {

    @Query(value = "select * from addressbookdata.contacts,addressbookdata.city_table where contacts.contacts_id=city_table.id and city_table.city=:city",nativeQuery = true)  //his is custom query (optional)
    List<Contacts> findUserByCity(String city);

    @Query(value = "select * from addressbookdata.contacts,addressbookdata.address_table where contacts.contacts_id=address_table.id and address_table.address= :address",nativeQuery = true)
    List<Contacts> findUserByAddress(String address);

    @Query(value = "select * from addressbookdata.contacts,addressbookdata.state_table where contacts.contacts_id=state_table.id and state_table.state=:state",nativeQuery = true)
    List<Contacts> findUserByState(String state);

    @Query(value = "select * from addressbookdata.contacts,addressbookdata.zip_code_table where addressbookdata.contacts.contacts_Id=addressbookdata.zip_code_table.id and  addressbookdata.zip_code_table.zip_code= :zipCode",nativeQuery = true)
    List<Contacts> findUserByZip(String zipCode);
}
