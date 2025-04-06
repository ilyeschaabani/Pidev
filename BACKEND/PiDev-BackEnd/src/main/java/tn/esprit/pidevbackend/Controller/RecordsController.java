package tn.esprit.pidevbackend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidevbackend.Entity.AttemptedQuizRecords;
import tn.esprit.pidevbackend.Entity.Evaluation;
import tn.esprit.pidevbackend.Services.RecordsService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user-activities")
public class RecordsController {
	
	@Autowired
	private RecordsService recordsService;
	
	// add record
	@PostMapping("/")
	public ResponseEntity<AttemptedQuizRecords> add(@RequestBody AttemptedQuizRecords record){
		return ResponseEntity.ok(this.recordsService.addRecord(record));	
	}
	
	// update record
	@PutMapping("/")
	public ResponseEntity<AttemptedQuizRecords> update(@RequestBody AttemptedQuizRecords record){
		return ResponseEntity.ok(this.recordsService.updateRecord(record));
	}
	
	// get all records
	@GetMapping("/")
	public ResponseEntity<?> records(){
		return ResponseEntity.ok(this.recordsService.getRecords());	
	}
	
	// get one record
	@GetMapping("/{aId}")
	public AttemptedQuizRecords getRecord(@PathVariable("aId") String aId) {
		return this.recordsService.getRecord(aId);
	}
	
	// delete record
	@DeleteMapping("/{aId}")
	public void delete(@PathVariable("aId") String aId) {
		this.recordsService.deleteRecord(aId);
	}
	
	// get records of quiz
	@GetMapping("/quiz/{qId}")
	public ResponseEntity<?> getRecordsOfQuiz(@PathVariable("qId") String idEvaluation){
		Evaluation evaluation = new Evaluation();
		evaluation.setIdEvaluation(idEvaluation);
		return ResponseEntity.ok(this.recordsService.getRecordsOfQuiz(evaluation));
	}
	


}
