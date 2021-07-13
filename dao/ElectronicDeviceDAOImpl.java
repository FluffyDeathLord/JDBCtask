package com.fis.app.dao;

import java.sql.SQLException;

import java.util.List;

import com.fis.app.exce.NoDeviceFoundException;
import com.fis.app.model.ElectronicDevice;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ElectronicDeviceDAOImpl implements  IElectronicDeviceDAO {
	Connection con=null;
	String addElecQuery="insert into elec.electronic values(?,?,?,?,?,?,?)";
	String selectAllQuery= "select * from electronic";
	public boolean addElec(ElectronicDevice e) throws Exception{

		con = DatabaseUtil.getConnection();
		boolean isInserted = false;
		if (con != null) {

		
			int id = e.getDeviceId();
			String name = e.getBrandName();
			int cost= e.getCost();
			String deviceType= e.getDeviceType();
			int power= e.getPower();
			int starRatings=e.getStarRatings();
			String color= e.getColor();

			// Step 2 :- write the code to create complete insert Query with data
			PreparedStatement ps = con.prepareStatement(addElecQuery);
			ps.setInt(1, id);
			ps.setString(2, deviceType);
			ps.setString(3, name);
			ps.setInt(4, cost);
			ps.setInt(5, power);
			ps.setInt(6, starRatings);
			ps.setString(7, color);
			

			// Step 3 : execute Query
			int i = ps.executeUpdate(); // only works with insert , update & delete

			if (i > 0)
				isInserted = true;

		}

		return isInserted;
	}
	
	public List<ElectronicDevice> getAllDevices() throws SQLException {
		Connection con = DatabaseUtil.getConnection();
		List<ElectronicDevice> eList = new ArrayList<>();
		if (con != null) {

		    PreparedStatement ps = con.prepareStatement(selectAllQuery);
	
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ElectronicDevice temp = new ElectronicDevice(); 

			
			
				temp.setBrandName(rs.getString(3));
				temp.setColor(rs.getString(7));
				temp.setCost(rs.getInt(4));
				temp.setDeviceId(rs.getInt(1));
				temp.setPower(rs.getInt(5));
				temp.setDeviceType(rs.getString(2));
				temp.setStarRatings(rs.getInt(6));
				

				eList.add(temp);
			}

		}
		return eList;
		
		
	}
	
	@Override
	public int changeDeviceRating(int deviceId, int newRating) throws NoDeviceFoundException, SQLException {
		Connection con = DatabaseUtil.getConnection();
		String newRatingsQuery="update elec.electronic set starRatings=? where deviceId?";
		int rs=0;
		if (con != null) {

			PreparedStatement ps = con.prepareStatement(newRatingsQuery);
			ps.setInt(1,newRating);
			ps.setInt(2, deviceId);
            rs = ps.executeUpdate();


			if (rs<=0)
				throw new NoDeviceFoundException(deviceId);

		}
		return rs;
		
	}
	

	@Override
	public boolean deleteDevice(int deviceId) throws NoDeviceFoundException,SQLException {
		Connection con = DatabaseUtil.getConnection();
		String deleteQuery="update elec.electronic set where deviceId?";
		boolean del=false;
		if (con != null) {

			PreparedStatement ps = con.prepareStatement(deleteQuery);
			ps.setInt(1, deviceId);
            int rs = ps.executeUpdate();


			if (rs>0)
			{
				del=true;
			}
				throw new NoDeviceFoundException(deviceId);

		}
		return del;
	
	}

	@Override
	public List<ElectronicDevice> geDevicesBasedOnBrandNameAndType(String brandName, String deviceType)throws SQLException {
		Connection con = DatabaseUtil.getConnection();
		String BrandnameTypeQuery="select * from elect.electronic where brandName=? and deviceType=?";
		List<ElectronicDevice> eList = new ArrayList<>();
		if (con != null) {

		    PreparedStatement ps = con.prepareStatement(BrandnameTypeQuery);
		    ps.setString(1, brandName);
		    ps.setString(2, deviceType);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ElectronicDevice temp = new ElectronicDevice(); 

			
			
				temp.setBrandName(rs.getString("BrandName"));
				temp.setColor(rs.getString("color"));
				temp.setCost(rs.getInt(4));
				temp.setDeviceId(rs.getInt(1));
				temp.setPower(rs.getInt(5));
				temp.setDeviceType(rs.getString("Type"));
				temp.setStarRatings(rs.getInt(6));
				

				eList.add(temp);
			}

		}
		return eList;
		
	}

	@Override
	public int countDeviceType(String type)throws SQLException {
		Connection con = DatabaseUtil.getConnection();
		String countQuery="select count(*) from elec.electronic where deviceType=?";
		
		int count=0;
		if (con != null) {

		    PreparedStatement ps = con.prepareStatement(countQuery);
		    ps.setString(1, type);
			ResultSet rs = ps.executeQuery();
            
			while (rs.next()) {
			     count= rs.getInt(1);
			}

		}
		
		return count;
	}

	@Override
	public int getSumofPriceBasedOnType(String type) throws NoDeviceFoundException, SQLException{
		Connection con = DatabaseUtil.getConnection();
		String sumQuery="select sum(cost) from elec.electronic where deviceType=?";
	
		int sum=0;
		if (con != null) {

		    PreparedStatement ps = con.prepareStatement(sumQuery);
		    ps.setString(1, type);
			ResultSet rs = ps.executeQuery();
            
			while (rs.next()) {
			     sum= rs.getInt(1);
			}

		}
		
		return sum;
	
	}
	
	
	@Override
	
	public int changeDevicePrice(int deviceId,int cost)throws NoDeviceFoundException, SQLException{
		Connection con =DatabaseUtil.getConnection();
		int rs=0;
		String priceUpdateQuery="update elec.electronic set cost=? where deviceId=?";
		if (con != null) {

			PreparedStatement ps = con.prepareStatement(priceUpdateQuery);
			ps.setInt(1, cost);
			ps.setInt(2, deviceId);

			rs = ps.executeUpdate();
		
		
			if (rs==0)
				throw new NoDeviceFoundException(deviceId);

		}
		return rs;
		
	}
	 

    @Override
	public List<ElectronicDevice> getDevicesBasedOnPriceRangeandType( int r1, int r2,
			String type, List<ElectronicDevice>eList)throws SQLException{
		eList= eList.stream().filter(e1->{return e1.getDeviceType().equals(type)&& e1.getCost()>=r1&&((ElectronicDevice)e1).getCost()<=r2;}).collect((Collectors.toList()));
		return eList;
		}
	}
	








