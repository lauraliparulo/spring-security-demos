package de.demo.entity.album;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Album {

  private @Id @GeneratedValue Long id;
  private String title;
  private String artist;
  private String username;
  private int yearOfRelease;
  private String description;

  @Column(nullable = false) 
  private String genre;

  public String getGenre() {
	return genre;
}


public void setGenre(String genre) {
	this.genre = genre;
}


public void setYearOfRelease(int yearOfRelease) {
	this.yearOfRelease = yearOfRelease;
}


public  Album() {
	    this(null, null,0, null,null);
	  }

	  
  public Album(String title, String artist, int yearOfRelease, String genre, String username) {
	  this.id = null;
	  this.username = username;
	this.title = title;
	this.artist = artist;
	this.yearOfRelease = yearOfRelease;
	this.genre = genre;
}





public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }



  public String getUsername() {
	return username;
}


public void setUsername(String username) {
	this.username = username;
}


public String getTitle() {
	return title;
}


public void setTitle(String title) {
	this.title = title;
}


public String getArtist() {
	return artist;
}


public void setArtist(String artist) {
	this.artist = artist;
}


public Integer getYearOfRelease() {
	return yearOfRelease;
}


public void setYearOfRelease(Integer yearOfRelease) {
	this.yearOfRelease = yearOfRelease;
}


public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
