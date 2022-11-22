package com.dlt.express.util.permission;

/**
 * 作者: Liyahui
 * 时间: 2021/4/15 13:29
 * 描述:权限授予监听
 */
public interface GrantListener {
    void onAgree();// 某权限同意

    void onDeny();// 某权限拒绝但没选择不再询问项

    void onDenyNotAskAgain();// 某权限拒绝并选择了不再询问项
}
