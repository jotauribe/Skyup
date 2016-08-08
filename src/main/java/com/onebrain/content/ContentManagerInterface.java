package com.onebrain.content;

import java.util.List;

public interface ContentManagerInterface {
	
	public Idea addIdea(Idea idea);
	public List<Idea> getIdeasByWorkspace(String wName);

}
