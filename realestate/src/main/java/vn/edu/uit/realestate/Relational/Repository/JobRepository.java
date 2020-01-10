package vn.edu.uit.realestate.Relational.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.uit.realestate.Relational.Model.Job;

public interface JobRepository extends JpaRepository<Job, Long>{
	public Optional<Job> findByName(String name);
}
