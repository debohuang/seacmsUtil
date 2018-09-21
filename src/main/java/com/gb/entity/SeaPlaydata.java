package com.gb.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sea_playdata database table.
 * 
 */
@Entity
@Table(name="sea_playdata")
@NamedQuery(name="SeaPlaydata.findAll", query="SELECT s FROM SeaPlaydata s")
public class SeaPlaydata extends com.gb.entity.support.BaseEntity  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="v_id", unique=true, nullable=false)
	private int vId;

	@Lob
	private String body;

	@Lob
	private String body1;

	@Column(nullable=false)
	private int tid;

	public SeaPlaydata() {
	}

	public int getVId() {
		return this.vId;
	}

	public void setVId(int vId) {
		this.vId = vId;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getBody1() {
		return this.body1;
	}

	public void setBody1(String body1) {
		this.body1 = body1;
	}

	public int getTid() {
		return this.tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

}