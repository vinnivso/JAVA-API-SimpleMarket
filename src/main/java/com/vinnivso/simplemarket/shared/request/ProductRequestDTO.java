package com.vinnivso.simplemarket.shared.request;

import java.util.UUID;

public record ProductRequestDTO(String name, String brand ,Integer quantity, Double price) {}
