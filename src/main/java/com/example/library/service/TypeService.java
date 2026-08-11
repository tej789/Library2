package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.model.Type;
import com.example.library.repository.TypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {

    private final TypeRepository tr;

    public TypeService(TypeRepository tr){
            this.tr = tr;}


    public List<Book> findBookByType(String name){
        return tr.findBookByType(name);
    }

    public Type addType(Type type){
        return tr.save(type);
    }
}
