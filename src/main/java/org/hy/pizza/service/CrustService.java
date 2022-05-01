package org.hy.pizza.service;

import org.hy.pizza.model.Crust;

import java.util.List;

public interface CrustService {
    List<Crust> findAll();
    Crust findById(Long id);
}
