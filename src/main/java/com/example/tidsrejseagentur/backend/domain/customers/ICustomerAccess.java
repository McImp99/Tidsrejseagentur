package com.example.tidsrejseagentur.backend.domain.customers;

import com.example.tidsrejseagentur.backend.domain.customers.models.CustomerCreate;
import com.example.tidsrejseagentur.backend.domain.customers.models.CustomerDelete;
import com.example.tidsrejseagentur.backend.domain.customers.models.CustomerRead;
import com.example.tidsrejseagentur.backend.domain.customers.models.CustomerUpdate;

import java.sql.SQLException;
import java.util.List;

public interface ICustomerAccess {
	List<CustomerRead> readAll() throws SQLException;
	CustomerRead read(int id) throws SQLException;
	int add(CustomerCreate customer) throws SQLException;
	int update(CustomerUpdate customer) throws SQLException;
	int delete(CustomerDelete customer) throws SQLException;
}