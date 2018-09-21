package com.gb.entity;

import java.math.BigInteger;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The persistent class for the sea_data database table.
 * 
 */
@Entity
@Table(name = "sea_data")
@NamedQuery(name = "SeaData.findAll", query = "SELECT s FROM SeaData s")
public class SeaData extends com.gb.entity.support.BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "v_id", unique = true, nullable = false)
	private int vId;

	@Column(name = "tid", nullable = false)
	private int tid;

	@Column(name = "v_actor", length = 200)
	private String vActor;

	@Column(name = "v_addtime", nullable = false)
	private int vAddtime;

	@Column(name = "v_color", nullable = false, length = 7)
	private String vColor;

	@Column(name = "v_commend", nullable = false)
	private short vCommend;

	@Column(name = "v_company", length = 60)
	private String vCompany;

	@Column(name = "v_dayhit")
	private int vDayhit;

	@Column(name = "v_daytime")
	private int vDaytime;

	@Column(name = "v_digg", nullable = false)
	private short vDigg;

	@Column(name = "v_director", length = 200)
	private String vDirector;

	@Column(name = "v_douban", length = 3)
	private String vDouban;

	@Column(name = "v_enname", length = 200)
	private String vEnname;

	@Lob
	@Column(name = "v_extratype")
	private String vExtratype;

	@Column(name = "v_gpic", nullable = false, length = 255)
	private String vGpic;

	@Column(name = "v_hit", nullable = false)
	private int vHit;

	@Column(name = "v_imdb", length = 3)
	private String vImdb;

	@Column(name = "v_ismake", nullable = false)
	private int vIsmake;

	@Column(name = "v_isunion", nullable = false)
	private short vIsunion;

	@Lob
	@Column(name = "v_jq")
	private String vJq;

	@Column(name = "v_lang", length = 200)
	private String vLang;

	@Column(name = "v_len", length = 6)
	private String vLen;

	@Column(name = "v_letter", nullable = false, length = 3)
	private String vLetter;

	@Lob
	@Column(name = "v_longtxt")
	private String vLongtxt;

	@Column(name = "v_money", nullable = false)
	private short vMoney;

	@Column(name = "v_monthhit")
	private int vMonthhit;

	@Column(name = "v_monthtime")
	private int vMonthtime;

	@Column(name = "v_mtime", length = 3)
	private String vMtime;

	@Column(name = "v_name", nullable = false, length = 60)
	private String vName;

	@Column(name = "v_nickname", length = 60)
	private String vNickname;

	@Column(name = "v_note", nullable = false, length = 30)
	private String vNote;

	@Column(name = "v_pic", nullable = false, length = 255)
	private String vPic;

	@Column(name = "v_psd", length = 30)
	private String vPsd;

	@Column(name = "v_publisharea", nullable = false, length = 20)
	private String vPublisharea;

	@Column(name = "v_publishyear", nullable = false, length = 20)
	private String vPublishyear;

	@Column(name = "v_rank", nullable = false)
	private short vRank;

	@Column(name = "v_recycled", nullable = false)
	private short vRecycled;

	@Column(name = "v_reweek", length = 60)
	private String vReweek;

	@Column(name = "v_score")
	private BigInteger vScore;

	@Column(name = "v_scorenum")
	private int vScorenum;

	@Column(name = "v_spic", nullable = false, length = 255)
	private String vSpic;

	@Column(name = "v_state", nullable = false)
	private int vState;

	@Column(name = "v_tags", nullable = false, length = 30)
	private String vTags;

	@Column(name = "v_topic", nullable = false)
	private int vTopic;

	@Column(name = "v_total", length = 6)
	private String vTotal;

	@Column(name = "v_tread", nullable = false)
	private short vTread;

	@Column(name = "v_tvs", length = 60)
	private String vTvs;

	@Column(name = "v_ver", length = 20)
	private String vVer;

	@Column(name = "v_weekhit")
	private int vWeekhit;

	@Column(name = "v_weektime")
	private int vWeektime;

	@Column(name = "v_wrong", nullable = false)
	private int vWrong;

	@OneToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "v_id", referencedColumnName = "v_id")
	@JsonProperty("a")
	private SeaContent seaContent1;

	@OneToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "v_id", referencedColumnName = "v_id")
	@JsonProperty("b")
	private SeaPlaydata seaPlaydata1;
	
	@OneToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name="tid", referencedColumnName = "tid", unique=true, nullable=false,insertable=false,updatable=false)
	@JsonProperty("c")
	private SeaType seaType1;
	
	public SeaData() {
	}

	public int getVId() {
		return this.vId;
	}

	public void setVId(int vId) {
		this.vId = vId;
	}

	public int getTid() {
		return this.tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getVActor() {
		return this.vActor;
	}

	public void setVActor(String vActor) {
		this.vActor = vActor;
	}

	public int getVAddtime() {
		return this.vAddtime;
	}

	public void setVAddtime(int vAddtime) {
		this.vAddtime = vAddtime;
	}

	public String getVColor() {
		return this.vColor;
	}

	public void setVColor(String vColor) {
		this.vColor = vColor;
	}

	public short getVCommend() {
		return this.vCommend;
	}

	public void setVCommend(short vCommend) {
		this.vCommend = vCommend;
	}

	public String getVCompany() {
		return this.vCompany;
	}

	public void setVCompany(String vCompany) {
		this.vCompany = vCompany;
	}

	public int getVDayhit() {
		return this.vDayhit;
	}

	public void setVDayhit(int vDayhit) {
		this.vDayhit = vDayhit;
	}

	public int getVDaytime() {
		return this.vDaytime;
	}

	public void setVDaytime(int vDaytime) {
		this.vDaytime = vDaytime;
	}

	public short getVDigg() {
		return this.vDigg;
	}

	public void setVDigg(short vDigg) {
		this.vDigg = vDigg;
	}

	public String getVDirector() {
		return this.vDirector;
	}

	public void setVDirector(String vDirector) {
		this.vDirector = vDirector;
	}

	public String getVDouban() {
		return this.vDouban;
	}

	public void setVDouban(String vDouban) {
		this.vDouban = vDouban;
	}

	public String getVEnname() {
		return this.vEnname;
	}

	public void setVEnname(String vEnname) {
		this.vEnname = vEnname;
	}

	public String getVExtratype() {
		return this.vExtratype;
	}

	public void setVExtratype(String vExtratype) {
		this.vExtratype = vExtratype;
	}

	public String getVGpic() {
		return this.vGpic;
	}

	public void setVGpic(String vGpic) {
		this.vGpic = vGpic;
	}

	public int getVHit() {
		return this.vHit;
	}

	public void setVHit(int vHit) {
		this.vHit = vHit;
	}

	public String getVImdb() {
		return this.vImdb;
	}

	public void setVImdb(String vImdb) {
		this.vImdb = vImdb;
	}

	public int getVIsmake() {
		return this.vIsmake;
	}

	public void setVIsmake(int vIsmake) {
		this.vIsmake = vIsmake;
	}

	public short getVIsunion() {
		return this.vIsunion;
	}

	public void setVIsunion(short vIsunion) {
		this.vIsunion = vIsunion;
	}

	public String getVJq() {
		return this.vJq;
	}

	public void setVJq(String vJq) {
		this.vJq = vJq;
	}

	public String getVLang() {
		return this.vLang;
	}

	public void setVLang(String vLang) {
		this.vLang = vLang;
	}

	public String getVLen() {
		return this.vLen;
	}

	public void setVLen(String vLen) {
		this.vLen = vLen;
	}

	public String getVLetter() {
		return this.vLetter;
	}

	public void setVLetter(String vLetter) {
		this.vLetter = vLetter;
	}

	public String getVLongtxt() {
		return this.vLongtxt;
	}

	public void setVLongtxt(String vLongtxt) {
		this.vLongtxt = vLongtxt;
	}

	public short getVMoney() {
		return this.vMoney;
	}

	public void setVMoney(short vMoney) {
		this.vMoney = vMoney;
	}

	public int getVMonthhit() {
		return this.vMonthhit;
	}

	public void setVMonthhit(int vMonthhit) {
		this.vMonthhit = vMonthhit;
	}

	public int getVMonthtime() {
		return this.vMonthtime;
	}

	public void setVMonthtime(int vMonthtime) {
		this.vMonthtime = vMonthtime;
	}

	public String getVMtime() {
		return this.vMtime;
	}

	public void setVMtime(String vMtime) {
		this.vMtime = vMtime;
	}

	public String getVName() {
		return this.vName;
	}

	public void setVName(String vName) {
		this.vName = vName;
	}

	public String getVNickname() {
		return this.vNickname;
	}

	public void setVNickname(String vNickname) {
		this.vNickname = vNickname;
	}

	public String getVNote() {
		return this.vNote;
	}

	public void setVNote(String vNote) {
		this.vNote = vNote;
	}

	public String getVPic() {
		return this.vPic;
	}

	public void setVPic(String vPic) {
		this.vPic = vPic;
	}

	public String getVPsd() {
		return this.vPsd;
	}

	public void setVPsd(String vPsd) {
		this.vPsd = vPsd;
	}

	public String getVPublisharea() {
		return this.vPublisharea;
	}

	public void setVPublisharea(String vPublisharea) {
		this.vPublisharea = vPublisharea;
	}

	public String getVPublishyear() {
		return this.vPublishyear;
	}

	public void setVPublishyear(String vPublishyear) {
		this.vPublishyear = vPublishyear;
	}

	public short getVRank() {
		return this.vRank;
	}

	public void setVRank(short vRank) {
		this.vRank = vRank;
	}

	public short getVRecycled() {
		return this.vRecycled;
	}

	public void setVRecycled(short vRecycled) {
		this.vRecycled = vRecycled;
	}

	public String getVReweek() {
		return this.vReweek;
	}

	public void setVReweek(String vReweek) {
		this.vReweek = vReweek;
	}

	public BigInteger getVScore() {
		return this.vScore;
	}

	public void setVScore(BigInteger vScore) {
		this.vScore = vScore;
	}

	public int getVScorenum() {
		return this.vScorenum;
	}

	public void setVScorenum(int vScorenum) {
		this.vScorenum = vScorenum;
	}

	public String getVSpic() {
		return this.vSpic;
	}

	public void setVSpic(String vSpic) {
		this.vSpic = vSpic;
	}

	public int getVState() {
		return this.vState;
	}

	public void setVState(int vState) {
		this.vState = vState;
	}

	public String getVTags() {
		return this.vTags;
	}

	public void setVTags(String vTags) {
		this.vTags = vTags;
	}

	public int getVTopic() {
		return this.vTopic;
	}

	public void setVTopic(int vTopic) {
		this.vTopic = vTopic;
	}

	public String getVTotal() {
		return this.vTotal;
	}

	public void setVTotal(String vTotal) {
		this.vTotal = vTotal;
	}

	public short getVTread() {
		return this.vTread;
	}

	public void setVTread(short vTread) {
		this.vTread = vTread;
	}

	public String getVTvs() {
		return this.vTvs;
	}

	public void setVTvs(String vTvs) {
		this.vTvs = vTvs;
	}

	public String getVVer() {
		return this.vVer;
	}

	public void setVVer(String vVer) {
		this.vVer = vVer;
	}

	public int getVWeekhit() {
		return this.vWeekhit;
	}

	public void setVWeekhit(int vWeekhit) {
		this.vWeekhit = vWeekhit;
	}

	public int getVWeektime() {
		return this.vWeektime;
	}

	public void setVWeektime(int vWeektime) {
		this.vWeektime = vWeektime;
	}

	public int getVWrong() {
		return this.vWrong;
	}

	public void setVWrong(int vWrong) {
		this.vWrong = vWrong;
	}

	public SeaContent getSeaContent1() {
		return seaContent1;
	}

	public void setSeaContent1(SeaContent seaContent1) {
		this.seaContent1 = seaContent1;
	}

	public SeaPlaydata getSeaPlaydata1() {
		return seaPlaydata1;
	}

	public void setSeaPlaydata1(SeaPlaydata seaPlaydata1) {
		this.seaPlaydata1 = seaPlaydata1;
	}

	public SeaType getSeaType1() {
		return seaType1;
	}

	public void setSeaType1(SeaType seaType1) {
		this.seaType1 = seaType1;
	}

}