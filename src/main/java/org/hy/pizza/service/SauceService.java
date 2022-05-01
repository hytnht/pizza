package org.hy.pizza.service;

import org.hy.pizza.model.Sauce;

import java.util.List;

public interface SauceService {
    List<Sauce> findAll();
    Sauce findById(Long id);
}
