package tn.esprit.paniermicroservice.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.paniermicroservice.dto.FormationDTO;

@FeignClient(name = "formationmicroservice", url = "http://localhost:8082") // Replace with Formation MS URL
public interface FormationClient {
     @GetMapping("/api/formations/get/{id}")
     FormationDTO getFormationById(@PathVariable("id") String id);
}