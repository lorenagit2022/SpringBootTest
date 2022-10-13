package com.evaluation.client;

import com.evaluation.client.controller.ClientController;
import com.evaluation.client.entities.Client;
import com.evaluation.client.exception.ClientNotFoundException;
import com.evaluation.client.service.ClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ClientController.class)
class ClientApplicationTests {

    static final Integer ID = 1;
    static final String IDENTIFICATION = "1712997517";

    static final String NAME = "Pepe";

    static final String GENDER = "masculino";

    static final Integer AGE = 25;

    static final String DIRECTION = "Calle y secundaria casa n";

    static final Integer PHONE = 022123456;

    static final String PASSWORD = "123";

    static final Boolean STATE = true;
    static final String URL_SAVE_PROSPECT = "/client/5467891235";

    Client client;

    ObjectMapper objectMapper = new ObjectMapper();

    Optional<Client> clientOptional;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ClientService clientService;

    @BeforeEach
    void init() {
        client = new Client();
		clientOptional = Optional.of(client);
    }

    @Test
    void shouldReturnClientWhenIdentificationIsValid() throws Exception {
        when(clientService.findByIdentify(IDENTIFICATION)).thenReturn(clientOptional);

        mockMvc.perform(get(URL_SAVE_PROSPECT).contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(client))).andExpect(status().isOk());
    }

	@Test
	void shouldReturnClientWhenUpdateClient() throws Exception {
		when(clientService.updateClient(client)).thenReturn(client);

		mockMvc.perform(post(URL_SAVE_PROSPECT).contentType(APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(client))).andExpect(status().isOk());
	}

}
