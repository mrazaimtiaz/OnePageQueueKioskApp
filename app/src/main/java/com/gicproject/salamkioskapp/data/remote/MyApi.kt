package com.gicproject.salamkioskapp.data.remote


import com.gicproject.salamkioskapp.data.remote.dto.*
import retrofit2.Response
import retrofit2.http.*

interface MyApi {
    @GET("api/getCivilIdAppointment")
    suspend fun getCivilIdAppointment(
        @Query("civilId")
        branchId: String,
    ): List<SelectServiceDto>?

    @GET("api/checkIsAppointment")
    suspend fun checkIsAppointment(
        @Query("serviceId")
        serviceId: String,
    ): List<SelectServiceDto>?

    @POST("api/BookTicket")
    suspend fun getBookTicket(
        @Query("serviceID")
        serviceID: String,
        @Query("IsHandicap")
        isHandicap: Boolean,
        @Query("isVip")
        isVip: Boolean,
        @Query("languageID")
        languageID: String,
        @Query("AppointmentCode")
        appointmentCode: String,
        @Query("isaapt")
        isaapt: Boolean,
        @Query("refid")
        refid: String,
        @Query("DoctorServiceID")
        DoctorServiceID: String,
    ): List<BookTicketDto>?

    @GET("api/GetTicket")
    suspend fun getTicket(@Query("QueueID") QueueID: Int, @Query("language") language: Int): List<GetTicketDto>?


    @GET("api/GetServices")
    suspend fun getSelectServices(
        @Query("branchid")
        branchId: String,
        @Query("DeptParentID")
        deptId: String,
    ): List<SelectServiceDto>?


    @GET("api/BranchDept")
    suspend fun getSelectDepartments(
        @Query("branchid")
        branchId: String,
        @Query("DeptParentID")
        DeptParentID: String,
    ): List<SelectDepartmentDto>?

    @GET("api/dept")
    suspend fun getDepartments(
    ): List<DepartmentDto>?

    @GET("api/Counters")
    suspend fun getCounters(
        @Query("branchid")
        branchId: String,
    ): List<CounterDto>?

    @GET("api/getbranches")
    suspend fun getBranches(
    ): List<BranchDto>?



}