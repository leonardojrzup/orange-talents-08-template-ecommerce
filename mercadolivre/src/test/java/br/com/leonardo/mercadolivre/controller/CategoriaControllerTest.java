package br.com.leonardo.mercadolivre.controller;


import br.com.leonardo.mercadolivre.dto.categoria.CategoriaForm;
import br.com.leonardo.mercadolivre.dto.usuario.UsuarioForm;
import br.com.leonardo.mercadolivre.repository.CategoriaRepository;
import br.com.leonardo.mercadolivre.repository.UsuarioRepository;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CategoriaControllerTest {
    @Autowired
    CategoriaRepository repository;
    @Autowired
    CategoriaController controle;
    @Autowired
    private MockMvc mvc;
    Gson gson = new Gson();


    @Test
    public void categoriaNormal() throws Exception {


        CategoriaForm form = new CategoriaForm("categoria de teste");


        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post("/categorias")
                .content(gson.toJson(form))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();
        String contentAsString = result.getResponse().getContentAsString();
        System.out.println(contentAsString);

    }

    @Test
    public void categoriaSemNome() throws Exception {


        CategoriaForm form = new CategoriaForm("", 1L);


        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post("/categorias")
                .content(gson.toJson(form))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andReturn();

        String content = result.getResponse().getContentAsString();
        String contentAsString = result.getResponse().getContentAsString();
        System.out.println(contentAsString);


    }


    // Categoria sem uma mãe cadastrada no banco
    @Test
    public void categoriaSemMae() throws Exception {

        //cenario
        CategoriaForm form = new CategoriaForm("categoria sem mãe");


        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post("/categorias")
                .content(gson.toJson(form))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();


        String content = result.getResponse().getContentAsString();
        String contentAsString = result.getResponse().getContentAsString();
        System.out.println(contentAsString);

    }

    @Test
    public void categoriaRepetida() throws Exception {

        //cenario
        CategoriaForm form = new CategoriaForm("categoria sem mãe");

        MvcResult result =  mvc.perform(MockMvcRequestBuilders
                .post("/categorias")
                .content(gson.toJson(form))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andReturn();



        String content = result.getResponse().getContentAsString();
        //MvcResult result = resultado.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        System.out.println(contentAsString);

        ///       SomeCustomResponse response = objectMapper.readValue(contentAsString, SomeCustomResponse.class);


        //  assertNotNull(resultado);
        //assertNull(resultado);
    }

    @Test
    public void categoriaComMae() throws Exception {

        //categoria ainda não inserida com o id de uma categoria mãe ja inserida
        CategoriaForm form = new CategoriaForm("categoria com mãe", 1L);

        MvcResult result =  mvc.perform(MockMvcRequestBuilders
                .post("/categorias")
                .content(gson.toJson(form))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();
        String contentAsString = result.getResponse().getContentAsString();
        System.out.println(contentAsString);

    }

    @Test(expected = NestedServletException.class)

    public void categoriaComMaeQueNaoExiste() throws Exception {

        //categoria com mãe não cadastrada no banco de dados
        CategoriaForm form = new CategoriaForm("categoria com mãe ausente", 35424L);

        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post("/categorias")
                .content(gson.toJson(form))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andReturn();


        String content = result.getResponse().getContentAsString();
        String contentAsString = result.getResponse().getContentAsString();
        System.out.println(contentAsString);

    }




}
