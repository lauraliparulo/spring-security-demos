package de.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Album {

  private @Id @GeneratedValue Long id;
  private String title;
  private String artist;
  private int yearOfRelease;
  private String description;

  protected Album() {
	    this(null, null,0);
	  }

	  
  public Album(String title, String artist, int yearOfRelease) {
	  this.id = null;
	this.title = title;
	this.artist = artist;
	this.yearOfRelease = yearOfRelease;
}





public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
