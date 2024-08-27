package ufrn.api.dto.PessoaDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import ufrn.api.dto.PerfilDTO.PerfilRequestDto;
import ufrn.api.domain.Pessoa;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaRequestDto {
    

    String nome;
    String dataNascimento;
    String sexo;
    PerfilRequestDto perfil;


}
