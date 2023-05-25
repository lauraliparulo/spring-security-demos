package de.demo.repo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Album {

  private @Id String id;
  private String title;
  private String artist;
  private Integer yearOfRelease;
  private String description;

//  protected Album() {
//    this(null, null, 0);
//  }
//
//
//  public Album(String title, String artist, int year) {
//	super();
//	this.id = null;
//	this.title = title;
//	this.artist = artist;
//	this.year = year;
//}



public String getId() {
    return id;
  }

  public void setId(String id) {
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
