package com.authenteq.greeting.resources;

import de.agilecoders.wicket.webjars.request.resource.WebjarsCssResourceReference;
import lombok.experimental.UtilityClass;
import org.apache.wicket.request.resource.ResourceReference;

@UtilityClass
public final class CssReferences {

    public static final ResourceReference BOOTSTRAP = new WebjarsCssResourceReference(
            "bootstrap/4.4.1-1/css/bootstrap.css");

}
