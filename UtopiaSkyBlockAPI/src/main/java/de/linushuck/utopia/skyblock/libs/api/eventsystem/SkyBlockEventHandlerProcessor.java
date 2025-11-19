package de.linushuck.utopia.skyblock.libs.api.eventsystem;

import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import java.util.List;
import java.util.Set;

@SupportedAnnotationTypes("de.linushuck.utopianetwork.eventsystem.BetterEventHandler")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class SkyBlockEventHandlerProcessor extends AbstractProcessor
{
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv)
    {
        for(Element element : roundEnv.getElementsAnnotatedWith(SkyBlockEventHandler.class))
        {
            if(element instanceof ExecutableElement method)
            {
                TypeMirror returnType = method.getReturnType();

                // Check if the return type is boolean
                if(!returnType.toString().equals("boolean"))
                {
                    processingEnv.getMessager()
                            .printMessage(Diagnostic.Kind.ERROR, "Method " + method.getSimpleName() + " must return a boolean type.", method);
                }

                // Check that there is exactly one parameter
                List<? extends VariableElement> parameters = method.getParameters();
                if(parameters.size() != 1)
                {
                    processingEnv.getMessager()
                            .printMessage(Diagnostic.Kind.ERROR, "Method " + method.getSimpleName() + " must have exactly one parameter.", method);
                }

                // Check that the parameter type extends BetterEvent
                VariableElement parameter = parameters.get(0);
                TypeMirror parameterType = parameter.asType();
                TypeElement betterEventType = processingEnv.getElementUtils()
                        .getTypeElement("de.linushuck.utopianetwork.eventsystem.BetterEvent"); // Replace with actual package

                if(!processingEnv.getTypeUtils().isAssignable(parameterType, betterEventType.asType()))
                {
                    processingEnv.getMessager()
                            .printMessage(Diagnostic.Kind.ERROR, "Method " + method.getSimpleName() + " must extend BetterEvent.", method);
                }
            }
        }
        return true;
    }
}