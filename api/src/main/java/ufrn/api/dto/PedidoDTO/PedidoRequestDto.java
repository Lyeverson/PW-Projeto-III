package ufrn.api.dto.PedidoDTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ufrn.api.dto.CamisaDTO.CamisaRequestDto;
import ufrn.api.dto.PessoaDTO.PessoaRequestDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoRequestDto {
    

    String descricao;
    PessoaRequestDto pessoa;
    List<CamisaRequestDto> camisas;
}
