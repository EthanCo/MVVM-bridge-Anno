package com.ethanco.mvvm_bridge_anno;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 * Created by YOLANDA on 2016-01-21.
 */
@SupportedAnnotationTypes({"com.ethanco.mvvm_bridge_anno.MVVM_Bridge"})
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class AnnoProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        //List<ProxyInfo> proxyInfoList = new ArrayList<>();

        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, ">>>>>>>>>>>>>>>>>>>>>>>>>process");
        for (Element ele : roundEnv.getElementsAnnotatedWith(MVVM_Bridge.class)) {
            if (ele.getKind() == ElementKind.CLASS) {
                //CLASS
                TypeElement classElement = (TypeElement) ele;
                PackageElement packageElement = (PackageElement) ele.getEnclosingElement();
                String fqClassName = classElement.getQualifiedName().toString();
                String className = classElement.getSimpleName().toString();
                String packageName = packageElement.getQualifiedName().toString();
                MVVM_Bridge mvvm_bridge = classElement.getAnnotation(MVVM_Bridge.class);
                String value = mvvm_bridge.value();

                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, ">>>>>>>>>>>>>>>>>>>>>>>>>value:" + value);
//                TransitionBean transitionBean = new TransitionBean();
//                transitionBean.setAllTransition(allTrans);
//                ProxyInfo proxyInfo = new ProxyInfo(packageName, className);
//                proxyInfo.setTypeElement(classElement);
//                proxyInfo.setTransitionBean(transitionBean);
//
//                proxyInfoList.add(proxyInfo);
//                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "annatated class : packageName = " + packageName + " , className = " + className + " , fqClassName = " + fqClassName);
            } else if (ele.getKind() == ElementKind.FIELD) {
                //FIELD
            }
        }

//        for (ProxyInfo proxyInfo : proxyInfoList) {
//            try {
//                JavaFileObject jfo = processingEnv.getFiler().createSourceFile(proxyInfo.getProxyClassFullName(), proxyInfo.getTypeElement());
//                Writer writer = jfo.openWriter();
//                writer.write(proxyInfo.generateJavaCode());
//                writer.flush();
//                writer.close();
//            } catch (IOException e) {
//                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, e.getMessage());
//            }
//        }
        return true;
    }
}
