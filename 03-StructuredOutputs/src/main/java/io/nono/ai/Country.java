package io.nono.ai;

import java.util.List;


public record  Country(
    String name,
    String capital,
    String region,
    String subregion,
    long population,
    String area,
    List<String> languages,
    List<String> currencies,
    String flag) {}

    