package io.rendecano.moneytreelight.common.domain.model

sealed class Failure(val exception: Exception = Exception("Failure")) {
    object None : Failure()
    object NetworkConnection : Failure()
    object ServerError : Failure()

    open class FeatureFailure(featureException: Exception = Exception("Feature failure")) : Failure(featureException)
}