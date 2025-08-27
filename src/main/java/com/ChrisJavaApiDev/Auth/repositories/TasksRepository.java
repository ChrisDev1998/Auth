package com.ChrisJavaApiDev.Auth.repositories;


import com.ChrisJavaApiDev.Auth.Entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TasksRepository extends JpaRepository<Task, Long> {

    Optional<List<Task>> findAllByUserId(Long userId);
}
