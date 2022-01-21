package com.cohort5projectrest.Services;

import com.cohort5projectrest.Entities.Organization;
import com.cohort5projectrest.Repositories.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrganizationService {

    private OrganizationRepository organizationRepository;

    @Autowired
    public OrganizationService(OrganizationRepository organizationRepository){
        this.organizationRepository = organizationRepository;
    }

    /*CREATE*/
    public void createOrganization(Organization organization){
        organizationRepository.save(organization);
    }

    /*READ*/
    public List<Organization> getOrganizations() {
        return organizationRepository.findAll();
    }

    public Organization getOrganizationById(int organizationId){
        //check if organization exists
        boolean exists = organizationRepository.existsById(organizationId);
        if (exists){
            return organizationRepository.findByOrganizationId(organizationId);
        } else throw new IllegalStateException("Organization with id " + organizationId + " doesn't exist");
    }
}
