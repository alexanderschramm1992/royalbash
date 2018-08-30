package de.schramm.royalbash.codeassert;

import guru.nidi.codeassert.config.AnalyzerConfig;
import guru.nidi.codeassert.dependency.*;
import org.junit.Test;

import static guru.nidi.codeassert.junit.CodeAssertMatchers.hasNoCycles;
import static org.hamcrest.MatcherAssert.assertThat;

public class DependencyTest {

    private final AnalyzerConfig config = AnalyzerConfig.maven().main();

    @Test
    public void noCycles() {
        assertThat(new DependencyAnalyzer(config).analyze(), hasNoCycles());
    }
}
