package ge.ibsu.demo.services;
import ge.ibsu.demo.dto.AddCity;
import ge.ibsu.demo.entities.City;
import ge.ibsu.demo.repositories.CityRepository;
import ge.ibsu.demo.utils.GeneralUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;
    public List<City> getAllCity(){
        return cityRepository.findAll();
    }



    public City getCityById(Long id) throws Exception{
        return cityRepository.findById(id).orElseThrow(()->new Exception("RECORD_NOT_FOUND"));
    }

    public City add(AddCity addCity) throws  Exception{
        City city=new City();

        GeneralUtils.getCopyOf(addCity,city);

        return cityRepository.save(city);

    }


    @Transactional
    public City edit(Long id, AddCity addCity) throws Exception{
        City city = getCityById(id);
        GeneralUtils.getCopyOf(addCity,city);

        return cityRepository.save(city);
    }

}
