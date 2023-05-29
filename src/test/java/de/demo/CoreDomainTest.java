package de.demo;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import de.demo.entity.album.AlbumEntity;

public class CoreDomainTest {

  @Test
  void newAlbumEntityShouldHaveNullId() {
    AlbumEntity entity = new AlbumEntity("alice cooper", "thrash", 1989, "hard rock", "alice");
    assertThat(entity.getId()).isNull();
    assertThat(entity.getUsername()).isEqualTo("alice");
    assertThat(entity.getYearOfRelease()) //
      .isEqualTo(1989);
  }

  @Test
  void settersShouldMutateState() {
	  AlbumEntity entity = new AlbumEntity("alice cooper", "thrash", 1989, "hard rock", "bob");
    entity.setId(99L);
    String HEY_STOOPID = "hey stoopid";
    
	entity.setTitle(HEY_STOOPID);
    entity.setYearOfRelease(1991);
    entity.setUsername("bob");
    assertThat(entity.getId()).isEqualTo(99L);
    assertThat(entity.getUsername()).isEqualTo("bob");
    assertThat(entity.getTitle()).isEqualTo(HEY_STOOPID);
//    assertThat(entity.getDescription()) //
//      .isEqualTo("new desc");
  }
}
