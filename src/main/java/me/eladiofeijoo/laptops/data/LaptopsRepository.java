package me.eladiofeijoo.laptops.data;

import me.eladiofeijoo.laptops.models.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaptopsRepository extends JpaRepository<Laptop, Long> {
}
