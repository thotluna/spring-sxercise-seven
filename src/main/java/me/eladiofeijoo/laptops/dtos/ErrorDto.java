package me.eladiofeijoo.laptops.dtos;

import java.time.LocalDate;

public record ErrorDto(LocalDate localDate, int statusCode, String message, String path) {}
