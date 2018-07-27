package de.homedev.springboot.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.homedev.springboot.jpa.entity.BlzEntity;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface BlzDao extends JpaRepository<BlzEntity, Long> {
	// @Query("SELECT MAX(LENGTH(p.bankname)) FROM BlzEntity p")
	// public int getMaxLengthBankName();

	@Query("SELECT p FROM BlzEntity p order by p.blz")
	public Iterable<BlzEntity> findAllOrderByBlz();
}
