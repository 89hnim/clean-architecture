package m.tech.baseclean.framework.datasource.network.api

import m.tech.baseclean.framework.datasource.network.api.NetworkConstants.POSTS
import m.tech.baseclean.framework.datasource.network.model.response.DummyNetworkEntity
import retrofit2.http.GET

interface DummyApi {

    @GET(POSTS)
    suspend fun getDummies(): List<DummyNetworkEntity>

    //example of post body
//    @POST(POSTS)
//    suspend fun postDummies(
//        @Body fakeRequest: DummyFakeRequest
//    )

}