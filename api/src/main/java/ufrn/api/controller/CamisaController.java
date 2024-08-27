package ufrn.api.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.AllArgsConstructor;
import ufrn.api.domain.Camisa;
import ufrn.api.dto.CamisaDTO.CamisaRequestDto;
import ufrn.api.dto.CamisaDTO.CamisaResponseDto;
import ufrn.api.service.CamisaService;




import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ufrn.api.domain.Pedido;


import java.net.URI;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@AllArgsConstructor
@RequestMapping("/camisas/")
public class CamisaController {
    
    private final CamisaService service;
    private final ModelMapper mapper;

    @GetMapping
    public Page<CamisaResponseDto> listAll(Pageable pageable) {
        Page<Camisa> camisasPage = service.listAll(pageable);
        return camisasPage.map(this::convertToDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<CamisaResponseDto> listById(@PathVariable("id") Long id) {
        Camisa camisa = service.listById(id);
        CamisaResponseDto dto = mapper.map(camisa, CamisaResponseDto.class);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<CamisaResponseDto> create(@RequestBody CamisaRequestDto camisaRequestDto) {
        Camisa created = service.create(convertToEntity(camisaRequestDto));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(convertToDto(created));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

      @PutMapping("{id}")
    public ResponseEntity<CamisaResponseDto> update(@RequestBody CamisaRequestDto camisaRequestDto, @PathVariable("id") Long id) {
        // Fetch the existing entity
        Camisa existingCamisa = service.listById(id);
        
        // Map updated fields
        Camisa camisaUpdated = mapper.map(camisaRequestDto, Camisa.class);
        camisaUpdated.setId(id); // Ensure the ID is set correctly
        
        // Update the entity
        Camisa updatedCamisa = service.update(camisaUpdated, camisaUpdated.getId());

        return ResponseEntity.ok(convertToDto(updatedCamisa));
    }

    private CamisaResponseDto convertToDto(Camisa camisa) {
        CamisaResponseDto camisaResponseDto = mapper.map(camisa, CamisaResponseDto.class);
        camisaResponseDto.addLinks(camisa);
        return camisaResponseDto;
    }

    private Camisa convertToEntity(CamisaRequestDto camisaRequestDto) {
        // Mapeia o DTO para a entidade Camisa
        Camisa camisaEntity = mapper.map(camisaRequestDto, Camisa.class);
    
        // Mapeia a lista de Pedidos, se existirem
        List<Pedido> pedidos = camisaRequestDto.getPedidos().stream()
            .map(pedidoRequestDto -> mapper.map(pedidoRequestDto, Pedido.class))
            .toList();
    
        // Define a lista de pedidos na entidade Camisa
        camisaEntity.setPedidos(pedidos);
    
        return camisaEntity;
    }




}
