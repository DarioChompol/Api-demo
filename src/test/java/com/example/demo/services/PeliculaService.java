package com.example.demo.services;

import com.example.demo.entities.Pelicula;
import com.example.demo.repositories.PeliculaRepository;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    public Pelicula buscarPorNombreYSala(String nombre, Long salaId) {
        return peliculaRepository.findByNombreAndSalaId(nombre, salaId);
    }

    public List<Pelicula> getAllPeliculas() {
        return peliculaRepository.findAll();
    }

    public Pelicula addPelicula(Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    public long contarPeliculasPublicadasAntesDeFecha(LocalDate fechaPublicacion) {
        return peliculaRepository.countByFechaPublicacionBefore(fechaPublicacion);

    }

    public String verificarEstadoSala(String nombreSala) {
        List<Pelicula> peliculas = peliculaRepository.findBySalaNombre(nombreSala);
        int cantidadPeliculas = peliculas.size();

        if (cantidadPeliculas < 3) {
            return "Sala casi vacía";
        } else if (cantidadPeliculas >= 3 && cantidadPeliculas <= 5) {
            return "Sala casi llena";
        } else {
            return "Sala llena";
        }
    }

    public Pelicula agregarPelicula(Pelicula pelicula) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

}
