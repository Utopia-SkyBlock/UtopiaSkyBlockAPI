package de.linushuck.utopia.skyblock.libs.api.eventsystem;

import java.lang.reflect.Method;

record HandlerMethod(Method method, int priority, HandlerGroup group, boolean callAlways, boolean cancelOtherGroups){}