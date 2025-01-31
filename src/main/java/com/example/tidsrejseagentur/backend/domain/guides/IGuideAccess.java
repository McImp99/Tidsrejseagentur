package com.example.tidsrejseagentur.backend.domain.guides;

import com.example.tidsrejseagentur.backend.domain.guides.models.GuideCreate;
import com.example.tidsrejseagentur.backend.domain.guides.models.GuideDelete;
import com.example.tidsrejseagentur.backend.domain.guides.models.GuideRead;
import com.example.tidsrejseagentur.backend.domain.guides.models.GuideUpdate;

import java.util.List;

public interface IGuideAccess {
	List<GuideRead> readAll();
	GuideRead read(int id);
	int add(GuideCreate guide);
	int update(GuideUpdate guide);
	int delete(GuideDelete guide);
}