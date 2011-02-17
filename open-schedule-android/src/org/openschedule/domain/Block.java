package org.openschedule.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties( ignoreUnknown = true )
public class Block {

	@JsonProperty
	private Integer id;

	@JsonProperty
	private Date date;
	
	@JsonProperty
	private Integer duration;
	
	@JsonProperty
	private Label label;

	@JsonProperty
	private Session session;

	public Block() {}
	
	public Integer getId() {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
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

	public Integer getDuration() {
		return duration;
	}

	public void setDuration( Integer duration ) {
		this.duration = duration;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel( Label label ) {
		this.label = label;
	}

	public Session getSession() {
		return session;
	}

	public void setSession( Session session ) {
		this.session = session;
	}

}
