package com.example.library.controller;

import com.example.library.model.Type;
import com.example.library.service.TypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TypeController {

    private final TypeService ts;

    public TypeController(TypeService ts){
        this.ts =ts;
    }

    @PostMapping("/type")
    public ResponseEntity<Type> addType(@RequestBody Type type){

        Type savedType = ts.addType(type);
        return new ResponseEntity<>(savedType, HttpStatus.OK);
    }

    @GetMapping("/book/type/{name}")
    public Object findBookByType(@PathVariable String name){
        return ts.findBookByType(name);
    }
}
