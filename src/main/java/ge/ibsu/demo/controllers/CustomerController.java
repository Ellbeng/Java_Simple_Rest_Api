package ge.ibsu.demo.controllers;

import ge.ibsu.demo.dto.AddCustomer;
import ge.ibsu.demo.entities.Customer;
import ge.ibsu.demo.repositories.CustomerRepository;
import ge.ibsu.demo.services.CustomreService;
import ge.ibsu.demo.utils.GeneralUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/customer")
public class CustomerController {
    @Autowired
    private CustomreService customreService;

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = {"application/json"})
    public List<Customer> getAll(){
        return customreService.getAllCustomer();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json"})
    public Customer getById(@PathVariable Long id) throws Exception{
        return customreService.getCustomerById(id);
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json"})
    public Customer add(@RequestBody AddCustomer addCustomer) throws Exception{
        GeneralUtils.checkRequiredProperties(addCustomer, Arrays.asList("firstName","lastName","addressId"));
        return customreService.add(addCustomer);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = {"application/json"})
    public Customer edit(@PathVariable Long id, @RequestBody AddCustomer addCustomer) throws Exception{
        GeneralUtils.checkRequiredProperties(addCustomer, Arrays.asList("firstName","lastName","addressId"));
        return customreService.edit(id, addCustomer);
    }
}
