package com.fis.app.dao;

import java.sql.SQLException;
import java.util.List;

import com.fis.app.exce.NoDeviceFoundException;
import com.fis.app.model.ElectronicDevice;

public interface IElectronicDeviceDAO {

	

	public List<ElectronicDevice> getAllDevices() throws SQLException;
	
	public int changeDevicePrice(int deviceId,int newPrice)throws NoDeviceFoundException, SQLException;
	public int changeDeviceRating(int deviceId,int newRating)throws NoDeviceFoundException, SQLException;
	
	public boolean deleteDevice(int deviceId)throws NoDeviceFoundException, SQLException;
	public List<ElectronicDevice> geDevicesBasedOnBrandNameAndType(String brandName,String type) throws SQLException;
	public int countDeviceType(String type) throws SQLException;
	public int getSumofPriceBasedOnType(String type) throws NoDeviceFoundException, SQLException;
	boolean addElec(ElectronicDevice e) throws Exception;
	public List<ElectronicDevice> getDevicesBasedOnPriceRangeandType( int r1, int r2,String type, List<ElectronicDevice>eList)throws SQLException;
	
	
}
