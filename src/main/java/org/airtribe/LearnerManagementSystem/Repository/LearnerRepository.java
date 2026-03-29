package org.airtribe.LearnerManagementSystem.Repository;

import org.airtribe.LearnerManagementSystem.Entity.Learner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LearnerRepository extends JpaRepository<Learner, Long> {

    public List<Learner> findLearnerByName(String name);

    Optional<Learner> findByEmail(String email);
}
