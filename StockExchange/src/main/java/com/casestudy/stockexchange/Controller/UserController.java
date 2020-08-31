package com.casestudy.stockexchange.Controller;

import com.casestudy.stockexchange.Exception.BadRequestException;
import com.casestudy.stockexchange.Exception.IllegalRowException;
import com.casestudy.stockexchange.Exception.RecordNotFoundException;
import com.casestudy.stockexchange.Model.UserRequestModel;
import com.casestudy.stockexchange.Model.UserResponseModel;
import com.casestudy.stockexchange.Service.UploadExcelService;
import com.casestudy.stockexchange.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private UserService userService;
    private UploadExcelService uploadExcelService;

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";


    //INSERT THE EXCEPTIONS AND ERRORS LIKE NOT FOUND EXCEPTION OR BAD REQUEST EXCEPTION FROM APPLICATION.PROPERTIES

    @Autowired
    public UserController(UserService userService, UploadExcelService uploadExcelService) {
        this.userService = userService;
        this.uploadExcelService = uploadExcelService;
    }

    @GetMapping("/users/all")
    public ResponseEntity<List<UserResponseModel>> findAllUsers(){
        return new ResponseEntity<List<UserResponseModel>>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping("users/add")
    public  ResponseEntity<UserResponseModel> addNewUser(@RequestBody UserRequestModel userRequestModel){
        return new ResponseEntity<UserResponseModel>(userService.addUser(userRequestModel),HttpStatus.OK);
    }

    @GetMapping("/users/byid/{id}")
    public  ResponseEntity<UserResponseModel> findUserById(@PathVariable Integer id) throws RecordNotFoundException {
        UserResponseModel user = userService.findById(id);
        if(user==null)
            throw new RecordNotFoundException("No such user by ID = "+String.valueOf(id));
        return new ResponseEntity<UserResponseModel>(userService.findById(id),HttpStatus.OK);
    }

    @GetMapping("users/login/{userName}/{password}")
    public ResponseEntity<UserResponseModel> loginUser(@PathVariable String userName, @PathVariable String password) throws RecordNotFoundException {
        UserResponseModel userResponseModel = userService.findByUserNameandPassword(userName,password);
        if(userResponseModel==null)
            throw new RecordNotFoundException("UserName or Password does not match please try again");
        return new ResponseEntity<UserResponseModel>(userResponseModel,HttpStatus.OK);
    }

    @PostMapping(value = "users/upload")
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) throws BadRequestException, ParseException, IllegalRowException, IOException {
       try{if (!TYPE.equals(file.getContentType())) {
           throw new BadRequestException("File Format not Excel");
       }}catch (NullPointerException e){throw new BadRequestException("No such file");}

        uploadExcelService.uploadExcel(file);
        String message = "upload Successful";
        return new ResponseEntity<String>(message,HttpStatus.OK);
    }

        /*
        public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
    String message = "";

    if (ExcelHelper.hasExcelFormat(file)) {
      try {
        fileService.save(file);

        message = "Uploaded the file successfully: " + file.getOriginalFilename();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
      } catch (Exception e) {
        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
      }
    }

    message = "Please upload an excel file!";
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
  }

         */

}
