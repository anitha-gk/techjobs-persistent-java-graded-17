package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;
import java.util.List;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private JobRepository jobRepository;

    @RequestMapping("/")
    public String index(Model model) {

        model.addAttribute("title", "My Jobs");

        model.addAttribute("jobs", jobRepository.findAll());
        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());
        System.out.println(" IN displayAddJobForm");

        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    Errors errors, Model model, @RequestParam int employerId, @RequestParam List<Integer> skills) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            return "add";
        }
        if (employerRepository.findById(employerId).isPresent()) {
            newJob.setEmployer(employerRepository.findById(employerId).get());
        }
        System.out.println(" IN processAddJobForm33 " + skills.toString() + " Done");
        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);

        newJob.setSkills(skillObjs);
        jobRepository.save(newJob);
        System.out.println(" processAddJobForm returning");

        return "redirect:";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {

        Job thisJob = jobRepository.findById(jobId).get();

        model.addAttribute("job",thisJob);

        return "view";
    }


}



