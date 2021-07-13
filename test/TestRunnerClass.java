package com.fis.app.test;

import java.util.List;

import com.fis.app.dao.ElectronicDeviceDAOImpl;
import com.fis.app.dao.IElectronicDeviceDAO;
import com.fis.app.model.ElectronicDevice;

public class TestRunnerClass {

	public static void main(String[] args) {
		
		try {
			
			
			IElectronicDeviceDAO dao = new ElectronicDeviceDAOImpl();
			
			
			
			List<ElectronicDevice> list = dao.getAllDevices();
			List<ElectronicDevice>filterlist=dao.getDevicesBasedOnPriceRangeandType(10000, 30000, "Laptop", list);
			for(ElectronicDevice filtered : filterlist)
			{
				System.out.println(filtered);
			}
			
			
			
		} catch (Exception e) {
			System.out.println(" Problem "+e);
		}
	}
}
