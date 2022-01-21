package com.cohort5projectrest.Repositories;

import com.cohort5projectrest.Entities.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrganizationRepository extends JpaRepository<Organization, Integer>{
    Organization findByOrganizationId(int organizationId);
}
