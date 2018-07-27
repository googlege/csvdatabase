package de.homedev.springboot.jpa.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.domain.Pageable;

import de.homedev.springboot.jpa.entity.BlzEntity;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
public interface IBlzService {
	public static final String SERVICE_NAME = "blzServiceImpl";

	public Iterable<BlzEntity> save(List<BlzEntity> list);

	public int saveNative(List<String> list) throws SQLException;

	public Iterable<BlzEntity> findAll();

	public Iterable<BlzEntity> findAll(Pageable p);

	public void deleteAll();

	public String getDbInfo();
}
