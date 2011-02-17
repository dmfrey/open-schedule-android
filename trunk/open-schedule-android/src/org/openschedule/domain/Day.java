package org.openschedule.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties( ignoreUnknown = true )
public class Day {

	@JsonProperty
	private Integer id;

	@JsonProperty
	private Date date;

	@JsonProperty
	private Integer numberOfSchedules;

	@JsonProperty
	private List<Schedule> schedules = new ArrayList<Schedule>();

	public Day() { }
	
	public Integer getId() {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public Day( Date date ) {
		this.date = date;
	}

	public Date getDate() {
		Calendar cal = new GregorianCalendar();
		cal.setTime( date );
		cal.add( Calendar.DATE, 1 );
		
		return cal.getTime();
	}

	public void setDate( Date date ) {
		this.date = date;
	}

	public Integer getNumberOfSchedules() {
		return numberOfSchedules;
	}

	public void setNumberOfSchedules( Integer numberOfSchedules ) {
		this.numberOfSchedules = numberOfSchedules;
	}

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules( List<Schedule> schedules ) {
		this.schedules = schedules;
	}
	
}
