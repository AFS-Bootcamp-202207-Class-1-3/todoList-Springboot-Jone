package com.example.todolist;

import com.example.todolist.Entity.Todo;
import com.example.todolist.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;

import static org.hamcrest.Matchers.hasSize;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
class TodoTests {
	@Resource
	MockMvc client;

	@Resource
	TodoRepository todoRepository;

	@BeforeEach
	void init(){
		todoRepository.deleteAll();
		todoRepository.save(new Todo("todo test", false));
	}


	@Test
	void should_return_all_todo_when_find_all_given_none() throws Exception {

	    // when  && then
		client.perform(MockMvcRequestBuilders.get("/todos"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.*",hasSize(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].context").value("todo test"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].done").value(false));
	}

}
