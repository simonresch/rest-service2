/*
 * Code Intelligence
 * This is an automatically generated fuzz target. Should not edit.
 * Modification of the fuzz target functionality should be done through the UI.
 * Modifying this code directly nonetheless might break its functionality.
 */
package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.code_intelligence.fuzzing.FuzzWeb;
import com.code_intelligence.static_analysis.EndpointDetection;

import javax.annotation.PostConstruct;

@Component
public class FuzzTarget_system_test {

    @Autowired
    private WebApplicationContext _webCtx;

    private static WebApplicationContext globalWebCtx = null;

    private static FuzzWeb fuzzWeb = null;

    @PostConstruct
    void initFuzzingEngine() {
        globalWebCtx = _webCtx;
    }

    // start the spring boot application
    public static void fuzzerInitialize(String[] fuzzerArgs) {
        // set additional settings here
        System.setProperty("logging.level.root", "error");
        System.getProperties().put("logging.level.org.springframework", "error");
        System.getProperties().put("logging.level.org.springframework.web", "error");
        String[] springBootArgs = {};
        try {
            Application.main(springBootArgs);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to start application.");
        }
        if (globalWebCtx == null) {
            throw new RuntimeException("Failed to run the @PostConstruct function.");
        }
        fuzzWeb = new FuzzWeb(globalWebCtx, fuzzerArgs /*, Class<? extends Filter>[] filterClasses */);
    }

    public static void fuzzerTearDown() {
        if (globalWebCtx != null) {
            System.err.println("INFO: exiting application");
            int exitCode = SpringApplication.exit(globalWebCtx);
            System.err.println("INFO: spring application exited with code " + exitCode);
        }
    }

    public static void main(String[] fuzzerArgs) {
        fuzzerInitialize(fuzzerArgs);
    }

    public static void fuzzerTestOneInput(byte[] input) throws Throwable {
        if (fuzzWeb == null) {
            throw new  IllegalStateException("fuzzRest is not initialized yet!");
        }
        if (fuzzWeb.doRequest(input)) {
            throw new IllegalStateException("Sending the request failed");
        }
    }
}
