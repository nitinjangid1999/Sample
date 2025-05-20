package com.example.sample.usecases

import android.app.Application
import com.bureau.base.Environment
import com.bureau.base.models.BureauConfig
import com.bureau.devicefingerprint.BureauAPI
import com.bureau.devicefingerprint.models.ErrorResponse
import com.bureau.devicefingerprint.models.SubmitResponse
import com.bureau.devicefingerprint.tools.DataCallback
import java.util.UUID

class SendEventToBureau(
    private val application: Application,
) {

    fun invoke() {
        val eventId = UUID.randomUUID().toString()

        val bureauConfig = BureauConfig(
            credentialId = "", // Enter your own credential id
            eventId = eventId,
            environment = Environment.ENV_SANDBOX,
            application = application,
        )

        BureauAPI.init(bureauConfig)
        BureauAPI.setUserId(UUID.randomUUID().toString())

        BureauAPI.submit(
            object : DataCallback {
                override fun onError(errorMessage: ErrorResponse) {
                    println("Error in sending event ${errorMessage.errorCode} -  ${errorMessage.message}")
                }

                override fun onResult(message: SubmitResponse) {
                    println("Event is recorde successfully ${message.message}")
                }
            },
        )
    }
}