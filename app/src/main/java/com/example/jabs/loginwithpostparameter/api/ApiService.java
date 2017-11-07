package com.example.jabs.loginwithpostparameter.api;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * @author Pratik Butani on 23/4/16.
 */
public interface ApiService {


    @Multipart
    @POST("doc_proof/")
    Call<ResponseBody> uploadFile(
            @Part("description") RequestBody description,
            @Part MultipartBody.Part file);

    // new code for multiple files
    @Multipart
    @POST("web_services_new/editprofile_doctor.php")
    Call<ResponseBody> uploadMultipleFiles(
            @Query("userid") String userid,
            @Query("name") String name,
            @Query("mobile") String mobile,
            @Query("email") String email,
            @Query("birth_date") String birth_date,
            @Query("gender") String gender,
            @Query("qualification") String qualification,
            @Query("qualification_other") String qualification_other,
            @Query("specialization") String specialization,
            @Query("specialization_other") String specialization_other,
            @Query("passingyear") String passingyear,
            @Query("registrationNumber") String registrationNumber,
            @Query("registration_council") String registration_council,
            @Query("registration_council_other") String registration_council_other,
            @Query("registrationyear") String registrationyear,
            @Query("yearOfExperiance") String yearOfExperiance,
            @Part MultipartBody.Part file1,
            @Part MultipartBody.Part file2,
            @Part MultipartBody.Part file3);
}
