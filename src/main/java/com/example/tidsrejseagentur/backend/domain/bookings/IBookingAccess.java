package com.example.tidsrejseagentur.backend.domain.bookings;

import com.example.tidsrejseagentur.backend.domain.bookings.models.BookingCreate;
import com.example.tidsrejseagentur.backend.domain.bookings.models.BookingDelete;
import com.example.tidsrejseagentur.backend.domain.bookings.models.BookingRead;
import com.example.tidsrejseagentur.backend.domain.bookings.models.BookingUpdate;

import java.util.List;

interface IBookingAccess {
	List<BookingRead> readAll();
	BookingRead read(int id);
	int add(BookingCreate booking);
	int update(BookingUpdate booking);
	int delete(BookingDelete booking);
}