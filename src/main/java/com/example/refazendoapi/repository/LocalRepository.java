package com.example.refazendoapi.repository;

import com.example.refazendoapi.model.Local;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface LocalRepository extends CrudRepository<Local, UUID> {
}
