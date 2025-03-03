package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Skill extends AbstractEntity {
    private String description;

    public List<Job> getJobs() {

        return jobs;
    }
    public void setJobs(List<Job> jobs) {

        this.jobs = jobs;
    }
    @ManyToMany(mappedBy="skills")
    private List<Job> jobs = new ArrayList<>();
    public Skill() {

    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
