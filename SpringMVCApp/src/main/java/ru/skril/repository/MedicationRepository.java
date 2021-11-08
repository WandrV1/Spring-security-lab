package ru.skril.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.skril.entity.Medication;

@Repository
public interface MedicationRepository extends CrudRepository<Medication, Integer> {
    Iterable<Medication> findAllBySubstanceStartingWith(String substance);
    Iterable<Medication> findAllByNameStartingWith(String name);
    Iterable<Medication> findAllByCountryStartingWith(String country);
    Iterable<Medication> findAllByPriceIsGreaterThanEqual(int value);
    Iterable<Medication> findAllByPriceIsLessThanEqual(int value);
    Iterable<Medication> findAllByPriceIs(int value);
    Iterable<Medication> findAllByRatingIsGreaterThanEqual(double value);
    Iterable<Medication> findAllByRatingIsLessThanEqual(double value);
    Iterable<Medication> findAllByRatingIs(double value);
}
