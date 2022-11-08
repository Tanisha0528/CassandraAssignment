package com.spring.cassandra.springCassandra.Controller;


import com.spring.cassandra.springCassandra.model.Employee;
import com.spring.cassandra.springCassandra.repository.EmployeeRepository;
import com.spring.cassandra.springCassandra.service.EmployeeService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;

// when backend and database are on different ports->(enable this)
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService service;

    public EmployeeController(EmployeeService service){
        this.service = service;
    }


    @PostMapping("/signup-employee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        return service.signupService(employee);
    }



    @GetMapping("/get-employee")
    public Employee getUser(HttpServletRequest httpServletRequest)
    {
        Integer id = (Integer) httpServletRequest.getAttribute("id");
        return service.getEmployee(id);

    }
    @PostMapping("/login")
    public String login(@RequestBody Map<String, Object> map){
        return service.loginService(map.get("email").toString(), map.get("password").toString());
    }


}
