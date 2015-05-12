/*
 * Copyright (C) 2013 salesforce.com, inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.auraframework.builder.design;

import org.auraframework.builder.RootDefinitionBuilder;
import org.auraframework.def.*;
import org.auraframework.def.design.*;

public interface DesignDefBuilder extends RootDefinitionBuilder<DesignDef> {
    public DesignDefBuilder addAttributeDesign(DefDescriptor<DesignAttributeDef> desc,
            DesignAttributeDef attributeDesign);

    public DesignDefBuilder addLayoutDesign(String desc, DesignLayoutDef layoutDesign);

    public DesignDefBuilder setLabel(String label);

    public DesignDefBuilder setDesignTemplateDef(DesignTemplateDef template);

    public DesignDefBuilder addOption(DesignOptionDef option);
}