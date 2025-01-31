package com.example.tidsrejseagentur.backend.domain.customers;

import com.example.tidsrejseagentur.backend.domain.customers.models.CustomerCreate;
import com.example.tidsrejseagentur.backend.domain.customers.models.CustomerDelete;
import com.example.tidsrejseagentur.backend.domain.customers.models.CustomerRead;
import com.example.tidsrejseagentur.backend.domain.customers.models.CustomerUpdate;

import java.util.List;

interface ICustomerAccess {
	List<CustomerRead> readAll();
	CustomerRead read(int id);
	int add(CustomerCreate customer);
	int update(CustomerUpdate customer);
	int delete(CustomerDelete customer);
}