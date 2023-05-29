package de.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import de.demo.controller.HomeController;
import de.demo.entity.album.NewAlbumDTO;
import de.demo.service.AlbumService;



@WebMvcTest(controllers = HomeController.class)
public class HomeControllerTest {

  @Autowired MockMvc mvc;

  @MockBean AlbumService albumService;

  @Test
  @WithMockUser
  void indexPageHasSeveralHtmlForms() throws Exception {
    String html = mvc.perform( //
      get("/").with(csrf().asHeader()))   //
      .andExpect(status().isOk()) //
      .andExpect( //
        content().string( //
          containsString("Username: user"))) //
      .andExpect( //
        content().string( //
          containsString("Authorities: [ROLE_USER]"))) //
      .andReturn() //
      .getResponse().getContentAsString();

    assertThat(html).contains( //
      "<form action=\"/logout\"", //
      "<form action=\"/search\"", //
      "<form action=\"/new-album\"");
  }

  @Test
  @WithMockUser
  void postNewAlbumShouldWork() throws Exception {
    mvc.perform( //
      post("/new-album") //
        .param("title", "new album") //
//        .param("description", "new desc") //
        .param("artist", "mike jackson") //
        .param("yearOfRelease", "1983") //
        .param("genre", "rock")
        .param("username", "user")//
        .with(csrf())) //
      .andExpect(redirectedUrl("/"));

    verify(albumService).create( //
      new NewAlbumDTO( //
    		 "mike jackson",
        "new album", // //
      1983, "rock"),"user");
  }
}
