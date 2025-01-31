package com.example.tidsrejseagentur.backend.domain.time_machines;

import com.example.tidsrejseagentur.backend.domain.time_machines.models.TimeMachineCreate;
import com.example.tidsrejseagentur.backend.domain.time_machines.models.TimeMachineDelete;
import com.example.tidsrejseagentur.backend.domain.time_machines.models.TimeMachineRead;
import com.example.tidsrejseagentur.backend.domain.time_machines.models.TimeMachineUpdate;

import java.util.List;

public interface ITimeMachineAccess {
	List<TimeMachineRead> readAll();
	TimeMachineRead read(int id);
	int add(TimeMachineCreate timeMachine);
	int update(TimeMachineUpdate timeMachine);
	int delete(TimeMachineDelete timeMachine);
}