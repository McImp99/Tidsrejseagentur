package com.example.tidsrejseagentur.backend.domain.guides;

import com.example.tidsrejseagentur.backend.domain.guides.models.GuideCreate;
import com.example.tidsrejseagentur.backend.domain.guides.models.GuideDelete;
import com.example.tidsrejseagentur.backend.domain.guides.models.GuideRead;
import com.example.tidsrejseagentur.backend.domain.guides.models.GuideUpdate;

import java.sql.SQLException;
import java.util.List;

public interface IGuideAccess {
	List<GuideRead> readAll() throws SQLException;
	GuideRead read(int id) throws SQLException;
	int add(GuideCreate guide) throws SQLException;
	int update(GuideUpdate guide);
	int delete(GuideDelete guide) throws SQLException;
}