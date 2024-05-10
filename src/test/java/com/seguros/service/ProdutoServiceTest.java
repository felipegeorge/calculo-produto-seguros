package com.seguros.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.seguros.api.dto.ProdutoDto;
import com.seguros.data.entity.CategoriaTarifariaEntity;
import com.seguros.data.entity.ProdutoEntity;
import com.seguros.data.repository.CategoriaTarifariaRepository;
import com.seguros.data.repository.ProdutoRepository;

@ExtendWith(SpringExtension.class)
class ProdutoServiceTest {
	
	@Mock
	private ModelMapper modelMapper = new ModelMapper(); 
	
	@Mock
    private CategoriaTarifariaRepository categoriaTarifariaRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;
	
	@Test
	void testPersistirProduto() {

		ProdutoDto inputProdutoDtoMock = new ProdutoDto();
		inputProdutoDtoMock.setCategoria("VIDA");
		inputProdutoDtoMock.setNome("Seguro VIDA I");
		inputProdutoDtoMock.setPrecoBase(100.00);
                
		CategoriaTarifariaEntity categoriaMock = new CategoriaTarifariaEntity();
		categoriaMock.setCategoria("VIDA");
		categoriaMock.setCofins(1.00);
		categoriaMock.setIof(2.00);
		categoriaMock.setPis(3.00);
		
		when(categoriaTarifariaRepository.findByCategoria(inputProdutoDtoMock.getCategoria())).thenReturn(categoriaMock);
		
		ProdutoEntity produtoEntityMock = new ProdutoEntity();
		produtoEntityMock.setId(1L);
		produtoEntityMock.setCategoria("VIDA");
		produtoEntityMock.setNome("Seguro VIDA I");
		produtoEntityMock.setPrecoBase(100.00);
		produtoEntityMock.setPrecoTarifado(110.00);
		
		when(modelMapper.map(inputProdutoDtoMock, ProdutoEntity.class)).thenReturn(produtoEntityMock);
				
        when(produtoRepository.save(produtoEntityMock)).thenReturn(produtoEntityMock);
                
        ProdutoDto retornoProdutoDto = new ProdutoDto();
        retornoProdutoDto.setId(1L);
        retornoProdutoDto.setCategoria("VIDA");
        retornoProdutoDto.setNome("Seguro VIDA I");
        retornoProdutoDto.setPrecoBase(100.00);
        retornoProdutoDto.setPrecoTarifado(110.00);
        
        when(modelMapper.map(produtoEntityMock, ProdutoDto.class)).thenReturn(retornoProdutoDto);
		
        ProdutoDto retornoDto = produtoService.persistirProduto(inputProdutoDtoMock);
        
        verify(categoriaTarifariaRepository, times(1)).findByCategoria(inputProdutoDtoMock.getCategoria());
        verify(produtoRepository, times(1)).save(produtoEntityMock);

        assertThat(retornoDto).isNotNull();
        assertThat(retornoDto.getId()).isNotNull();
        assertThat(retornoDto.getPrecoTarifado()).isNotNull();
	}

	@Test
    public void testRemoverProduto() {
        Long id = 1L;
        
        ProdutoEntity produtoEntityMock = new ProdutoEntity();
		produtoEntityMock.setId(1L);
		produtoEntityMock.setCategoria("VIDA");
		produtoEntityMock.setNome("Seguro VIDA I");
		produtoEntityMock.setPrecoBase(100.00);
		produtoEntityMock.setPrecoTarifado(110.00);
		
        when(produtoRepository.findById(id)).thenReturn(Optional.of(produtoEntityMock));
        
        produtoService.removerProduto(id);

        verify(produtoRepository, times(1)).findById(id);
        verify(produtoRepository, times(1)).delete(produtoEntityMock);
    }
}
