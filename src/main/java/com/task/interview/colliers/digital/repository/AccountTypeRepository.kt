package com.task.interview.colliers.digital.repository

import com.task.interview.colliers.digital.document.AccountType
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountTypeRepository : MongoRepository<AccountType?, Long?>