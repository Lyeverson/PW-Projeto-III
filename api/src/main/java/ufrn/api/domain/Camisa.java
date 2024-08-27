package ufrn.api.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ufrn.api.domain.generic.AbstractEntity;

import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE pessoa SET deleted_at = CURRENT_TIMESTAMP where id=?")
@SQLRestriction("deleted_at is null")
public class Camisa extends AbstractEntity{

    
    @Size(min = 3, max = 30, message = "Erro no cadastro do nome da camisa")
    @NotBlank(message = "O nome não pode conter caracteres em branco")
    @NotEmpty(message = "O nome não pode ser vazio")
    private String nome;

    private Float preco;

    private String descricao;

    @Size(min = 3, max = 30, message = "Erro no cadastro do material da camisa")
    @NotBlank(message = "O nome do material da camisa não pode conter caracteres em branco")
    @NotEmpty(message = "O nome do material da camisa não pode ser vazio")
    private String material;

    private String imageUri;


    @ManyToMany(mappedBy = "camisas")
    private List<Pedido> pedidos;
}
