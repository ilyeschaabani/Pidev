package tn.esprit.forummicroservice.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.forummicroservice.Entity.Reaction;
import tn.esprit.forummicroservice.Service.ReactionService;

import java.util.List;

@RestController
@RequestMapping("/api/reactions")
public class ReactionController {

    @Autowired
    private ReactionService reactionService;

    // Add a new reaction to a comment or event topic
    @PostMapping("/add")
    public Reaction addReaction(@RequestBody Reaction reaction) {
        return reactionService.addOrUpdateReaction(
                reaction.getEntityName(),
                reaction.getEntityId(),
                reaction.getUserId(),
                reaction.getEmoji()
        );
    }

    // Remove a reaction from a comment or event topic
    @DeleteMapping("/remove")
    public void removeReaction(@RequestBody Reaction reaction) {
        reactionService.removeReaction(
                reaction.getEntityName(),
                reaction.getEntityId(),
                reaction.getUserId(),
                reaction.getEmoji()
        );
    }


}

