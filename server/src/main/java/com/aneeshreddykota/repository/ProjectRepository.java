package com.aneeshreddykota.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aneeshreddykota.modal.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Integer> {

}
