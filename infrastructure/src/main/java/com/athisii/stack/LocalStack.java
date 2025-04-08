package com.athisii.stack;

import software.amazon.awscdk.*;

/**
 * @author athisii ekhe
 * @version 1.0
 * @since 4/8/25
 */

public class LocalStack extends Stack {

    public LocalStack(final App scope, String id, StackProps props) {
        super(scope, id, props);
    }

    public static void main(String[] args) {
        App app = new App(AppProps.builder().outdir("./cdk.out").build());
        StackProps stackProps = StackProps.builder().synthesizer(new BootstraplessSynthesizer()).build();
        new LocalStack(app, "localstack", stackProps);
        app.synth();
        System.out.println("App synthesizing in progress...");
    }
}
