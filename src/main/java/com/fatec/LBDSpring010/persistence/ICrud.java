package com.fatec.LBDSpring010.persistence;

import java.sql.SQLException;
import java.util.List;

public interface ICrud<T> 
{
	T consultar(T t) throws SQLException, ClassNotFoundException;
	List<T> listar() throws SQLException, ClassNotFoundException;
}
