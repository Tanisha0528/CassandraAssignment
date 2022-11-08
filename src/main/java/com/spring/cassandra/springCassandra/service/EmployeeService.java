package com.spring.cassandra.springCassandra.service;

import com.spring.cassandra.springCassandra.model.Employee;
import com.spring.cassandra.springCassandra.repository.EmployeeRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository repository;

    private TokenService service;




    public String loginService(String email, String password){
        List<Employee> founded=  repository.findByEmail(email);

        if(founded.isEmpty()){
            return "{\n" +
                    "\"message\":"+"\" Authentication Failed !!! (Employee NOT FOUND) \",\n"+
                    "}";
        }

        else if(!founded.get(0).getPassword().equals(password)){
            return "{\n" +
                    "\"message\":"+"\" Password Incorrect !!!\",\n"+
                    "}";
        }

        return "{\n" +
                "\"message\":"+"\" Successfully Logged-in\",\n"+
                "\"data\": {\n"+"       Name : "+founded.get(0).getName()+",\n"+
                "       id : "+founded.get(0).getId()+",\n"+
                "       Email : "+founded.get(0).getEmail()+"\n"+
                "   }\n"+
                "}";

    }

    public Employee getEmployee(Integer id)
    {
        Optional<Employee> optionalUser = repository.findById(id);
        return  optionalUser.orElseGet(optionalUser::get);
    }

    public ResponseEntity<Employee> signupService( Employee employee)
    {
        try{
//
            Employee tempEmployee = repository.save(employee);
            String str=service.createToken((tempEmployee.getId()));
           /* return ("Output:{\n" +
                    "\tmessage : Successfully Created User,\n" +
                    "\tdata:{\n \t\t\"id\"="+tempEmployee.getId()+"\",\n"
                    +"\t\t\"name\"=\'"+tempEmployee.getName()+"\',\n"+
                    "\t\t\"email\"=\'"+tempEmployee.getEmail()+"\',\n"+
                    "\t\t\"password\"=\'"+tempEmployee.getState()+"\',"+"\n\t},\n"+
                    "\t\"token\": "+str+"\"}");*/
            return new ResponseEntity<>(tempEmployee, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}
