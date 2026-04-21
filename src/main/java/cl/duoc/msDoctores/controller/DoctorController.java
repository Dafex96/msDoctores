package cl.duoc.msDoctores.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cl.duoc.msDoctores.dto.DoctorDTO;
import cl.duoc.msDoctores.model.Doctor;
import cl.duoc.msDoctores.service.DoctorService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/doctores")
public class DoctorController {

    @Autowired
    private DoctorService service;

    @GetMapping
    public ResponseEntity<List<Doctor>> listarDoctores(){

        List<Doctor> listaDoctores = service.listarDoctores();
        if (listaDoctores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(listaDoctores);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Doctor> obtenerPorId(@PathVariable Integer id){
        try {
            Doctor doctor = service.buscarPorId(id);
            return ResponseEntity.ok(doctor);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<Doctor> obtenerPorRut(@PathVariable String rut){
        try {
            Doctor doctor = service.buscarPorRut(rut);
            return ResponseEntity.ok(doctor);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Doctor> guardar(@RequestBody Doctor doctor){
        Doctor nuevoDoctor = service.agregarDoctor(doctor);
        return ResponseEntity.ok(nuevoDoctor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> actualizar(@PathVariable Integer id, @RequestBody Doctor doctor){
        try {
            Doctor doctorActualizado = service.actualizaDoctor(id, doctor);
            return ResponseEntity.ok(doctorActualizado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        try {
            service.eliminarDoctor(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/dto/{id}")
    public ResponseEntity<DoctorDTO> buscarDTO(@PathVariable Integer id){
        try {
            Doctor doctor = service.buscarPorId(id);
            
            DoctorDTO dto = new DoctorDTO();

            dto.setId(doctor.getId());
            dto.setNombre(doctor.getNombre());
            dto.setEspecialidad(doctor.getEspecialidad().getNombre());

            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
}

