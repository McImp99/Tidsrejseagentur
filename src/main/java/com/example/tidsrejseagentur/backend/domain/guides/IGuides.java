interface IGuides {
	List<GuideRead> readAll();
	GuideRead read(int id);
	int add(GuideCreate guide);
	int update(GuideUpdate guide);
	int delete(GuideDelete guide);
}