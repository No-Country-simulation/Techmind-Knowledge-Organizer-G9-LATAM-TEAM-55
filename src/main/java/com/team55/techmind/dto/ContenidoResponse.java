package com.team55.techmind.dto;

import java.util.List;

public record ContenidoResponse(
        String categoria,
        double probabilidad,
        List<String> palabrasClave
) {}
