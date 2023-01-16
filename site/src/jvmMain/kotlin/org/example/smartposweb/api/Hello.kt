package org.example.smartposweb.api

import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.http.HttpMethod
import com.varabyte.kobweb.api.http.setBodyText

@Api()
fun hello(ctx: ApiContext) {

 when(    ctx.req.method){
     HttpMethod.DELETE -> TODO()
     HttpMethod.GET ->  ctx.res.setBodyText("hello world")
     HttpMethod.HEAD -> TODO()
     HttpMethod.OPTIONS -> TODO()
     HttpMethod.PATCH -> TODO()
     HttpMethod.POST -> TODO()
     HttpMethod.PUT -> TODO()
 }

}