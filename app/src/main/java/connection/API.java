package connection;

import model.UserMember;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
//    @GET("api_kontak/KontakController/{id}")
//    Call<UserMember> responseMember(@Query(value = "id") String id);

    @GET("api_kontak/kontak/")
    Call<UserMember> responseMember();

    @GET("api_kontak/kontak/pagination/{page}")
    Call<UserMember> responseArtikel(@Query(value = "page") String page);
}
