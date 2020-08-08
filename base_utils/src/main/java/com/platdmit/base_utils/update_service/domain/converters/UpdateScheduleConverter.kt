package com.platdmit.base_utils.update_service.domain.converters

interface UpdateScheduleConverter<DbModel, DomainModel> {
    fun fromDbToDomain(dbModel: DbModel) : DomainModel
}