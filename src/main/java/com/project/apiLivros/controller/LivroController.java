package com.project.apiLivros.controller;

import com.project.apiLivros.entity.Ano;
import com.project.apiLivros.entity.Livro;
import com.project.apiLivros.entity.LivroAno;
import com.project.apiLivros.repository.AnoRepository;
import com.project.apiLivros.repository.LivroAnoRepository;
import com.project.apiLivros.repository.LivroRepository;
import com.project.apiLivros.request.LivroRequest;
import com.project.apiLivros.request.RemoveRequest;
import com.project.apiLivros.request.UpdateLivroRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

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
            //mapeia os dados de livroRequest para um objeto livro
            Livro newLivro = modelMapper.map(livrorequest, Livro.class);

            livroRepository.save(newLivro);
            System.out.println(newLivro);

            Ano ano = anoRepository.findByAno(livrorequest.getAno());

            //cria registro de Livro associado com o Ano
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

    @DeleteMapping(path="/remove")
    public ResponseEntity removeBook (@RequestBody RemoveRequest removeRequest){
        try {
            Optional<LivroAno> optLivroano = livroAnoRepository.findByIdLivro(removeRequest.getIdLivro());

            if(!optLivroano.isPresent()){
                return new ResponseEntity<>("Esse livro não está cadastrado!", HttpStatus.BAD_REQUEST);
            }

            //deleta registro da tabela intermediaria de livro com ano
            livroAnoRepository.delete(optLivroano.get());

            Livro livro = new Livro();
            livro.setId(removeRequest.getIdLivro());

            //deleta livro
            livroRepository.delete(livro);

            return new ResponseEntity<>("Livro deletado com sucesso!", HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            return new ResponseEntity<>(sw.toString(), HttpStatus.BAD_GATEWAY);
        }
    }

    @PutMapping(path="/update")
    public ResponseEntity updateBook(@RequestBody UpdateLivroRequest updateLivroRequest){
        try{
            //pega registro atual do livro
            Optional<Livro> optLivro = livroRepository.findById(updateLivroRequest.getIdLivro());

            if(!optLivro.isPresent()){
                return new ResponseEntity<>("Esse livro não está cadastrado!", HttpStatus.BAD_REQUEST);
            }

            Livro livro = optLivro.get();

            //pega registro da tabela LivroAno
            Optional<LivroAno> optLivroAno = livroAnoRepository.findByIdLivro(updateLivroRequest.getIdLivro());

            if(!optLivroAno.isPresent()){
                return new ResponseEntity<>("Esse livro não possui ano!", HttpStatus.BAD_REQUEST);
            }

            //pega o ano que está associado ao id que encontramos na tabela LivroAno
            String ano = anoRepository.findAnoById(optLivroAno.get().getIdAno());

            //se o ano passado no request for diferente do ano atual no banco
            if (!ano.equals(updateLivroRequest.getAno())){
                //deleta o registro da tabela LivroAno associado a esse livro
                livroAnoRepository.delete(optLivroAno.get());

                LivroAno livroAno = new LivroAno();

                //pega codigo do ano novo que vai ser inserido
                Ano novoAno = anoRepository.findByAno(updateLivroRequest.getAno());

                livroAno.setIdAno(novoAno.getId());
                livroAno.setIdLivro(updateLivroRequest.getIdLivro());

                //salva novo registro do livroAno
                livroAnoRepository.save(livroAno);
            }

            livro.setNome(updateLivroRequest.getNome());
            livro.setAutor(updateLivroRequest.getAutor());

            //atualiza livro
            livroRepository.save(livro);
            return new ResponseEntity<>("Livro atualizado com sucesso!", HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            return new ResponseEntity<>(sw.toString(), HttpStatus.BAD_GATEWAY);
        }
    }
}
