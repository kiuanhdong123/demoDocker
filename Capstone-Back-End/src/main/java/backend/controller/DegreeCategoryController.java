package backend.controller;

import backend.entity.DegreeCategory;
import backend.service.DegreeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/category/degree")
public class DegreeCategoryController {

    @Autowired
    private DegreeCategoryService service;

    @GetMapping(value = "")
    public ResponseEntity<?> getAll() {
        try {
            List<DegreeCategory> listDegreeCategory = service.getAll();
            if(listDegreeCategory.isEmpty()){
                return new ResponseEntity<>("List category is empty.", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(listDegreeCategory, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String pv) {
        try {
            int id = Integer.parseInt(pv);
            DegreeCategory c = service.getById(id);
            if(c==null){
                return new ResponseEntity<>("Category not found.", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(c, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> create(@RequestBody DegreeCategory degreeCategory) {
        try {
            DegreeCategory t = service.save(degreeCategory);
            if(t==null){
                return new ResponseEntity<>("Type of degree already exist.", HttpStatus.EXPECTATION_FAILED);
            }
            return new ResponseEntity<>("Add successful.", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String pv, @RequestBody DegreeCategory degreeCategory) {
        try {
            int id = Integer.parseInt(pv);
            degreeCategory.setId(id);
            DegreeCategory t = service.save(degreeCategory);
            if(t==null){
                return new ResponseEntity<>("Type of degree already exist.", HttpStatus.EXPECTATION_FAILED);
            }
            return new ResponseEntity<>("Update successfully", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String pv) {
        try {
            int id = Integer.parseInt(pv);
            service.delete(id);
            return new ResponseEntity<>("Delete successful.", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
