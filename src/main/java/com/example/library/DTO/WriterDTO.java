package com.example.library.DTO;

import com.example.library.model.Writer;

public class WriterDTO{

    private String name;

    public WriterDTO(Writer writer){
        this.name = writer.getName();
    }

    public String getName(){
        return name;
    }
}
