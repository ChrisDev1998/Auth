package com.ChrisJavaApiDev.Auth.Controller;

import com.ChrisJavaApiDev.Auth.Entities.Task;
import com.ChrisJavaApiDev.Auth.repositories.TasksRepository;
import com.ChrisJavaApiDev.Auth.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/task")
public class TaskContoller {

    @Autowired
    private TasksRepository tasksRepository;

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/myTasks")
    private Optional<List<Task>> MyTasks(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long usersId = usersRepository.findByUsername(user.getUsername()).get().getId();
        return tasksRepository.findAllByUserId(usersId);
    }

    @PostMapping("/createMyTask")
    private String CreateMyTasks(@RequestBody Task task){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long usersId = usersRepository.findByUsername(user.getUsername()).get().getId();
        task.setUserId(usersId);
        tasksRepository.save(task);
        return "TODO OK :)";
    }

    @PutMapping("/updateMyTask")
    private String UpdateMyTask(@RequestBody Task task){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long usersId = usersRepository.findByUsername(user.getUsername()).get().getId();
        tasksRepository.save(task);
        return "Task Updated";
    }

    @DeleteMapping("/deleteMyTask")
    private String DeleteMyTask(@RequestParam Long id){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long usersId = usersRepository.findByUsername(user.getUsername()).get().getId();

        tasksRepository.deleteById(id);
        return "Lo borraste :(";
    }
}
