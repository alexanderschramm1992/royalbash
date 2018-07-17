package de.schramm.royalbash.jdepend;

import jdepend.framework.PackageFilter;
import jdepend.textui.JDepend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static de.schramm.royalbash.jdepend.JDependCycleTest.DIRECTORY;
import static de.schramm.royalbash.jdepend.JDependCycleTest.PACKAGE_TO_ANALYZE;

class ReportHelper {
    static void printReportToSystemOut() throws IOException {

        JDepend jdepend = new JDepend();
        jdepend.addDirectory(DIRECTORY);

        Collection filters = new ArrayList();
        filters.add(PACKAGE_TO_ANALYZE);
        jdepend.setFilter(new PackageFilter(filters));
        jdepend.analyze();

    }
}
