package com.example.demo.repositories;

import com.example.demo.entities.Pelicula;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

    public Pelicula findByNombreAndSalaId(String nombre, Long salaId);

    public long countByFechaPublicacionBefore(LocalDate fechaPublicacion);

    public List<Pelicula> findBySalaNombre(String nombreSala);
}
