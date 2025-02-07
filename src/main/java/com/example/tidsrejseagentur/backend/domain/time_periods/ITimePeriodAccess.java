package com.example.tidsrejseagentur.backend.domain.time_periods;

import com.example.tidsrejseagentur.backend.domain.time_periods.models.TimePeriodCreate;
import com.example.tidsrejseagentur.backend.domain.time_periods.models.TimePeriodDelete;
import com.example.tidsrejseagentur.backend.domain.time_periods.models.TimePeriodRead;
import com.example.tidsrejseagentur.backend.domain.time_periods.models.TimePeriodUpdate;

import java.sql.SQLException;
import java.util.List;

public interface ITimePeriodAccess {
	List<TimePeriodRead> readAll() throws SQLException;
	TimePeriodRead read(int id) throws SQLException;
	int add(TimePeriodCreate timePeriod) throws SQLException;
	int update(TimePeriodUpdate timePeriod) throws SQLException;
	int delete(TimePeriodDelete timePeriod) throws SQLException;
}