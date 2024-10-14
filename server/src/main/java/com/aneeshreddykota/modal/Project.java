package com.aneeshreddykota.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="project")
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String projectName;
	
	private String description;
	
	private String githubUrl;
	
	private String liveUrl;
	
	private String Overview;
	
	private String imageName;

	public Project() {
		
	}
	
	public Project(Integer id, String projectName, String description, String githubUrl, String liveUrl,
			String overview, String imageName) {
		super();
		this.id = id;
		this.projectName = projectName;
		this.description = description;
		this.githubUrl = githubUrl;
		this.liveUrl = liveUrl;
		Overview = overview;
		this.imageName = imageName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGithubUrl() {
		return githubUrl;
	}

	public void setGithubUrl(String githubUrl) {
		this.githubUrl = githubUrl;
	}

	public String getLiveUrl() {
		return liveUrl;
	}

	public void setLiveUrl(String liveUrl) {
		this.liveUrl = liveUrl;
	}

	public String getOverview() {
		return Overview;
	}

	public void setOverview(String overview) {
		Overview = overview;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
}
