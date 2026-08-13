package com.example.library.controller;

import com.example.library.DTO.WriterDTO;
import com.example.library.model.Writer;
import com.example.library.service.WriterService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WriterController {

    private final WriterService ws;

    public WriterController(WriterService ws){
        this.ws = ws;
    }

    @PostMapping("/writer")
    public ResponseEntity<Writer> addWriter(@Valid @RequestBody Writer writer){

        Writer savedWriter = ws.addWriter(writer);

        return new ResponseEntity<>(savedWriter, HttpStatus.CREATED);

    }

    @GetMapping("/writer")
    public ResponseEntity<List<Writer>> getWriter(){
        List<Writer> w = ws.getWriters();
        return new ResponseEntity<>(w,HttpStatus.OK);
    }
//
//    @GetMapping("/writerDTO")
//    public WriterDTO GW(){
//        Writer writer = ws.gw_DTO();
//  return new WriterDTO(writer.getName());
//    }

    @GetMapping("writerDTO")
    public List<WriterDTO> GW(){
        return ws.gw_DTO()
                  .stream()
                  .map(writer -> new WriterDTO(writer))
                .toList();
    }


    @PostMapping("writer/addWriterAndBook")
    public String addWriterBook(){
        ws.addWriterAndBook();
        return "Saved";
    }


}
