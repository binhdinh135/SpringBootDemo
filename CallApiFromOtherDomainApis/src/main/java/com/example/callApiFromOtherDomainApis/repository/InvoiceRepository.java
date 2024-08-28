package com.example.callApiFromOtherDomainApis.repository;

import com.example.callApiFromOtherDomainApis.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}
