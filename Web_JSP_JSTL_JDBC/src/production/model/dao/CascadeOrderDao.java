package model.dao;

import model.CascadeOrder;

public interface CascadeOrderDao {
	int insert(CascadeOrder cascadeOrder);
	CascadeOrder get(int id);
	
}
