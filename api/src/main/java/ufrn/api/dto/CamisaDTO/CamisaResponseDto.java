package ufrn.api.dto.CamisaDTO;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ufrn.api.controller.CamisaController;
import ufrn.api.controller.PedidoController;
import ufrn.api.domain.Camisa;
import ufrn.api.domain.Pedido;
import ufrn.api.dto.PedidoDTO.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class CamisaResponseDto extends RepresentationModel<CamisaResponseDto> {
    
   String nome;
   Float preco;
   String descricao;
   String material;
   String imageUri;

   @JsonIgnore
   List<Pedido> pedidos;

   public void addLinks(Camisa c){
        this.add(linkTo(CamisaController.class).slash(c.getId()).withSelfRel());

       /*  if(pedidos != null){
            pedidos.forEach(pedido ->
            this.add(linkTo(PedidoController.class).slash(pedido.getId()).withRel("pedido")));
        }
            */
   }

}
