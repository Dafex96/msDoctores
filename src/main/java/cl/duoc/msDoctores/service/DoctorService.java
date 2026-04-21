package cl.duoc.msDoctores.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.duoc.msDoctores.model.Doctor;
import cl.duoc.msDoctores.repository.DoctorRepository;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> listarDoctores(){
        return doctorRepository.findAll();
    }

    public Doctor buscarPorId(Integer id){
        return doctorRepository.findById(id).orElseThrow(() -> new RuntimeException("Doctor no encontrado..."));
    }

    public Doctor buscarPorRut(String rut){
        return doctorRepository.findByRut(rut).orElseThrow(() -> new RuntimeException("Doctor no encontrado..."));
    }

    public void eliminarDoctor(Integer id){
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
        }else{
            throw new RuntimeException("Doctor no encontrado...");
        }
    }

    public Doctor agregarDoctor(Doctor doctor){
        return doctorRepository.save(doctor);
    }

    public Doctor actualizaDoctor(Integer id, Doctor doctorActualizado){

        Doctor doctorAnt = doctorRepository.findById(id).orElseThrow(() -> new RuntimeException("Doctor no encontrado..."));

        doctorAnt.setNombre(doctorActualizado.getNombre());
        doctorAnt.setRut(doctorActualizado.getRut());

        return doctorRepository.save(doctorAnt);
    }


}
