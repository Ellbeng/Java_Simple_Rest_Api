package ge.ibsu.demo.controllers;


import ge.ibsu.demo.dto.AddAddress;
import ge.ibsu.demo.dto.AddCustomer;
import ge.ibsu.demo.entities.Address;
import ge.ibsu.demo.entities.Customer;
import ge.ibsu.demo.services.AddressService;
import ge.ibsu.demo.utils.GeneralUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @RequestMapping(value="/all",method = RequestMethod.GET, produces = {"application/json"})
    public List<Address> getAll(){
        return addressService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json"})
    public Address getById(@PathVariable Long id) throws Exception{
        return addressService.getById(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json"})
    public Address add(@RequestBody AddAddress addAddress) throws Exception{
        GeneralUtils.checkRequiredProperties(addAddress, Arrays.asList("address","cityId"));
        return addressService.add(addAddress);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = {"application/json"})
    public Address edit(@PathVariable Long id, @RequestBody AddAddress addAddress) throws Exception{
        GeneralUtils.checkRequiredProperties(addAddress, Arrays.asList("address","cityId"));
        return addressService.edit(id, addAddress);
    }
}
