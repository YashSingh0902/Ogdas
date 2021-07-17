package com.pg.tho.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.pg.tho.entities.Address;
import com.pg.tho.entities.Section;
import com.pg.tho.repositories.ISectionRepository;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class SectionServiceTest {

	@Autowired
ISectionRepository sectionRepo;
	
	@Test
	void testAddSection() {
		
		Section section=sectionRepo.save(getSection());
		assertNotNull(section);
	}

	@Test
	void testUpdateSection() {

		Section section=sectionRepo.save(getSection());
		assertNotNull(section);
	}

	@Test
	void testViewAllSections() {
		
		List<Section> list=sectionRepo.findAll();
		assertNotNull(list);
	}
//
//	@Test
//	void testViewSectionById() {
//
//		Section section=sectionRepo.findById(5).orElse(null);
//		assertNotNull(section);
//	}

	@Test
	void testViewSectionByItemName() {

		List<Section> section=sectionRepo.viewSectionByItemName("Tomato");
		assertNotNull(section);
	}
	
	public Section getSection()
	{
		Section section = new Section();
	
		section.setSectionId(13);
		section.setSectionName("Dairy Product");
		section.setInChargeName("Dhanashree");
		return section;
	}
	
	
	

}
