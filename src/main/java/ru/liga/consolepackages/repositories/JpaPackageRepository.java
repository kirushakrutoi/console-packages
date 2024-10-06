package ru.liga.consolepackages.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.liga.consolepackages.models.Package;

import java.util.Optional;

@Repository
public interface JpaPackageRepository extends JpaRepository<Package, String> {
    Optional<Package> findByName(String name);
}
