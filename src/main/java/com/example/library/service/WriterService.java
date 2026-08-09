package com.example.library.service;

import com.example.library.model.Writer;
import com.example.library.repository.WriterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WriterService {

    private final WriterRepository wr;

   public WriterService(WriterRepository wr){
       this.wr = wr;
   }
public Writer addWriter(Writer writer){
       return wr.save(writer);
}

public List<Writer> getWriters(){
       return wr.findAll();
}

}
