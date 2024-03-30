package com.example.demo.controller;

import com.example.demo.repository.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class DemoController {

    private final DemoRepository demoRepository;

    @Autowired
    public DemoController(DemoRepository demoRepository) {
        this.demoRepository = demoRepository;
    }

    @GetMapping("demo")
    public String demo(Model model){
        model.addAttribute("data","numberPlate");
        return "demo";
    }

    @GetMapping("demo-mvc")
    public String demoMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "demo-template";
    }

    @GetMapping("demo-string")
    public String demoString(@RequestParam("name")String name, Model model){
        model.addAttribute("name", name);
        return "demo-template";
    }

    @GetMapping("demo-api")
    public Demo demoApi(@RequestParam("name") String name) {
        Demo demo = new Demo();
        demo.setName(name);
        return demo;
    }

    @GetMapping("demo-num")
    public String showUserData(Model model) {
        List<Map<String, Object>> userData = demoRepository.getAllUserData();
        model.addAttribute("userData", userData);
        return "demo-num";
    }

    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        List<Map<String, Object>> searchData = demoRepository.searchUserData(keyword);
        model.addAttribute("userData", searchData);
        return "demo-num";
    }

    @PostMapping("/add")
    public String addUser(@RequestParam("number") String number, @RequestParam("name") String name) {
        demoRepository.addUserData(number, name);
        return "redirect:/demo-num";
    }

    @PostMapping("/edit")
    public String editUser(@RequestParam("id") Long id, @RequestParam("number") String number, @RequestParam("name") String name) {
        demoRepository.editUserData(id, number, name);
        return "redirect:/demo-num";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        demoRepository.deleteUserData(id);
        return "redirect:/demo-num";
    }

    static class Demo {
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
