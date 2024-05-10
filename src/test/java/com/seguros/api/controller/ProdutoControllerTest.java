package com.seguros.api.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seguros.api.dto.ProdutoDto;
import com.seguros.service.ProdutoService;


@WebMvcTest(ProdutoController.class)
@AutoConfigureMockMvc
class ProdutoControllerTest {
	
	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    
    @MockBean
    private ProdutoService produtoService;
    
    
	@Test
	public void testObterProdutos()  throws Exception {
		ProdutoDto dto = new ProdutoDto();
    	dto.setId(1L);
		dto.setNome("Seguro VIDA I");
		dto.setCategoria("VIDA");
    	dto.setPrecoBase(100.00);
    	dto.setPrecoTarifado(110.00);
               
        when(produtoService.obterProdutos()).thenReturn(Collections.singletonList(dto));

        mockMvc.perform(get("/produtos"))
               .andExpect(status().isOk())
               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
               .andDo(print());
    }
	
	@Test
	public void testObterProduto()  throws Exception {
		ProdutoDto dto = new ProdutoDto();
    	dto.setId(1L);
		dto.setNome("Seguro VIDA I");
		dto.setCategoria("VIDA");
    	dto.setPrecoBase(100.00);
    	dto.setPrecoTarifado(110.00);
        
        when(produtoService.obterProduto(dto.getId())).thenReturn(dto);

        mockMvc.perform(get("/produtos/{id}", dto.getId()))
               .andExpect(status().isOk())
               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
               .andDo(print());
    }
	
	@Test
	public void testSalvarNovoProduto()  throws Exception {
		ProdutoDto dto = new ProdutoDto();
    	dto.setId(1L);
		dto.setNome("Seguro VIDA I");
		dto.setCategoria("VIDA");
    	dto.setPrecoBase(100.00);
    	dto.setPrecoTarifado(110.00);
    	
        when(produtoService.persistirProduto(dto)).thenReturn(dto);

        mockMvc.perform(post("/produtos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
               .andExpect(status().isCreated())
               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
               .andDo(print());
        
    }
	
	@Test
	public void testAtualizarProduto()  throws Exception {
		ProdutoDto dto = new ProdutoDto();
    	dto.setId(1L);
		dto.setNome("Seguro VIDA I");
		dto.setCategoria("VIDA");
    	dto.setPrecoBase(1000.00);
    	dto.setPrecoTarifado(1100.00);
    	
        mockMvc.perform(put("/produtos/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
               .andExpect(status().isNoContent());
        
        verify(produtoService).persistirProduto(dto);
    }
	
	@Test
	public void testRemoverProduto()  throws Exception {
		
        mockMvc.perform(delete("/produtos/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isNoContent());
        
        verify(produtoService).removerProduto(1L);
    }

}
