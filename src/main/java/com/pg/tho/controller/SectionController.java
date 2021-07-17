package com.pg.tho.controller;

import java.util.ArrayList;


import java.util.List;

import javax.naming.NameNotFoundException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pg.tho.entities.Category;
import com.pg.tho.entities.Customer;
import com.pg.tho.entities.Section;
import com.pg.tho.exceptions.IdNotFoundException;
import com.pg.tho.exceptions.invalidItemNameException;
import com.pg.tho.exceptions.invalidLocationException;
import com.pg.tho.exceptions.removeFailedException;
import com.pg.tho.service.ISectionService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping(value = "/section")
public class SectionController {
	
	@Autowired
	ISectionService service;
	
	@Autowired
	private Environment environment;
	
	Logger logger=LoggerFactory.getLogger(SectionController.class);
	
	@PostMapping("/addSection")
	public ResponseEntity<Section> addSection(@Valid @RequestBody Section section){
		
		logger.info("ADD Section  ->  /./././.");
	    Section section2 = service.addSection(section);
		return new ResponseEntity<Section>(section2, HttpStatus.OK);
	}
	
	@PutMapping("/updateSection")
	public ResponseEntity<Section> updateSection(@Valid @RequestBody Section section) throws IdNotFoundException{
		
		logger.info("UPDATE Section  ->  /./././.");
		Section section1=service.viewSectionById(section.getSectionId());
		if(section1==null)
		{
			throw new IdNotFoundException("Section is not available - Try Again ");
		}
		else
		{
			Section updateSection = service.updateSection(section);
			return new ResponseEntity<Section>(updateSection, HttpStatus.OK);
		}
	}
	
	
	/*@PutMapping(value = "updateSection/{sectionId}")
	// @ApiOperation("Updates the section by their identifier else throws 404 if does not exist")
	public ResponseEntity<String> updateSection(@PathVariable Integer sectionId, @RequestBody Section name) throws Exception {
		service.updateSection(sectionId, name.getSectionName());
		String successMessage = environment.getProperty("API.UPDATE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
	
	@PutMapping(value = "updateInCharger/{sectionId}")
	// @ApiOperation("Updates the section by their identifier else throws 404 if does not exist")
	public ResponseEntity<String> updateInCharger(@PathVariable Integer sectionId, @RequestBody Section inCharger) throws Exception {
		service.updateInCharger(sectionId, inCharger.getInChargerName());
		String successMessage = environment.getProperty("API.UPDATE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}*/
	
	
	
	@DeleteMapping("/removeSectionById/{sectionId}")
	public ResponseEntity<String> removeSectionById(@PathVariable("sectionId") int Sid) throws removeFailedException{
		
		logger.info("REMOVE Section  ->  /./././.");
		Section section=service.viewSectionById(Sid);
		if(section==null)
		{
			throw new removeFailedException("Entered section unavailable - Retry ");
		}
		else
		{
			String msg = service.removeSectionById(Sid); 
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		}
		
	}
	
	@GetMapping("/viewAllSections")
	public ResponseEntity<List<Section>> viewAllSections(){
		
		logger.info("VIEW ALL Section  ->  /./././.");
		List <Section> list = new ArrayList<Section>();
		for(Section s : service.viewAllSections())
		{
			Section s1 = new Section();
			s1.setSectionId(s.getSectionId());
			s1.setSectionName(s.getSectionName());
			list.add(s1);
			
		}
		return new ResponseEntity<List<Section>>(list, HttpStatus.OK);
		
	}
	
	@GetMapping("/viewSectionByItemName/{itemName}")
	public ResponseEntity<List<Section>> viewSectionByItemName(@PathVariable String itemName) throws invalidItemNameException{
		
		logger.info("VIEW Section (NAME)  ->  /./././.");
		List <Section> list =  service.viewSectionByItemName(itemName);
		System.err.println(list);
		if(list==null)
		{
			throw new invalidItemNameException("Item name is invalid - Try with another ");
		}
		else
		{
			return new ResponseEntity<List<Section>>(list, HttpStatus.OK);
		}		
		
	}
	
	/*@GetMapping("/viewNearBySection/{location}")
	public ResponseEntity<List<Section>> viewNearBySection(@PathVariable String location) throws invalidLocationException{
		
		logger.info("VIEW Section  ->  /./././.");
		List <Section> list = service.viewNearBySection(location);
		if(list==null)
		{
			throw new invalidLocationException("Not available !!!");
		}
		else
		{
			return new ResponseEntity<List<Section>>(list, HttpStatus.OK);
		}		
	}*/
	
	@GetMapping("/viewSectionByName/{name}")
	public ResponseEntity<List<Section>> viewSectionByName(@PathVariable String name) throws NameNotFoundException{
		
		logger.info("VIEW Section (NAME)  ->  /./././.");
		List <Section> list = service.viewSectionByName(name);
		if(list==null)
		{
			throw new NameNotFoundException("Invalid name - Change");
		}
		else
		{
			return new ResponseEntity<List<Section>>(list, HttpStatus.OK);
		}		
	}

	@GetMapping("/viewSectionById/{sectionId}")
	public ResponseEntity<Section> viewSectionById(@PathVariable("sectionId") int id) throws IdNotFoundException{
		
		logger.info("VIEW Section (ID)  ->  /./././.");
		Section sect2 = new Section();
		Section section = service.viewSectionById(id);
		if(section==null)
		{
			throw new IdNotFoundException("Id is invalid - Retry with another");
		}
		else
		{
			sect2.setInChargeName(section.getInChargeName());
			sect2.setSectionName(section.getSectionName());
			sect2.setSectionId(section.getSectionId());
			sect2.setEmail(section.getEmail());
			sect2.setPassword(section.getPassword());
			return new ResponseEntity<Section>(sect2, HttpStatus.OK);
	
		}
	}
}
