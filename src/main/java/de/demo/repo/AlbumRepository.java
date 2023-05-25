package de.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
	
public interface AlbumRepository extends JpaRepository<Album, Long> {

  List<Album> findByTitleContainsIgnoreCase(String partialTitle);

  List<Album> findByDescriptionContainsIgnoreCase(String partialDescription);

  List<Album> findByTitleContainsOrDescriptionContainsAllIgnoreCase(String partialTitle,
    String partialDescription);

  //TODO
  @Query("select v from Album v where v.title = ?1")
  List<Album> findCustomerReport(String title);
  
}
