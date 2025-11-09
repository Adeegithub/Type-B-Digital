package com.adeesha.typeBDigital.controller;

import com.adeesha.typeBDigital.response.ErrorResponse;
import com.adeesha.typeBDigital.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/hello-world")
public class NameController {
    @GetMapping
    public ResponseEntity<?> getResult(@RequestParam String name) {

        // If the name is Empty or Null
        if (name == null || name.trim().isBlank()){
           return ResponseEntity
                   .status(HttpStatus.BAD_REQUEST)
                   .body(new ErrorResponse("Invalid Input"));
        }

        char firstLetter = Character.toUpperCase(name.charAt(0));

        // If the name is in First Half
        if (firstLetter >= 'A' && firstLetter <= 'N'){
            String capitalizedName = name.substring(0,1).toUpperCase() +
                    name.substring(1).toLowerCase();

            return ResponseEntity.ok(new MessageResponse("Hello " + capitalizedName));
        }

        // If the name is in Second Half
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("Invalid Input"));
    }
}
