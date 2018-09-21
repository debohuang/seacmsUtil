package com.gb.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the sea_news database table.
 * 
 */
@Entity
@Table(name="sea_news")
@NamedQuery(name="SeaNew.findAll", query="SELECT s FROM SeaNew s")
public class SeaNew extends com.gb.entity.support.BaseEntity  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="n_id", unique=true, nullable=false)
	private int nId;

	@Column(name="n_addtime", nullable=false)
	private int nAddtime;

	@Column(name="n_author", length=80)
	private String nAuthor;

	@Column(name="n_color", nullable=false, length=7)
	private String nColor;

	@Column(name="n_commend", nullable=false)
	private short nCommend;

	@Lob
	@Column(name="n_content")
	private String nContent;

	@Column(name="n_digg", nullable=false)
	private short nDigg;

	@Column(name="n_entitle", length=200)
	private String nEntitle;

	@Column(name="n_from", length=50)
	private String nFrom;

	@Column(name="n_hit", nullable=false)
	private int nHit;

	@Column(name="n_isunion", nullable=false)
	private short nIsunion;

	@Column(name="n_keyword", length=80)
	private String nKeyword;

	@Column(name="n_letter", nullable=false, length=3)
	private String nLetter;

	@Column(name="n_money", nullable=false)
	private short nMoney;

	@Column(name="n_note", nullable=false)
	private short nNote;

	@Column(name="n_outline", length=255)
	private String nOutline;

	@Column(name="n_pic", nullable=false, length=255)
	private String nPic;

	@Column(name="n_rank", nullable=false)
	private short nRank;

	@Column(name="n_recycled", nullable=false)
	private short nRecycled;

	@Column(name="n_score")
	private BigInteger nScore;

	@Column(name="n_scorenum")
	private int nScorenum;

	@Column(name="n_title", nullable=false, length=60)
	private String nTitle;

	@Column(name="n_tread", nullable=false)
	private short nTread;

	@Column(nullable=false)
	private int tid;

	public SeaNew() {
	}

	public int getNId() {
		return this.nId;
	}

	public void setNId(int nId) {
		this.nId = nId;
	}

	public int getNAddtime() {
		return this.nAddtime;
	}

	public void setNAddtime(int nAddtime) {
		this.nAddtime = nAddtime;
	}

	public String getNAuthor() {
		return this.nAuthor;
	}

	public void setNAuthor(String nAuthor) {
		this.nAuthor = nAuthor;
	}

	public String getNColor() {
		return this.nColor;
	}

	public void setNColor(String nColor) {
		this.nColor = nColor;
	}

	public short getNCommend() {
		return this.nCommend;
	}

	public void setNCommend(short nCommend) {
		this.nCommend = nCommend;
	}

	public String getNContent() {
		return this.nContent;
	}

	public void setNContent(String nContent) {
		this.nContent = nContent;
	}

	public short getNDigg() {
		return this.nDigg;
	}

	public void setNDigg(short nDigg) {
		this.nDigg = nDigg;
	}

	public String getNEntitle() {
		return this.nEntitle;
	}

	public void setNEntitle(String nEntitle) {
		this.nEntitle = nEntitle;
	}

	public String getNFrom() {
		return this.nFrom;
	}

	public void setNFrom(String nFrom) {
		this.nFrom = nFrom;
	}

	public int getNHit() {
		return this.nHit;
	}

	public void setNHit(int nHit) {
		this.nHit = nHit;
	}

	public short getNIsunion() {
		return this.nIsunion;
	}

	public void setNIsunion(short nIsunion) {
		this.nIsunion = nIsunion;
	}

	public String getNKeyword() {
		return this.nKeyword;
	}

	public void setNKeyword(String nKeyword) {
		this.nKeyword = nKeyword;
	}

	public String getNLetter() {
		return this.nLetter;
	}

	public void setNLetter(String nLetter) {
		this.nLetter = nLetter;
	}

	public short getNMoney() {
		return this.nMoney;
	}

	public void setNMoney(short nMoney) {
		this.nMoney = nMoney;
	}

	public short getNNote() {
		return this.nNote;
	}

	public void setNNote(short nNote) {
		this.nNote = nNote;
	}

	public String getNOutline() {
		return this.nOutline;
	}

	public void setNOutline(String nOutline) {
		this.nOutline = nOutline;
	}

	public String getNPic() {
		return this.nPic;
	}

	public void setNPic(String nPic) {
		this.nPic = nPic;
	}

	public short getNRank() {
		return this.nRank;
	}

	public void setNRank(short nRank) {
		this.nRank = nRank;
	}

	public short getNRecycled() {
		return this.nRecycled;
	}

	public void setNRecycled(short nRecycled) {
		this.nRecycled = nRecycled;
	}

	public BigInteger getNScore() {
		return this.nScore;
	}

	public void setNScore(BigInteger nScore) {
		this.nScore = nScore;
	}

	public int getNScorenum() {
		return this.nScorenum;
	}

	public void setNScorenum(int nScorenum) {
		this.nScorenum = nScorenum;
	}

	public String getNTitle() {
		return this.nTitle;
	}

	public void setNTitle(String nTitle) {
		this.nTitle = nTitle;
	}

	public short getNTread() {
		return this.nTread;
	}

	public void setNTread(short nTread) {
		this.nTread = nTread;
	}

	public int getTid() {
		return this.tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

}