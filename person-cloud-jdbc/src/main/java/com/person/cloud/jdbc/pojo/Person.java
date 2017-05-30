package com.person.cloud.jdbc.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Table(name = "person")
public class Person implements Serializable {

	/**
	 * @author gxq
	 * @date 20170516
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private String cardno;
	private String descriot;
	private String ctftp;
	private String ctfid;
	private String gender;
	private String birthday;
	private String address;
	private String zip;
	private String dirty;
	private String distrct1;
	private String distrct2;
	private String distrct3;
	private String distrct4;
	private String distrct5;
	private String distrct6;
	private String firstnm;
	private String lastnm;
	private String duty;
	private String mobile;
	private String tel;
	private String fax;
	private String email;
	private String nation;
	private String taste;
	private String education;
	private String company;
	private String ctel;
	private String caddress;
	private String czip;
	private String family;
	private String version;
	private String id;

	@Column(name = "id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "cardno")
	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardNo) {
		this.cardno = cardNo;
	}

	@Column(name = "descriot")
	public String getDescriot() {
		return descriot;
	}

	public void setDescriot(String descriot) {
		this.descriot = descriot;
	}

	@Column(name = "ctftp")
	public String getCtftp() {
		return ctftp;
	}

	public void setCtftp(String ctftp) {
		this.ctftp = ctftp;
	}

	@Column(name = "ctfid")
	public String getCtfid() {
		return ctfid;
	}

	public void setCtfId(String ctfid) {
		this.ctfid = ctfid;
	}

	@Column(name = "gender")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "birthday")
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "zip")
	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Column(name = "dirty")
	public String getDirty() {
		return dirty;
	}

	public void setDirty(String dirty) {
		this.dirty = dirty;
	}

	@Column(name = "distrct1")
	public String getDistrct1() {
		return distrct1;
	}

	public void setDistrct1(String distrct1) {
		this.distrct1 = distrct1;
	}

	@Column(name = "distrct2")
	public String getDistrct2() {
		return distrct2;
	}

	public void setDistrct2(String distrct2) {
		this.distrct2 = distrct2;
	}

	@Column(name = "distrct3")
	public String getDistrct3() {
		return distrct3;
	}

	public void setDistrct3(String distrct3) {
		this.distrct3 = distrct3;
	}

	@Column(name = "distrct4")
	public String getDistrct4() {
		return distrct4;
	}

	public void setDistrct4(String distrct4) {
		this.distrct4 = distrct4;
	}

	@Column(name = "distrct5")
	public String getDistrct5() {
		return distrct5;
	}

	public void setDistrct5(String distrct5) {
		this.distrct5 = distrct5;
	}

	@Column(name = "distrct6")
	public String getDistrct6() {
		return distrct6;
	}

	public void setDistrct6(String distrct6) {
		this.distrct6 = distrct6;
	}

	@Column(name = "duty")
	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	@Column(name = "mobile")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "tel")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "fax")
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "nation")
	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	@Column(name = "taste")
	public String getTaste() {
		return taste;
	}

	public void setTaste(String taste) {
		this.taste = taste;
	}

	@Column(name = "education")
	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	@Column(name = "company")
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name = "ctel")
	public String getCtel() {
		return ctel;
	}

	public void setCtel(String ctel) {
		this.ctel = ctel;
	}

	@Column(name = "caddress")
	public String getCaddress() {
		return caddress;
	}

	public void setCaddress(String caddress) {
		this.caddress = caddress;
	}

	@Column(name = "czip")
	public String getCzip() {
		return czip;
	}

	public void setCzip(String czip) {
		this.czip = czip;
	}

	@Column(name = "family")
	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	@Column(name = "version")
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Column(name = "firstnm")
	public String getFirstnm() {
		return firstnm;
	}

	public void setFirstnm(String firstnm) {
		this.firstnm = firstnm;
	}

	@Column(name = "lastnm")
	public String getLastnm() {
		return lastnm;
	}

	public void setLastnm(String lastnm) {
		this.lastnm = lastnm;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
