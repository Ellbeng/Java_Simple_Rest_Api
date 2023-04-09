package ge.ibsu.demo.controllers;

import ge.ibsu.demo.dto.AddCity;
import ge.ibsu.demo.entities.City;
import ge.ibsu.demo.services.CityService;
import ge.ibsu.demo.utils.GeneralUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = {"application/json"})
    public List<City> getAll(){
        return cityService.getAllCity();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json"})
    public City getById(@PathVariable Long id) throws Exception{
        return cityService.getCityById(id);
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json"})
    public City add(@RequestBody AddCity addCity) throws Exception{
        GeneralUtils.checkRequiredProperties(addCity, Arrays.asList("city"));
        return cityService.add(addCity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = {"application/json"})
    public City edit(@PathVariable Long id, @RequestBody AddCity addCity) throws Exception{
        GeneralUtils.checkRequiredProperties(addCity, Arrays.asList("city"));
        return cityService.edit(id, addCity);
    }
}
