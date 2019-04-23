package com.anyulled.quickstart.service;

import com.anyulled.quickstart.model.Country;

public interface CountryService {

    Country getByName(String name);
}
