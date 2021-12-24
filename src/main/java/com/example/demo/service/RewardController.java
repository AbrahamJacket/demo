package com.example.demo.service;

import com.example.demo.domain.Reward;
import com.example.demo.domain.RewardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class RewardController {

    private final RewardRepository rewardRepository;

    public RewardController(RewardRepository rewardRepository) {
        this.rewardRepository = rewardRepository;
    }

    @PostMapping("/reward")
    @ResponseStatus(HttpStatus.CREATED)
    public Reward saveReward(@RequestBody Reward reward) {
        return rewardRepository.save(reward);
    }

    @GetMapping("/reward")
    @ResponseStatus(HttpStatus.OK)
    public List<Reward> getAllRewards() {
        return rewardRepository.findAll();
    }

    @GetMapping("/reward/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Reward getRewardById(@PathVariable Integer id) {

        Reward reward = rewardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reward not found with id = " + id));

        return reward;
    }

    @PutMapping("/reward/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Reward refreshReward(@PathVariable("id") Integer id, @RequestBody Reward reward) {

        return rewardRepository.findById(id)
                .map(entity -> {
                    entity.setTitle(reward.getTitle());
                    entity.setYear(reward.getYear());
                    return rewardRepository.save(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Reward not found with id = " + id));
    }

    @DeleteMapping("/reward/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeRewardById(@PathVariable Integer id) {
        Reward reward = rewardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reward not found with id = " + id));
        rewardRepository.delete(reward);
    }

    @DeleteMapping("/reward")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllRewards() {
        rewardRepository.deleteAll();
    }
}
