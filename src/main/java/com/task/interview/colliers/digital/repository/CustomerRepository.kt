package com.task.interview.colliers.digital.repository

import com.task.interview.colliers.digital.document.Customer
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : MongoRepository<Customer?, Long?>