package com.ethanco.mvvm_bridge_anno;


import javax.lang.model.element.TypeElement;

public class ProxyInfo {
    private String packageName;
    private String targetClassName;
    private String proxyClassName;
    private TypeElement typeElement;

    public static final String PROXY_SUFFIX = "TRANSPROXY";

    public ProxyInfo(String packageName, String className) {
        this.packageName = packageName;
        this.targetClassName = className;
        this.proxyClassName = className + "$$" + PROXY_SUFFIX;
    }

    public String getProxyClassFullName() {
        return packageName + "." + proxyClassName;
    }

    public String generateJavaCode() {
        StringBuilder builder = new StringBuilder();
        builder.append("package ").append(packageName).append(";\n\n");

        builder.append("import android.app.Activity;\n");
        builder.append("import android.view.View;\n");
        builder.append("import com.ethanco.mvvm_bridge_anno.Finder;\n");
        builder.append("import com.ethanco.mvvm_bridge_anno.AbstractInjector;\n");
        builder.append("import android.os.Build;\n");
        builder.append("import android.os.Bundle;\n");
        //TODO if - else
        builder.append("import android.transition.*;\n");
        //builder.append("import android.transition.Explode;");
        //builder.append("import android.transition.Fade;");
        //builder.append("import android.transition.Slide;");

        builder.append('\n');

        builder.append("public class ").append(proxyClassName);
        builder.append("<T extends ").append(getTargetClassName()).append(">");
        builder.append(" implements AbstractInjector<T>");
        builder.append(" {\n");

        generateInjectMethod(builder);
        builder.append('\n');

        builder.append("}\n");
        return builder.toString();

    }

    private String getTargetClassName() {
        return targetClassName.replace("$", ".");
    }

    private void generateInjectMethod(StringBuilder builder) {
        builder.append("  @Override ").append("public void inject(final Finder finder, final T target, Object source) {\n");
        builder.append("if(finder.equals(Finder.ACTIVITY)){").append("");
        builder = generateActivityFinderCode(builder);
        builder.append("}");
        builder.append("  }\n");
    }

    private StringBuilder generateActivityFinderCode(StringBuilder builder) {

        //TODO

        return builder;
    }

    public TypeElement getTypeElement() {
        return typeElement;
    }

    public void setTypeElement(TypeElement typeElement) {
        this.typeElement = typeElement;
    }
}
