package tn.esprit.paniermicroservice.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "formationmicroservice", url = "http://localhost:8082") // Replace with Formation MS URL
public interface FormationClient {

//    @GetMapping("/formations/{id}")
//    Formation getFormationById(@PathVariable("id") String id);
}