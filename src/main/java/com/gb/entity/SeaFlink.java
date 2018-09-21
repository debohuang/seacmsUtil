package com.gb.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sea_flink database table.
 * 
 */
@Entity
@Table(name="sea_flink")
@NamedQuery(name="SeaFlink.findAll", query="SELECT s FROM SeaFlink s")
public class SeaFlink extends com.gb.entity.support.BaseEntity  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false)
	private int dtime;

	@Column(nullable=false, length=50)
	private String email;

	@Column(nullable=false)
	private short ischeck;

	@Column(nullable=false, length=60)
	private String logo;

	@Column(nullable=false, length=200)
	private String msg;

	@Column(nullable=false)
	private short sortrank;

	@Column(nullable=false, length=60)
	private String url;

	@Column(nullable=false, length=30)
	private String webname;

	public SeaFlink() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDtime() {
		return this.dtime;
	}

	public void setDtime(int dtime) {
		this.dtime = dtime;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public short getIscheck() {
		return this.ischeck;
	}

	public void setIscheck(short ischeck) {
		this.ischeck = ischeck;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public short getSortrank() {
		return this.sortrank;
	}

	public void setSortrank(short sortrank) {
		this.sortrank = sortrank;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getWebname() {
		return this.webname;
	}

	public void setWebname(String webname) {
		this.webname = webname;
	}

}