package de.schramm.royalbash.gameengine.rule;

import de.schramm.royalbash.gameengine.exception.DomainObjectDoesNotExistException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class RequiredDomainObjectChecker {

    public void checkIfRequiredDomainObjectsExist(
            Object... domainObjects
    ) throws DomainObjectDoesNotExistException {

        for (Object domainObject : domainObjects) {

            if (domainObject == null) {

                throw new DomainObjectDoesNotExistException(
                        String.format(
                                "One or more Domain Objects do not exist: %s",
                                Arrays.toString(domainObjects)
                        )
                );
            }
        }
    }
}
