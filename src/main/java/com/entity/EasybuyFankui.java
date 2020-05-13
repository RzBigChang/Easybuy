package com.entity;

public class EasybuyFankui {
	//用户评论
	private String 	faxie;
	public String getFaxie() {
		return faxie;
	}
	public void setFaxie(String faxie) {
		this.faxie = faxie;
	}
	public int getManyidu() {
		return manyidu;
	}
	public void setManyidu(int manyidu) {
		this.manyidu = manyidu;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIpone() {
		return ipone;
	}
	public void setIpone(String ipone) {
		this.ipone = ipone;
	}
	//用户满意度
	private int manyidu;
	//用户名称
	private String name;
	//用户电话
	private String ipone;
	//无参构造
	public EasybuyFankui() {}
	public EasybuyFankui(String faxie,int manyidu,String name,String ipone) {
		this.faxie=faxie;
		this.manyidu=manyidu;
		this.name=name;
		this.ipone=ipone;
	}
}


