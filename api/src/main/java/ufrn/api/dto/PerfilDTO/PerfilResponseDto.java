package ufrn.api.dto.PerfilDTO;

import org.springframework.hateoas.RepresentationModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ufrn.api.controller.PerfilController;
import ufrn.api.domain.Perfil;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerfilResponseDto extends RepresentationModel<PerfilResponseDto> {
    String gmail;
    String senha;

    public void addLinks(Perfil p){
        this.add(linkTo(PerfilController.class).slash(p.getId()).withSelfRel());
    }
}
