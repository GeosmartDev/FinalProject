package com.udacity.gradle.builditbigger.backend;


import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.milsat.mylib.MyClass;

/** An endpoint class we are exposing */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {
    MyClass myClass = new MyClass();
    MyBean response = new MyBean();
    @ApiMethod(name = "getJoke")

    public MyBean myJoke() {
        response.setData(myClass.myJokes());
        return response;
    }






}
