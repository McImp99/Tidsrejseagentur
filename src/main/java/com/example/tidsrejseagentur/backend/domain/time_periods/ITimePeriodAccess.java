package com.example.tidsrejseagentur.backend.domain.time_periods;

import com.example.tidsrejseagentur.backend.domain.time_periods.models.TimePeriodCreate;
import com.example.tidsrejseagentur.backend.domain.time_periods.models.TimePeriodDelete;
import com.example.tidsrejseagentur.backend.domain.time_periods.models.TimePeriodRead;
import com.example.tidsrejseagentur.backend.domain.time_periods.models.TimePeriodUpdate;

import java.util.List;

interface ITimePeriodAccess {
	List<TimePeriodRead> readAll();
	TimePeriodRead read(int id);
	int add(TimePeriodCreate timePeriod);
	int update(TimePeriodUpdate timePeriod);
	int delete(TimePeriodDelete timePeriod);
}