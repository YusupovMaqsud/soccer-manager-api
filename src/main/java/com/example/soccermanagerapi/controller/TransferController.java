package com.example.soccermanagerapi.controller;

import com.example.soccermanagerapi.entity.Transfer;
import com.example.soccermanagerapi.payload.ApiResponse;
import com.example.soccermanagerapi.payload.TransferDto;
import com.example.soccermanagerapi.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transfer")
public class TransferController {

    @Autowired
    TransferService transferService;

    @PreAuthorize(value = "hasAuthority('ADD_TRANSFER')")
    @PostMapping
    public HttpEntity<?> addTransfer(@RequestBody TransferDto transferDto){
        ApiResponse apiResponse = transferService.addTransfer(transferDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }


    @PreAuthorize(value = "hasAuthority('VIEW_MY_TRANSFER')")
    @GetMapping("/{id}")
    public Transfer getTransfer(@PathVariable Long id){
        Transfer transfer = transferService.getTransfer(id);
        return transfer;
    }


    @PreAuthorize(value = "hasAuthority(VIEW_TRANSFER)")
    @GetMapping
    public List<Transfer> getTransferes(){
        List<Transfer> transfers = transferService.getTransfers();
        return transfers;
    }


    @PreAuthorize(value = "hasAuthority('EDIT_TRANSFER')")
    @PutMapping("/{id}")
    public HttpEntity<?> editTransfer(@RequestBody TransferDto transferDto,Long id){
        ApiResponse apiResponse = transferService.editTransfer(transferDto, id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }


    @PreAuthorize(value = "hasAuthority('DELETE_TRANSFER')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteTransfer(@PathVariable Long id){
        ApiResponse apiResponse = transferService.deleteTransfer(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
