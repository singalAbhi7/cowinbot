package com.cowin.vaccine

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.time.ExperimentalTime

private val test = "{\"centers\":[{\"center_id\":591585,\"name\":\"Anekal TH\",\"address\":\"Tali Road Opp Taluk Office\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Anekal\",\"pincode\":562106,\"lat\":12,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"d7d0f20b-0968-45fb-892b-a63517866807\",\"date\":\"10-05-2021\",\"available_capacity\":5,\"min_age_limit\":18,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]},{\"session_id\":\"30c14d33-6e81-4a03-b6b1-2fb9c218dd9c\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":18,\"vaccine\":\"COVISHIELD\",\"slots\":[\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-02:00PM\",\"02:00PM-04:00PM\"]}]},{\"center_id\":249069,\"name\":\"Hesaraghatta PHC\",\"address\":\"Near IIHR\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Bengaluru North\",\"pincode\":560088,\"lat\":13,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"68e40e3e-3e53-4aed-a8dd-75dc616b8e21\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]}]},{\"center_id\":249076,\"name\":\"KODATHI PHC\",\"address\":\"Ambedkarnagar\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Bengaluru East\",\"pincode\":560035,\"lat\":12,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"c2330286-8d64-46b2-8bda-c23a0bb5de1f\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]}]},{\"center_id\":249078,\"name\":\"Kumbalgodu PHC\",\"address\":\"Mysore Main Road\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Bengaluru South\",\"pincode\":560074,\"lat\":12,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"25fcf032-237b-4ecb-958c-c06e8def8af5\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]}]},{\"center_id\":249080,\"name\":\"Mahanthalingapura PHC\",\"address\":\"Harohalli Main Road\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Anekal\",\"pincode\":560083,\"lat\":12,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"40daf72e-1cc2-461e-ac05-a5c0b7819a18\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]}]},{\"center_id\":249073,\"name\":\"Kaggalipura CHC\",\"address\":\"Kanakapura Main Road\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Bengaluru South\",\"pincode\":560116,\"lat\":12,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"34245bdc-176d-4307-9d5c-80b89b45b7ab\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]}]},{\"center_id\":249077,\"name\":\"General Hospital K R Puram\",\"address\":\"Hosakote Main Road\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Bengaluru East\",\"pincode\":560036,\"lat\":13,\"long\":77,\"from\":\"11:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"edc9266d-4318-4cb3-b4c8-7ba4eee58d1f\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":18,\"vaccine\":\"COVISHIELD\",\"slots\":[\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-02:00PM\",\"02:00PM-04:00PM\"]},{\"session_id\":\"7d0be0e2-a8a5-468d-b4d5-96136481d0e9\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]}]},{\"center_id\":249053,\"name\":\"Dommasandra PHC\",\"address\":\"Sarjapura Main Road\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Anekal\",\"pincode\":562125,\"lat\":12,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"32124df6-f776-45a3-8204-7a1aa1448281\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]}]},{\"center_id\":249079,\"name\":\"Machohalli PHC Valmiki Bhavana\",\"address\":\"Valmiki Samudaya Bhavana Machohalli Colony\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Bengaluru North\",\"pincode\":560091,\"lat\":12,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"054f394a-160f-4500-9489-2fce1ef445a8\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]}]},{\"center_id\":249051,\"name\":\"Chikkabanavara PHC\",\"address\":\"Dwarakanagar\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Bengaluru North\",\"pincode\":560090,\"lat\":13,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"5dc2798b-5d27-417c-ae15-e57c6b6c2fed\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]}]},{\"center_id\":249067,\"name\":\"Haragadde PHC\",\"address\":\"Bannerghatta Main Road\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Anekal\",\"pincode\":560105,\"lat\":12,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"659b8d2a-c32d-4469-b679-512a61d292f4\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]}]},{\"center_id\":249048,\"name\":\"Bolare PHC\",\"address\":\"Kanakapura Main Road\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Bengaluru South\",\"pincode\":560116,\"lat\":12,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"5f51ae3a-e6c9-4acf-9f72-30811bf67792\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]}]},{\"center_id\":586940,\"name\":\"DCHC Leprosy Hospital\",\"address\":\"DCHC Leprosy Hospital\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Bengaluru North\",\"pincode\":560023,\"lat\":12,\"long\":77,\"from\":\"11:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"5c2e42d1-a27e-44b9-b003-7c75d25c147d\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":18,\"vaccine\":\"COVISHIELD\",\"slots\":[\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-02:00PM\",\"02:00PM-04:00PM\"]},{\"session_id\":\"549f721f-5b06-45eb-863e-0722b9ab4b8c\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]}]},{\"center_id\":249152,\"name\":\"Bettahalsuru PHC\",\"address\":\"Near Govt School Bettahalsuru Post\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Bengaluru North\",\"pincode\":562157,\"lat\":13,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"8da10a6d-00fc-4a75-a97a-d647b2aeae47\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]}]},{\"center_id\":573111,\"name\":\"Makali PHC 1\",\"address\":\"Alur Main Road\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Bengaluru North\",\"pincode\":562162,\"lat\":13,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"7d0e5c32-2258-4c9d-b569-2c560c0557d1\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]}]},{\"center_id\":249085,\"name\":\"Sarjapura PHC\",\"address\":\"Near Sarjapura Police Station\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Anekal\",\"pincode\":562125,\"lat\":12,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"ca648996-4ffe-4574-8073-50261eb2c2be\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]}]},{\"center_id\":249066,\"name\":\"Halanayakanahalli PHC\",\"address\":\"Sarjapura Main Road\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Bengaluru East\",\"pincode\":560035,\"lat\":12,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"a4cfc3b6-7fea-4416-8b09-53b719378966\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]}]},{\"center_id\":590616,\"name\":\"Yelahanka TH.\",\"address\":\"Yelahanka Old Town Behind Veeranjaneya Temple\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Bengaluru North\",\"pincode\":562157,\"lat\":13,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"4568147f-09a1-4b9a-a0d9-d81b9c318c64\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]},{\"session_id\":\"f88c0a69-87e0-499f-957f-8e73830ee771\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":18,\"vaccine\":\"COVISHIELD\",\"slots\":[\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-02:00PM\",\"02:00PM-04:00PM\"]}]},{\"center_id\":605619,\"name\":\"NIMHANS Hospitals\",\"address\":\"Lakkasandra Hombegowda Nagar\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Bengaluru South\",\"pincode\":560029,\"lat\":12,\"long\":77,\"from\":\"11:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"1d3614ea-72a9-46d4-b686-d2aa8631e9e4\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":18,\"vaccine\":\"COVISHIELD\",\"slots\":[\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-02:00PM\",\"02:00PM-04:00PM\"]},{\"session_id\":\"d07459fd-93c7-4826-931a-22abb42d738f\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]}]},{\"center_id\":249075,\"name\":\"Kannuru PHC\",\"address\":\"Yelahanka Main Road\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Bengaluru East\",\"pincode\":562149,\"lat\":13,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"3fd512cc-5804-47c9-b78e-93feb4ef4f05\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]}]},{\"center_id\":249082,\"name\":\"Manduru PHC\",\"address\":\"Opp Manduru Government School\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Bengaluru East\",\"pincode\":560049,\"lat\":13,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"9dee1a70-5322-4b0d-910e-adc03380a7c0\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]}]},{\"center_id\":249046,\"name\":\"Bidarahalli PHCSamudaya Bhavan\",\"address\":\"Samudaya Bhavan Bidarahalli Circle Behind Chennakeshava High School\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Bengaluru East\",\"pincode\":560049,\"lat\":13,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:30:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"b12904cb-b4b1-49bb-976c-c9d82fb19eaf\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:30PM\"]}]},{\"center_id\":249068,\"name\":\"Hebbagodi PHC\",\"address\":\"Nathal Bihari Vajapayee Circle\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Anekal\",\"pincode\":560099,\"lat\":12,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"a751c691-7685-4822-8e85-69d47a907e5d\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]}]},{\"center_id\":249065,\"name\":\"Gunjuru PHC\",\"address\":\"Varthur Main Road\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Bengaluru East\",\"pincode\":560087,\"lat\":12,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"1718781b-f0e3-4323-8cc7-ae2e3798252b\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]}]},{\"center_id\":249040,\"name\":\"Avalahalli CHC\",\"address\":\"Old Madras Road\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Bengaluru East\",\"pincode\":560049,\"lat\":13,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"a8cb1847-dac1-470a-a958-a6c3e5b6ad43\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]}]},{\"center_id\":249097,\"name\":\"Tavarekere PHC\",\"address\":\"Magadi Main Road\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Bengaluru South\",\"pincode\":562130,\"lat\":12,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"6d97ed2c-7d56-4a54-b427-87865ddc3c3d\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]}]},{\"center_id\":249049,\"name\":\"Chandapura PHC\",\"address\":\"Hosur Main Road\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Anekal\",\"pincode\":560099,\"lat\":12,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"2166aa69-f71e-415e-ad0d-f7f56d1b4d45\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]}]},{\"center_id\":519615,\"name\":\"Kadusonnappanahalli PHC\",\"address\":\"Bagaluru Main Road\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Bengaluru East\",\"pincode\":562149,\"lat\":13,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"929ac6b7-943f-4fc6-ab49-873be05f6e99\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]}]},{\"center_id\":249071,\"name\":\"Jigani PHC\",\"address\":\"APC Circle Bannerghatta Road\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Anekal\",\"pincode\":560105,\"lat\":12,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"957d74c4-14b8-420e-97c4-da1f1b0fe287\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]}]},{\"center_id\":249050,\"name\":\"Chandrappa Circle CHC\",\"address\":\"Tavarekere Main Road\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Bengaluru South\",\"pincode\":562130,\"lat\":12,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"9bee5662-a25e-48e2-afbf-85ae6ab3c221\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]}]},{\"center_id\":249087,\"name\":\"Sonnenahalli PHC\",\"address\":\"Sonnenahalli Bytha Post Hesaraghatta Hobli Bengaluru North Bengaluru Urban District\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Bengaluru North\",\"pincode\":560089,\"lat\":13,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"9e0aeafd-74f5-46d3-8fd8-2177fef679bb\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]}]},{\"center_id\":249031,\"name\":\"Attibele PHC\",\"address\":\"Sadara Beedi A Anganwadi Hosuru Main Road Near Attibele Bus Stop\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Anekal\",\"pincode\":562107,\"lat\":12,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"a2178e5b-039d-4f17-a8ea-bcd1ee2fc6f6\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]}]},{\"center_id\":249052,\"name\":\"Chikkajala PHC\",\"address\":\"Airport Main Road\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Bengaluru North\",\"pincode\":562157,\"lat\":13,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"a65a7b3b-3687-47eb-b733-2992140912b6\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]}]},{\"center_id\":656719,\"name\":\"Indalwadi PHC\",\"address\":\"Indalvadi Govt Hospital\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Anekal\",\"pincode\":562106,\"lat\":12,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"3f7f80fd-b610-4b41-8605-f6dbb7e3969c\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]}]},{\"center_id\":249086,\"name\":\"Sondekoppa PHC\",\"address\":\"Tavarekere Main Road\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Bengaluru North\",\"pincode\":562130,\"lat\":13,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"c84eabb6-6414-4906-b76a-20a70c26a6a4\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]}]},{\"center_id\":249084,\"name\":\"Rajanukunte PHC\",\"address\":\"Doddaballapur Main Road\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Bengaluru North\",\"pincode\":560064,\"lat\":13,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"db9755fa-40b4-476a-902f-a011096cd19d\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]}]},{\"center_id\":249074,\"name\":\"Kannalli PHC\",\"address\":\"Near Veerabhadraswamy Temple Kannalli\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Bengaluru North\",\"pincode\":560112,\"lat\":12,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"5d0550d4-5d6c-49d0-8991-837ad1c37521\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]}]},{\"center_id\":249041,\"name\":\"Bagaluru PHC\",\"address\":\"Jala Hobli\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Bengaluru North\",\"pincode\":562149,\"lat\":13,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"b1999735-ac7f-46f3-ba71-ac811958a21c\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]}]},{\"center_id\":249072,\"name\":\"Kumbalagodu Gollahalli PHC\",\"address\":\"Gonipura Main Road\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Bengaluru South\",\"pincode\":560074,\"lat\":12,\"long\":77,\"from\":\"10:00:00\",\"to\":\"13:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"1365978a-d2cc-4057-8e9f-7354de37ab1a\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-12:00PM\",\"12:00PM-01:00PM\"]}]},{\"center_id\":249054,\"name\":\"Gopalpura PHC\",\"address\":\"Railway Gollahalli Post\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Bengaluru North\",\"pincode\":562123,\"lat\":13,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"7cb7d987-7582-4bf6-af3d-0af8b4c3aaf4\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]}]},{\"center_id\":249096,\"name\":\"Sulikere PHC\",\"address\":\"Near Sulikere Government School\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Bengaluru South\",\"pincode\":560060,\"lat\":12,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"c456faa0-ffb8-4c0e-9062-956ca060009d\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]}]},{\"center_id\":249083,\"name\":\"Marasuru PHC\",\"address\":\"Marasuru Main Road\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Anekal\",\"pincode\":562106,\"lat\":12,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"614bbedf-e503-4169-acce-25ee2958de6f\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]}]},{\"center_id\":249042,\"name\":\"Balluru PHC\",\"address\":\"Dasanapura Road\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Anekal\",\"pincode\":562107,\"lat\":12,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"0d838830-7c8d-4a79-86ca-ccfdd240b457\",\"date\":\"10-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]}]},{\"center_id\":249064,\"name\":\"Guddahatti PHC\",\"address\":\"Near Budda Temple\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Anekal\",\"pincode\":562107,\"lat\":12,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"5df55b69-596e-417e-a806-75ccc6d1e5f3\",\"date\":\"11-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"]}]}]}"

// TODO: Tests for all the logical branches.
@OptIn(ExperimentalTime::class)
class PollTest {
    private val cowin: Cowin = mockk()

    @Test
    fun `Test json processing code for sample input`() {
        val groups = mapOf("test" to arrayOf("test"))
        val names = mapOf("test" to "testName")
        every { cowin.getCowinResponse("test") } returns test
        val poller = Poller(cowin, groups, names)

        val result = poller.getStatuses("test")

        assertTrue(result.isNotEmpty())
    }
}