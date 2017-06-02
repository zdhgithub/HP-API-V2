package com.heipiao.api.v2;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"config=classpath:config.properties"})
@WebAppConfiguration
public class AllianceTests {

	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mvc;
	
	@Before
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testAlliance() throws Exception {
		getAlliance();
	}
	
	@Test
	private void getAlliance() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/alliance/3475");
		mvc.perform(request)
			.andExpect(MockMvcResultMatchers.status().isOk())
			;
	}

}
