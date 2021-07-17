package com.pg.tho.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pg.tho.entities.InCharge;

public interface IInChargerRepository extends JpaRepository<InCharge, Integer>{

	@Query(value="SELECT i FROM InCharge i where i.adminId=?1")
	 Optional<InCharge> findById(Integer adminId);

}
