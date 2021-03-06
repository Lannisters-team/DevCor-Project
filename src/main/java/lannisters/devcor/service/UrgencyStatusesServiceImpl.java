package lannisters.devcor.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import lannisters.devcor.dao.UrgencyStatusesDAO;
import lannisters.devcor.entity.UrgencyStatus;

@Service
public class UrgencyStatusesServiceImpl implements UrgencyStatusesService {

	@Autowired
	private UrgencyStatusesDAO urgencyStatusesDao;

	@Override
	public List<UrgencyStatus> getAllUrgencyStatuses() {
		return urgencyStatusesDao.getAllUrgencyStatuses();
	}

	@Override
	public UrgencyStatus getUrgencyStatusById(int urgencyStatus) {
		return urgencyStatusesDao.getUrgencyStatusById(urgencyStatus);
	}

	@Override
	public void updateUrgencyStatus(UrgencyStatus urgencyStatus) throws SQLException {
		urgencyStatusesDao.updateUrgencyStatus(urgencyStatus);
	}

	@Override
	public void addUrgencyStatus(UrgencyStatus urgencyStatus) throws SQLException {
		urgencyStatusesDao.addUrgencyStatus(urgencyStatus);
	}

	@Override
	public void deleteUrgencyStatus(int urgencyStatus) throws SQLException {
		urgencyStatusesDao.deleteUrgencyStatus(urgencyStatus);
	}

	@Override
	public int getUrgencyStatusMinutes(int urgencyStatusId) {
		return urgencyStatusesDao.getUrgencyStatusMinutes(urgencyStatusId);
	}

	@Override
	public UrgencyStatus getUrgencyStatusByTitle(String title) {
		return urgencyStatusesDao.getUrgencyStatusByTitle(title);
	}

	@Override
	public boolean checkUrgencyStatusExistence(UrgencyStatus status) {
		boolean existence;
		try {
			urgencyStatusesDao.getUrgencyStatusByTitle(status.getUrgencyStatus());
			existence = true;
		} catch (EmptyResultDataAccessException e) {
			existence = false;
		}
		return existence;
	}
}