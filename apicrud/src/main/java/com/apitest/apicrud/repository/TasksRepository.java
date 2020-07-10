package com.apitest.apicrud.repository;

import com.apitest.apicrud.models.Tarefa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRepository extends JpaRepository<Tarefa, Long> {
        Tarefa findById(long id);
}