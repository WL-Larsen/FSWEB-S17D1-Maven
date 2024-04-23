package com.workintech.fswebs17d1.controller;

import com.workintech.fswebs17d1.entity.Animal;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/workintech/animal")
public class AnimalController {

    @Value("${course.name}")
    private String courseName;

    @Value("${project.developer.fullname}")
    private String fullName;

    private Map<Integer, Animal> animals ;

    @GetMapping("/")
    public List<Animal> getAllAnimals() {
        return animals.values().stream().collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Animal getAnimalById(@PathVariable int id) {
        return animals.get(id);
    }


    @PutMapping("/{id}")
    public Animal updateAnimal(@PathVariable int id, @RequestBody Animal updatedAnimal) {
        if (animals.containsKey(id)) {
            updatedAnimal.setId(id);
            animals.put(id, updatedAnimal);
            return updatedAnimal;
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public String deleteAnimal(@PathVariable int id) {
        if (animals.containsKey(id)) {
            animals.remove(id);
            return "Animal with ID " + id + " deleted";
        } else {
            return "Animal with ID " + id + " not found";
        }
    }
}