package com.vivek.fitness.slot.booking.service;

import com.vivek.fitness.slot.booking.dao.Database;
import com.vivek.fitness.slot.booking.model.FitnessClass;
import com.vivek.fitness.slot.booking.model.FitnessClassType;

import java.util.Map;
import java.util.stream.Collectors;

public class FitnessClassService {

    public Map<FitnessClassType, FitnessClass> getAvailableClasses() {
        return Database.FITNESS_CLASSES.entrySet().stream().collect(Collectors.toMap(entry -> entry.getValue().getType(), Map.Entry::getValue));
    }

}
