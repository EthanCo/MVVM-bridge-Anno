package com.ethanco.mvvm_bridge_anno;


public enum Finder {
    ACTIVITY {
        @Override
        public String setActivityTrans() {
            StringBuilder sb = new StringBuilder();
            return sb.toString();
        }
    };

    public abstract String setActivityTrans();
}
