package demo.springdemo.controller;

import demo.springdemo.bean.Person;
import demo.springdemo.rep.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DataController {
    @Autowired
    private PersonRepository personRepository;

    @RequestMapping("/save")
    public Person save(String name, String address, int age) {
        return personRepository.save(new Person(name, age, address));
    }

    @RequestMapping("/queryall")
    public List<Person> queryAll() {
        return personRepository.findAll();
    }

    @RequestMapping("/querybyname")
    public List<Person> queryByName(String name) {
        return personRepository.findByName(name);
    }

    @RequestMapping("/querybynamewithsort")
    public List<Person> queryByNameWithSort(String name, String sort) {
        return personRepository.findByName(name, new Sort(Sort.DEFAULT_DIRECTION, sort));
    }

    @RequestMapping("/page")
    public Page<Person> page() {
        return personRepository.findAll(PageRequest.of(0, 10));
    }

    @RequestMapping("/pagebyaddress")
    public Page<Person> pageByAddress(String address) {
        return personRepository.findByAddress(address, PageRequest.of(0, 10));
    }
}
