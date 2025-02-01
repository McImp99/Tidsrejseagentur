package com.example.tidsrejseagentur.backend.domain.time_periods;

import com.example.tidsrejseagentur.backend.domain.time_machines.models.TimeMachineRead;
import com.example.tidsrejseagentur.backend.domain.time_periods.models.TimePeriodCreate;
import com.example.tidsrejseagentur.backend.domain.time_periods.models.TimePeriodDelete;
import com.example.tidsrejseagentur.backend.domain.time_periods.models.TimePeriodRead;
import com.example.tidsrejseagentur.backend.domain.time_periods.models.TimePeriodUpdate;

import java.sql.Connection;
import java.sql.SQLException;
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
        var stmt = conn.prepareStatement("INSERT INTO time_periods (name, description) VALUES (?, ?) RETURNING id");
        stmt.setString(1, timePeriod.name());
        stmt.setString(2, timePeriod.description());
        var results = stmt.executeQuery();

        int id = Integer.parseInt(null);
        if (results.next()) {
            id = results.getInt("id");
        }

        return id;
    }

    @Override
    public int update(TimePeriodUpdate timePeriod) {
        return 0;
    }

    @Override
    public int delete(TimePeriodDelete timePeriod) throws SQLException {
        var stmt = conn.prepareStatement("DELETE FROM time_periods WHERE id = ?");
        stmt.setInt(1, timePeriod.id());
        return stmt.executeUpdate();
    }
}
