package com.task.interview.colliers.digital.config

import com.task.interview.colliers.digital.command.datainit.InitDocumentCommand
import com.task.interview.colliers.digital.command.datainit.enums.DocumentInitType
import com.task.interview.colliers.digital.command.datainit.factory.DocumentInitFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.annotation.PostConstruct

@Component
open class DataLoader @Autowired constructor(private val documentInitFactory: DocumentInitFactory) : ApplicationRunner {

    private var documentInitCommandSteps: Map<Int, InitDocumentCommand> = TreeMap()

    @PostConstruct
    private fun initStepsMap() {
        documentInitCommandSteps = mapOf(
                1 to documentInitFactory.resolve(DocumentInitType.ACCOUNT_TYPE),
                2 to documentInitFactory.resolve(DocumentInitType.CUSTOMER),
                3 to documentInitFactory.resolve(DocumentInitType.TRANSACTION)
        )
    }

    @Transactional
    override fun run(args: ApplicationArguments) {
        documentInitCommandSteps.values
                .forEach { obj: InitDocumentCommand -> obj.execute() }
    }

}