package ufrn.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import ufrn.api.service.PerfilService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import ufrn.api.domain.*;

@RestController
@AllArgsConstructor
@RequestMapping("/perfils/")
public class PerfilController {
    
   PerfilService service;

   @GetMapping
   public Page<Perfil> listAll(Pageable pageable) {
       
   return service.listAll(pageable);

   }
   

}
