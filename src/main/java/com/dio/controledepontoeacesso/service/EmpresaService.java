package com.dio.controledepontoeacesso.service;

import com.dio.controledepontoeacesso.exception.NoSuchElementException;
import com.dio.controledepontoeacesso.model.Empresa;
import com.dio.controledepontoeacesso.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {
    EmpresaRepository empresaRepository;

    @Autowired
    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public Empresa saveEmpresa(Empresa empresa){
        return empresaRepository.save(empresa);
    }

    public List<Empresa> findAll() {
        return empresaRepository.findAll();
    }

    public Optional<Empresa> getById(Long idEmpresa) {
        return empresaRepository.findById(idEmpresa);
    }

    public Empresa updateEmpresa(Empresa empresa) throws NoSuchElementException {
        var empresaToBeUpdated = this.getById(empresa.getId());

        if(empresaToBeUpdated.isEmpty()) {
            throw new NoSuchElementException();
        }

        return empresaRepository.save(empresa);
    }

    public void deleteEmpresa(Long idEmpresa) {
        empresaRepository.deleteById(idEmpresa);
    }
}
