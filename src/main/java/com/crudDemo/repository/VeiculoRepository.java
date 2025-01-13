package com.crudDemo.repository;

import com.crudDemo.domain.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Integer>{


    Veiculo findByPlaca(String placa);

}

