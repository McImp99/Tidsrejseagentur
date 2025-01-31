interface ICustomers {
	List<CustomerRead> readAll();
	CusomerRead read(int id);
	int add(CustomerCreate customer);
	int update(CustomerUpdate customer);
	int delete(CustomerDelete customer);
}