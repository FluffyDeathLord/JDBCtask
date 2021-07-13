package com.fis.app.model;

import java.util.Objects;

public class ElectronicDevice {
	
	private String deviceType;
	private int deviceId;
	private String brandName;
	private int cost;
	private int power;
	private int starRatings;
	private String color;


	

	public ElectronicDevice() {
	super();
	}

	
	
	public String getDeviceType() {
		return deviceType;
	}
	
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public int getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public int getStarRatings() {
		return starRatings;
	}
	public void setStarRatings(int starRatings) {
		this.starRatings = starRatings;
	}
	

	public String getColor() {
		return color;
	}



	public void setColor(String color) {
		this.color = color;
	}



	@Override
	public int hashCode() {
		return Objects.hash(brandName, cost, deviceId, deviceType, power, starRatings, color);
	}



	



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ElectronicDevice other = (ElectronicDevice) obj;
		return Objects.equals(brandName, other.brandName) && Objects.equals(color, other.color) && cost == other.cost
				&& deviceId == other.deviceId && Objects.equals(deviceType, other.deviceType) && power == other.power
				&& starRatings == other.starRatings;
	}



	@Override
	public String toString() {
		return "ElectronicDevice [deviceType=" + deviceType + ", deviceId=" + deviceId + ", brandName=" + brandName
				+ ", cost=" + cost + ", power=" + power + ", starRatings=" + starRatings + ", color=" + color + "]";
	}
	
	
	

}
