package com.dio.controledepontoeacesso.service;

import com.dio.controledepontoeacesso.dto.BancoHorasDTO;
import com.dio.controledepontoeacesso.exception.NoSuchElementException;
import com.dio.controledepontoeacesso.exception.NotFoundException;
import com.dio.controledepontoeacesso.exception.RelationshipNotFoundException;
import com.dio.controledepontoeacesso.mapper.BancoHorasMapper;
import com.dio.controledepontoeacesso.model.BancoHoras;
import com.dio.controledepontoeacesso.repository.BancoHorasRepository;
import com.dio.controledepontoeacesso.repository.BancoHorasRepository;
import com.dio.controledepontoeacesso.response.BancoHorasResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BancoHorasService {
    @Autowired
    BancoHorasRepository bancoHorasRepository;

    @Autowired
    BancoHorasMapper bancoHorasMapper;

    public BancoHorasDTO saveBancoHoras(BancoHorasDTO bancoHoras){
        return bancoHorasMapper.toBancoHorasDTO(
                bancoHorasRepository.save(
                        bancoHorasMapper.toBancoHoras(bancoHoras)
                )
        );
    }

    public List<BancoHorasDTO> findAll() {
        return bancoHorasMapper.toBancoHorasDTOs(bancoHorasRepository.findAll());
    }

    public BancoHorasDTO getById(Long idBancoHoras) throws NotFoundException {
        return bancoHorasRepository.findById(idBancoHoras)
                .map(bancoHorasMapper::toBancoHorasDTO)
                .orElseThrow(() -> new NotFoundException(BancoHorasResponse.ENTITY_NOT_FOUND));
    }

    public BancoHorasDTO updateBancoHoras(BancoHorasDTO bancoHoras) throws NotFoundException {
        var bancoHorasToBeUpdated = bancoHorasRepository.findById(bancoHoras.getBancoHorasId().getIdBancoHoras());

        if(bancoHorasToBeUpdated.isEmpty()) {
            throw new NotFoundException(BancoHorasResponse.ENTITY_NOT_FOUND);
        }

        return bancoHorasMapper.toBancoHorasDTO(
                bancoHorasRepository.save(
                        bancoHorasMapper.toBancoHoras(bancoHoras)
                )
        );
    }

    public void deleteBancoHoras(Long idBancoHoras) {
        bancoHorasRepository.deleteById(idBancoHoras);
    }
}
