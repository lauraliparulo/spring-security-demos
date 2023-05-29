package de.demo;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import de.demo.entity.album.AlbumEntity;
import de.demo.repo.AlbumRepository;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(initializers = de.demo.AlbumRepositoryTestcontainersTest.DataSourceInitializer.class)
public class AlbumRepositoryTestcontainersTest {

  @Autowired AlbumRepository repository;

  @Container //
  static final PostgreSQLContainer<?> database = //
    new PostgreSQLContainer<>("postgres:9.6.12") //
      .withUsername("postgres");

  static class DataSourceInitializer //
    implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
      TestPropertySourceUtils.addInlinedPropertiesToEnvironment(applicationContext, //
        "spring.datasource.url=" + database.getJdbcUrl(), //
        "spring.datasource.username=" + database.getUsername(), //
        "spring.datasource.password=" + database.getPassword(), //
        "spring.jpa.hibernate.ddl-auto=create-drop");
    }
  }

  @BeforeEach
  void setUp() {
    repository.saveAll( //
      List.of( //
    		   new AlbumEntity("Def Leppard", "Hysteria", 1987, "rock","admin"),
    		    new AlbumEntity("Poison", "Look what the cat dragged in", 1985, "glam rock","admin"),
    		    new AlbumEntity("Kiss", "Revenge", 1991, "rock","admin")));
  }

  @Test
  void findAllShouldProduceAllVideos() {
    List<AlbumEntity> albums = repository.findAll();
    assertThat(albums).hasSize(3);
  }

  @Test
  void findByName() {
    List<AlbumEntity> albums = repository.findByTitleContainsIgnoreCase("what");
    assertThat(albums).hasSize(1);
  }

  @Test
  void findByNameOrDescription() {
    List<AlbumEntity> albums = repository.findByGenreIgnoreCase("rock");
    assertThat(albums).hasSize(2);
  }
}
