package com.workintech.fswebs17d1.controller;

import com.workintech.fswebs17d1.entity.Animal;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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



    @PostConstruct
    public void loadData() {
        System.out.println("loading course catalog...");
        this.animals = new HashMap<>();
        this.animals.put(1, new Animal(1, "Lion"));
        this.animals.put(2, new Animal(2, "Crocodile"));
        System.out.println("fullName=" + fullName + ", courseName=" + courseName);
    }

    @GetMapping
    public List<Animal> getAnimals() {
        System.out.println(this.animals.values());
        return new ArrayList<>(this.animals.values());
    }

    @GetMapping("/{id}")
    public Animal getAnimal(@PathVariable("id") int id) {
        return this.animals.get(id);
    }
    @PostMapping
    public void addAnimal(@RequestBody Animal animal) {
        System.out.println("post animal triggered");
        this.animals.put(animal.getId(), animal);
    }

    @PutMapping("/{id}")
    public Animal updateAnimal(@PathVariable("id") int id, @RequestBody Animal newAnimal) {
        this.animals.replace(id, newAnimal);
        return this.animals.get(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAnimal(@PathVariable("id") int id) {
        System.out.println("delete triggered");
        this.animals.remove(id);
    }

}