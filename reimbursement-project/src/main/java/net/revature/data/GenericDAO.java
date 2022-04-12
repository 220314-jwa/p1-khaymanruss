package net.revature.data;

import java.util.List;

public interface GenericDAO <T> {

	public int create(T newObj);
	public T getById(int id);
	public List<T> getAll();
	public void update(T updateObj);
	public void delete(T objToDelete);
}
