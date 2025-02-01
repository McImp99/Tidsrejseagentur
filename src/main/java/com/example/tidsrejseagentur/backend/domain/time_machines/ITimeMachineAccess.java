package com.example.tidsrejseagentur.backend.domain.time_machines;

import com.example.tidsrejseagentur.backend.domain.time_machines.models.TimeMachineCreate;
import com.example.tidsrejseagentur.backend.domain.time_machines.models.TimeMachineDelete;
import com.example.tidsrejseagentur.backend.domain.time_machines.models.TimeMachineRead;
import com.example.tidsrejseagentur.backend.domain.time_machines.models.TimeMachineUpdate;

import java.sql.SQLException;
import java.util.List;

public interface ITimeMachineAccess {
	List<TimeMachineRead> readAll() throws SQLException;
	TimeMachineRead read(int id) throws SQLException;
	int add(TimeMachineCreate timeMachine) throws SQLException;
	int update(TimeMachineUpdate timeMachine);
	int delete(TimeMachineDelete timeMachine) throws SQLException;
}