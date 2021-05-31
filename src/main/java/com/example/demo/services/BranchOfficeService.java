package com.example.demo.services;

import com.example.demo.DTO.BranchOfficeDTO;
import com.example.demo.models.BranchOffice;
import com.example.demo.repositories.BranchOfficeRepository;
import org.apache.tomcat.jni.Address;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BranchOfficeService {

    @Autowired
    BranchOfficeRepository branchOfficeRepository;

   @Transactional(readOnly = true)
    public Page<BranchOffice> getAllBranchOffice(){
       List<BranchOffice> offices = branchOfficeRepository.findAll();
       int total = offices.size();
       return new PageImpl<>(offices, PageRequest.of(0, 10),total);
   }

   @Transactional(readOnly = true)
   public Optional<BranchOffice> getByName(String name){
      return branchOfficeRepository.findByName(name);
   }

   @Transactional
   public void save(BranchOffice office){
      branchOfficeRepository.save(office);
   }

   public BranchOffice createOffice(BranchOfficeDTO officeDTO){
      BranchOffice office = BranchOffice.builder()
         .address(officeDTO.getAddress())
         .name(officeDTO.getName())
         .latitude(officeDTO.getLatitude())
         .longitude(officeDTO.getLatitude())
         .build();

      save(office);
      return office;
   }



   @Transactional(readOnly = true)
   public Optional<BranchOffice> findByPoints(double latitude, double longitude){
      //Latitude is Y1 coordinate
      //Longitude is X1 coordinate

      List<BranchOffice> offices = branchOfficeRepository.findAll();
      if (offices.isEmpty())
         return Optional.empty();
      else{
         return Optional.of(calculateDistance(longitude, latitude, offices));
      }
   }

   public BranchOffice calculateDistance(double X1, double Y1, List<BranchOffice> offices){

      BranchOffice officeSelected = offices.get(0);
      double distance = Math.sqrt(Math.pow(X1-officeSelected.getLongitude(), 2) + Math.pow(Y1-officeSelected.getLatitude(),2));


      offices.stream().forEach(office -> {
         double distance2 = Math.sqrt(Math.pow(X1-office.getLongitude(), 2) + Math.pow(Y1-office.getLatitude(),2));
         if (distance2 < distance){
            officeSelected.setId(office.getId());
            officeSelected.setName(office.getName());
            officeSelected.setAddress(office.getAddress());
            officeSelected.setLatitude(office.getLatitude());
            officeSelected.setLongitude(office.getLongitude());
         }
      });
      return officeSelected;
   }


}
