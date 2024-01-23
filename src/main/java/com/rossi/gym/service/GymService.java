package com.rossi.gym.service;

import com.rossi.gym.entity.Gym;
import com.rossi.gym.repository.GymRepository;
import com.rossi.gym.service.exceptions.Database;
import com.rossi.gym.service.exceptions.NotFound;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GymService {

    @Autowired
    private GymRepository gymRepository;

    public List<Gym> findAll() {
        return gymRepository.findAll();
    }

    public Gym findById(UUID id) {
        Optional<Gym> gym = gymRepository.findById(id);
        return gym.orElseThrow(() -> new NotFound(id));
    }

    public Gym insert(Gym gym) {
        return gymRepository.save(gym);
    }

    public void delete(UUID id) {
        try {
            gymRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFound(id);
        } catch (DataIntegrityViolationException e) {
            throw new Database(e.getMessage());
        }
    }

    public Gym update(UUID id, Gym gym) {
        try {
            Gym entity = gymRepository.getReferenceById(id);
            updateData(entity, gym);
            return gymRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new NotFound(id);
        }
    }

    private void updateData(Gym entity, Gym gym) {
        entity.setNome(gym.getNome());
        entity.setDescricao(gym.getDescricao());
        entity.setImagem(gym.getImagem());
        entity.setSerie(gym.getSerie());
        entity.setRepeticao(gym.getRepeticao());
    }
}
