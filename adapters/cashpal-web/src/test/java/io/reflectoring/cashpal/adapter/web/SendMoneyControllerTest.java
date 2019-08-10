package io.reflectoring.cashpal.adapter.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.reflectoring.cashpal.adapter.web.SendMoneyController.SendMoneyForm;
import io.reflectoring.cashpal.application.port.in.SendMoneyUseCase;
import io.reflectoring.cashpal.application.port.in.SendMoneyUseCase.SendMoneyCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = SendMoneyController.class)
class SendMoneyControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SendMoneyUseCase sendMoneyUseCase;

	private ObjectMapper jsonMapper = new ObjectMapper();

	@Test
	void testSendMoney() throws Exception {

		SendMoneyForm form = new SendMoneyForm(
				41L,
				42L,
				500L
		);

		mockMvc.perform(post("/sendMoney")
				.header("Content-Type", "application/json")
				.content(jsonMapper.writeValueAsString(form)))
				.andExpect(status().isOk());

		then(sendMoneyUseCase).should().sendMoney(any(SendMoneyCommand.class));
	}

}