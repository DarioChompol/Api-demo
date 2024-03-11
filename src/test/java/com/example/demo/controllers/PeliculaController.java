package com.example.demo.controllers;

import com.example.demo.entities.Pelicula;
import com.example.demo.services.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {
    @Autowired
    private PeliculaService peliculaService;

    @GetMapping
    public ResponseEntity<List<Pelicula>> getAllPeliculas() {
        List<Pelicula> peliculas = peliculaService.getAllPeliculas();
        return new ResponseEntity<>(peliculas, HttpStatus.OK);
    }

    @PostMapping("/agregar")
    public ResponseEntity<Pelicula> agregarPelicula(@RequestBody Pelicula pelicula) {
        Pelicula nuevaPelicula = peliculaService.agregarPelicula(pelicula);
        return new ResponseEntity<>(nuevaPelicula, HttpStatus.CREATED);
    }

    //endpoints para usar con postman
    //por nombre y sala
    @GetMapping("/buscar")
    public ResponseEntity<Pelicula> buscarPorNombreYSala(@RequestParam String nombre, @RequestParam Long salaId) {
        Pelicula pelicula = peliculaService.buscarPorNombreYSala(nombre, salaId);
        if (pelicula != null) {
            return new ResponseEntity<>(pelicula, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //por fecha
    @GetMapping("/contar")
    public ResponseEntity<Long> contarPeliculasPublicadasAntesDeFecha(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaPublicacion) {
        long cantidadPeliculas = peliculaService.contarPeliculasPublicadasAntesDeFecha(fechaPublicacion);
        return new ResponseEntity<>(cantidadPeliculas, HttpStatus.OK);
    }

    //estado de la sala
    @GetMapping("/sala/{nombreSala}/estado")
    public ResponseEntity<String> verificarEstadoSala(@PathVariable String nombreSala) {
        String estadoSala = peliculaService.verificarEstadoSala(nombreSala);
        return new ResponseEntity<>(estadoSala, HttpStatus.OK);
    }

    
}
