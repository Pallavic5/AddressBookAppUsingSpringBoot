package com.bridgelabz.addressbookappbackend.service;

import com.bridgelabz.addressbookappbackend.dto.AddressBookDTO;
import com.bridgelabz.addressbookappbackend.exception.AddressBookMessageException;
import com.bridgelabz.addressbookappbackend.model.Contacts;
import com.bridgelabz.addressbookappbackend.repository.AddressBookRepositories;
import com.bridgelabz.addressbookappbackend.util.EmailSenderService;
import com.bridgelabz.addressbookappbackend.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressBookServices implements IAddressBookServices {
    @Autowired
    AddressBookRepositories repo;
    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    EmailSenderService emailSenderService;

    @Override
    public Contacts insertDetails(AddressBookDTO entity) {
        Contacts contacts = new Contacts(entity);
        emailSenderService.sendEmail(contacts.getEmail(),"Test Mail", "Hii...."+contacts.getName()+" your details are added successfully!\n\n Name:  "+contacts.getName()+"\n Phone number:  "+contacts.getPhoneNumber()+"\n Email:  "+contacts.getEmail()+"\n Address:  "+contacts.getAddress()+"\n City:  "+contacts.getCity()+"\n State:  "+contacts.getState()+"\n ZipCode:  "+contacts.getZipCode());
        repo.save(contacts);
        return contacts;
    }
    @Override
    public Contacts updateDetails(long id, AddressBookDTO entity) {
        Contacts contacts = repo.findById(id).get();
        if (repo.findById(id).isPresent()){
            contacts.setName(entity.getName());
            contacts.setPhoneNumber(entity.getPhoneNumber());
            contacts.setAddress(entity.getAddress());
            contacts.setCity(entity.getCity());
            contacts.setState(entity.getState());
            contacts.setZipCode(entity.getZipCode());
            contacts.setState(entity.getState());
            contacts.setEmail(entity.getEmail());
            emailSenderService.sendEmail(contacts.getEmail(),"Test Mail", "Hii...."+contacts.getName()+" your details are updated!\n\n Name:  "+contacts.getName()+"\n Phone number:  "+contacts.getPhoneNumber()+"\n Email:  "+contacts.getEmail()+"\n Address:  "+contacts.getAddress()+"\n City:  "+contacts.getCity()+"\n State:  "+contacts.getState()+"\n ZipCode:  "+contacts.getZipCode());
            return repo.save(contacts);
        }else{
          throw new AddressBookMessageException("Id is not found");
        }
   }
    @Override
    public Optional<Contacts> search(long id) {
        Optional<Contacts> contacts = repo.findById(id);
        if (contacts.isPresent()){
            return repo.findById(id);
        }else {
           throw new AddressBookMessageException("Id is not found");
        }
   }
    @Override
    public List<Contacts> displayAll() {
        List<Contacts> contactsList = repo.findAll();
        return contactsList;
    }
    @Override
    public String deleteById(long id){
        Optional<Contacts> contacts = repo.findById(id);
        if(contacts.isPresent()){
            repo.deleteById(id);
            emailSenderService.sendEmail(contacts.get().getEmail(), "Test Mail","Hii....! Your details has been deleted!");
            return "Deleted";
        }else {
            throw new AddressBookMessageException("Id are not found");
        }
    }
    //CustomQuery
    @Override
    public List<Contacts> findUserByAddress(String address){
        List<Contacts> getByAddress=repo.findUserByAddress(address);
        if(getByAddress.isEmpty()){
            throw new AddressBookMessageException("User By Address not found: "+address);
        }else {
            return repo.findUserByAddress(address);
        }
    }
    @Override
    public List<Contacts> findUserByCity(String city){
        List<Contacts> getByCity=repo.findUserByCity(city);
        if(getByCity.isEmpty()){
            throw new AddressBookMessageException("User By City not found: "+city);
        }else {
            return repo.findUserByCity(city);
        }
    }
    @Override
    public List<Contacts> findUserByState(String state){
        List<Contacts> getByState=repo.findUserByState(state);
        if(getByState.isEmpty()){
            throw new AddressBookMessageException("User By State not found: "+state);
        }else {
            return getByState;
        }
    }
    @Override
    public List<Contacts> findUserByZip(String zipCode){
        List<Contacts> getByZip=repo.findUserByZip(zipCode);
        if(getByZip.isEmpty()){
            throw new AddressBookMessageException("User By zipcode not found: "+zipCode);
        }else {
            return repo.findUserByZip(zipCode);
        }
    }
    //insert, update and delete the data using token
    //jms also apply here(Java Message Service API)
    @Override
    public String uploadByToken(AddressBookDTO entity){
        Contacts contacts = new Contacts(entity);
        repo.save(contacts);
        String token=tokenUtil.createToken(contacts.getContactsId());
        emailSenderService.sendEmail(contacts.getEmail(),"Yayyy!! Data is Uploaded Successfully!!","Token: " + token);
        return token;
    }

    @Override
   public Optional<Contacts> findByToken(String token){
        long id = tokenUtil.decodeToken(token);
        Optional<Contacts> contacts = repo.findById(id);
        if(contacts.isPresent()){
            return contacts;
        }
        throw new AddressBookMessageException("Token not found: ");
    }

    @Override
    public String deleteByUsingtoken(String token){
        long id = tokenUtil.decodeToken(token);
        Contacts contacts = repo.findById(id).get();
        if (repo.findById(id).isPresent()) {
            repo.deleteById(id);
            emailSenderService.sendEmail(contacts.getEmail(),"Test Mail", "Hii...!Your details has been deleted");
            return "Data Using Token Deleted Sucessfully!!";
        } else {
            throw new AddressBookMessageException("Invalid token ");
        }
    }

}
