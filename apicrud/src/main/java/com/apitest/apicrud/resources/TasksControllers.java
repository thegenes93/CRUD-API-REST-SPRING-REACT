package com.apitest.apicrud.resources;

import java.util.List;

import com.apitest.apicrud.models.Tarefa;
import com.apitest.apicrud.repository.TasksRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api")
public class TasksControllers {

    @Autowired
    TasksRepository tasksRepository;

    @GetMapping("/tarefas")
    public List<Tarefa> listaProdutos() {
        return tasksRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @GetMapping(value = "/tarefas/{id}")
    public Tarefa listaTarefaUnica(@PathVariable(value = "id") long id) {
        return tasksRepository.findById(id);
    }

    @PostMapping("/tarefa")
    public Tarefa salvaTarefa(@RequestBody Tarefa tarefa) {
        return tasksRepository.save(tarefa);
    }

    @DeleteMapping("/tarefa/{id}")
    public void deleteTarefa(@PathVariable(value = "id") long id) {
        tasksRepository.deleteById(id);
    }
    
    // @DeleteMapping("/tarefa/{id}")
    // public void deleteTarefa(@RequestBody Tarefa tarefa) {
    //     tasksRepository.delete(tarefa);
    // }

    @PutMapping("/tarefa")
    public Tarefa editeTarefa(@RequestBody Tarefa tarefa) {
        return tasksRepository.save(tarefa);
    }

}