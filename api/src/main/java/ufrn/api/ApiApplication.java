package ufrn.api;


import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ufrn.api.core.security.RsaKeyProperties;
import ufrn.api.domain.Perfil;
import ufrn.api.domain.Pessoa;
import ufrn.api.domain.SecurityUser;
import ufrn.api.repository.SecurityUserRerpository;
@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    };

    @Autowired
    SecurityUserRerpository securityUserRerpository;

    @Autowired
    BCryptPasswordEncoder encoder;


	@PostConstruct
    public void started() {
        Pessoa p = new Pessoa();

        p.setNome("Joao");
        p.setAdmin(true);
        p.setDataNascimento("12/12/1920");

        Perfil e = new Perfil();
        e.setGmail("joao1211@gmail.com");
        e.setSenha("12323");

        p.setPerfil(e);

        SecurityUser securityUser = new SecurityUser();
        securityUser.setPessoa(p);
        securityUser.setUsername("admin");
        securityUser.setPassword(encoder.encode("admin"));

        securityUserRerpository.save(securityUser);
    }
}
