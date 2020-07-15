package com.guomz.demo.singleton;

/**
 * 基于枚举的单例模式
 */
public class EnumVer {

    private EnumVer(){}

    public static EnumVer getInstance(){
        return SingleEnum.HOLDER.enumVer;
    }

    private enum SingleEnum{
        HOLDER;
        private EnumVer enumVer;
        SingleEnum(){
            this.enumVer = new EnumVer();
        }
    }
}
