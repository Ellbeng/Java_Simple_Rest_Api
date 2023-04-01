package ge.ibsu.demo.services;


import ge.ibsu.demo.entities.Customer;
import ge.ibsu.demo.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomreService {
    @Autowired
    private CustomerRepository customerRepository;
    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }


    public Customer getCustomerById(Long id) throws Exception{
        return customerRepository.findById(id).orElseThrow(()->new Exception("RECORD_NOT_FOUND"));
    }


}
