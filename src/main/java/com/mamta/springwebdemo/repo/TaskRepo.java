package com.mamta.springwebdemo.repo;

import com.mamta.springwebdemo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends JpaRepository<Task,Long> {
}
