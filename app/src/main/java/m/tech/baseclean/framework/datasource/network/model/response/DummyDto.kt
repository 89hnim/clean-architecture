package m.tech.baseclean.framework.datasource.network.model.response

import com.google.gson.annotations.SerializedName
import m.tech.baseclean.business.domain.DummyModel

data class DummyDto(
    @SerializedName("id") val id: Int?,
    @SerializedName("title") val title: String?,
    @SerializedName("body") val body: String?,
    @SerializedName("userId") val userId: Int?
)

fun DummyDto.mapToDomain(): DummyModel? {
    if (id == null) return null
    return DummyModel(
        id = id,
        name = title.orEmpty(),
        desc = body.orEmpty(),
    )
}

fun List<DummyDto>.mapToDomain() = mapNotNull { it.mapToDomain() }