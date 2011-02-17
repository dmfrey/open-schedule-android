package org.openschedule.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties( ignoreUnknown = true )
public class Event {

	@JsonProperty
	private Integer id;

	@JsonProperty
	private String name;

	@JsonProperty
	private String shortName;

	@JsonProperty
	private Date publishDate;

	@JsonProperty
	private Date startDate;

	@JsonProperty
	private Date endDate;

	@JsonProperty
	private Integer numberOfDays;

	@JsonProperty
	private String username;

	@JsonProperty
	private List<Day> days;

	@JsonProperty
	private List<Venue> venues;

	private List<Comment> comments;
	
	public Event() { }

	public Integer getId() {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName( String shortName ) {
		this.shortName = shortName;
	}

	public Date getPublishDate() {
		Calendar cal = new GregorianCalendar();
		cal.setTime( publishDate );
		cal.add( Calendar.DATE, 1 );
		
		return cal.getTime();
	}

	public void setPublishDate( Date publishDate ) {
		this.publishDate = publishDate;
	}

	public Date getStartDate() {
		Calendar cal = new GregorianCalendar();
		cal.setTime( startDate );
		cal.add( Calendar.DATE, 1 );
		
		return cal.getTime();
	}

	public void setStartDate( Date startDate ) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		Calendar cal = new GregorianCalendar();
		cal.setTime( endDate );
		cal.add( Calendar.DATE, 1 );
		
		return cal.getTime();
	}

	public void setEndDate( Date endDate ) {
		this.endDate = endDate;
	}

	public Integer getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays( Integer numberOfDays ) {
		this.numberOfDays = numberOfDays;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername( String username ) {
		this.username = username;
	}

	public List<Day> getDays() {
		return days;
	}

	public void setDays( List<Day> days ) {
		this.days = days;
	}

	public List<Venue> getVenues() {
		return venues;
	}

	public void setVenues( List<Venue> venues ) {
		this.venues = venues;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments( List<Comment> comments ) {
		this.comments = comments;
	}

}
