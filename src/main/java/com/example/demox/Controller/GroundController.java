package com.example.demox.Controller;

import com.example.demox.Pojo.GroundPojo;
import com.example.demox.entity.Ground;
import com.example.demox.service.GroundService;
import com.example.demox.shared.pojo.GlobalApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ground")
@RequiredArgsConstructor

public class GroundController {

    private final GroundService groundService;

    @GetMapping
    public GlobalApiResponse<List<Ground>> getData() {
        return GlobalApiResponse.
                <List<Ground>>builder()
                .data(this.groundService.getAll())
                .statusCode(200)
                .message("Successfully fetched")
                .build();
    }

    @PostMapping
    public GlobalApiResponse<String> save(@RequestBody GroundPojo groundPojo) {
        this.groundService.saveData(groundPojo);
        return GlobalApiResponse.
                <String>builder()
                .data("saved")
                .statusCode(200)
                .message("Successfully saved")
                .build();
    }

    @PutMapping
    public GlobalApiResponse <Ground> update(@RequestBody GroundPojo groundPojo) {
        Ground updateGround = this.groundService.saveData(groundPojo);
        return  GlobalApiResponse.<Ground>builder()
                .data(updateGround)
                .statusCode(200)
                .message("Successfully updated")
                .build();
    }

    @DeleteMapping("delete/{id}")
    public GlobalApiResponse<String> delete(@PathVariable int id) {
        this.groundService.deleteById(id);
        return GlobalApiResponse.<String>builder()
                .data("Ground entity with  ID " + id + "delete successfully")
                .statusCode(200)
                .message("Successfully deleted")
                .build() ;

    }


}


