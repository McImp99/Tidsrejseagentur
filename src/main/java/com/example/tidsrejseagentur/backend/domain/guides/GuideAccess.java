package com.example.tidsrejseagentur.backend.domain.guides;

import com.example.tidsrejseagentur.backend.domain.customers.models.CustomerRead;
import com.example.tidsrejseagentur.backend.domain.guides.models.GuideCreate;
import com.example.tidsrejseagentur.backend.domain.guides.models.GuideDelete;
import com.example.tidsrejseagentur.backend.domain.guides.models.GuideRead;
import com.example.tidsrejseagentur.backend.domain.guides.models.GuideUpdate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuideAccess implements IGuideAccess {
    Connection conn;

    public GuideAccess(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<GuideRead> readAll() throws SQLException {
        var stmt = conn.prepareStatement("SELECT * FROM guides");
        var results = stmt.executeQuery();

        List<GuideRead> mapped = new ArrayList<>();

        while (results.next()) {
            var read = new GuideRead(
                    results.getInt("id"),
                    results.getString("name"),
                    results.getString("speciality")
            );

            mapped.add(read);
        }

        results.close();
        stmt.close();

        return mapped;
    }

    @Override
    public GuideRead read(int id) throws SQLException {
        var stmt = conn.prepareStatement("SELECT * FROM guides WHERE id = ? LIMIT 1");
        stmt.setInt(1, id);

        var results = stmt.executeQuery();

        GuideRead read = null;
        if (results.next()) {
            read = new GuideRead(
                    results.getInt("id"),
                    results.getString("name"),
                    results.getString("speciality")
            );
        }

        results.close();
        stmt.close();

        return read;
    }

    @Override
    public int add(GuideCreate guide) throws SQLException {
        var stmt = conn.prepareStatement("INSERT INTO guides (name, speciality) VALUES (?, ?) RETURNING id");
        stmt.setString(1, guide.name());
        stmt.setString(2, guide.speciality());
        var results = stmt.executeQuery();

        int id = Integer.parseInt(null);
        if (results.next()) {
            id = results.getInt("id");
        }

        return id;
    }

    @Override
    public int update(GuideUpdate guide) {
        return 0;
    }

    @Override
    public int delete(GuideDelete guide) throws SQLException {
        var stmt = conn.prepareStatement("DELETE FROM guides WHERE id = ?");
        stmt.setInt(1, guide.id());
        return stmt.executeUpdate();
    }
}
