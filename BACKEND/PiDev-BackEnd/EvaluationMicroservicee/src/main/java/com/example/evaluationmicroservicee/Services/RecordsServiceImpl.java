package com.example.evaluationmicroservicee.Services;

import com.example.evaluationmicroservicee.Entity.AttemptedQuizRecords;
import com.example.evaluationmicroservicee.Entity.Evaluation;
import com.example.evaluationmicroservicee.Repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RecordsServiceImpl implements RecordsService {

	@Autowired
	private RecordRepository recordsRepository; // Correction du nom (typo dans 'Repositiry')

	@Override
	public AttemptedQuizRecords addRecord(AttemptedQuizRecords record) {
		return this.recordsRepository.save(record);
	}

	@Override
	public AttemptedQuizRecords updateRecord(AttemptedQuizRecords record) {
		return this.recordsRepository.save(record);
	}

	@Override
	public Set<AttemptedQuizRecords> getRecords() {
		return new HashSet<>(this.recordsRepository.findAll());
	}

	@Override
	public AttemptedQuizRecords getRecord(String recordId) {
		return this.recordsRepository.findById(recordId).orElse(null); // Meilleure gestion des Optional
	}

	@Override
	public void deleteRecord(String recordId) {
		this.recordsRepository.deleteById(recordId); // Pas besoin de String.valueOf()
	}

	@Override
	public Set<AttemptedQuizRecords> getRecordsOfQuiz(Evaluation evaluation) {
		// Solution 1: Si vous utilisez @DBRef dans votre entité
		// return new HashSet<>(this.recordsRepository.findByEvaluation(evaluation));

		// Solution 2: Si vous stockez juste l'ID (recommandé)
		return new HashSet<>(this.recordsRepository.findByEvaluationId(evaluation.getIdEvaluation()));
	}
}