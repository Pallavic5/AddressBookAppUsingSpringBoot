package com.bridgelabz.addressbookappbackend.model;

import com.bridgelabz.addressbookappbackend.dto.AddressBookDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
public class Contacts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long contactsId;
    private String name;
    private String phoneNumber;

    @ElementCollection
    @CollectionTable(name = "address_table",joinColumns = @JoinColumn(name = "id"))
    @Column(name = "address")
    private List<String> address;

    @ElementCollection
    @CollectionTable(name = "city_table",joinColumns = @JoinColumn(name = "id"))
    @Column(name = "city")
    private List<String> city;

    @ElementCollection
    @CollectionTable(name = "state_table",joinColumns = @JoinColumn(name = "id"))
    @Column(name = "state")
    private List<String> state;

    @ElementCollection
    @CollectionTable(name = "zipCode_table",joinColumns = @JoinColumn(name = "id"))
    @Column(name = "zipCode")
    private List<String> zipCode;

    private String email;

    public Contacts(AddressBookDTO addressBookDTO) {
        this.setName(addressBookDTO.getName());
        this.setPhoneNumber(addressBookDTO.getPhoneNumber());
        this.setAddress(addressBookDTO.getAddress());
        this.setCity(addressBookDTO.getCity());
        this.setState(addressBookDTO.getState());
        this.setZipCode(addressBookDTO.getZipCode());
        this.setState(addressBookDTO.getState());
        this.setEmail(addressBookDTO.getEmail());
    }
}
