package org.zerock.domain;

//import java.util.Arrays;

public class StoreVO {
	
	private Integer prodnum;
	private String name;
	private String thumnail;
	private String infoimage;
	private String introduce;
	private int price;
	private int dcprice;
	private String brand;
	private String keywd;
	private String category;
	private String freeyn;
	private String link;
	private Integer avggrade;
	
//	private String[] files;
	
		
//	public String[] getFiles() {
//		return files;
//	}
//	public void setFiles(String[] files) {
//		this.files = files;
//	}
	
	
	public Integer getProdnum() {
		return prodnum;
	}
	public void setProdnum(Integer prodnum) {
		this.prodnum = prodnum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getThumnail() {
		return thumnail;
	}
	public void setThumnail(String thumnail) {
		this.thumnail = thumnail;
	}
	public String getInfoimage() {
		return infoimage;
	}
	public void setInfoimage(String infoimage) {
		this.infoimage = infoimage;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getDcprice() {
		return dcprice;
	}
	public void setDcprice(int dcprice) {
		this.dcprice = dcprice;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getKeywd() {
		return keywd;
	}
	public void setKeywd(String keywd) {
		this.keywd = keywd;
	}
	public String getCategody() {
		return category;
	}
	public void setCategody(String category) {
		this.category = category;
	}
	public String getFreeyn() {
		return freeyn;
	}
	public void setFreeyn(String freeyn) {
		this.freeyn = freeyn;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Integer getAvggrade() {
		return avggrade;
	}
	public void setAvggrade(Integer avggrade) {
		this.avggrade = avggrade;
	}
	@Override
	public String toString() {
		return "BoardVO [prodnum=" + prodnum + ", name=" + name + ", thumnail="
				+ thumnail + ", infoimage=" + infoimage + ", introduce=" + introduce
				+ ", price=" + price + ", dcprice=" + dcprice+ ", brand=" + brand+ ", keywd=" + keywd
				+ ", category=" + category+ ", freeyn=" + freeyn+ ", link=" + link+ ", avggrade=" + avggrade
				 + "]";
	}

}
