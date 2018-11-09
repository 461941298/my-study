package com.yjh.study.ch5并发容器.bitwise;

public class Permission {

    private static final int ALLOW_INSERT = 1 << 0; //1
    private static final int ALLOW_DELETE = 1 << 1; //2
    private static final int ALLOW_UPDATE = 1 << 2; //4
    private static final int ALLOW_SELECT = 1 << 3; //8
    //    表示拥有的权限
    private int flag = 0;

    public int getFlag() {
        return flag;
    }

    //    添加权限
    public void enable(int permission) {
        flag = flag | permission;
    }

    public void enable(int... permission) {
        for (int p : permission) {
            flag = flag | p;
        }
    }

    //    禁用权限
    public void disenable(int permission) {
        flag = flag & (~permission);
    }

    //    查看是否有某一权限
    public boolean has(int permission) {
        return permission == (permission & flag);
    }

    //    查看是否没有某一权限
    public boolean noHas(int permission) {
        return 0 == (permission & flag);
    }

    public static void main(String[] args) {
        Permission p = new Permission();
        p.enable(ALLOW_INSERT);
        System.out.println("有了 添加 " + p.getFlag());
        p.enable(ALLOW_DELETE, ALLOW_UPDATE, ALLOW_SELECT);
        System.out.println("有了 删改查 " + p.getFlag());
        p.disenable(ALLOW_INSERT);
        System.out.println("没了 添加 " + p.getFlag());
        System.out.println("是否有 删除 " + p.has(ALLOW_DELETE));
        System.out.println("是否有 添加 " + p.has(ALLOW_INSERT));
        System.out.println("是否没有 查看 " + p.noHas(ALLOW_SELECT));

    }

}
