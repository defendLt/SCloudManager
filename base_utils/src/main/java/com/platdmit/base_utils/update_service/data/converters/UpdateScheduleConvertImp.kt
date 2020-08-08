package com.platdmit.base_utils.update_service.data.converters

import com.platdmit.base_utils.update_service.data.room.entity.DbUpdateSchedule
import com.platdmit.base_utils.update_service.domain.converters.UpdateScheduleConverter
import com.platdmit.base_utils.update_service.domain.models.UpdateSchedule
import javax.inject.Inject

class UpdateScheduleConvertImp
@Inject
constructor() : UpdateScheduleConverter<DbUpdateSchedule, UpdateSchedule> {
    override fun fromDbToDomain(dbModel: DbUpdateSchedule): UpdateSchedule {
        return UpdateSchedule(
                dbModel.name,
                dbModel.nextUpdate,
                dbModel.updateInterval,
                dbModel.lastUpdate
        )
    }
}