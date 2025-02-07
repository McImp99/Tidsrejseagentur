package com.example.tidsrejseagentur.backend.domain.time_machines;

import com.example.tidsrejseagentur.backend.domain.time_machines.models.TimeMachineCreate;
import com.example.tidsrejseagentur.backend.domain.time_machines.models.TimeMachineDelete;
import com.example.tidsrejseagentur.backend.domain.time_machines.models.TimeMachineRead;
import com.example.tidsrejseagentur.backend.domain.time_machines.models.TimeMachineUpdate;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TimeMachineAccess implements ITimeMachineAccess {
    Connection conn;

    public TimeMachineAccess(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<TimeMachineRead> readAll() throws SQLException {
        var stmt = conn.prepareStatement("SELECT * FROM time_machines");
        var results = stmt.executeQuery();

        List<TimeMachineRead> mapped = new ArrayList<>();

        while (results.next()) {
            var read = new TimeMachineRead(
                    results.getInt("id"),
                    results.getString("name"),
                    results.getInt("capacity"),
                    results.getString("status")
            );

            mapped.add(read);
        }

        results.close();
        stmt.close();

        return mapped;
    }

    @Override
    public TimeMachineRead read(int id) throws SQLException {
        var stmt = conn.prepareStatement("SELECT * FROM time_machines WHERE id = ? LIMIT 1");
        stmt.setInt(1, id);

        var results = stmt.executeQuery();

        TimeMachineRead read = null;
        if (results.next()) {
            read = new TimeMachineRead(
                    results.getInt("id"),
                    results.getString("name"),
                    results.getInt("capacity"),
                    results.getString("status")
            );
        }

        results.close();
        stmt.close();

        return read;
    }

    @Override
    public int add(TimeMachineCreate timeMachine) throws SQLException {
        var stmt = conn.prepareStatement("INSERT INTO time_machines (name, status, capacity) VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, timeMachine.name());
        stmt.setInt(2, timeMachine.capacity());
        stmt.setString(3, timeMachine.status());

        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            try (var generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        }

        return -1;
    }

    @Override
    public int update(TimeMachineUpdate timeMachine) throws SQLException {
        var stmt = conn.prepareStatement(
                "UPDATE time_machines SET name = ?, capacity = ?, status = ? WHERE id = ?"
        );
        stmt.setString(1, timeMachine.name());
        stmt.setInt(2, timeMachine.capacity());
        stmt.setString(3, timeMachine.status());
        stmt.setInt(4, timeMachine.id());

        return stmt.executeUpdate();
    }

    @Override
    public int delete(TimeMachineDelete timeMachine) throws SQLException {
        var stmt = conn.prepareStatement("DELETE FROM time_machines WHERE id = ?");
        stmt.setInt(1, timeMachine.id());
        return stmt.executeUpdate();
    }
}
