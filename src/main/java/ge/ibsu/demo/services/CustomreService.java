package ge.ibsu.demo.services;


import ge.ibsu.demo.dto.AddCustomer;
import ge.ibsu.demo.dto.SearchCustomer;
import ge.ibsu.demo.dto.request.Paging;
import ge.ibsu.demo.entities.Address;
import ge.ibsu.demo.entities.Customer;
import ge.ibsu.demo.repositories.CustomerRepository;
import ge.ibsu.demo.utils.GeneralUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

@Service
public class CustomreService {
    @Autowired
    private CustomerRepository customerRepository;
    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }

    @Autowired
    private AddressService addressService;

    public Customer getCustomerById(Long id) throws Exception{
        return customerRepository.findById(id).orElseThrow(()->new Exception("RECORD_NOT_FOUND"));
    }


    public Customer add(AddCustomer addCustomer) throws  Exception{
        Customer customer=new Customer();
        customer.setCreateDate(new Date());
//        customer.setLastName(addCustomer.getLastName());
//        customer.setFirstName(addCustomer.getFirstName());
//        customer.setMiddleName(addCustomer.getMiddleName());
//        customer.setActive(addCustomer.getActive());

        GeneralUtils.getCopyOf(addCustomer,customer);
        Address address=addressService.getById(addCustomer.getAddressId());
        customer.setAddress(address);
        return customerRepository.save(customer);

    }


    @Transactional
    public Customer edit(Long id, AddCustomer addCustomer) throws Exception{
        Customer customer = getCustomerById(id);
        GeneralUtils.getCopyOf(addCustomer,customer);
        if(addCustomer.getAddressId()!=null && !addCustomer.getAddressId().equals(customer.getAddress().getAddressId())){
            Address address=addressService.getById(addCustomer.getAddressId());
            customer.setAddress(address);
        }
        return customerRepository.save(customer);
    }


    public Slice<Customer> search(SearchCustomer searchCustomer, Paging paging){
        String searchText=null;
        String email=null;
        if(searchCustomer.getName()!=null && !searchCustomer.equals("")){
            searchText="%"+ searchCustomer.getName()+"%";
        }
        if(searchCustomer.getEmail()!=null && !searchCustomer.equals("")){
            email="%"+ searchCustomer.getEmail()+"%";
        }
        Pageable pageable= PageRequest.of(paging.getPage(),paging.getSize(), Sort.by("createDate").descending());
        return customerRepository.search(searchCustomer.getActive(),searchText,email,pageable);

    }
}
