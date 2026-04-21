package cl.duoc.msDoctores.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cl.duoc.msDoctores.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer>{

    Optional<Doctor> findByRut(String rut);
}
