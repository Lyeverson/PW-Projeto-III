package ufrn.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import ufrn.api.service.PerfilService;
import ufrn.api.service.PessoaService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ufrn.api.dto.PessoaDTO.*;
import ufrn.api.domain.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("/pessoas/")
@AllArgsConstructor
public class PessoaController {
    
   private final PessoaService service;
   private final ModelMapper mapper;

   @GetMapping
   public Page<PessoaResponseDto> listAll(Pageable pageAble) {
       Page<Pessoa> pessoasPage = service.listAll(pageAble);

       return pessoasPage.map(this::convertToDto);
   }

   @GetMapping("{id}")
   public ResponseEntity<PessoaResponseDto> listById(@PathVariable("id") Long id) {
       Pessoa p = service.listById(id);
       PessoaResponseDto dto = mapper.map(p, PessoaResponseDto.class);
       return ResponseEntity.ok(dto);
   }

   @PostMapping
   public ResponseEntity<PessoaResponseDto> create(@RequestBody PessoaRequestDto pessoa) {
           Pessoa created = service.create(convertToEntity(pessoa));

           URI location = ServletUriComponentsBuilder
                  .fromCurrentRequest()
                  .path("{id}")
                  .buildAndExpand(created.getId())
                  .toUri();

        return ResponseEntity.created(location).body(convertToDto(created));
   
       
   }

   @DeleteMapping("{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        service.deleteById(id);
    }
    

     @PutMapping("{id}")
    public ResponseEntity<PessoaResponseDto> update(@RequestBody PessoaRequestDto requestDto, @PathVariable("id") Long id) {

        try {
            Pessoa p = service.listById(id);
        } catch (Exception e) {
            return this.create(requestDto);
        }
        Pessoa PessoaUpdated = service.update(mapper.map(requestDto, Pessoa.class), id);
        return ResponseEntity.ok(convertToDto(PessoaUpdated));
    }
    
   
   


   private PessoaResponseDto convertToDto(Pessoa created) {
      PessoaResponseDto pessoaResponseDto = mapper.map(created, PessoaResponseDto.class);
      pessoaResponseDto.addLinks(created);
      return pessoaResponseDto;
  }
   

  private Pessoa convertToEntity(PessoaRequestDto pessoa) {
        Pessoa entityPessoa = mapper.map(pessoa, Pessoa.class);
        Perfil entityPerfil = mapper.map(pessoa.getPerfil(), Perfil.class);
        entityPessoa.setPerfil(entityPerfil);
        return entityPessoa;
    }
    
}
