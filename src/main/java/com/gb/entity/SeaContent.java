package com.gb.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sea_content database table.
 * 
 */
@Entity
@Table(name="sea_content")
@NamedQuery(name="SeaContent.findAll", query="SELECT s FROM SeaContent s")
public class SeaContent extends com.gb.entity.support.BaseEntity  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="v_id", unique=true, nullable=false)
	private int vId;

	@Lob
	private String body;

	@Column(nullable=false)
	private int tid;

	public SeaContent() {
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

	public int getTid() {
		return this.tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

}