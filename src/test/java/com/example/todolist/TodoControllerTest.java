package com.example.todolist;

import com.example.todolist.Entity.Todo;
import com.example.todolist.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
class TodoControllerTest {
	@Resource
	MockMvc client;

	@Resource
	TodoRepository todoRepository;

	private int savedTodoId;

	@BeforeEach
	void init(){
		todoRepository.deleteAll();
		Todo todo = todoRepository.save(new Todo("todo test", false));
		savedTodoId = todo.getId();
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

	@Test
	public void should_add_an_Todo_when_perform_post_given_an_Todo() throws Exception {
		// given
		String newTodo = "{\n" +
				"    \"context\": \"todo post test\",\n" +
				"    \"done\": false\n" +
				"}";
		// when & then
		client.perform(MockMvcRequestBuilders.post("/todos")
				.contentType(MediaType.APPLICATION_JSON)
				.content(newTodo))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.context").value("todo post test"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.done").value(false));
	}

	@Test
	public void should_delete_a_todo_when_perform_delete_given_id() throws Exception {
		client.perform(MockMvcRequestBuilders.delete("/todos/{id}", savedTodoId))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
	}

}
