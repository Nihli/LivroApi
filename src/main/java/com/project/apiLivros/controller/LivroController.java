package com.project.apiLivros.controller;

import com.project.apiLivros.entity.Ano;
import com.project.apiLivros.entity.Livro;
import com.project.apiLivros.entity.LivroAno;
import com.project.apiLivros.repository.AnoRepository;
import com.project.apiLivros.repository.LivroAnoRepository;
import com.project.apiLivros.repository.LivroRepository;
import com.project.apiLivros.request.LivroRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.PrintWriter;
import java.io.StringWriter;

@RestController
@RequestMapping(path="/livro")
public class LivroController {
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private AnoRepository anoRepository;
    @Autowired
    private LivroAnoRepository livroAnoRepository;

    @PostMapping(path="/add")
    public ResponseEntity addNewBook (@RequestBody LivroRequest livrorequest){
        ModelMapper modelMapper = new ModelMapper();

        try {
            Livro newLivro = modelMapper.map(livrorequest, Livro.class);
            System.out.println(newLivro);

            livroRepository.save(newLivro);
            System.out.println(newLivro);

            Ano ano = anoRepository.findByAno(livrorequest.getAno());

            LivroAno livroAno = new LivroAno();
            livroAno.setIdLivro(newLivro.getId());
            livroAno.setIdAno(ano.getId());

            livroAnoRepository.save(livroAno);

            return new ResponseEntity<>("Livro cadastrado!", HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            return new ResponseEntity<>(sw.toString(), HttpStatus.BAD_GATEWAY);
        }
    }
}
