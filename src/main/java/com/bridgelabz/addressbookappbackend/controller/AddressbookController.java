package com.bridgelabz.addressbookappbackend.controller;

import com.bridgelabz.addressbookappbackend.dto.AddressBookDTO;
import com.bridgelabz.addressbookappbackend.dto.ResponseDTO;
import com.bridgelabz.addressbookappbackend.model.Contacts;
import com.bridgelabz.addressbookappbackend.service.IAddressBookServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AddressbookController {
    @Autowired
    IAddressBookServices addressBookServices;

    @GetMapping("/param/{name}")
    public String sendParameter(@PathVariable String name) {
        return "Hello " + "Mr/Mrs. " + name + " Welcome to AddressBook Application!!!";
    }
    //Insert details into database
    @PostMapping("/insert")
    public ResponseEntity<ResponseDTO> addDetails(@Valid @RequestBody AddressBookDTO entity) {         //Before @RequestBody we have to add @Valid
        Contacts contacts = addressBookServices.insertDetails(entity);
        ResponseDTO responseDTO = new ResponseDTO("Data Inserted Successfully!!", contacts);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
    //Update Details from database
    @PutMapping("/edit/{id}")    //to update
    public ResponseEntity<ResponseDTO> updateData(@PathVariable long id, @Valid @RequestBody AddressBookDTO entity) {  //insert update (requestbody)
        Contacts contacts = addressBookServices.updateDetails(id, entity);
        ResponseDTO responseDTO = new ResponseDTO("Data has been Updated!!", contacts);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    //Delete Details from database
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable long id) { //findout id (pathvariable)
        String contacts = addressBookServices.deleteById(id);
        ResponseDTO responseDTO = new ResponseDTO("Data has been deleted!!", contacts);
        return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
    }
    //Fetch details as per the id
    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseDTO> findUserId(@PathVariable long id) {
        Optional<Contacts> userFindId = addressBookServices.search(id);
        ResponseDTO responseDTO = new ResponseDTO("Data has been found!!", userFindId);
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }
    //Fetch all the data stored in the database
    @GetMapping("/getAllDetails")
    public ResponseEntity<ResponseDTO> getAllDetails() {
        List<Contacts> contactsList = addressBookServices.displayAll();
        ResponseDTO responseDTO = new ResponseDTO("All Data has been found!!", contactsList);
        return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
    }
    //Fetch the data as per the address
    @GetMapping("/findByAddress/{address}")
    public ResponseEntity<ResponseDTO> findByAddress(@PathVariable String address) {
        List<Contacts> contactsList = addressBookServices.findUserByAddress(address);
        ResponseDTO responseDTO = new ResponseDTO("User details are found by address!", contactsList);
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }
    //Fetch the data as per the city
    @GetMapping("/findByCity/{city}")
    public ResponseEntity<ResponseDTO> findByCity(@PathVariable String city) {
        List<Contacts> contactsList = addressBookServices.findUserByCity(city);
        ResponseDTO responseDTO = new ResponseDTO("User details are found by city!", contactsList);
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }
    //Fetch the data as per the state
    @GetMapping("/findByState/{state}")
    public ResponseEntity<ResponseDTO> findByState(@PathVariable String state) {
        List<Contacts> contactsList = addressBookServices.findUserByState(state);
        ResponseDTO responseDTO = new ResponseDTO("User details is found by state!", contactsList);
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }
    //Fetch the data as per the zipcode
    @GetMapping("/findByZip/{zipCode}")
    public ResponseEntity<ResponseDTO> findByZip(@PathVariable String zipCode) {
        List<Contacts> contactsList = addressBookServices.findUserByZip(zipCode);
        ResponseDTO responseDTO = new ResponseDTO("User details are found by zipCode!", contactsList);
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }
    //insert the data in token format
    @PostMapping("/upload")
    public ResponseEntity<ResponseDTO> uploadByToken(@Valid @RequestBody AddressBookDTO entity) {
        String token = addressBookServices.uploadByToken(entity);
        ResponseDTO responseDTO = new ResponseDTO("Data uploaded using token!", token);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
    //fetch the data in the form of token
    @GetMapping("/getByToken/{token}")
    public ResponseEntity<ResponseDTO> findByToken(@PathVariable String token) {
        Optional<Contacts> contacts = addressBookServices.findByToken(token);
        ResponseDTO responseDTO = new ResponseDTO("Data found using token!", contacts);
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }
    //delete the data using token
    @DeleteMapping("/deleteByToken/{token}")
    public ResponseEntity<ResponseDTO> deleteByUsingToken(@PathVariable String token) { //findout token (pathvariable)
        String contacts = addressBookServices.deleteByUsingtoken(token);
        ResponseDTO responseDTO = new ResponseDTO("Data has been deleted using token!!", contacts);
        return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
    }
}