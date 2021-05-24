package ru.itis.sharing.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.sharing.dto.Log.LogDto;
import ru.itis.sharing.service.RentSerivce.RentService;

@RestController
public class RentController {

    @Autowired
    private RentService rentService;

    @PostMapping("rent/log")
    public ResponseEntity<LogDto> addNewLog(@RequestBody LogDto logDto) throws NotFoundException {
        rentService.addNewLog(logDto);
        return new ResponseEntity(logDto, HttpStatus.OK);
    }
}
