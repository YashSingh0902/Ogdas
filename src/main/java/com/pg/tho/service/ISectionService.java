package com.pg.tho.service;

import java.util.List;

import com.pg.tho.entities.Section;

public interface ISectionService {

	public Section addSection(Section sec);
	public Section updateSection(Section sec);
	public String removeSectionById(int id);
	public Section viewSectionById(int id);
	public List<Section> viewAllSections();
	//public List<Section> viewNearBySection(String location);
	public List<Section> viewSectionByItemName(String name);
	public List<Section> viewSectionByName(String name);
	Section InChargeLogin(String username, String password);
	//public void updateSection(Integer sectionId, String sectionName) throws Exception;
	//public void updateInCharger(Integer sectionId, String inChargerName) throws Exception;
	String InChargeLogout();

}
