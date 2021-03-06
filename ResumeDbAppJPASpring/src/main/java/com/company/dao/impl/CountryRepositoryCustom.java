package com.company.dao.impl;

import com.company.entity.Country;

import java.util.List;

public interface CountryRepositoryCustom {

	public List<Country> getAllCountry();
	
	public Country getById(int id);
	
	public boolean addCountry(Country obj);
	
	public boolean updateCountry(Country obj);
	
}
