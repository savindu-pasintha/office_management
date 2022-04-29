package com.braavocreations.office_management.resources;

import com.braavocreations.office_management.domain.Sector;
import com.braavocreations.office_management.services.SectorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sector")
public class SectorResource {

    @Autowired
    SectorServices sectorServices;

    @GetMapping("")
    public ResponseEntity<List<Sector>> getAllSectors(HttpServletRequest request) {

        List<Sector> sectors = sectorServices.fetchAllSectors();
        return new ResponseEntity<>(sectors, HttpStatus.OK);
    }

    @GetMapping("/{sectorId}")
    public ResponseEntity<Sector> getSectorById(HttpServletRequest request,
                                                        @PathVariable("sectorId") Integer sectorId) {

        Sector sector = sectorServices.fetchSectorById(sectorId);
        return new ResponseEntity<>(sector, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Sector> addSector(HttpServletRequest request,
                                                    @RequestBody Map<String, Object> sectorMap) {

        String sectorName = (String) sectorMap.get("sector_name");
        Sector sector = sectorServices.addSector(sectorName);
        return new ResponseEntity<>(sector, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<Sector> updateSector(HttpServletRequest request,
                                            @RequestBody Map<String, Object> sectorMap) {

        Integer sectorId = (Integer) sectorMap.get("sector_id");
        String sectorName = (String) sectorMap.get("sector_name");
        Sector sector = sectorServices.updateSector(sectorId,sectorName);
        return new ResponseEntity<>(sector, HttpStatus.CREATED);
    }

    @DeleteMapping("/{sectorId}")
    public ResponseEntity<Map<String, Boolean>> deleteSector(HttpServletRequest request,
                                                               @PathVariable("sectorId") Integer sectorId) {

        sectorServices.removeSector(sectorId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
