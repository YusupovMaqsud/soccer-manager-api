package com.example.soccermanagerapi.service;

import com.example.soccermanagerapi.entity.Team;
import com.example.soccermanagerapi.entity.Transfer;
import com.example.soccermanagerapi.payload.ApiResponse;
import com.example.soccermanagerapi.payload.TransferDto;
import com.example.soccermanagerapi.repository.TeamRepository;
import com.example.soccermanagerapi.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransferService {
    @Autowired
    TransferRepository transferRepository;
    @Autowired
    TeamRepository teamRepository;



    public ApiResponse addTransfer(TransferDto transferDto) {
        boolean exists = transferRepository.existsByTransferred(transferDto.isTransferred());
        if (exists) {
            return new ApiResponse("Bunday transfer bo'lgan", false);
        }

        Optional<Team> optionalTeamFrom = teamRepository.findById(transferDto.getTransferredFromId());
        if (!optionalTeamFrom.isPresent()) {
            return new ApiResponse("Bunday taransferredFrom mavjud emas", false);
        }

        Optional<Team> optionalTeamTo = teamRepository.findById(transferDto.getTransferredToId());
        if (optionalTeamTo.isPresent()) {
            return new ApiResponse("Bunday taransferredTo mavjud emas", false);
        }

        Transfer transfer = new Transfer(
                transferDto.getMarketValue(),
                optionalTeamFrom.get(),
                optionalTeamTo.get(),
                true
        );

        transferRepository.save(transfer);
        return new ApiResponse("Transfer added", true);
    }





    public Transfer getTransfer(Long id) {
        Optional<Transfer> optionalTransfer = transferRepository.findById(id);
        return optionalTransfer.get();
    }




    public List<Transfer> getTransfers() {
        List<Transfer> transfers = transferRepository.findAll();
        return transfers;
    }




    public ApiResponse editTransfer(TransferDto transferDto, Long id) {
        Optional<Transfer> optionalTransfer = transferRepository.findById(id);
        if(!optionalTransfer.isPresent()){
            return new ApiResponse("Bunday transfer mavjud emas",false);
        }

        Optional<Team> optionalTeamFrom = teamRepository.findById(transferDto.getTransferredFromId());
        if (!optionalTeamFrom.isPresent()) {
            return new ApiResponse("Bunday taransferredFrom mavjud emas", false);
        }

        Optional<Team> optionalTeamTo = teamRepository.findById(transferDto.getTransferredToId());
        if (optionalTeamTo.isPresent()) {
            return new ApiResponse("Bunday taransferredTo mavjud emas", false);
        }

        Transfer transfer = optionalTransfer.get();
        transfer.setMarketValue(transferDto.getMarketValue());
        transfer.setTransferredFrom(optionalTeamFrom.get());
        transfer.setTransferredTo(optionalTeamTo.get());
        transfer.setTransferred(true);
        transferRepository.save(transfer);
        return new ApiResponse("Transfer edit qilindi",true);
    }




    public ApiResponse deleteTransfer(Long id) {
        try {
            transferRepository.deleteById(id);
            return new ApiResponse("Tranfer deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Tranfer not deleted", false);
        }
    }
}
