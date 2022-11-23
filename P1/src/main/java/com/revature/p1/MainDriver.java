package com.revature.p1;
import com.revature.p1.utils.Router;
import io.javalin.Javalin;

public class MainDriver {
    public static void main(String[] args) {

        Javalin app = Javalin.create(c -> {
            c.contextPath = "/p1";
        }).start(8080);

        Router.router(app);
    }
}
