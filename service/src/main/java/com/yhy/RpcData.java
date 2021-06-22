package com.yhy;

import java.io.Serializable;

public class RpcData implements Serializable {
    String flag;
    String methodName;
    String parameterTypes;
    Integer arguments;
    String interfaceName;

    @Override
    public String toString() {
        return "RpcData{" +
                "flag='" + flag + '\'' +
                ", methodName='" + methodName + '\'' +
                ", parameterTypes='" + parameterTypes + '\'' +
                ", arguments=" + arguments +
                ", interfaceName='" + interfaceName + '\'' +
                '}';
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(String parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Integer getArguments() {
        return arguments;
    }

    public void setArguments(Integer arguments) {
        this.arguments = arguments;
    }
}
