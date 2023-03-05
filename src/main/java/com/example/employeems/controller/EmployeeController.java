package com.example.employeems.controller;

import com.example.employeems.dto.EmployeeDTO;
import com.example.employeems.dto.ResponseDTO;
import com.example.employeems.service.EmployeeService;
import com.example.employeems.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping(value = "/saveEmployee")
    public ResponseEntity<?> saveEmployee(@RequestBody EmployeeDTO employeeDTO){
        try {
            String res= employeeService.saveEmployee(employeeDTO);
            if (res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success!");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            }else if (res.equals("06")){
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Employee already registered!");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }else {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Error!");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping (value = "/updateEmployee")
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDTO employeeDTO){
        try {
            String res= employeeService.updateEmployee(employeeDTO);
            if (res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success!");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            }else if (res.equals("01")){
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Not a registered employee!");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }else {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Error!");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
