package vn.edu.uit.realestate.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.uit.realestate.Relational.Model.Job;
import vn.edu.uit.realestate.Relational.Repository.JobRepository;

@RestController
public class JobController {
	@Autowired
	private JobRepository jobRepository;

	@RequestMapping(value = "/jobs", method = RequestMethod.GET)
	public ResponseEntity<List<Job>> getAllJobs() {
		List<Job> jobs = jobRepository.findAll();
		return new ResponseEntity<>(jobs, HttpStatus.OK);
	}
}
