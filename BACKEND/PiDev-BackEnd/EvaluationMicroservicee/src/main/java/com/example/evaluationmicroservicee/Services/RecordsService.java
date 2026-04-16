package com.example.evaluationmicroservicee.Services;


import com.example.evaluationmicroservicee.Entity.AttemptedQuizRecords;
import com.example.evaluationmicroservicee.Entity.Evaluation;

import java.util.Set;

public interface RecordsService {
	
	public AttemptedQuizRecords addRecord(AttemptedQuizRecords record);
	
	public AttemptedQuizRecords updateRecord(AttemptedQuizRecords record);
	
	public Set<AttemptedQuizRecords> getRecords();
	
	public AttemptedQuizRecords getRecord(String recordId);
	
	public void deleteRecord(String recordId);

	public Set<AttemptedQuizRecords> getRecordsOfQuiz(Evaluation evaluation);

}
