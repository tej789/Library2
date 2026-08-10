package com.example.library.service;

import com.example.library.model.Book;
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

@Transactional
public void addWriterAndBook(){
       Writer writer1 = new Writer(8,"tej2");
       wr.save(writer1);

       Book book4 = new Book(8,"Book8",writer1,555);
       br.save(book4);


}

}
