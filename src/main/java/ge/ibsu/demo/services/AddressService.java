package ge.ibsu.demo.services;


import ge.ibsu.demo.dto.AddAddress;
import ge.ibsu.demo.dto.AddCity;
import ge.ibsu.demo.dto.AddCustomer;
import ge.ibsu.demo.entities.Address;
import ge.ibsu.demo.entities.City;
import ge.ibsu.demo.entities.Customer;
import ge.ibsu.demo.repositories.AddressRepository;
import ge.ibsu.demo.utils.GeneralUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
   @Autowired
   private AddressRepository addressRepository;
    @Autowired
    private CityService cityService;
   public List<Address> getAll(){
       return addressRepository.findAll();
   }

   public Address getById(Long id) throws Exception {
       return addressRepository.findById(id).orElseThrow(()->new Exception("RECORD_NOT_FOUND"));
   }

    public Address add(AddAddress addAddress) throws  Exception{
        Address address=new Address();

        GeneralUtils.getCopyOf(addAddress,address);
        City city=cityService.getCityById(addAddress.getCityId());
        address.setCity(city);
        return addressRepository.save(address);

    }


    @Transactional
    public Address edit(Long id, AddAddress addAddress) throws Exception{
        Address address=getById(id);
        GeneralUtils.getCopyOf(addAddress,address);
        if(addAddress.getCityId()!=null && !addAddress.getCityId().equals(address.getCity().getCityId())){
            City city=cityService.getCityById(addAddress.getCityId());
            address.setCity(city);
        }
        return addressRepository.save(address);
    }


}
