package de.homedev.springboot.jpa.service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.homedev.springboot.jpa.dao.BlzDao;
import de.homedev.springboot.jpa.entity.BlzEntity;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
@Service(IBlzService.SERVICE_NAME)
public class BlzServiceImpl implements IBlzService {
	private static Logger log = Logger.getLogger(BlzServiceImpl.class.getName());

	@Autowired
	private BlzDao userDao;

	@Autowired
	private EntityManager entityManager;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@Override
	public int saveNative(List<String> list) throws SQLException {
		int result = 0;
		for (String str : list) {
			result = result + entityManager.createNativeQuery(str).executeUpdate();
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@Override
	public Iterable<BlzEntity> save(List<BlzEntity> list) {
		Iterable<BlzEntity> result = userDao.saveAll(list);
		return result;

	}

	@Transactional(readOnly = true)
	@Override
	public Iterable<BlzEntity> findAll() {
		return userDao.findAllOrderByBlz();

	}

	@Transactional(readOnly = true)
	@Override
	public Iterable<BlzEntity> findAll(Pageable p) {
		return userDao.findAll(p);

	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@Override
	public void deleteAll() {
		userDao.deleteAll();
	}

	@Override
	public String getDbInfo() {
		StringBuilder sb = new StringBuilder(1000);
		sb.append("database size:").append(userDao.count());
		// sb.append("Max Length Bank
		// Name:").append(userDao.getMaxLengthBankName());
		return sb.toString();
	}

}
