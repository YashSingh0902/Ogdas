package com.pg.tho.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pg.tho.entities.Item;

@Repository
public interface IItemRepository extends JpaRepository<Item, Integer>{

	@Query(value="select i from item i inner join cart_item_list ci on i.item_id=ci.item_id inner join food_cart f on ci.cart_id=f.cart_id where f.cart_id=:cartId", nativeQuery = true)
	public List<Item> findItemsByCart(@Param("cartId") int cartId);
		
	@Query("select i from Item i where i.itemName=:name")
	public List<Item> findItemsByItemName(@Param("name") String name);
	
	@Query("select i from Item i inner join Category c on i.category.catId=c.catId where c.categoryName=:name")
	public List<Item> findItemsByCategory(@Param("name") String name);
	     
	@Query("select i from Item i inner join Section s on i.section.sectionId = s.sectionId where s.sectionName =:name")
	public List<Item> findItemsBySection(@Param("name") String name);

	
}
