package de.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PreAuthorize;

import de.demo.entity.album.AlbumEntity;

import java.util.List;
	
public interface AlbumRepository extends JpaRepository<AlbumEntity, Long> {

  List<AlbumEntity> findByTitleContainsIgnoreCase(String partialTitle);

  List<AlbumEntity> findByDescriptionContainsIgnoreCase(String partialDescription);
  
  List<AlbumEntity> findByGenreIgnoreCase(String genre);

  List<AlbumEntity> findByTitleContainsOrDescriptionContainsAllIgnoreCase(String partialTitle,
    String partialDescription);
//
//  //TODO
//  @Query("select v from Album v where v.title = ?1")
//  List<AlbumEntity> findCustomerReport(String title);
  
  @PreAuthorize("#entity.username == authentication.name")
  @Override
  void delete(AlbumEntity entity);
}
