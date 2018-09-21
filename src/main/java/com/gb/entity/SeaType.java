package com.gb.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sea_type database table.
 * 
 */
@Entity
@Table(name="sea_type")
@NamedQuery(name="SeaType.findAll", query="SELECT s FROM SeaType s")
public class SeaType extends com.gb.entity.support.BaseEntity  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int tid;

	@Column(nullable=false, length=50)
	private String description;

	@Column(nullable=false)
	private short ishidden;

	@Column(nullable=false, length=50)
	private String keyword;

	@Column(nullable=false, length=50)
	private String templist;

	@Column(name="templist_1", nullable=false, length=50)
	private String templist1;

	@Column(name="templist_2", nullable=false, length=50)
	private String templist2;

	@Column(nullable=false, length=60)
	private String tenname;

	@Column(nullable=false, length=50)
	private String title;

	@Column(nullable=false, length=30)
	private String tname;

	@Column(nullable=false)
	private int torder;

	@Column(nullable=false)
	private short tptype;

	@Lob
	private String unionid;

	@Column(nullable=false)
	private byte upid;

	public SeaType() {
	}

	public int getTid() {
		return this.tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public short getIshidden() {
		return this.ishidden;
	}

	public void setIshidden(short ishidden) {
		this.ishidden = ishidden;
	}

	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getTemplist() {
		return this.templist;
	}

	public void setTemplist(String templist) {
		this.templist = templist;
	}

	public String getTemplist1() {
		return this.templist1;
	}

	public void setTemplist1(String templist1) {
		this.templist1 = templist1;
	}

	public String getTemplist2() {
		return this.templist2;
	}

	public void setTemplist2(String templist2) {
		this.templist2 = templist2;
	}

	public String getTenname() {
		return this.tenname;
	}

	public void setTenname(String tenname) {
		this.tenname = tenname;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTname() {
		return this.tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public int getTorder() {
		return this.torder;
	}

	public void setTorder(int torder) {
		this.torder = torder;
	}

	public short getTptype() {
		return this.tptype;
	}

	public void setTptype(short tptype) {
		this.tptype = tptype;
	}

	public String getUnionid() {
		return this.unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public byte getUpid() {
		return this.upid;
	}

	public void setUpid(byte upid) {
		this.upid = upid;
	}

}