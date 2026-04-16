package com.mamta.springwebdemo.service;

import com.mamta.springwebdemo.entity.Task;
import com.mamta.springwebdemo.repo.TaskRepo;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private TaskRepo taskRepo;

    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public Task createTask(Task task){
        return taskRepo.save(task);
    }

}
