package com.ethanco.mvvm_bridge_anno;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Name;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

/**
 * Created by YOLANDA on 2016-01-21.
 */
@SupportedAnnotationTypes({"com.ethanco.mvvm_bridge_anno.MVVM"})
@SupportedSourceVersion(SourceVersion.RELEASE_7)
//@SupportedAnnotationTypes("*")
//@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class AnnoProcessor extends AbstractProcessor {

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        List<ProxyInfo> proxyInfoList = new ArrayList<>();

        System.out.printf("Z-process");
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, ">>>>>>>>>>>>>>>>>>>>>>>>>process");
        /*for (Element ele : roundEnv.getElementsAnnotatedWith(MVVM.class)) {
            if (ele.getKind() == ElementKind.CLASS) {
                //CLASS
                TypeElement classElement = (TypeElement) ele;
                PackageElement packageElement = (PackageElement) ele.getEnclosingElement();
                String fqClassName = classElement.getQualifiedName().toString();
                String className = classElement.getSimpleName().toString();
                String packageName = packageElement.getQualifiedName().toString();
                MVVM mvvm = classElement.getAnnotation(MVVM.class);

                Class view = mvvm.View();
                Class viewModel = mvvm.ViewModel();

                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, ">>>>>>>>>>>>>>>>>>>>>>>>>view:" + view + "viewModel:" + viewModel);
                ProxyInfo proxyInfo = new ProxyInfo(packageName, className);
                proxyInfo.setTypeElement(classElement);
                proxyInfoList.add(proxyInfo);
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "annatated class : packageName = " + packageName + " , className = " + className + " , fqClassName = " + fqClassName);
            } else if (ele.getKind() == ElementKind.FIELD) {
                //FIELD
            }
        }

        for (ProxyInfo proxyInfo : proxyInfoList) {
            try {
                JavaFileObject jfo = processingEnv.getFiler().createSourceFile(proxyInfo.getProxyClassFullName(), proxyInfo.getTypeElement());
                Writer writer = jfo.openWriter();
                writer.write(proxyInfo.generateJavaCode());
                writer.flush();
                writer.close();
            } catch (IOException e) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, e.getMessage());
            }
        }*/


       /* for (TypeElement te : annotations) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, ">>>>>>>>>>>>>>>>>>>>>>>>>test1");
            Element actionElement = processingEnv.getElementUtils().getTypeElement(MVVM.class.getName());
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, ">>>>>>>>>>>>>>>>>>>>>>>>>test2");
            TypeMirror actionType = actionElement.asType();
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, ">>>>>>>>>>>>>>>>>>>>>>>>>test3");
            for (ExecutableElement ee : ElementFilter.methodsIn(te.getEnclosedElements())) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, ">>>>>>>>>>>>>>>>>>>>>>>>>test4");
                ExecutableElement oe = ee;
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, ">>>>>>>>>>>>>>>>>>>>>>>>>test5");
                AnnotationValue action = null;
                while (action == null && oe != null) {
                    processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, ">>>>>>>>>>>>>>>>>>>>>>>>>test6 size:" + oe.getAnnotationMirrors().size());
                    for (AnnotationMirror am : oe.getAnnotationMirrors()) {
                        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, ">>>>>>>>>>>>>>>>>>>>>>>>>test7");
                        if (am.getAnnotationType().equals(actionType)) {
                            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, ">>>>>>>>>>>>>>>>>>>>>>>>>test8");
                            for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : am.getElementValues().entrySet()) {
                                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, ">>>>>>>>>>>>>>>>>>>>>>>>>test9");
                                if ("value".equals(entry.getKey().getSimpleName().toString())) {
                                    processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, ">>>>>>>>>>>>>>>>>>>>>>>>>test10");
                                    action = entry.getValue();
                                    break;
                                }
                            }
                        }
                    }
                    // Look for the overridden method
                    oe = getExecutableElement(findEnclosingTypeElement(oe), ee.getSimpleName());
                }
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, ">>>>>>>>>>>>>>>>>>>>>>>>>process: " + ee.getSimpleName());
            }
        }*/


        if (!roundEnv.processingOver()) {
            for (Element e : roundEnv.getRootElements()) {
                /*if (e.getKind() == ElementKind.CLASS) {
                    //CLASS

                } else if (e.getKind() == ElementKind.FIELD) {
                    //FIELD
                }*/

                TypeElement te = findEnclosingTypeElement(e);
                final String actionName = MVVM.class.getName();
                for (ExecutableElement ee : ElementFilter.methodsIn(te.getEnclosedElements())) {
                    ExecutableElement oe = ee;
                    AnnotationValue action = null;
                    while (action == null && oe != null) {
                        for (AnnotationMirror am : oe.getAnnotationMirrors()) {
                            if (actionName.equals(am.getAnnotationType().toString())) {
                                for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : am.getElementValues().entrySet()) {
                                    if ("View".equals(entry.getKey().getSimpleName().toString())) {
                                        action = entry.getValue();
                                        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "view:" + action);
                                        PackageElement packageElement = (PackageElement) te.getEnclosingElement();
                                        String fqClassName = te.getQualifiedName().toString();
                                        String className = te.getSimpleName().toString();
                                        String packageName = packageElement.getQualifiedName().toString();
                                        ProxyInfo proxyInfo = new ProxyInfo(packageName, className);
                                        proxyInfo.setTypeElement(te);
                                        proxyInfoList.add(proxyInfo);
                                        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "annatated class : packageName = " + packageName + " , className = " + className + " , fqClassName = " + fqClassName);
                                        //break;
                                    } else if ("ViewModel".equals(entry.getKey().getSimpleName().toString())) {
                                        action = entry.getValue();
                                        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "ViewModel:" + action);
                                    } else if ("value".equals(entry.getKey().getSimpleName().toString())) {
                                        action = entry.getValue();
                                        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "value:" + action);
                                    }
                                }
                            }
                        }
                        oe = getExecutableElement(findEnclosingTypeElement(oe), ee.getSimpleName());
                    }
                }
            }
        }

        for (ProxyInfo proxyInfo : proxyInfoList) {
            try {
                JavaFileObject jfo = processingEnv.getFiler().createSourceFile(proxyInfo.getProxyClassFullName(), proxyInfo.getTypeElement());
                Writer writer = jfo.openWriter();
                writer.write(proxyInfo.generateJavaCode());
                writer.flush();
                writer.close();
            } catch (IOException e) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, e.getMessage());
            }
        }

        return true;
    }

    public ExecutableElement getExecutableElement(final TypeElement typeElement, final Name name) {
        TypeElement te = typeElement;
        do {
            te = (TypeElement) processingEnv.getTypeUtils().asElement(
                    te.getSuperclass());
            if (te != null) {
                for (ExecutableElement ee : ElementFilter.methodsIn(
                        te.getEnclosedElements())) {
                    if (name.equals(ee.getSimpleName()) && ee.getParameters().isEmpty()) {
                        return ee;
                    }
                }
            }
        } while (te != null);
        return null;
    }

    public static TypeElement findEnclosingTypeElement(Element e) {
        while (e != null && !(e instanceof TypeElement)) {
            e = e.getEnclosingElement();
        }
        return TypeElement.class.cast(e);
    }
}
