interface ITimeMachines {
	List<TimeMachineRead> readAll();
	TimeMachineRead read(int id);
	int add(TimeMachineCreate timeMachine);
	int update(TimeMachineUpdate timeMachine);
	int delete(TimeMachineDelete timeMachine);
}