package ual.modyst.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ual.modyst.response.AuthenticationResponse;
import ual.modyst.response.RequestResponse;
import ual.modyst.response.UserResponse;
import ual.modyst.response.UsersResponse;

public interface RESTInterface {
    @GET("authenticate/{email}/{password}/{registration_key}")
    Call<AuthenticationResponse> authenticate(@Path("email") String email, @Path("password") String password, @Path("registration_key") String registration_key);

    @GET("register/{full_name}/{email}/{password}/{sex}/{phone_number}/{address}")
    Call<RequestResponse> register(@Path("full_name") String full_name, @Path("email") String email, @Path("password") String password, @Path("sex") String sex, @Path("phone_number") String phone_number, @Path("address") String address);

    @GET("getuser/{unique_id}")
    Call<UserResponse> getUser(@Path("unique_id") int unique_id);

    @GET("getusers")
    Call<UsersResponse> getUsers();
}
