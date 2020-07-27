package com.platdmit.data.converters

import com.platdmit.data.database.entity.DbUpdateSchedule
import com.platdmit.domain.converters.UpdateScheduleConverter
import com.platdmit.domain.models.UpdateSchedule
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