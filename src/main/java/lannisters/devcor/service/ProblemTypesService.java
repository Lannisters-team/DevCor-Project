package lannisters.devcor.service;

import java.sql.SQLException;
import java.util.List;

import lannisters.devcor.entity.ProblemType;

public interface ProblemTypesService {

	public List<ProblemType> getAllProblemTypes();

	public ProblemType getProblemTypeById(int problemTypeId);

	public void updateProblemType(ProblemType problemType) throws SQLException;

	public void addProblemType(ProblemType problemType) throws SQLException;

	public void deleteProblemType(int problemType) throws SQLException;

	public ProblemType getProblemTypeByTitle(String title);

	public boolean checkProblemTypeExistence(ProblemType type);
}
