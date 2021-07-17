package com.pg.tho.repositories;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pg.tho.entities.Section;


@Repository
public interface ISectionRepository extends JpaRepository<Section, Integer> {

	@Query("select s from Section s inner join Item i on s.sectionId = i.section.sectionId where i.itemName = ?1")
	public List<Section> viewSectionByItemName(String itemName);

	//@Query(value="select * from section s inner join address a on s.add_id = a.address_id where a.area =:area",nativeQuery = true)
	//public List<Section> viewNearBySection(@Param("area") String location);

	public Section findSectionByEmail(String username);

	
	public List<Section> findSectionBySectionName(String name);



}
