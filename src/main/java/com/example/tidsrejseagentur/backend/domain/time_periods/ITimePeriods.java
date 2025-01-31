interface ITimePeriods {
	List<TimePeriodRead> readAll();
	TimePeriodRead read(int id);
	int add(TimePeriodCreate timePeriod);
	int update(TimePeriodUpdate timePeriod);
	int delete(TimePeriodDelete timePeriod);
}