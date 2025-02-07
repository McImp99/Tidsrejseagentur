package com.example.tidsrejseagentur.backend.domain.time_periods;

import com.example.tidsrejseagentur.backend.domain.time_machines.models.TimeMachineRead;
import com.example.tidsrejseagentur.backend.domain.time_periods.models.TimePeriodCreate;
import com.example.tidsrejseagentur.backend.domain.time_periods.models.TimePeriodDelete;
import com.example.tidsrejseagentur.backend.domain.time_periods.models.TimePeriodRead;
import com.example.tidsrejseagentur.backend.domain.time_periods.models.TimePeriodUpdate;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TimePeriodAccess implements ITimePeriodAccess {
    Connection conn;

    public TimePeriodAccess(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<TimePeriodRead> readAll() throws SQLException {
        var stmt = conn.prepareStatement("SELECT * FROM time_periods");
        var results = stmt.executeQuery();

        List<TimePeriodRead> mapped = new ArrayList<>();

        while (results.next()) {
            var read = new TimePeriodRead(
                    results.getInt("id"),
                    results.getString("name"),
                    results.getString("description")
            );

            mapped.add(read);
        }

        results.close();
        stmt.close();

        return mapped;
    }

    @Override
    public TimePeriodRead read(int id) throws SQLException {
        var stmt = conn.prepareStatement("SELECT * FROM time_periods WHERE id = ? LIMIT 1");
        stmt.setInt(1, id);

        var results = stmt.executeQuery();

        TimePeriodRead read = null;
        if (results.next()) {
            read = new TimePeriodRead(
                    results.getInt("id"),
                    results.getString("name"),
                    results.getString("description")
            );
        }

        results.close();
        stmt.close();

        return read;
    }

    @Override
    public int add(TimePeriodCreate timePeriod) throws SQLException {
        var stmt = conn.prepareStatement("INSERT INTO time_periods (name, description) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);

        stmt.setString(1, timePeriod.name());
        stmt.setString(2, timePeriod.description());
        stmt.executeUpdate();

        var results = stmt.getGeneratedKeys();
        if (results.next()) {
            return results.getInt(1);
        }
        return -1;
    }

    @Override
    public int update(TimePeriodUpdate timePeriod) throws SQLException {

        var stmt = conn.prepareStatement(
                "UPDATE time_periods SET name = ?, description = ? WHERE id = ?"
        );

        stmt.setString(1, timePeriod.name());
        stmt.setString(2, timePeriod.description());
        stmt.setInt(3, timePeriod.id());

        return stmt.executeUpdate();
    }

    @Override
    public int delete(TimePeriodDelete timePeriod) throws SQLException {
        var stmt = conn.prepareStatement("DELETE FROM time_periods WHERE id = ?");
        stmt.setInt(1, timePeriod.id());
        return stmt.executeUpdate();
    }
}
