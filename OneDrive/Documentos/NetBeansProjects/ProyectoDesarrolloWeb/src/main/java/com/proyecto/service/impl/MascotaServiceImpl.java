/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.service.impl;


import com.proyecto.dao.MascotaDao;
import com.proyecto.domain.Mascota;
import com.proyecto.service.MascotaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MascotaServiceImpl implements MascotaService {

    @Autowired
    private MascotaDao mascotaDao;

    @Override
    public List<Mascota> getMascotas() {
        return mascotaDao.findAll();
    }

    @Override
    public Mascota getMascota(Long idMascota) {
        return mascotaDao.findById(idMascota).orElse(null);
    }

    @Override
    public void save(Mascota mascota) {
        mascotaDao.save(mascota);
    }

    @Override
    public void delete(Long idMascota) {
        mascotaDao.deleteById(idMascota);
    }
}