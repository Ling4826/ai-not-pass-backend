package se331.passport.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se331.passport.service.LevelService;
import se331.passport.util.PassportMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import se331.passport.entity.Level;
import se331.passport.entity.Benefit;
import se331.passport.entity.BenefitDTO;
import se331.passport.entity.ServiceItem;
import se331.passport.entity.ServiceItemDTO;
import se331.passport.entity.LevelDTO;

@RestController
@RequiredArgsConstructor
public class LevelController {
    final LevelService levelService;

    @GetMapping("/levels")
    public ResponseEntity<?> getLevels() {
        return ResponseEntity.ok(PassportMapper.INSTANCE.getLevelDto(levelService.getLevels()));
    }

    @GetMapping("/levels/{id}")
    public ResponseEntity<?> getLevel(@PathVariable("id") Long id) {
        Level output = levelService.getLevel(id);
        if (output == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
        return ResponseEntity.ok(PassportMapper.INSTANCE.getLevelDto(output));
    }

    @PostMapping("/levels/{id}/benefits")
    public ResponseEntity<?> addBenefit(@PathVariable("id") Long id, @RequestBody BenefitDTO benefitDTO) {
        Benefit output = levelService.addBenefit(id, benefitDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(PassportMapper.INSTANCE.getBenefitDto(output));
    }

    @PutMapping("/benefits/{id}")
    public ResponseEntity<?> updateBenefit(@PathVariable("id") Long id, @RequestBody BenefitDTO benefitDTO) {
        Benefit output = levelService.updateBenefit(id, benefitDTO);
        return ResponseEntity.ok(PassportMapper.INSTANCE.getBenefitDto(output));
    }

    @PostMapping("/levels/{id}/services")
    public ResponseEntity<?> addServiceItem(@PathVariable("id") Long id, @RequestBody ServiceItemDTO serviceItemDTO) {
        ServiceItem output = levelService.addServiceItem(id, serviceItemDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(PassportMapper.INSTANCE.getServiceItemDto(output));
    }

    @PutMapping("/services/{id}")
    public ResponseEntity<?> updateServiceItem(@PathVariable("id") Long id, @RequestBody ServiceItemDTO serviceItemDTO) {
        ServiceItem output = levelService.updateServiceItem(id, serviceItemDTO);
        return ResponseEntity.ok(PassportMapper.INSTANCE.getServiceItemDto(output));
    }

    @PutMapping("/levels/{id}/pass-criteria")
    public ResponseEntity<?> updatePassCriteria(@PathVariable("id") Long id, @RequestBody LevelDTO levelDTO) {
        Level output = levelService.updatePassCriteria(id, levelDTO.getPassCriteria());
        return ResponseEntity.ok(PassportMapper.INSTANCE.getLevelDto(output));
    }

    @DeleteMapping("/benefits/{id}")
    public ResponseEntity<?> deleteBenefit(@PathVariable("id") Long id) {
        levelService.deleteBenefit(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/services/{id}")
    public ResponseEntity<?> deleteServiceItem(@PathVariable("id") Long id) {
        levelService.deleteServiceItem(id);
        return ResponseEntity.noContent().build();
    }
}