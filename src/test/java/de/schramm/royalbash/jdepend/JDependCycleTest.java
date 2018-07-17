package de.schramm.royalbash.jdepend;

import jdepend.framework.JDepend;
import jdepend.framework.PackageFilter;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class JDependCycleTest {

    static final String DIRECTORY = ".";
    static final String PACKAGE_TO_ANALYZE = "de.schramm.royalbash.**";
    
    @Test
    public void corePackagesShouldNotContainsSycles() throws IOException {

        JDepend jdepend = new JDepend();
        jdepend.addDirectory(DIRECTORY);

        Collection filters = new ArrayList();
        filters.add(PACKAGE_TO_ANALYZE);
        jdepend.setFilter(new PackageFilter(filters));

        jdepend.analyze();

        if(jdepend.containsCycles()){
            ReportHelper.printReportToSystemOut();
        }

        //assertThat(jdepend.containsCycles()).isFalse(); ToDo: Activate Test

    }


}
