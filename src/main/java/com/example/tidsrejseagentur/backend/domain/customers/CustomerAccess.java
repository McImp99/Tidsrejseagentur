package com.example.tidsrejseagentur.backend.domain.customers;

import com.example.tidsrejseagentur.backend.domain.customers.models.CustomerCreate;
import com.example.tidsrejseagentur.backend.domain.customers.models.CustomerDelete;
import com.example.tidsrejseagentur.backend.domain.customers.models.CustomerRead;
import com.example.tidsrejseagentur.backend.domain.customers.models.CustomerUpdate;

import java.util.List;

class CustomerAccess implements ICustomerAccess {

    @Override
    public List<CustomerRead> readAll() {
        return null;
    }

    @Override
    public CustomerRead read(int id) {
        return null;
    }

    @Override
    public int add(CustomerCreate customer) {
        return 0;
    }

    @Override
    public int update(CustomerUpdate customer) {
        return 0;
    }

    @Override
    public int delete(CustomerDelete customer) {
        return 0;
    }
}