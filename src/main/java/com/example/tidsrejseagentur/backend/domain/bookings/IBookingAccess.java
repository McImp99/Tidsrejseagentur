package com.example.tidsrejseagentur.backend.domain.bookings;

import com.example.tidsrejseagentur.backend.domain.bookings.models.BookingCreate;
import com.example.tidsrejseagentur.backend.domain.bookings.models.BookingDelete;
import com.example.tidsrejseagentur.backend.domain.bookings.models.BookingRead;
import com.example.tidsrejseagentur.backend.domain.bookings.models.BookingUpdate;

import java.sql.SQLException;
import java.util.List;

public interface IBookingAccess {
	List<BookingRead> readAll() throws SQLException;
	BookingRead read(int id) throws SQLException;
	int add(BookingCreate booking) throws SQLException;
	int update(BookingUpdate booking);
	int delete(BookingDelete booking) throws SQLException;
}