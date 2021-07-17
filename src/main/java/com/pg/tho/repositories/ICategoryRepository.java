package com.pg.tho.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pg.tho.entities.Category;

public interface ICategoryRepository extends JpaRepository<Category, Integer> {

}
