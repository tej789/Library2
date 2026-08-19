package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.model.Type;
import com.example.library.model.Writer;
import com.example.library.repository.BookRepository;
import com.example.library.repository.WriterRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WriterService {

    private final WriterRepository wr;
    private final BookRepository br;

   public WriterService(WriterRepository wr,BookRepository br){
       this.wr = wr;
       this.br = br;
   }
public Writer addWriter(Writer writer){
       return wr.save(writer);
}

public List<Writer> getWriters(){
       return wr.findAll();
}

//public Writer gw_DTO(){
//       return wr.findById(4).orElseThrow();
//}


    public List<Writer> gw_DTO(){
       return wr.findAll();
    }

@Transactional
public void addWriterAndBook(){
       Writer writer17 = new Writer(18,"tej18");
       wr.save(writer17);


       Book book4 = new Book(19,null,writer17,444);
       br.save(book4);


}

}
