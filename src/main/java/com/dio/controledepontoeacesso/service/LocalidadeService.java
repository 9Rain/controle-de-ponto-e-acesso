package com.dio.controledepontoeacesso.service;

import com.dio.controledepontoeacesso.exception.NoSuchElementException;
import com.dio.controledepontoeacesso.exception.RelationshipNotFoundException;
import com.dio.controledepontoeacesso.mapper.NivelAcessoMapper;
import com.dio.controledepontoeacesso.model.Localidade;
import com.dio.controledepontoeacesso.repository.LocalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocalidadeService {
    @Autowired
    LocalidadeRepository localidadeRepository;

    @Autowired
    NivelAcessoService nivelAcessoService;

    @Autowired
    NivelAcessoMapper nivelAcessoMapper;

    public Localidade saveLocalidade(Localidade localidade) {
        var relatedAccessLevel = nivelAcessoService.getById(localidade.getNivelAcessoId());

//        if(relatedAccessLevel.isEmpty()) {
//            throw new RelationshipNotFoundException();
//        }

//        localidade.setNivelAcesso(relatedAccessLevel.get());
        localidade.setNivelAcesso(nivelAcessoMapper.toNivelAcesso(relatedAccessLevel));

        return localidadeRepository.save(localidade);
    }

    public List<Localidade> findAll() {
        return localidadeRepository.findAll();
    }

    public Optional<Localidade> getById(Long idLocalidade) {
        return localidadeRepository.findById(idLocalidade);
    }

    public Localidade updateLocalidade(Localidade localidade) throws NoSuchElementException {
        var localidadeToBeUpdated = this.getById(localidade.getId());

        if(localidadeToBeUpdated.isEmpty()) {
            throw new NoSuchElementException();
        }

        localidade.setNivelAcesso(localidadeToBeUpdated.get().getNivelAcesso());

        return localidadeRepository.save(localidade);
    }

    public void deleteLocalidade(Long idLocalidade) {
        localidadeRepository.deleteById(idLocalidade);
    }
}
