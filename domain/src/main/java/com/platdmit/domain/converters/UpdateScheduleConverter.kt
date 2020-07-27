package com.platdmit.domain.converters

interface UpdateScheduleConverter<DbModel, DomainModel> {
    fun fromDbToDomain(dbModel: DbModel) : DomainModel
}