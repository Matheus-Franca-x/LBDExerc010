package com.fatec.LBDSpring010.persistence;

import java.sql.SQLException;

public interface ICrudProcedure<T>
{
	String crud (String acao, T t) throws SQLException, ClassNotFoundException;
}
