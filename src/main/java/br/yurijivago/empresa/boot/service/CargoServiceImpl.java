package br.yurijivago.empresa.boot.service;

import br.yurijivago.empresa.boot.dao.CargoDao;
import br.yurijivago.empresa.boot.dao.DepartamentoDao;
import br.yurijivago.empresa.boot.domain.Cargo;
import br.yurijivago.empresa.boot.domain.Departamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class CargoServiceImpl implements CargoService{

    @Autowired
    private CargoDao dao;

    @Autowired
    private DepartamentoDao dptoDao;

    @Override
    public void salvar(Cargo cargo) {
        dao.save(cargo);
    }

    @Override
    public void editar(Cargo cargo) {
        dao.update(cargo);
    }

    @Override
    public void excluir(Long id) {
        dao.delete(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Cargo buscarPorId(Long id) {
        return dao.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Cargo> buscarTodos() {
        return dao.findAll();
    }
}
