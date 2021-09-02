package br.com.leonardo.mercadolivre.controller;

import br.com.leonardo.mercadolivre.dto.usuario.UsuarioForm;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioControllerTest {

    @Autowired
    UsuarioRepository repository;
    @Autowired
    UsuarioController controle;
    @Autowired
   private MockMvc mvc;
    Gson gson = new Gson();




    //Se der tudo certo, deve retornar 200
    @Test
    public void usuarioNormal() throws Exception {

        //cenario
        UsuarioForm form = new UsuarioForm("leonardo.junior@gmail.com", "123456");


        mvc.perform(MockMvcRequestBuilders
                .post("/usuarios")
                .content(gson.toJson(form))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
    //Se não tiver login, deve retornar 400
    @Test
    public void usuarioSemLogin() throws Exception {

        //cenario
        UsuarioForm form = new UsuarioForm("", "123456");


        mvc.perform(MockMvcRequestBuilders
                .post("/usuarios")
                .content(gson.toJson(form))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andReturn();
    }


    //Se não tiver senha, deve retornar 400
    @Test
    public void usuarioSemSenha() throws Exception {

        //cenario
        UsuarioForm form = new UsuarioForm("leonardo.junior@gmail.com", "");


        mvc.perform(MockMvcRequestBuilders
                .post("/usuarios")
                .content(gson.toJson(form))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andReturn();
    }



   // Se a senha tiver menos que 6 caracteres deve retornar 400
    @Test
    public void usuarioSenhaMenorque6() throws Exception {

        //cenario
        UsuarioForm form = new UsuarioForm("leonardo.junior@gmail.com", "1234");

        MvcResult result =  mvc.perform(MockMvcRequestBuilders
                .post("/usuarios")
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

    // Se o usuario ja existir no banco deve retornar 400
    @Test
    public void usuarioJáExiste() throws Exception {

        //cenario
        UsuarioForm form = new UsuarioForm("leonardo.junior@gmail.com", "123456");

        mvc.perform(MockMvcRequestBuilders
                .post("/usuarios")
                .content(gson.toJson(form))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());






    }


}
