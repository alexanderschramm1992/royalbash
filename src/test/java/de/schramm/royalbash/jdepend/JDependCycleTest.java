package de.schramm.royalbash.jdepend;

import jdepend.framework.JDepend;
import jdepend.framework.PackageFilter;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class JDependCycleTest {

    static final String DIRECTORY = ".";
    static final String PACKAGE_TO_ANALYZE = "de.schramm.royalbash.**";
    
    @Test
    public void corePackagesShouldNotContainsSycles() throws IOException {

        JDepend jdepend = new JDepend();
        jdepend.addDirectory(DIRECTORY);

        jdepend.setFilter(new PackageFilter(Collections.singleton(PACKAGE_TO_ANALYZE)));
        jdepend.analyze();

        if(jdepend.containsCycles()){
            ReportHelper.printReportToSystemOut();
        }

        assertThat(jdepend.containsCycles()).isFalse();
    }


}
