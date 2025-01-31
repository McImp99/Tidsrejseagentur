interface IBookingAccess {
	List<BookingRead> readAll();
	BookingRead read(int id);
	int add(BookingCreate booking);
	int update(BookingUpdate booking);
	int delete(BookingDelete booking);
}