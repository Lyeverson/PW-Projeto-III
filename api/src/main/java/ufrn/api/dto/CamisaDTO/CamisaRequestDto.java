package ufrn.api.dto.CamisaDTO;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ufrn.api.dto.PedidoDTO.PedidoRequestDto;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CamisaRequestDto {
    
   String nome;
   Float preco;
   String descricao;
   String material;
   String imageURi;
   List<PedidoRequestDto> pedidos; 
}
