package com.breezevasista.features.micro_learning.api

object MicroLearningRepoProvider {
    @JvmStatic
    fun microLearningRepoProvider(): MicroLearningRepo {
        return MicroLearningRepo(MicroLearningApi.create())
    }
}