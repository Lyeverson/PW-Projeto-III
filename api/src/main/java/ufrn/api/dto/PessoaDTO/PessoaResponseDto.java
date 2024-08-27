package ufrn.api.dto.PessoaDTO;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ufrn.api.controller.PerfilController;
import ufrn.api.controller.PessoaController;
import ufrn.api.domain.Pessoa;
import ufrn.api.dto.PedidoDTO.*;
import ufrn.api.dto.PerfilDTO.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaResponseDto extends RepresentationModel<PessoaResponseDto> {
    
    String nome;
    String dataNascimento;
    String sexo;

    PerfilResponseDto perfil;

    
    //List<PedidoResponseDto> pedidos;

    public void addLinks(Pessoa p){
       this.add(linkTo(PessoaController.class).slash(p.getId()).withSelfRel());
       this.add(linkTo(PerfilController.class).slash(p.getPerfil().getId()).withRel("perfil"));
    }

}
