package com.training.victor.development.data

import com.training.victor.development.data.models.TokenViewModel

class TokenManager {
    // Values retrieved from https://developers.shutterstock.com/images/apis/get/images/search
    companion object {
        const val TOKEN_TYPE = "Bearer"
        const val TOKEN_VALUE = "1/eyJjbGllbnRfaWQiOiIyMGFhMWMwYjM2ZGM4NmEyZjliMyIsInJlYWxtIjoiY3VzdG9tZXIiLCJzY29wZSI6InVzZXIudmlldyBjb2xsZWN0aW9ucy52aWV3IiwidXR2IjoiUVBkMyIsInVzZXJuYW1lIjoidnBhbG1hY2FycmFzY285OCIsInVzZXJfaWQiOjIyMTYxNTc4MSwib3JnYW5pemF0aW9uX2lkIjpudWxsLCJvcmdhbml6YXRpb25fdXNlcl9pZCI6bnVsbCwicGFyZW50X29yZ2FuaXphdGlvbl9pZHMiOltdLCJjdXN0b21lcl9pZCI6MjIxNjE1NzgxLCJleHAiOjE1NDUxMzY4NDB9.SrJVex0TTwkaIdVBFHktbxXhD1L53cBIlkb9qJudlO4DPVrhg08BKw-DNrhmLaTBFyyrpBPQgUHSuMSbtQVS7w"
    }

    var sessionToken = TokenViewModel(TOKEN_TYPE, TOKEN_VALUE)
}