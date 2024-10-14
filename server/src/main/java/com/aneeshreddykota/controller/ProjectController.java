package com.aneeshreddykota.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aneeshreddykota.modal.Project;
import com.aneeshreddykota.repository.ProjectRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ProjectController {
	
	private static final String UPLOAD_DIR = "./images/";
	private ProjectRepository projectRepository;
	
	public ProjectController() {
		
	}
	
	@Autowired
	public ProjectController(ProjectRepository projectRepository) {
		this.projectRepository=projectRepository;
	}
	
	@GetMapping("/projects")
	public @ResponseBody List<Project> getAllProjects(){
		List<Project> projects = projectRepository.findAll();
		return projects;
	}
	
	@PostMapping("/project")
    public ResponseEntity<Project> uploadProject(
    		 @RequestParam("projectName") String projectName,
             @RequestParam("description") String description,
             @RequestParam("githubUrl") String githubUrl,
             @RequestParam("liveUrl") String liveUrl,
             @RequestParam("overview") String overview,
             @RequestParam("imageName") MultipartFile image // Receive the image file
    ) throws IOException {

        if (image.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

     // Generate a unique filename using UUID to prevent conflicts
        String originalFileName = image.getOriginalFilename();
        String fileExtension = ""; 

        // If the file has an extension, extract it
        if (originalFileName != null && originalFileName.contains(".")) {
            fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        }

        // Generate a unique file name with UUID
        String fileName = UUID.randomUUID().toString() + fileExtension;
        
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(image.getInputStream(), filePath);

        Project project = new Project();
        project.setProjectName(projectName);
        project.setDescription(description);
        project.setGithubUrl(githubUrl);
        project.setLiveUrl(liveUrl);
        project.setOverview(overview);
        project.setImageName(fileName);

        Project newProject = projectRepository.save(project);
        return new ResponseEntity<>(newProject, HttpStatus.CREATED); 
    }
	
	@GetMapping("/images/{filename}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        try {
            // Construct file path
            Path file = Paths.get(UPLOAD_DIR).resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                // Set Content-Disposition header to indicate a downloadable file
                return ResponseEntity.ok()
                                     .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                                     .body(resource);
            } else {
                // File not found or not readable
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            // Handle malformed URL exception
            return ResponseEntity.badRequest().build();
        } catch (IOException e) {
            // Handle general I/O error
            return ResponseEntity.status(500).build();
        }
    }
	
	@GetMapping("/project/{id}")
	public @ResponseBody Optional<Project> getProjectById(@PathVariable int id) {
		Optional<Project> project = projectRepository.findById(id);
		return project;
	}
	
	@DeleteMapping("/project/{id}")
	public @ResponseBody void removeProjectById(@PathVariable int id) {
		projectRepository.deleteById(id);
	}


}
