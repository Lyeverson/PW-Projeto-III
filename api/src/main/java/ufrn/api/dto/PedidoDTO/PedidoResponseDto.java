package ufrn.api.dto.PedidoDTO;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ufrn.api.controller.CamisaController;
import ufrn.api.controller.PedidoController;
import ufrn.api.controller.PessoaController;
import ufrn.api.domain.Camisa;
import ufrn.api.domain.Pedido;
import ufrn.api.dto.PessoaDTO.PessoaResponseDto;
import ufrn.api.dto.CamisaDTO.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoResponseDto extends RepresentationModel<PedidoResponseDto>{
    
    String descricao;

    
    PessoaResponseDto pessoa;

   @JsonIgnore
    List<Camisa> camisas;


    public void addLinks(Pedido p){
       this.add(linkTo(PedidoController.class).slash(p.getId()).withSelfRel());
       this.add(linkTo(PessoaController.class).slash(p.getPessoa().getId()).withRel("Pessoa"));
       

       if(camisas!=null){
        camisas.forEach(camisa -> 
        this.add(linkTo(CamisaController.class).slash(camisa.getId()).withRel("camisa"))
        );
       }
    }
}
