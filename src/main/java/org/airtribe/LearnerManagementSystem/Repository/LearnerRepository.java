package org.airtribe.LearnerManagementSystem.Repository;

import org.airtribe.LearnerManagementSystem.Entity.Learner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LearnerRepository extends JpaRepository<Learner, Long> {

    public List<Learner> findLearnerByName(String name);
}
