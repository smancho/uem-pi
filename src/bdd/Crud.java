package bdd;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Crud {
	public void create(Object newRecord) throws SQLException;
	public ArrayList<Object> read(Object filter) throws SQLException;
	public int update(Object filter, Object newData) throws SQLException;
	public int delete(Object filter) throws SQLException;
}
