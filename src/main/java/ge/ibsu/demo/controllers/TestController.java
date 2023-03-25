package ge.ibsu.demo.controllers;

import ge.ibsu.demo.dto.Test;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/test")
public class TestController {
    @RequestMapping(value="/call", method = RequestMethod.GET, produces = {"application/json"})
    public Test test(){
        return new Test("Elene","Bendeliani");
    }
    @RequestMapping(value = "/post", method = RequestMethod.POST, produces = {"application/json"})
    public Test testPost(@RequestBody Test t){
        System.out.println(t.getFirstName()+" "+t.getLastName());
        return new Test(t.getFirstName()+ " from server", t.getLastName());
    }
}
