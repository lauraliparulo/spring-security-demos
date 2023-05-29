package de.demo;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import de.demo.controller.HomeController;
import de.demo.service.AlbumService;

@WebMvcTest(controllers = HomeController.class)
public class SecurityBasedTest {

  @Autowired MockMvc mvc;

  @MockBean AlbumService albumService;

  @Test
  void unauthUserShouldNotAccessHomePage() throws Exception {
    mvc //
    .perform(get("/")) //
      .andExpect(status().isUnauthorized());
  }

  @Test
  @WithMockUser(username = "alice", roles = "USER")
  void authUserShouldAccessHomePage() throws Exception {
    mvc //
      .perform(get("/").with(csrf().asHeader())) //
      .andExpect(status().isOk());
  }

  @Test
  @WithMockUser(username = "alice", roles = "ADMIN")
  void adminShouldAccessHomePage() throws Exception {
    mvc //
    .perform(get("/").with(csrf().asHeader()))  //
      .andExpect(status().isOk());
  }

  @Test
  void newVideoFromUnauthUserShouldFail() throws Exception {
    mvc.perform( //
      post("/new-album") //
        .param("name", "new video") //
        .param("description", "new desc") //
        .with(csrf())) //
      .andExpect(status().isUnauthorized());
  }

  @Test
  @WithMockUser(username = "alice", roles = "USER")
  void newVideoFromUserShouldWork() throws Exception {
    mvc.perform( //
      post("/new-album") //
        .param("title", "new album") //
//      .param("description", "new desc") //
      .param("artist", "mike jackson") //
      .param("yearOfRelease", "1983") //
      .param("genre", "rock")
      .param("username", "user")//
        .with(csrf())) //
      .andExpect(status().is3xxRedirection()) //
      .andExpect(redirectedUrl("/"));
  }
}
