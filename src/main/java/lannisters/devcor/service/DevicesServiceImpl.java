package lannisters.devcor.service;

import java.sql.SQLException;
import java.util.List;

import lannisters.devcor.dao.DevicesDAO;
import lannisters.devcor.entity.Device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class DevicesServiceImpl implements DevicesService {

	@Autowired
	private DevicesDAO devicesDao;

	@Override
	public List<Device> getAllDevices() {
		return devicesDao.getAllDevices();
	}

	@Override
	public Device getDeviceById(int deviceId) {
		return devicesDao.getDeviceById(deviceId);
	}

	@Override
	public void addDevice(Device device) throws SQLException {
		devicesDao.addDevice(device);
	}

	@Override
	public void updateDevice(Device device) throws SQLException {
		devicesDao.updateDevice(device);

	}

	@Override
	public void deleteDevice(int deviceId) throws SQLException {
		devicesDao.deleteDevice(deviceId);
	}

	@Override
	public List<Device> getAllDevicesOfRoom(int roomId) {
		return devicesDao.getAllDevicesOfRoom(roomId);
	}

	@Override
	public Device getDeviceBySerial(String deviceSerialId) {
		return devicesDao.getDeviceBySerial(deviceSerialId);
	}

	@Override
	public boolean checkSerialExistence(Device device) {
		boolean existence;
		try {
			devicesDao.getDeviceBySerial(device.getDeviceSerialId());
			existence = true;
		} catch (EmptyResultDataAccessException e) {
			existence = false;
		}
		return existence;
	}

	@Override
	public List<Device> getDevicesByType(int deviceTypeId) {
		return devicesDao.getDevicesByType(deviceTypeId);
	}

	@Override
	public boolean checkDeviceTypeDevices(int deviceTypeId) {
		boolean existence;
		List<Device> devices = devicesDao.getDevicesByType(deviceTypeId);
		if (devices.isEmpty())
			existence = false;
		else
			existence = true;
		return existence;
	}
}