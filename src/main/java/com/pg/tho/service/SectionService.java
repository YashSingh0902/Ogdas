package com.pg.tho.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pg.tho.entities.Item;
import com.pg.tho.entities.Section;
import com.pg.tho.repositories.IItemRepository;
import com.pg.tho.repositories.ISectionRepository;


@Service
@Transactional
public class SectionService implements ISectionService {

	@Autowired
	ISectionRepository repository;
	
	@Autowired
	IItemRepository repo2;
	
	Logger logger=LoggerFactory.getLogger(SectionService.class);
	
	@Override
	public Section addSection(Section sec) {
		
		logger.info("ADD Section -> /./././");
		repository.save(sec);
		return sec;
	}

	@Override
	public Section updateSection(Section section) {
		
		logger.info("UPDATE Section -> /./././");
		repository.save(section);
		return section;
	}
	
	/*@Override
	public void updateSection(Integer sectionId, String sectionName) throws Exception {
		// TODO Auto-generated method stub
		Optional<Section> updateName = repository.findById(sectionId);
		Section name = updateName.orElseThrow(() -> new Exception("Service.SECTION_NOT_FOUND"));
		name.setSectionName(sectionName);
		
	}
	
	@Override
	public void updateInCharger(Integer sectionId, String inChargerName) throws Exception {
		// TODO Auto-generated method stub
		Optional<Section> inchargerName = repository.findById(sectionId);
		Section name = inchargerName.orElseThrow(() -> new Exception("Service.SECTION_NOT_FOUND"));
		name.setInChargerName(inChargerName);
		
	}*/
	
	@Override
	public String removeSectionById(int Sid) {
		
		logger.info("REMOVE Section -> /./././");
		Section sect=repository.findById(Sid).orElse(null);
		List<Item> list=repo2.findItemsBySection(sect.getSectionName());
		for(int i=0;i<list.size();i++)
		{
			Item item=list.get(i);
			int id=item.getItemId();
			repo2.deleteById(id);
		}
		repository.deleteById(Sid);
		
		String msg="Section is removed => DONE";
		return msg;
	}

	@Override
	public List<Section> viewAllSections() {
		
		logger.info("VIEW Section -> /./././");
		List<Section> list=repository.findAll();
		return list;
	}
	
	@Override
	public Section viewSectionById(int id) {
		
		logger.info("VIEW Section (ID) -> /./././");
		Section section = repository.findById(id).orElse(null);
		return section;	
	}

	@Override
	public List<Section> viewSectionByName(String name) {
		
		logger.info("VIEW Section (NAME) -> /./././");
		List<Section> list = repository.findSectionBySectionName(name);
		return list;
	}
	/*@Override
	public List<Section> viewNearBySection(String location) {
		
		logger.info("VIEW Section -> /./././");
		List<Section> list = repository.viewNearBySection(location);
		return list;
	}*/


	@Override
	public List<Section> viewSectionByItemName(String sectionName) {
		
		logger.info("VIEW Section (ITEMNAME) -> /./././");
		List<Section> list=repository.viewSectionByItemName(sectionName);
		return list;
	}

	@Override
	public Section InChargeLogin(String username, String password) {

		logger.info("LOGIN INCHARGER -> /./././");
		Section section=repository.findSectionByEmail(username);
		String usr=section.getEmail();
		String pwd=section.getPassword();
		if(section!=null)
		{
			if(usr.equals(username) && pwd.equals(password))
			{
				return section;
			}
		}
		return null;
	}


	@Override
	public String InChargeLogout() {

		return "You are Logged out successfully";
	}

	

	
}
