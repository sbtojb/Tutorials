/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bs.controller;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.bs.dao.ItemDAO;
import com.bs.service.BillGenerationService;
import com.bs.vo.SelectedItem;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes={BillGenerationController.class,BillGenerationService.class,ItemDAO.class})
@SpringBootTest
@AutoConfigureMockMvc
public class BillGenerationControllerTests {

	private static final Logger log = LoggerFactory.getLogger(BillGenerationControllerTests.class);
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
	
	private HttpMessageConverter mappingJackson2HttpMessageConverter;
	
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
            .findAny()
            .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }
    
//    @Test
//    public void noParamGreetingShouldReturnDefaultMessage() throws Exception {
//
//        this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk())
//                .andExpect(jsonPath("$.content").value("Hello, World!"));
//    }
//
//    @Test
//    public void paramGreetingShouldReturnTailoredMessage() throws Exception {
//
//        this.mockMvc.perform(get("/greeting").param("name", "Spring Community"))
//                .andDo(print()).andExpect(status().isOk())
//                .andExpect(jsonPath("$.content").value("Hello, Spring Community!"));
//    }

    @Test
    public void billWithValidInput() throws IOException, Exception{
    	 mockMvc.perform(post("/bill")
                .content(convertToJson(getItemsUserWantToPurchase()))
                .contentType(contentType))
    	 		.andDo(print())
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.content").value("Hello, World!"));
    }

    private String convertToJson(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        String jsonString = mockHttpOutputMessage.getBodyAsString();
        log.info("JSON INPUT---------------"+jsonString);
        return jsonString;
    }
    
    private List<SelectedItem> getItemsUserWantToPurchase(){
    	List<SelectedItem> items = new ArrayList<>();
    	items.add(new SelectedItem(1,2));
    	items.add(new SelectedItem(2,3));
    	items.add(new SelectedItem(3,4));
    	return items;
    }
}
